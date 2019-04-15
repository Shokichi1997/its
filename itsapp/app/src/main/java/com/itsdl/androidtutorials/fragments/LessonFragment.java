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
import com.itsdl.androidtutorials.networks.GetLessonRequest;
import com.itsdl.androidtutorials.networks.SeverRequest;
import com.itsdl.androidtutorials.networks.SignInRequest;
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
    private LessonAdapter lessonAdapter=null;
    ExpandableListView expandableListViewLesson;
    ArrayList<Lesson> arr_Lessons=new ArrayList<>();
    ArrayList<LessonItems> arr_LessonItems=new ArrayList<>();
    public LessonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_lesson,container,false);

        getViews();

        List<String> chapter =new ArrayList<>();
        chapter.add("Lesson 1");
        chapter.add("Lesson 2");
        chapter.add("Lesson 3");

        List<String> listLessonItem1=new ArrayList<>();
        listLessonItem1.add("Items 1");
        listLessonItem1.add("Items 2");
        listLessonItem1.add("Items 3");
        List<String> listLessonItem2=new ArrayList<>();
        listLessonItem2.add("Items 1");
        listLessonItem2.add("Items 2");
        listLessonItem2.add("Items 3");
        List<String> listLessonItem3=new ArrayList<>();
        listLessonItem3.add("Items 1");
        listLessonItem3.add("Items 2");
        listLessonItem3.add("Items 3");

        loadLesson();
     /*   HashMap<String,List<String>> listLessonItem = new HashMap<>(



        );
        listLessonItem.put(chapter.get(0),listLessonItem1);
        listLessonItem.put(chapter.get(1),listLessonItem2);
        listLessonItem.put(chapter.get(2),listLessonItem3);

        lessonAdapter=new LessonAdapter(getActivity().getBaseContext(),chapter,listLessonItem);
        expandableListViewLesson.setAdapter(lessonAdapter);*/

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

    public void loadLesson(){

      Map<String,String> parameter=new HashMap<>();
      parameter.put("chapter_id","2");

        GetLessonRequest request=new GetLessonRequest(new SeverRequest.SeverRequestListener() {
            @Override
            public void completed(Object obj) {
                if (obj != null) {
                    ArrayList<Object> arr=(ArrayList<Object>)obj;

                    arr_Lessons=(ArrayList<Lesson>) arr.get(0);
                    arr_LessonItems=(ArrayList<LessonItems>) arr.get(1);

                    List<String> listLessonName=new ArrayList<>();
                    HashMap<String,List<LessonItems>> listLessonItem = new HashMap<>();
                    for (int i=0;i<arr_Lessons.size();i++){
                       listLessonName.add(arr_Lessons.get(i).getLessonName());
                        ArrayList<LessonItems> arr_lessonitemtoLesson=new ArrayList<>();
                        for(int j=0;j<arr_LessonItems.size();j++){
                            if(arr_Lessons.get(i).getIdLesson()==arr_LessonItems.get(j).getIdLesson()){
                            arr_lessonitemtoLesson.add(arr_LessonItems.get(j));
                            }
                        }
                       listLessonItem.put(listLessonName.get(i),arr_lessonitemtoLesson);
                    }
                    lessonAdapter=new LessonAdapter(getActivity().getBaseContext(),listLessonName,listLessonItem);
                    expandableListViewLesson.setAdapter(lessonAdapter);
                }
            }
        });
        request.execute(parameter);
        //khong lam viec gi nua
    }

    private void setExpandableListViewLessonClick(){
        expandableListViewLesson.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
               // Log.e(TAG, "onChildClick: " + listHeader.get(groupPosition) + ", " + mData.get(listHeader.get(groupPosition)).get(childPosition).getName());

                return false;
            }
        });
    }
}
