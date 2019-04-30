package com.itsdl.androidtutorials.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.itsdl.androidtutorials.R;
import com.itsdl.androidtutorials.utils.CustomDialog;
import com.itsdl.androidtutorials.utils.Extension;
import com.itsdl.androidtutorials.utils.ProfileUser;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
    public ChangePasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root= inflater.inflate(R.layout.fragment_change_password, container, false);
        getViews();
        return root;
    }
    public void getViews(){
        //add tool bar
        toolbar = root.findViewById(R.id.lesson_toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Lesson");
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
          if(checkData()){
              if(edtPassword_old.getText().toString().trim().toLowerCase()==
                      ProfileUser.getInstance().getPassword().toLowerCase())
              return;
          }else{
              showMessage("Password old, password new, password repeat is not empty!");
              return;
          }
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
