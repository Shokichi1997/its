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

}
