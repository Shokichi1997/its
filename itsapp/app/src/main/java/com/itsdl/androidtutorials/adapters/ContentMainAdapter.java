package com.itsdl.androidtutorials.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.itsdl.androidtutorials.fragments.ContentLessonFragment;
import com.itsdl.androidtutorials.fragments.ExampleLessonFragment;
import com.itsdl.androidtutorials.fragments.TestLessonFragment;

public class ContentMainAdapter extends FragmentStatePagerAdapter {
    private static final int NUM_PAGES = 3;
    public ContentMainAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        if(i==0){
            return new ContentLessonFragment();
        }
        else if(i==1){
            return new ExampleLessonFragment();
        }
        else return new TestLessonFragment();
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
            title = "Lesson";
        }
        else if(position == 1){
            title = "Example";
        }
        else {
            title = "Quiz";
        }
        return title;
    }
}
