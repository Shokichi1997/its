package com.itsdl.androidtutorials.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

import java.util.ArrayList;

public class ChapterLessonFragment extends Fragment {
    private ListView listChapterLesson;
    private Toolbar toolbar;

    public ChapterLessonFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_chapter_lesson,container,false);

        toolbar = root.findViewById(R.id.chapter_lesson_toolbar);
        ((AppCompatActivity )getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Chapter Lesson");
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listChapterLesson = root.findViewById(R.id.listChapterLesson);
        ArrayList<ChapterLesson> chapterLessons = new ArrayList<>();
        chapterLessons.add(new ChapterLesson
                (R.drawable.ic_mood_black_24dp,"Android Introduce",false,1));
        chapterLessons.add(new ChapterLesson
                (R.drawable.ic_menu_slideshow,"Fragment",false,1));
        chapterLessons.add(new ChapterLesson
                (R.drawable.ic_mood_black_24dp,"Activity",false,2));
        chapterLessons.add(new ChapterLesson
                (R.drawable.ic_menu_24dp,"Animation",false,3));
        chapterLessons.add(new ChapterLesson
                (R.drawable.ic_mood_black_24dp,"Menu context",true,4));
        chapterLessons.add(new ChapterLesson
                (R.drawable.ic_mood_black_24dp,"Network",true,5));
        chapterLessons.add(new ChapterLesson
                (R.drawable.ic_mood_black_24dp,"Interface",true,6));
        chapterLessons.add(new ChapterLesson
                (R.drawable.ic_mood_black_24dp,"Layout",true,7));
        chapterLessons.add(new ChapterLesson
                (R.drawable.ic_mood_black_24dp,"Services",true,8));

        ChapterLessonAdapter adapter = new ChapterLessonAdapter(root.getContext(),chapterLessons);
        this.listChapterLesson.setAdapter(adapter);

        adapter.areAllItemsEnabled();
        adapter.isEnabled(1);
        return root;
    }
}
