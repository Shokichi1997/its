package com.itsdl.androidtutorials.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.itsdl.androidtutorials.fragments.ChapterLessonFragment;
import com.itsdl.androidtutorials.fragments.ContentLessonFragment;
import com.itsdl.androidtutorials.fragments.ExampleLessonFragment;
import com.itsdl.androidtutorials.fragments.TestLessonFragment;

public class ContentMainAdapter extends FragmentStatePagerAdapter {
    private static final int NUM_PAGES = 2;
    public ContentMainAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        if(i==0){
            return new ChapterLessonFragment();
        }
        else{
            return new ExampleLessonFragment();
        }
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title;
        if(position==0){
            title = "Chapters";
        }
        else{
            title = "Examples";
        }
        return title;
    }
}
