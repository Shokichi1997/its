package com.itsdl.androidtutorials.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.itsdl.androidtutorials.R;
import com.itsdl.androidtutorials.utils.Lesson;
import com.itsdl.androidtutorials.utils.LessonItems;

import java.util.HashMap;
import java.util.List;

public class LessonAdapter extends BaseExpandableListAdapter {


    private Context context;
    private List<String> listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String,List<LessonItems>> listDataChild;

    public LessonAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, List<LessonItems>> listChildData) {
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.listDataChild = listChildData;
    }

    @Override
    public int getGroupCount() {
        return listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listDataChild.get(listDataHeader.get(groupPosition)).size();

    }

    @Override
    public Object getGroup(int groupPosition) {
        return listDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
      return listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String lesson=(String) getGroup(groupPosition);
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(R.layout.list_lesson_items,null);
        TextView txtLesonName=convertView.findViewById(R.id.lblLessonName);
        txtLesonName.setText(lesson);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        LessonItems lessonItem=(LessonItems) getChild(groupPosition,childPosition);
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(R.layout.list_lesson_items_item,null);
        TextView txtLesonItemName=convertView.findViewById(R.id.lblLessonItemName);
        txtLesonItemName.setText(lessonItem.getLessonItemName());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
