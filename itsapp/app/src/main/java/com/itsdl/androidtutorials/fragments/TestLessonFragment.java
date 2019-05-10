package com.itsdl.androidtutorials.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatRadioButton;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.itsdl.androidtutorials.R;
import com.itsdl.androidtutorials.networks.GetProblemRequest;
import com.itsdl.androidtutorials.networks.SeverRequest;
import com.itsdl.androidtutorials.networks.UpdateScoreRequest;
import com.itsdl.androidtutorials.utils.Answer;
import com.itsdl.androidtutorials.utils.CustomDialog;
import com.itsdl.androidtutorials.utils.DrawerLocker;
import com.itsdl.androidtutorials.utils.ProfileUser;
import com.itsdl.androidtutorials.utils.Question;
import com.itsdl.androidtutorials.utils.Result;
import com.itsdl.androidtutorials.utils.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TestLessonFragment extends Fragment implements View.OnClickListener{
    private Button btnNewProblem,btnCheckAnswer,btnHintQuestion,btnSolution;
    private TextView lblProblem,lblQuestionContent,lblLevel;
    private LinearLayout llAnswer;
    private Toolbar toolbar;
    private ProgressBar progressBarProblem;
    private Context context;
    private Question questionAll = null;
    private ArrayList<Integer> checkArr = null;
    private EditText edtAnswerUser;
    private View root;
    private boolean isAnswered;
    private int problemNumber = 0;
    private int lesson_id;
    private String lesson_name;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
       inflater.inflate(R.menu.feedback_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_feedback:
                // Not implemented here
                composeEmail(ProfileUser.getInstance().getEmail(),"Phan Hoi Dap An",lesson_name);
                return false;
            default:
                break;
        }

        return false;
    }
    public void composeEmail(String addresses, String subject,String lesson_name) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:its@example.com"));; // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, lesson_name +
                "\n"+
                "------------NOT DELETE----------"
                +"\n"
        );
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_test_lesson,container,false);
        addControls();
        addEvents();
        return root;
    }
    /**addControls add Views to fragment*/
    private void addControls() {
        if(getActivity()!=null){
            ((DrawerLocker ) getActivity()).setDrawerEnabled(true);
        }

        Bundle args = getArguments();
        if(args!=null && args.containsKey("lesson_id")){
            lesson_id = args.getInt("lesson_id");
            lesson_name=args.getString("lesson_name");
        }
        else {
            lesson_id = 1;
        }
        //add tool bar
        context = getContext();
        checkArr = new ArrayList<>();
        toolbar = root.findViewById(R.id.test_toolbar);
        ((AppCompatActivity )getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Problem - Quiz");
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //anh xa ListChapterLesson
        btnCheckAnswer = root.findViewById(R.id.btnCheckAnswer);
        btnNewProblem = root.findViewById(R.id.btnNewProblem);
        btnHintQuestion = root.findViewById(R.id.btnHintQuestion);
        btnSolution = root.findViewById(R.id.btnSolution);
        lblLevel = root.findViewById(R.id.lblLevel);

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

    /**addEvents add event to view*/
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

    /**
     * onClick handler click button event  */
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

    /**
     * addNewProblem get new problem in server */
    private void addNewProblem() {
        Map<String, String> parameter = new HashMap<>();
        parameter.put("lessonID", String.valueOf(lesson_id));
        parameter.put("user_id", String.valueOf(ProfileUser.getInstance().getUser_id()));
        progressBarProblem.setVisibility(View.VISIBLE);
        GetProblemRequest request = new GetProblemRequest(new SeverRequest.SeverRequestListener() {
            @Override
            public void completed(Object obj) {
                if(obj!=null){
                    Result res = (Result) obj;
                    if(res.getError()==0){
                        Question question = ( Question ) res.getData();
                        setContentProblem(question);
                        setLabelLevel(question.getLevel());
                    }
                    else {
                        Toast.makeText(getContext(),"Create problem is error. Please to try again!",Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    showDialogNonChooseLesson("This lesson has no questions");
                }
                progressBarProblem.setVisibility(View.GONE);
            }
        });
        request.execute(parameter);
    }

    private void showDialogNonChooseLesson(String message) {
        final CustomDialog dialog = new CustomDialog(getContext());
        dialog.setLblMessageHint(message);
        dialog.setLblTitleHint("Notification");
        dialog.setImgIconHint(R.drawable.tick_green);
        dialog.setBtnCloseHint(R.drawable.background_card);
        dialog.setEventsClose(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    /**
     * setContentProblem set question content */
    private void setContentProblem(Question question) {
        problemNumber++;
        lblProblem.setText("Problem #"+problemNumber+": ");
        initQuestionInfo(question);
        lblQuestionContent.setText(question.getContent());
        int type = question.getType_qs();
        ArrayList<Answer> answers = question.getAnswers();
        RadioGroup group = null;
        int index = 100;
        if(type == 1){
            group = new RadioGroup(context);
            group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if(!checkArr.isEmpty()){
                        checkArr.clear();
                    }
                    checkArr.add(checkedId-100);
                }
            });
            llAnswer.addView(group);
        }
        int size = answers.size();
        for (int i = 0;i<size;i++) {
            switch (type){
                case 1:
                    RadioButton radioButton = new RadioButton(context);
                    radioButton.setText(answers.get(i).getContent());
                    radioButton.setId(index+i);
                    radioButton.setTag("radio"+(index+i));
                    LinearLayout.LayoutParams paramsRadio = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                    paramsRadio.bottomMargin = 15;
                    group.addView(radioButton,paramsRadio);
                    break;
                case 2:
                    final CheckBox box = new CheckBox(context);
                    box.setText(answers.get(i).getContent());
                    box.setId(index+i);

                    box.setTag("checkbox"+(index+i));

                    box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if(isChecked){
                                checkArr.add(box.getId());
                            }
                            else {
                                int size = checkArr.size();
                                for(int i=0;i<size;i++){
                                    if(box.getId() == checkArr.get(i)){
                                        checkArr.remove(i);
                                        size--;
                                    }
                                }
                            }
                        }
                    });
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                    params.bottomMargin = 15;
                    llAnswer.addView(box,params);
                    break;
                case 3:
                    TextView txtAnswer = new TextView(context);
                    txtAnswer.setText("Answer: ");
                    edtAnswerUser = new EditText(context);
                    edtAnswerUser.setInputType(1);
                    edtAnswerUser.setId(index+i);
                    edtAnswerUser.setPadding(8,8,8,8);
                    llAnswer.addView(txtAnswer);
                    llAnswer.addView(edtAnswerUser);
                    break;
            }
        }

    }

    /**
     * setLabelLevel set question difficult level*/
    private void setLabelLevel(int level) {
        if(level == 1){
            lblLevel.setText(R.string.ease);
            lblLevel.setTextColor(Color.GREEN);
        }
        else if(level == 2){
            lblLevel.setText(R.string.medium);
            lblLevel.setTextColor(Color.BLUE);
        }
        else if(level == 3){
            lblLevel.setText(R.string.hard);
            lblLevel.setTextColor(Color.RED);
        }
    }

    /**
     * initQuestionInfo int base of question*/
    private void initQuestionInfo(Question question) {
        questionAll = question;
        llAnswer.removeAllViews();
        isAnswered = false;
        if(!checkArr.isEmpty()){
            checkArr.clear();
        }
        if(question.getType_qs()==3){
            if(edtAnswerUser!=null){
                edtAnswerUser.setText("");
            }

        }

    }

    /**
     * checkAnswer check answer of current question*/
    private void checkAnswer() {
        if(questionAll!=null){
            int type_qs = questionAll.getType_qs();
            switch (type_qs){
                case 1:
                    checkAnswerForRadioButton();
                    break;
                case 2:
                    checkAnswerForCheckBox();
                    break;
                case 3:
                    checkAnswerForEditText();
                    break;
            }
        }
    }

    /**
     * checkAnswerForEditText check answer type of the question to fill in*/
    private void checkAnswerForEditText() {
        if(edtAnswerUser!=null){
            String answerUser = edtAnswerUser.getText().toString().trim().toLowerCase();
            if(!answerUser.isEmpty()){
                ArrayList<Answer> answers = questionAll.getAnswers();
                if(answerUser.equals(answers.get(0).getContent().toLowerCase())){
                    edtAnswerUser.setBackgroundColor(Color.GREEN);
                    updateScoreLesson(1);
                }
                else {
                    edtAnswerUser.setBackgroundColor(Color.RED);
                    updateScoreLesson(-1);
                }
                edtAnswerUser.setEnabled(false);
                edtAnswerUser.setTextColor(Color.BLACK);
                isAnswered = true;
            }
        }
    }

    /**
     * checkAnswerForCheckBox check answer type of the question has multi option*/
    private void checkAnswerForCheckBox() {
        if(!checkArr.isEmpty()){
            ArrayList<Answer> answers = questionAll.getAnswers();
            ArrayList<Integer> trueAnswerBox = new ArrayList<>();
            for (int i = 0;i<answers.size();i++){
                if(answers.get(i).getResult()==1){
                    trueAnswerBox.add(i);
                }
            }
            int numberTrue = 0;
            for(int id:checkArr){
                boolean isExist = false;
                for(int i = 0;i<trueAnswerBox.size();i++){
                    if(trueAnswerBox.get(i).equals(id-100)){
                        isExist = true;
                        break;
                    }
                }
                if(isExist){
                    numberTrue++;
                    root.findViewWithTag("checkbox"+id).setBackgroundColor(Color.GREEN);
                }
                else {
                    root.findViewWithTag("checkbox"+id).setBackgroundColor(Color.RED);
                }
            }
            for(int i =0;i<answers.size();i++){
                root.findViewWithTag("checkbox"+(100+i)).setClickable(false);
            }

            if(numberTrue==trueAnswerBox.size()){
                updateScoreLesson(1);
            }
            else {
                updateScoreLesson(-1);
            }
            isAnswered = true;
        }
    }

    /**
     * checkAnswerForRadioButton check answer type of the question has an option*/
    private void checkAnswerForRadioButton() {
        if(!checkArr.isEmpty()){
            ArrayList<Answer> answers = questionAll.getAnswers();
            int trueAnswer = -1;
            for (int i = 0;i<answers.size();i++){
                if(answers.get(i).getResult()==1){
                    trueAnswer = i;
                    break;
                }
            }
            if(checkArr.get(0)== trueAnswer){
                root.findViewWithTag("radio"+(100+trueAnswer)).setBackgroundColor(Color.GREEN);
                updateScoreLesson(1);
            }
            else {
                root.findViewWithTag("radio"+(100+checkArr.get(0))).setBackgroundColor(Color.RED);
                updateScoreLesson(-1);
            }

            for(int i =0;i<answers.size();i++){
                root.findViewWithTag("radio"+(100+i)).setClickable(false);
            }
            isAnswered = true;
        }
    }

    /**
     * showHint show hint of current question*/
    private void showHint() {
        if(questionAll!=null){
            final CustomDialog dialog = new CustomDialog(context);
            dialog.setLblMessageHint(questionAll.getHint());
            dialog.setEventsClose(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        }

    }

    /**
     * showSolution show solution of current question*/
    private void showSolution() {
        if(questionAll!=null&&isAnswered){
            ArrayList<Answer> ans= questionAll.getAnswers();
            String answerTrue = "";
            for(int i = 0;i< ans.size();i++){
                if(ans.get(i).getResult()==1){
                    answerTrue += ans.get(i).getContent()+"\n";
                }
            }
            final CustomDialog dialog = new CustomDialog(context);
            dialog.setLblMessageHint(answerTrue);
            dialog.setLblTitleHint("Solution");
            dialog.setImgIconHint(R.drawable.tick_pink);
            dialog.setBtnCloseHint(R.drawable.background_card_pink);
            dialog.setEventsClose(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();

        }
    }

    /**
     * updateScoreLesson create request to update score in this lesson
     * @param scoreProblem current question score*/
    private void updateScoreLesson(float scoreProblem){
        Map<String, String> parameter = new HashMap<>();
        parameter.put("lesson_id", String.valueOf(lesson_id));
        parameter.put("user_id", String.valueOf(ProfileUser.getInstance().user_id));
        parameter.put("score", String.valueOf(scoreProblem));

        UpdateScoreRequest request = new UpdateScoreRequest(new SeverRequest.SeverRequestListener() {
            @Override
            public void completed(Object obj) {
                if(obj!=null){
                    Result res = (Result) obj;
                    if(res.getError()==0){
                        //Toast.makeText(getContext(),"Add score successfully!",Toast.LENGTH_LONG).show();
                    }
                    else {
                        //Toast.makeText(getContext(),"Add score not successfully!",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        request.execute(parameter);
    }


}
