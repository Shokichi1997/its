package com.itsdl.androidtutorials.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.itsdl.androidtutorials.R;
import com.itsdl.androidtutorials.networks.SeverRequest;
import com.itsdl.androidtutorials.networks.UpdateStudentRequest;
import com.itsdl.androidtutorials.utils.DrawerLocker;
import com.itsdl.androidtutorials.utils.ProfileUser;
import com.itsdl.androidtutorials.utils.Result;
import com.itsdl.androidtutorials.utils.User;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserProfileFragmentForStudent extends Fragment {
    View root;
    android.support.v7.widget.Toolbar toolbar;
    EditText edtStudentName, edtEmail, edtStudentCode,pphonenb, edtDateCreate;
    Button btnSave;
    User user;
    String date=null;
    Button btnClear;
    public UserProfileFragmentForStudent() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root= inflater.inflate(R.layout.fragment_user_profile, container, false);
        //create ui
        getViews();
        loadProUser();
        //event button
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfileUser();
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtEmail.setText("");
                edtStudentName.setText("");
                edtStudentCode.setText("");
            }
        });
        return root;
    }
    public void getViews(){
        if(getActivity()!=null) {
            (( DrawerLocker ) getActivity()).setDrawerEnabled(true);
        }
        //add tool bar
        toolbar=root.findViewById(R.id.app_bar_profile_user);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getFragmentManager().getBackStackEntryCount() > 0) {
                    getFragmentManager().popBackStack();
                }
            }
        });
        edtEmail       = root.findViewById(R.id.pemail);
        edtStudentName = root.findViewById(R.id.pfname);
        edtStudentCode = root.findViewById(R.id.pStudenCode);
        edtStudentCode.setEnabled(false);
        edtDateCreate  = root.findViewById(R.id.pDateCreate);
        btnSave        = root.findViewById(R.id.btsave);
        btnClear       = root.findViewById(R.id.btnClear);
        pphonenb = root.findViewById(R.id.pphonenb);
        pphonenb.setEnabled(false);
        edtDateCreate.setEnabled(false);
    }

    public void loadProUser(){
        ProfileUser profileUser = ProfileUser.getInstance();
        edtStudentName.setText(profileUser.getFull_name());
        edtEmail.setText(profileUser.getEmail());
        edtStudentCode.setText(profileUser.getStudent_code());
        edtDateCreate.setText(String.valueOf(profileUser.getDate_create()));

    }
    public void updateProfileUser(){
        if(checkDataInput()==true){
            Map<String,String> parameter=new HashMap<>();
            parameter.put("user_id",String.valueOf(user.getUser_id()));
            parameter.put("full_name",edtStudentName.getText().toString());
            parameter.put("email",edtEmail.getText().toString());
            parameter.put("student_code",edtStudentCode.getText().toString());

            UpdateStudentRequest request= new UpdateStudentRequest(new SeverRequest.SeverRequestListener() {
                @Override
                public void completed(Object obj) {
                    Result res=(Result) obj;
                    if(obj!=null){
                        if(res.getError()==0){
                            Toast.makeText(getContext(),"Update success",Toast.LENGTH_LONG).show();
                            StudentManagementFragment fConv=new StudentManagementFragment();
                            replaceFragment(fConv);
                        }
                    }
                    else{

                    }
                }
            });
            request.execute(parameter);
        }else {
            Toast.makeText(getContext(),"Enter data error",Toast.LENGTH_LONG).show();
        }
    }

    public boolean checkDataInput(){
        return  !edtStudentName.getText().toString().isEmpty()&&
                !edtStudentCode.getText().toString().isEmpty()&&
                !edtEmail.getText().toString().isEmpty();
    }
    private void replaceFragment(Fragment fConv) {

        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frContainer, fConv,"Details");
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
