package com.itsdl.androidtutorials.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.itsdl.androidtutorials.R;
import com.itsdl.androidtutorials.adapters.LessonAdapter;
import com.itsdl.androidtutorials.networks.CheckLessonOpeningRequest;
import com.itsdl.androidtutorials.networks.GetLessonRequest;
import com.itsdl.androidtutorials.networks.SeverRequest;
import com.itsdl.androidtutorials.utils.CustomDialog;
import com.itsdl.androidtutorials.utils.DrawerLocker;
import com.itsdl.androidtutorials.utils.Global;
import com.itsdl.androidtutorials.utils.Lesson;
import com.itsdl.androidtutorials.utils.LessonItems;
import com.itsdl.androidtutorials.utils.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class LessonFragment extends Fragment {

    private Toolbar toolbar;
    private View root;
    private ExpandableListView expandableListViewLesson;
    private ArrayList<Lesson> arr_Lessons = new ArrayList<>();
    private ProgressBar progressBarLesson;
    private int chapter_id=1;

    public LessonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_lesson, container, false);

        getViews();
        //Bundle
        Bundle args = getArguments();
        chapter_id = 1;
        ImageView im = root.findViewById(R.id.imgExampleItem);
        if(args!=null && args.containsKey("chapter_id")){
            chapter_id = args.getInt("chapter_id");
        }
        loadLesson(chapter_id);
        return root;
    }

    private void getViews() {
        if(getActivity()!=null){
            ((DrawerLocker ) getActivity()).setDrawerEnabled(true);
        }
        //add tool bar
            toolbar = root.findViewById(R.id.lesson_toolbar);
            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Lesson");
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            progressBarLesson = root.findViewById(R.id.progressBarLesson);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getFragmentManager().getBackStackEntryCount() > 0) {
                        getFragmentManager().popBackStack();
                    }
                }
            });
        //
        expandableListViewLesson = (ExpandableListView) root.findViewById(R.id.expandLesson);
    }

    public void loadLesson(int chapter_id) {
        Map<String, String> parameter = new HashMap<>();
        parameter.put("chapter_id", String.valueOf(chapter_id));
        // @TODO get user_id
        parameter.put("user_id","1");
        progressBarLesson.setVisibility(View.VISIBLE);
        GetLessonRequest request = new GetLessonRequest(new SeverRequest.SeverRequestListener() {
            @Override
            public void completed(Object obj) {
                boolean success = false;
                if (obj != null) {
                    Result res = (Result) obj;
                    if(res.getError()==0) {
                        Object arr = res.getData();
                        arr_Lessons = ( ArrayList<Lesson> ) arr;
                        success = true;
                        LessonAdapter lessonAdapter = new LessonAdapter(getContext(), arr_Lessons);
                        expandableListViewLesson.setAdapter(lessonAdapter);
                        setExpandableListViewLessonClick();
                    }
                }
                if(!success){
                    showDialogChapterIsNotOpened();
                }
                progressBarLesson.setVisibility(View.GONE);
            }
        });
        request.execute(parameter);
    }

    private void showDialogChapterIsNotOpened() {
        final CustomDialog dialog = new CustomDialog(getContext());
        dialog.setLblMessageHint("This chapter is not opened. Please learn and pass the test of the previous lesson!");
        dialog.setLblTitleHint("Notification");
        dialog.setImgIconHint(R.drawable.tick_green);
        dialog.setBtnCloseHint(R.drawable.background_card);
        dialog.setEventsClose(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.popBackStack();
            }
        });
        dialog.show();
    }

    //su kien click ExpandableListView
    private void setExpandableListViewLessonClick() {
        expandableListViewLesson.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                LessonItems lessonItem =  arr_Lessons.get(groupPosition).getLesson_item_list().get(childPosition);
                loadContentLessonItem(lessonItem.getIdLessonItem(),lessonItem.getLessonItemName());
                return false;
            }
        });

        expandableListViewLesson.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(final int groupPosition) {
                int lessonID = arr_Lessons.get(groupPosition).getLesson_id();
                Map<String, String> parameter = new HashMap<>();
                parameter.put("lesson_id", String.valueOf(lessonID));
                // TODO get user_id
                parameter.put("user_id","1");
                progressBarLesson.setVisibility(View.VISIBLE);
                CheckLessonOpeningRequest request = new CheckLessonOpeningRequest(new SeverRequest.SeverRequestListener() {
                    @Override
                    public void completed(Object obj) {
                        boolean success = false;
                        if (obj != null) {
                            Result res = (Result) obj;
                            if(res.getError()==0) {
                                Object arr = res.getData();
                                ArrayList<Integer> flag = ( ArrayList<Integer> ) arr;
                                if(flag.get(1)==1){
                                    success = true;
                                    Global.numberOpenningLesson = flag.get(0);
                                }
                            }
                        }
                        if(!success){
                            expandableListViewLesson.collapseGroup(groupPosition);
                            showDialogLessonIsNotOpened();
                        }
                        progressBarLesson.setVisibility(View.GONE);
                    }
                });
                request.execute(parameter);
            }
        });
    }

    private void showDialogLessonIsNotOpened() {
        final CustomDialog dialog = new CustomDialog(getContext());
        dialog.setLblMessageHint("This lesson is not opened. Please learn and pass the test of the previous lesson!");
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

    private void replaceFragment(Fragment fConv) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.frContainer, fConv, "LessonItemContent");
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void loadContentLessonItem(int lessonItemID,String lesson_item_name) {
        int id = lessonItemID;
        Bundle bundle = new Bundle();
        bundle.putInt("lesson_item_id", id);
        bundle.putString("lesson_item_name",lesson_item_name);
        ContentLessonFragment fConv = new ContentLessonFragment();
        fConv.setArguments(bundle);
        replaceFragment(fConv);
    }
}
