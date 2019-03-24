package com.itsdl.androidtutorials.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.itsdl.androidtutorials.R;
import com.itsdl.androidtutorials.utils.FunctionUser;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Subject;

public class Functions_adapter extends ArrayAdapter<FunctionUser> {


    public Functions_adapter(@NonNull Context context, @NonNull ArrayList<FunctionUser> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView==null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.grid_function_item,parent,false);
        }

        FunctionUser currentFunctionUser = getItem(position);
        ImageView imgFUIcon = listItemView.findViewById(R.id.imgFunctionItem);
        if(currentFunctionUser.hasImage()){
            imgFUIcon.setImageResource(currentFunctionUser.getImageResourceId());
        }
        else {
            imgFUIcon.setVisibility(View.GONE);
        }

        TextView lblNameFU = listItemView.findViewById(R.id.lblFunctionNameItem);
        lblNameFU.setText(currentFunctionUser.getFunction_name());


        return listItemView;
    }
}
