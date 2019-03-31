package com.itsdl.androidtutorials.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.itsdl.androidtutorials.R;
import com.itsdl.androidtutorials.utils.ChapterLesson;

import java.util.List;

public class ChapterLessonAdapter extends ArrayAdapter<ChapterLesson> {
    public ChapterLessonAdapter(@NonNull Context context, @NonNull List<ChapterLesson> objects) {
        super(context, 0, objects);
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return super.isEnabled(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView==null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_chapter_lesson_item,parent,false);
        }

        ChapterLesson currentChapterLesson = getItem(position);

        ImageView imgChapterIcon = listItemView.findViewById(R.id.imgIconChapter);
        if(currentChapterLesson.hasImage()){
            imgChapterIcon.setImageResource(currentChapterLesson.getIconChapter());
        }
        else {
            imgChapterIcon.setVisibility(View.GONE);
        }

        TextView lblFunctionNameItem = listItemView.findViewById(R.id.lblChapterName);
        lblFunctionNameItem.setText(currentChapterLesson.getNameChapter());

        boolean isLock = currentChapterLesson.isLock();
        ImageView imgLockChapter = listItemView.findViewById(R.id.imgLockChapter);
        if(isLock) {
            imgLockChapter.setImageResource(R.drawable.ic_lock_black_24dp);
        }//isLock
        else{
            imgLockChapter.setImageResource(R.drawable.ic_lock_open_black_24dp);
        }

        return listItemView;
    }
}
