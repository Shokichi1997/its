package com.itsdl.androidtutorials.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.itsdl.androidtutorials.R;
import com.itsdl.androidtutorials.networks.GetProblemRequest;
import com.itsdl.androidtutorials.networks.SeverRequest;
import com.itsdl.androidtutorials.utils.Answer;
import com.itsdl.androidtutorials.utils.Question;
import com.itsdl.androidtutorials.utils.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TestLessonFragment extends Fragment implements View.OnClickListener{
    private Button btnNewProblem,btnCheckAnswer,btnHintQuestion,btnSolution;
    private TextView lblProblem,lblQuestionContent;
    private LinearLayout llAnswer;
    private Toolbar toolbar;
    private ProgressBar progressBarProblem;
    private Context context;
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
        context = getContext();
        toolbar = root.findViewById(R.id.test_toolbar);
        ((AppCompatActivity )getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Problem - Quiz");
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //anh xa ListChapterLesson
        btnCheckAnswer = root.findViewById(R.id.btnCheckAnswer);
        btnNewProblem = root.findViewById(R.id.btnNewProblem);
        btnHintQuestion = root.findViewById(R.id.btnHintQuestion);
        btnSolution = root.findViewById(R.id.btnSolution);

        btnSolution.setOnClickListener(this);
        btnHintQuestion.setOnClickListener(this);
        btnNewProblem.setOnClickListener(this);
        btnCheckAnswer.setOnClickListener(this);

        lblProblem =  root.findViewById(R.id.lblProblem);
        lblQuestionContent = root.findViewById(R.id.lblQuestionContent);
        llAnswer = root.findViewById(R.id.llAnswer);

        progressBarProblem = root.findViewById(R.id.progressBarProblem);
        progressBarProblem.setVisibility(View.GONE);
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
        Map<String, String> parameter = new HashMap<>();
        parameter.put("lessonID", "1");
        progressBarProblem.setVisibility(View.VISIBLE);
        GetProblemRequest request = new GetProblemRequest(new SeverRequest.SeverRequestListener() {
            @Override
            public void completed(Object obj) {
                if(obj!=null){
                    Result res = (Result) obj;
                    if(res.getError()==0){
                        Question question = ( Question ) res.getData();
                        setContentProblem(question);
                    }
                    else {
                        Toast.makeText(getContext(),"Create problem is error. Please to try again!",Toast.LENGTH_LONG).show();
                    }
                }
                progressBarProblem.setVisibility(View.GONE);
            }
        });
        request.execute(parameter);
    }

    private void setContentProblem(Question question) {
        llAnswer.removeAllViews();
        lblQuestionContent.setText(question.getContent());
        int type = question.getType_qs();
        ArrayList<Answer> answers = question.getAnswers();
        RadioGroup group = null;
        int index = 100;
        if(type == 1){
            group = new RadioGroup(context);
            llAnswer.addView(group);
        }
        int size = answers.size();
        for (int i = 0;i<size;i++) {
            switch (type){
                case 1:
                    RadioButton radioButton = new RadioButton(context);
                    radioButton.setText(answers.get(i).getContent());
                    radioButton.setId(index+i);
                    group.addView(radioButton);
                    break;
                case 2:
                    CheckBox box = new CheckBox(context);
                    box.setText(answers.get(i).getContent());
                    box.setId(index+i);
                    llAnswer.addView(box);
                    break;
                case 3:
                    EditText editText = new EditText(context);
                    editText.setInputType(1);
                    editText.setId(index+i);
                    llAnswer.addView(editText);
                    break;
            }
        }

    }

    private void checkAnswer() {

    }

    private void showHint() {

    }

    private void showSolution() {

    }
}
