package com.itsdl.androidtutorials.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toolbar;

import com.itsdl.androidtutorials.R;
import com.itsdl.androidtutorials.adapters.LessonAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class LessonFragment extends Fragment {

android.support.v7.widget.Toolbar toolbar;

    View root;
    private LessonAdapter lessonAdapter=null;
    ExpandableListView expandableListViewLesson;
    public LessonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_lesson,container,false);

        getViews();

        List<String> chapter =new ArrayList<>();
        chapter.add("Chapter 1");
        chapter.add("Chapter 2");
        chapter.add("Chapter 3");

        List<String> listLessonItem1=new ArrayList<>();
        listLessonItem1.add("Lesson 1");
        listLessonItem1.add("Lesson 2");
        listLessonItem1.add("Lesson 3");
        List<String> listLessonItem2=new ArrayList<>();
        listLessonItem2.add("Lesson 1");
        listLessonItem2.add("Lesson 2");
        listLessonItem2.add("Lesson 3");
        List<String> listLessonItem3=new ArrayList<>();
        listLessonItem3.add("Lesson 1");
        listLessonItem3.add("Lesson 2");
        listLessonItem3.add("Lesson 3");

        HashMap<String,List<String>> listLessonItem = new HashMap<>(



        );
        listLessonItem.put(chapter.get(0),listLessonItem1);
        listLessonItem.put(chapter.get(1),listLessonItem2);
        listLessonItem.put(chapter.get(2),listLessonItem3);

        lessonAdapter=new LessonAdapter(getActivity().getBaseContext(),chapter,listLessonItem);
        expandableListViewLesson.setAdapter(lessonAdapter);

        return root;
    }

    private void getViews(){
        //add tool bar
        toolbar = root.findViewById(R.id.lesson_toolbar);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Lesson");
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getFragmentManager().getBackStackEntryCount()>0){
                    getFragmentManager().popBackStack();
                }//getFragmentManager().getBackStackEntryCount()>0
            }
        });

        expandableListViewLesson=(ExpandableListView) root.findViewById(R.id.expandLesson);


    }


}
