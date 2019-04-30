package com.itsdl.androidtutorials.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;

import com.itsdl.androidtutorials.R;
import com.itsdl.androidtutorials.adapters.ChapterLessonAdapter;
import com.itsdl.androidtutorials.utils.ChapterLesson;
import com.itsdl.androidtutorials.utils.DrawerLocker;

import java.util.ArrayList;

public class ChapterLessonFragment extends Fragment {
    private ListView listChapterLesson;
    View root;
    ArrayList<ChapterLesson> chapterLessons;
    public ChapterLessonFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         root = inflater.inflate(R.layout.fragment_chapter_lesson,container,false);

         getViews();
         loadChpater();
         setListChapterLessonItemClick();
        return root;
    }

    private void getViews(){
        if(getActivity()!=null){
            ((DrawerLocker ) getActivity()).setDrawerEnabled(true);
        }
         //anh xa ListChapterLesson
        listChapterLesson = root.findViewById(R.id.listChapterLesson);
        //

    }
    private void loadChpater(){
        chapterLessons = new ArrayList<>();
        chapterLessons.add(new ChapterLesson
                (R.drawable.chapter1,"Chapter 01 - Introduction to Mobile Programming",false,1));
        chapterLessons.add(new ChapterLesson
                (R.drawable.chapter2,"Chapter 02 - Building Interactive Apps: Apps That Do Something",false,2));
        chapterLessons.add(new ChapterLesson
                (R.drawable.chapter3,"Chapter 03 - State Your Intent",false,3));
        chapterLessons.add(new ChapterLesson
                (R.drawable.chapter4,"Chapter 04 - The Activity Lifecycle: Being an Activity",false,4));
        chapterLessons.add(new ChapterLesson
                (R.drawable.chapter5,"Chapter 5 - The User Interface: Enjoy the View",true,5));
        chapterLessons.add(new ChapterLesson
                (R.drawable.chapter6,"Chapter 6 - List Views and Adapters: Getting Organized",true,6));
        chapterLessons.add(new ChapterLesson
                (R.drawable.chapter7,"Chapter 7 - Fragments: Make it Modular",true,7));


        ChapterLessonAdapter adapter = new ChapterLessonAdapter(root.getContext(),chapterLessons);
        this.listChapterLesson.setAdapter(adapter);

        adapter.areAllItemsEnabled();
        adapter.isEnabled(1);
    }

    private void setListChapterLessonItemClick(){
        listChapterLesson.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              loadLesson(chapterLessons.get(position).getIdChapter());
            }
        });
    }

    private void replaceFragment(Fragment fConv) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.frContainer, fConv, "LESSON_FRAGMENT");
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void loadLesson(int chapter_id) {
        int id = chapter_id;
        Bundle bundle = new Bundle();
        bundle.putInt("chapter_id", id);
        LessonFragment fConv = new LessonFragment();
        fConv.setArguments(bundle);
        replaceFragment(fConv);
    }

}
