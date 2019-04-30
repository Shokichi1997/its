package com.itsdl.androidtutorials.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.itsdl.androidtutorials.R;
import com.itsdl.androidtutorials.networks.ChangePasswordRequest;
import com.itsdl.androidtutorials.networks.SeverRequest;
import com.itsdl.androidtutorials.utils.CustomDialog;
import com.itsdl.androidtutorials.utils.DrawerLocker;
import com.itsdl.androidtutorials.utils.Extension;
import com.itsdl.androidtutorials.utils.ProfileUser;
import com.itsdl.androidtutorials.utils.Result;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChangePasswordFragment extends Fragment {


    View root;
    private Toolbar toolbar;
    EditText edtPassword_old;
    EditText edtPassword_new;
    EditText edtPassword_repeat;
    Button btnUpdate_pass;
    Button btnCancel;
    private ProgressDialog mUpdatePassProgress;
    public ChangePasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root= inflater.inflate(R.layout.fragment_change_password, container, false);
        getViews();
        addEvents();
        return root;
    }
    public void getViews(){
        //add tool bar
        if(getActivity()!=null){
            ((DrawerLocker ) getActivity()).setDrawerEnabled(true);
        }
        toolbar = root.findViewById(R.id.change_password_toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Change password");
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getFragmentManager().getBackStackEntryCount() > 0) {
                    getFragmentManager().popBackStack();
                }
            }
        });

        edtPassword_old    = root.findViewById(R.id.edtPassword_old);
        edtPassword_new    = root.findViewById(R.id.edtPassword_new);
        edtPassword_repeat = root.findViewById(R.id.edtPassword_repeat);
        btnUpdate_pass     = root.findViewById(R.id.btnUpdatePassword);
        btnCancel          = root.findViewById(R.id.btnCancel);

        mUpdatePassProgress= new ProgressDialog(getContext());
    }
    public void addEvents(){
        btnUpdate_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePassword();
            }
        });
     }

    public void changePassword(){
        String passCurent=ProfileUser.getInstance().getPassword();
          if(checkData()){
              String pass_old="";
              try {
                  pass_old = SHA256(edtPassword_old.getText().toString().trim());
              } catch (NoSuchAlgorithmException e) {
                  e.printStackTrace();
              }
              if(pass_old.equals(passCurent)){
                  if(edtPassword_new.getText().toString().trim().equals(edtPassword_repeat.getText().toString().trim())){

                          String pass_new=edtPassword_new.getText().toString().trim();
                          String sha256OfPassword = "";
                          try {
                             sha256OfPassword = SHA256(pass_new);
                           } catch (NoSuchAlgorithmException e) {
                              e.printStackTrace();
                           }
                          requestChangePassWord(sha256OfPassword,String.valueOf(ProfileUser.getInstance().getUser_id()));
                  }else{
                      showMessage("Reqeat password incorrect. ");
                  }
              }else{
                  showMessage("Password old incorrect");
              }
              return;
          }else{
              showMessage("Password old, password new, password repeat is not empty. ");
              return;
          }
    }
    public void requestChangePassWord(final String password_new,String user_id){

        mUpdatePassProgress.setTitle("Update password");
        mUpdatePassProgress.setMessage("Please wait while we check your request.");
        mUpdatePassProgress.setCanceledOnTouchOutside(false);

        Map<String,String> parameter=new HashMap<>();
        parameter.put("password_new",password_new);
        parameter.put("user_id",user_id);
        mUpdatePassProgress.show();
        ChangePasswordRequest request=new ChangePasswordRequest(new SeverRequest.SeverRequestListener() {
            @Override
            public void completed(Object obj) {
                if(obj!=null){
                    Result res=(Result) obj;
                    if(res.getError()==0){
                        ProfileUser.getInstance().setPassword(password_new);
                    }else{
                        Toast.makeText(getContext(),"Error",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getContext(),"Error 0",Toast.LENGTH_LONG).show();
                }
                mUpdatePassProgress.dismiss();
            }
        });
        request.execute(parameter);
    }
    private void showMessage(String message) {
        final CustomDialog dialog = new CustomDialog(getContext());
        dialog.setLblMessageHint(message);
        dialog.setLblTitleHint("Notification");
        dialog.setImgIconHint(R.drawable.tick_green);
        dialog.setBtnCloseHint(R.drawable.background_card);
        dialog.setEventsClose(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    public static String SHA256(String pass) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(pass.getBytes());
        byte[] digest = messageDigest.digest();
        return Extension.toHexString(digest);
    }
    public boolean checkData(){
        return !edtPassword_old.getText().toString().isEmpty() &&
               !edtPassword_new.getText().toString().isEmpty() &&
               !edtPassword_repeat.getText().toString().isEmpty();
    }
}
