package com.itsdl.androidtutorials.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toolbar;

import com.itsdl.androidtutorials.R;
import com.itsdl.androidtutorials.utils.User;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserProfileFragment extends Fragment {

    View root;
    android.support.v7.widget.Toolbar toolbar;
    EditText edtStudentName;
    EditText edtEmail;
    EditText edtnumberPhone;
    Button btnSave;
    User user;
    public UserProfileFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root= inflater.inflate(R.layout.fragment_user_profile, container, false);
        //create ui
        getViews();
        getProfileUser();
        loadProUser();
        //
        return root;
    }
    public void getViews(){
        //add tool bar
        toolbar=root.findViewById(R.id.app_bar_profile_user);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        edtEmail       = root.findViewById(R.id.pemail);
        edtStudentName = root.findViewById(R.id.pfname);
        edtnumberPhone = root.findViewById(R.id.pphonenb);
        btnSave        = root.findViewById(R.id.btsave);
    }

    public void getProfileUser(){
        //get Bundle
        String student_name = "Student Data";
        user=new User();
        Bundle args = getArguments();

        if(args!=null && args.containsKey("FullName")){
            user.setFull_name(args.getString("FullName"));
            student_name = args.getString("FullName");
        }
        if(args!=null && args.containsKey("Email")){
            user.setEmail(args.getString("Email"));
        }
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(student_name);
    }
    public void loadProUser(){
        edtStudentName.setText(user.getFull_name());
        edtEmail.setText(user.getEmail());
    }
}
