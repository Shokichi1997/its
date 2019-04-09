package com.itsdl.androidtutorials.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.itsdl.androidtutorials.R;

public class TestLessonFragment extends Fragment implements View.OnClickListener{
    private Button btnNewProblem,btnCheckAnswer,btnHintQuestion,btnSolution;
    private TextView lblProblem,lblQuestionContent;
    private LinearLayout llAnswer;
    private Toolbar toolbar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_test_lesson,container,false);
        addControls(root);
        addEvents(root);
        return root;
    }
    /**addControls add Views to fragment
     * @param root parent view contain views*/
    private void addControls(View root) {
        //add tool bar
        toolbar = root.findViewById(R.id.test_toolbar);
        ((AppCompatActivity )getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Problem - Quiz");
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //anh xa ListChapterLesson
        btnCheckAnswer = root.findViewById(R.id.btnCheckAnswer);
        btnNewProblem = root.findViewById(R.id.btnNewProblem);
        btnHintQuestion = root.findViewById(R.id.btnHintQuestion);
        btnSolution = root.findViewById(R.id.btnSolution);
        lblProblem =  root.findViewById(R.id.lblProblem);
        lblQuestionContent = root.findViewById(R.id.lblQuestionContent);
        llAnswer = root.findViewById(R.id.llAnswer);
    }

    /**addEvents add event to view
     * @param root parent view contain views*/
    private void addEvents(View root) {
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnNewProblem:
                addNewProblem();
                break;
            case R.id.btnCheckAnswer:
                checkAnswer();
                break;
            case R.id.btnHintQuestion:
                showHint();
                break;
            case R.id.btnSolution:
                showSolution();
                break;
        }
    }

    private void addNewProblem() {

    }

    private void checkAnswer() {

    }

    private void showHint() {

    }

    private void showSolution() {

    }
}
