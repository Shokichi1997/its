package com.itsdl.androidtutorials.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.itsdl.androidtutorials.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
   View view;
   Button btnLogin;
   Button btnSignUp;
   EditText edtEmail,edtPssword;
   CheckBox ckbRemember;
   Toolbar toolbar;
    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_login, container, false);
        getViews();
        return view;
    }
    public void getViews(){
        toolbar = view.findViewById(R.id.login_app_bar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("LoginApp");
        edtEmail=view.findViewById(R.id.edtEmail);
        edtPssword=view.findViewById(R.id.edtPassword);
        btnSignUp=view.findViewById(R.id.btnSignUp);
        btnLogin=view.findViewById(R.id.btnLogin);
        ckbRemember=view.findViewById(R.id.ckbRemember);
    }

}
