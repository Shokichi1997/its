package com.itsdl.androidtutorials.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.itsdl.androidtutorials.R;
import com.itsdl.androidtutorials.networks.SeverRequest;
import com.itsdl.androidtutorials.networks.SignInRequest;
import com.itsdl.androidtutorials.utils.CustomDialog;
import com.itsdl.androidtutorials.utils.DrawerLocker;
import com.itsdl.androidtutorials.utils.Extension;
import com.itsdl.androidtutorials.utils.ProfileUser;
import com.itsdl.androidtutorials.utils.Result;
import com.itsdl.androidtutorials.utils.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
   View view;
   Button btnLogin;
   EditText edtStudentCode,edtPassword;
   CheckBox ckbRemember;
   Toolbar toolbar;
   String student_code;
   String password;
    private ProgressDialog mLoginProgress;
    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_login, container, false);
        getViews();
        addEvents();
        return view;
    }
    public void getViews(){
        if(getActivity()!=null){
            ((DrawerLocker ) getActivity()).setDrawerEnabled(false);
        }

        edtStudentCode=view.findViewById(R.id.edtStudentCode);
        edtPassword=view.findViewById(R.id.edtPassword);
        btnLogin=view.findViewById(R.id.btnLogin);
        ckbRemember=view.findViewById(R.id.ckbRemember);
        mLoginProgress = new ProgressDialog(getContext());
    }

    private void addEvents() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /***Kiểm tra email và password trống
                 trông => call showMessage(message) và return*/
                logIn();
                /**request => thành công call goToFunctionsMain()*/
               // goToFunctionsMain();

            }
        });
    }

    public void goToFunctionsMain(){
        Fragment fragment = new FunctionsFragment();
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        Fragment login=getActivity().getSupportFragmentManager().findFragmentById(R.id.frContainer);
        if(login!=null){
            transaction.remove(login);
        }
        transaction.add(R.id.frContainer2,fragment,"FUNCTIONS")
                .addToBackStack("FUNCTIONS")
                .commit();
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
    public void logIn(){
        if(!edtStudentCode.getText().toString().isEmpty()&&!edtPassword.getText().toString().isEmpty()){
            mLoginProgress.setTitle("Logging In");
            mLoginProgress.setMessage("Please wait while we check your account.");
            mLoginProgress.setCanceledOnTouchOutside(false);
            student_code = edtStudentCode.getText().toString().trim();
            password = edtPassword.getText().toString().trim();
            String sha256OfPassword = "";

            try {
                sha256OfPassword = SHA256(password);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            Map<String, String> parameter = new HashMap<>();
            parameter.put("student_code", student_code);
            parameter.put("password", sha256OfPassword);
            mLoginProgress.show();

            SignInRequest request =new SignInRequest(new SeverRequest.SeverRequestListener() {
                @Override
                public void completed(Object obj) {
                    if(obj!=null){
                        Result res=(Result) obj;
                        if(res.getError()==0){
                            goToFunctionsMain();
                        }


                    }else{

                    }
                    mLoginProgress.dismiss();
                }
            });
            request.execute(parameter);
        }else{
            showMessage("Student code and Password is not empty ");
            return;
        }

    }
    public static String SHA256(String pass) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(pass.getBytes());
        byte[] digest = messageDigest.digest();
        return Extension.toHexString(digest);
    }


}
