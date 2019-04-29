package com.itsdl.androidtutorials.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.itsdl.androidtutorials.R;
import com.itsdl.androidtutorials.networks.GetOpeningLessonRequest;
import com.itsdl.androidtutorials.networks.SeverRequest;
import com.itsdl.androidtutorials.utils.CustomDialog;
import com.itsdl.androidtutorials.utils.Global;
import com.itsdl.androidtutorials.utils.Lesson;
import com.itsdl.androidtutorials.utils.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SelectLessonTestFragment extends Fragment {
    private Spinner spnLesson;
    private Toolbar toolbar;
    private Button btnSelectLesson;
    private ProgressBar progressBarSelectLesson;
    private Context context;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_select_lesson_test,container,false);
        addControls(root);
        addEvents();
        return root;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(Global.numberOpenningLesson > Global.lessons.size() || Global.lessons.size()==0){
            callRequestGetOpeningLesson();
        }
        else {
            if(Global.lessons.size()>0){
                addDataToSpinner();
            }
            else {
                showDialogNonChooseLesson("You must study first");
            }
        }
    }

    private void callRequestGetOpeningLesson() {
        Map<String, String> parameter = new HashMap<>();
        parameter.put("user_id", "1");
        progressBarSelectLesson.setVisibility(View.VISIBLE);
        GetOpeningLessonRequest request = new GetOpeningLessonRequest(new SeverRequest.SeverRequestListener() {
            @Override
            public void completed(Object obj) {
                if(obj!=null){
                    Result res = (Result) obj;
                    if(res.getError()==0){
                        Object lessons =  res.getData();
                        Global.lessons.clear();
                        Global.lessons = ( ArrayList<Lesson> ) lessons;
                        if(Global.lessons.size()>0){
                            addDataToSpinner();
                        }
                    }
                    else {
                        Toast.makeText(getContext(),"Get opening fail. Please to try again!",Toast.LENGTH_LONG).show();
                    }
                }
                progressBarSelectLesson.setVisibility(View.GONE);
            }
        });
        request.execute(parameter);
    }

    private void addDataToSpinner(){
        ArrayList<String> s = new ArrayList<>();
        for(Lesson lesson : Global.lessons){
            s.add(lesson.getLesson_name());
        }
        spnLesson.setAdapter(new ArrayAdapter<>(context,
                R.layout.support_simple_spinner_dropdown_item,s));


    }

    private void addControls(View root) {
        context = getContext();
        btnSelectLesson = root.findViewById(R.id.btnSelectLesson);
        toolbar = root.findViewById(R.id.toolbar);
        ((AppCompatActivity )getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Problem - Quiz");
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        spnLesson = root.findViewById(R.id.spnLesson);
        progressBarSelectLesson = root.findViewById(R.id.progressBarSelectLesson);
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

        btnSelectLesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get lesson_id in spinner
                int selected = spnLesson.getSelectedItemPosition();
                String lesson_name = null;
                if(selected<0){
                    String message = "You must choose a lesson to test!";
                    showDialogNonChooseLesson(message);
                }
                else {
                    Fragment fragment = new TestLessonFragment();
                    Bundle args = new Bundle();
                    int lesson_id = 1;
                    if(Global.lessons.get(selected) != null){
                        lesson_id = Global.lessons.get(selected).getLesson_id();
                        lesson_name=Global.lessons.get(selected).getLesson_name();
                    }
                    args.putInt("lesson_id",lesson_id);
                    args.putString("lesson_name",lesson_name);

                    fragment.setArguments(args);
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.add(R.id.frContainer,fragment,"PROBLEM")
                            .addToBackStack("PROBLEM")
                            .commit();
                }

            }
        });
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
}
