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

import java.util.ArrayList;
import java.util.List;

public class LessonAdapter extends BaseExpandableListAdapter {


    private Context context;
    private List<Lesson> groups;

    public LessonAdapter(Context context, ArrayList<Lesson> groups){
        this.context = context;
        this.groups = groups;
    }

    public void addItem(LessonItems item, Lesson group) {
        if (!groups.contains(group)) {
            groups.add(group);
        }
        int index = groups.indexOf(group);
        ArrayList<LessonItems> ch = groups.get(index).getLesson_item_list();
        ch.add(item);
        groups.get(index).setLesson_item_list(ch);
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        ArrayList<LessonItems> chList = groups.get(groupPosition).getLesson_item_list();
        return chList.get(childPosition);
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
        Lesson group = (Lesson ) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(R.layout.list_lesson_items, null);
        }
        TextView txtLesonName=convertView.findViewById(R.id.lblLessonName);
        txtLesonName.setText(group.getLesson_name());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        LessonItems child = ( LessonItems ) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_lesson_items_item, null);
        }
        TextView txtLesonItemName=convertView.findViewById(R.id.lblLessonItemName);
        txtLesonItemName.setText(child.getLessonItemName());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public int getChildrenCount(int groupPosition) {
        ArrayList<LessonItems> chList = groups.get(groupPosition).getLesson_item_list();
        return chList.size();
    }

}