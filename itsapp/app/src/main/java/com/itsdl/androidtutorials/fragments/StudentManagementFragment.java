package com.itsdl.androidtutorials.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Toast;

import com.itsdl.androidtutorials.R;
import com.itsdl.androidtutorials.activities.MainActivity;
import com.itsdl.androidtutorials.adapters.ChapterLessonAdapter;
import com.itsdl.androidtutorials.adapters.UserAdapter;
import com.itsdl.androidtutorials.networks.GetListStudentRequest;
import com.itsdl.androidtutorials.networks.SeverRequest;
import com.itsdl.androidtutorials.utils.Result;
import com.itsdl.androidtutorials.utils.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class StudentManagementFragment extends Fragment
 {

   View root;
   ListView listStudent;
   Toolbar toolbar;
   ArrayList<User> list_student;
   UserAdapter adapter;
   android.support.v7.widget.SearchView searchView;
    public StudentManagementFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            adapter.notifyDataSetChanged();
        }catch (Exception e){

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root= inflater.inflate(R.layout.fragment_student, container, false);

        getViews();
        getlistStudent();
        setupSearchView();

        return root;
    }

    public void getViews(){
       listStudent= root.findViewById(R.id.list_Student);
       toolbar= root.findViewById(R.id.student_toolbar1);
        //set tool bar app
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Data student");
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        searchView=root.findViewById(R.id.search);

    }

    public void loadDanhSachHocVien(final ArrayList<User> data){
        //load danhs sach hoc vien vafo list view

        adapter = new UserAdapter(root.getContext(),data);
        listStudent.setAdapter(adapter);
        // event listviewStudent item click

        listStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), view.getMeasuredHeight()+"",Toast.LENGTH_SHORT).show();
                User us = data.get(position);
                Bundle bundle = new Bundle();
                bundle.putInt("UserID", us.getUser_id());
                UserProfileFragment fConv = new UserProfileFragment();
                fConv.setArguments(bundle);
                replaceFragment(fConv);


            }
        });

    }

    private void replaceFragment(Fragment fConv) {
        if(getChildFragmentManager()!=null){
            FragmentManager manager = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.frContainer, fConv,"Details");
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }


    //get lít student
    private void getlistStudent(){
        Map<String,String> parameter=new HashMap<>();
        GetListStudentRequest request=new GetListStudentRequest(new SeverRequest.SeverRequestListener() {
            @Override
            public void completed(Object obj) {
                Result res=(Result) obj;
                if(res.getError()==0){
                    final ArrayList<User>  arr_users=(ArrayList<User>) res.getData();
                    loadDanhSachHocVien(arr_users);

                }else{

                }
            }
        });
        request.execute(parameter);
    }

     private void setupSearchView()
     {
         searchView.setQueryHint("Search Here");
       //  searchView.setIconifiedByDefault(false);
         searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
             @Override
             public boolean onQueryTextSubmit(String s) {
                 return false;
             }

             @Override
             public boolean onQueryTextChange(String s) {
                 if (TextUtils.isEmpty(s.toString())) {
                     listStudent.clearTextFilter();
                 } else {
                     Toast.makeText(getContext(),s,Toast.LENGTH_LONG).show();
                     adapter.getFilter().filter(s);
                    // listStudent.setFilterText(s.toString());
                 }
                 return true;
             }
         });
       //  searchView.setSubmitButtonEnabled(true);

     }

 }
