package com.itsdl.androidtutorials.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.itsdl.androidtutorials.R;

import java.util.ArrayList;
import java.util.List;

public class SelectLessonTestFragment extends Fragment {
    private Spinner spnLesson;
    private Toolbar toolbar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_select_lesson_test,container,false);
        addControls(root);
        addEvents();
        return root;
    }

    private void addControls(View root) {
        List<String> s = new ArrayList<>();
        toolbar = root.findViewById(R.id.toolbar);
        ((AppCompatActivity )getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Problem - Quiz");
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        s.add("aadsssssssssssssssssssssssssssssss");
        s.add("báddddddddddddddddddddddddddddddđabcknfds12fds1fdsfsf1f22222");
        s.add("c");
        spnLesson = root.findViewById(R.id.spnLesson);
        spnLesson.setAdapter(new ArrayAdapter<String>(getContext(),R.layout.support_simple_spinner_dropdown_item,s));
    }

    private void addEvents() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getFragmentManager()!=null){
                    if(getFragmentManager().getBackStackEntryCount()>0){
                        getFragmentManager().popBackStack();
                    }
                }

            }
        });
    }
}
