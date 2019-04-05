package com.itsdl.androidtutorials.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.itsdl.androidtutorials.R;

public class ContentLessonFragment extends Fragment {
    private BottomNavigationView bottomNavigationView;
    private Toolbar toolbar;
    WebView wv_lesson_content;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_lesson_content,container,false);

        addControl(root);
        addEvents();
        return root;
    }

    /**
     * addControl: add controls with layout
     * @param root view contain control in layout*/
    private void addControl(View root) {
        //add tool bar
        toolbar = root.findViewById(R.id.add_content_toolbar);
        ((AppCompatActivity )getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Lesson");
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //add bottomNavigationView
        bottomNavigationView = root.findViewById(R.id.navbottomLesson);

        wv_lesson_content = root.findViewById(R.id.wv_lesson_content);

    }

    /**
     * addEvents: add events to handle event click*/
    private void addEvents() {
        //add event for BottomNavigationView item click
        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.navigation_prev:
                        /**@// TODO: 4/5/2019  */
                    case R.id.navigation_feedback:
                        /**@// TODO: 4/5/2019  */
                    case R.id.navigation_next:
                        /**@// TODO: 4/5/2019  */
                }
            }
        });
    }


}
