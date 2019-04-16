package com.itsdl.androidtutorials.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.itsdl.androidtutorials.R;
import com.itsdl.androidtutorials.utils.FunctionUser;
import com.itsdl.androidtutorials.utils.Lesson;

import java.util.ArrayList;

public class SpinnerLessonAdapter extends ArrayAdapter<Lesson> {
    public SpinnerLessonAdapter(@NonNull Context context, @NonNull ArrayList<Lesson> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView==null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.grid_function_item,parent,false);
        }

        Lesson currentLesson = getItem(position);




        return listItemView;
    }
}
