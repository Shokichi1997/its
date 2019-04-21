package com.itsdl.androidtutorials.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.itsdl.androidtutorials.R;
import com.itsdl.androidtutorials.activities.MainActivity;
import com.itsdl.androidtutorials.fragments.UserProfileFragment;
import com.itsdl.androidtutorials.networks.DeleteStudentRequest;
import com.itsdl.androidtutorials.networks.SeverRequest;
import com.itsdl.androidtutorials.utils.ChapterLesson;
import com.itsdl.androidtutorials.utils.Result;
import com.itsdl.androidtutorials.utils.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserAdapter extends ArrayAdapter<User> implements Filterable {
    ValueFilter valueFilter;
    List<User> mStringFilterList;
    List<User> mData;
    public UserAdapter(@NonNull Context context, @NonNull List<User> objects) {
        super(context, 0, objects);
        mData=objects;
        mStringFilterList=objects;
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
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_student_items,parent,false);
        }
        final User currentStudent = getItem(position);
        ImageView imgChapterIcon = listItemView.findViewById(R.id.imgIconChapter);
        TextView lblUsername = listItemView.findViewById(R.id.lblUserName);
        lblUsername.setText(currentStudent.getFull_name());
        final ImageButton showpopup=listItemView.findViewById(R.id.imgShowPopup);
        showpopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* PopupMenu popup = new PopupMenu(getContext(), v);
                //  popup.setOnMenuItemClickListener();
                popup.inflate(R.menu.popup_menu);*/
                final PopupMenu popup = new PopupMenu(getContext(), showpopup);
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        int i = item.getItemId();
                        if (i == R.id.update) {
                            //do something
                            function(currentStudent);
                            return true;
                        }
                        else if (i == R.id.delete){
                            //do something
                            deleteStudent(currentStudent.getUser_id());
                            return true;
                        }
                        else if (i == R.id.viewprofile) {
                            //do something
                            function(currentStudent);
                            return true;
                        }
                        else {
                            return onMenuItemClick(item);
                        }
                    }
                });
                popup.show();
            }
        });
        return listItemView;
    }

    private void replaceFragment(Fragment fConv) {
         MainActivity myActivity=(MainActivity) getContext();
            FragmentManager manager = myActivity.getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.frContainer, fConv,"Details");
            transaction.addToBackStack(null);
            transaction.commit();
    }

    private void function(User currentChapterLesson){
        Toast.makeText(getContext(),"Click me",Toast.LENGTH_SHORT).show();
        User us = currentChapterLesson;
        Bundle bundle = new Bundle();
        bundle.putInt("UserID", us.getUser_id());
        bundle.putString("FullName",us.getFull_name());
        bundle.putString("Email",us.getEmail());
        UserProfileFragment fConv = new UserProfileFragment();
        fConv.setArguments(bundle);
        replaceFragment(fConv);
    }

    private void deleteStudent(int userID){
         Map<String,String> parameter=new HashMap<>();
         parameter.put("user_id",String.valueOf(userID));

         DeleteStudentRequest request=new DeleteStudentRequest(new SeverRequest.SeverRequestListener() {
             @Override
             public void completed(Object obj) {
                 if(obj!=null){
                     Result res=(Result) obj;
                     if(res.getError()==0){
                         //Sucdess

                         Toast.makeText(getContext(),"Success",Toast.LENGTH_LONG).show();
                     }else{
                         //Delete student error
                         Toast.makeText(getContext(),"Delete error",Toast.LENGTH_LONG).show();
                     }
                 }
             }
         });
         request.execute(parameter);
     }

    @Override
    public int getCount() {
        //tra ve so view duoc tao
        return super.getCount();
    }


    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                List<User> filterList = new ArrayList<>();
                for (int i = 0; i < mStringFilterList.size(); i++) {
                    if ((mStringFilterList.get(i).getFull_name().toUpperCase()).contains(constraint.toString().toUpperCase())) {
                        filterList.add(mStringFilterList.get(i));
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = mStringFilterList.size();
                results.values = mStringFilterList;
            }
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            mData = (List<User>) results.values;
            notifyDataSetChanged();
        }

    }
}
