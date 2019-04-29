package com.itsdl.androidtutorials.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.itsdl.androidtutorials.R;
import com.itsdl.androidtutorials.networks.SeverRequest;
import com.itsdl.androidtutorials.networks.UpdateStudentRequest;
import com.itsdl.androidtutorials.utils.Result;
import com.itsdl.androidtutorials.utils.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserProfileFragment extends Fragment {

    View root;
    android.support.v7.widget.Toolbar toolbar;
    EditText edtStudentName;
    EditText edtEmail;
    EditText edtStudentCode;
    Button btnSave;
    User user;
    EditText edtDateCreate;
    String date=null;
    Button btnClear;
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
        edtDateCreate  = root.findViewById(R.id.pDateCreate);
        btnSave        = root.findViewById(R.id.btsave);
        btnClear       = root.findViewById(R.id.btnClear);
    }

    public void getProfileUser(){
        //get Bundle
        String student_name = "Student Data";
        user=new User();
        Bundle args = getArguments();
        if(args!=null && args.containsKey("UserID")){
            user.setUser_id(args.getInt("UserID"));

        }
        if(args!=null && args.containsKey("FullName")){
            user.setFull_name(args.getString("FullName"));
            student_name = args.getString("FullName");
        }
        if(args!=null && args.containsKey("Email")){
            user.setEmail(args.getString("Email"));
        }
        if(args!=null && args.containsKey("StudentCode")){
            user.setStudent_code(args.getString("StudentCode"));
        }
        if(args!=null && args.containsKey("DateCreate")){
           date=args.getString("DateCreate");
           // user.setDate_create(Date.parse());
        }
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(student_name);
    }
    public void loadProUser(){
        edtStudentName.setText(user.getFull_name());
        edtEmail.setText(user.getEmail());
        edtStudentCode.setText(user.getStudent_code());
        edtDateCreate.setText(date);

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
        return  edtStudentName.getText().toString()!=null&&
                edtStudentCode.getText().toString()!=null&&
                edtEmail.getText().toString()!=null;
    }
}
