package com.itsdl.androidtutorials.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.itsdl.androidtutorials.R;
import com.itsdl.androidtutorials.adapters.LessonAdapter;
import com.itsdl.androidtutorials.networks.GetLessonRequest;
import com.itsdl.androidtutorials.networks.SeverRequest;
import com.itsdl.androidtutorials.utils.CustomDialog;
import com.itsdl.androidtutorials.utils.Lesson;
import com.itsdl.androidtutorials.utils.LessonItems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class LessonFragment extends Fragment {

    android.support.v7.widget.Toolbar toolbar;

    View root;
    private LessonAdapter lessonAdapter = null;
    ExpandableListView expandableListViewLesson;
    ArrayList<Lesson> arr_Lessons = new ArrayList<>();
    ArrayList<LessonItems> arr_LessonItems = new ArrayList<>();
    private ProgressBar progressBarLesson;
    int lessonID=1;
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
        lessonID = 1;
        ImageView im = root.findViewById(R.id.imgExampleItem);
        if(args!=null && args.containsKey("lesson_id")){
            lessonID = args.getInt("lesson_id");
        }
        loadLesson(lessonID);
        return root;
    }

    private void getViews() {
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

    public void loadLesson(int lessonID) {
        Map<String, String> parameter = new HashMap<>();
        parameter.put("chapter_id", String.valueOf(lessonID));
        // @TODO get user_id
        parameter.put("user_id","1");
        progressBarLesson.setVisibility(View.VISIBLE);
        GetLessonRequest request = new GetLessonRequest(new SeverRequest.SeverRequestListener() {
            @Override
            public void completed(Object obj) {
                if (obj != null) {
                    ArrayList<Object> arr = (ArrayList<Object>) obj;
                    arr_Lessons = (ArrayList<Lesson>) arr.get(0);
                    arr_LessonItems = (ArrayList<LessonItems>) arr.get(1);
                    //get data
                    List<String> listLessonName = new ArrayList<>();
                    HashMap<String, List<LessonItems>> listLessonItem = new HashMap<>();
                    for (int i = 0; i < arr_Lessons.size(); i++) {
                        listLessonName.add(arr_Lessons.get(i).getLesson_name());
                        ArrayList<LessonItems> arr_lessonitemtoLesson = new ArrayList<>();
                        for (int j = 0; j < arr_LessonItems.size(); j++) {
                            if (arr_Lessons.get(i).getLesson_id() == arr_LessonItems.get(j).getIdLesson()) {
                                arr_lessonitemtoLesson.add(arr_LessonItems.get(j));
                            }
                        }
                        listLessonItem.put(listLessonName.get(i), arr_lessonitemtoLesson);
                    }
                    //load data-error rack app
                    try{
                        lessonAdapter = new LessonAdapter(getActivity().getBaseContext(), listLessonName, listLessonItem);
                        expandableListViewLesson.setAdapter(lessonAdapter);
                        //add event
                        setExpandableListViewLessonClick(listLessonName, listLessonItem);
                    }
                    catch (Exception e){
                    }
                }else{
                    showDialogChapterIsNotOpened();

                }
                progressBarLesson.setVisibility(View.GONE);
            }
        });
        request.execute(parameter);
        //khong lam viec gi nua
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
    private void setExpandableListViewLessonClick(final List<String> listGroup, final HashMap<String, List<LessonItems>> listChild) {
        expandableListViewLesson.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(getContext(), listChild.get(listGroup.get(groupPosition)).get(childPosition).getIdLessonItem() + "", Toast.LENGTH_LONG).show();
                loadContentLessonItem(listChild.get(listGroup.get(groupPosition)).get(childPosition).getIdLessonItem(),
                                      listChild.get(listGroup.get(groupPosition)).get(childPosition).getLessonItemName()
                                     );
                return false;
            }
        });

        expandableListViewLesson.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return false;
            }
        });
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
