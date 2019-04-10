package com.itsdl.androidtutorials.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.itsdl.androidtutorials.R;
import com.itsdl.androidtutorials.adapters.ChapterLessonAdapter;
import com.itsdl.androidtutorials.adapters.UserAdapter;
import com.itsdl.androidtutorials.utils.User;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class StudentManagementFragment extends Fragment {

   View root;
   ListView listStudent;
   Toolbar toolbar;
   List<User> arr_users;
    public StudentManagementFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root= inflater.inflate(R.layout.fragment_student, container, false);
        getViews();
        //load danh sach hoc vien
        arr_users=new ArrayList<>();
        arr_users.add(new User(111,"Danh",null,-1,"buidanh97@gmail.com"));
        arr_users.add(new User(111,"Lam",null,-1,"buidanh97@gmail.com"));
        arr_users.add(new User(111,"Na",null,-1,"buidanh97@gmail.com"));
        arr_users.add(new User(111,"Thuong",null,-1,"buidanh97@gmail.com"));

        loadDanhSachHocVien(arr_users);
        return root;
    }

    public void getViews(){
       listStudent= root.findViewById(R.id.list_Student);
       toolbar= root.findViewById(R.id.student_toolbar);
        //set tool bar app
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Data Student");
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getFragmentManager().getBackStackEntryCount()>0){
                    getFragmentManager().popBackStack();
                }//getFragmentManager().getBackStackEntryCount()>0
            }
        });
    }

    public void loadDanhSachHocVien(List<User> arr_users){
        UserAdapter adapter = new UserAdapter(root.getContext(),arr_users);
        this.listStudent.setAdapter(adapter);

    }

}
