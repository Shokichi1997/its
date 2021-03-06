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
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.itsdl.androidtutorials.R;
import com.itsdl.androidtutorials.activities.MainActivity;
import com.itsdl.androidtutorials.fragments.ScoresFragment;
import com.itsdl.androidtutorials.fragments.UserProfileFragment;
import com.itsdl.androidtutorials.networks.DeleteStudentRequest;
import com.itsdl.androidtutorials.networks.SeverRequest;
import com.itsdl.androidtutorials.utils.ChapterLesson;
import com.itsdl.androidtutorials.utils.Result;
import com.itsdl.androidtutorials.utils.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class UserAdapter extends BaseAdapter implements Filterable {

    public Context context;
    public ArrayList<User> userArrayList;
    public ArrayList<User> orig;

    public UserAdapter(Context context, ArrayList<User> userArrayList) {
        super();
        this.context = context;
        this.userArrayList = userArrayList;
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return userArrayList.size();
    }
    @Override
    public Object getItem(int position) {
        return userArrayList.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView==null){
            listItemView = LayoutInflater.from(context).inflate(R.layout.list_student_items,parent,false);
        }
        final User currentStudent = (User) getItem(position);
        ImageView imgChapterIcon = listItemView.findViewById(R.id.imgIconChapter);
        TextView lblUsername = listItemView.findViewById(R.id.lblUserName);
        lblUsername.setText(currentStudent.getFull_name());
        final ImageButton showpopup=listItemView.findViewById(R.id.imgShowPopup);
        showpopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopupMenu popup = new PopupMenu(context, showpopup);
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        int i = item.getItemId();
                        if (i == R.id.update) {
                            //do something
                            gotoUserProfileFragment(currentStudent);
                            return true;
                        }
                        else if (i == R.id.delete){
                            //do something
                            deleteStudent(currentStudent.getUser_id(),position);
                            return true;
                        }
                        else if (i == R.id.viewprofile) {
                            //do something
                            gotoScoresFragment(currentStudent);
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
    @Override
 //USER SEARCH VIEW
    public Filter getFilter() {
     return new Filter() {

         @Override
         protected FilterResults performFiltering(CharSequence constraint) {
             final FilterResults oReturn = new FilterResults();
             final ArrayList<User> results = new ArrayList<User>();
             if (orig == null)
                 orig = userArrayList;
             if (constraint != null) {
                 if (orig != null && orig.size() > 0) {
                     for (final User g : orig) {
                         if (g.getFull_name().toLowerCase()
                                 .contains(constraint.toString().toLowerCase()))
                             results.add(g);
                     }
                 }
                 oReturn.values = results;
             }
             return oReturn;
         }

         @SuppressWarnings("unchecked")
         @Override
         protected void publishResults(CharSequence constraint,
                                       FilterResults results) {
             userArrayList = (ArrayList<User>) results.values;
             notifyDataSetChanged();
         }
     };
    }
  // FUNCTION ADD
    private void replaceFragment(Fragment fConv) {
      MainActivity myActivity=(MainActivity)context;
      FragmentManager manager = myActivity.getSupportFragmentManager();
      FragmentTransaction transaction = manager.beginTransaction();
      transaction.replace(R.id.frContainer, fConv,"Details");
      transaction.addToBackStack(null);
      transaction.commit();
  }

    private void gotoScoresFragment(User currentChapterLesson){
        User us = currentChapterLesson;
        Bundle bundle = new Bundle();
        bundle.putInt("UserID", us.getUser_id());
        bundle.putString("FullName",us.getFull_name());
        bundle.putString("Email",us.getEmail());
        ScoresFragment fConv = new ScoresFragment();
        fConv.setArguments(bundle);
        replaceFragment(fConv);
    }
    private void gotoUserProfileFragment(User currentChapterLesson){
        Toast.makeText(context,"Click me",Toast.LENGTH_SHORT).show();
        User us = currentChapterLesson;
        Bundle bundle = new Bundle();
        bundle.putInt("UserID", us.getUser_id());
        bundle.putString("FullName",us.getFull_name());
        bundle.putString("Email",us.getEmail());
        UserProfileFragment fConv = new UserProfileFragment();
        fConv.setArguments(bundle);
        replaceFragment(fConv);
    }
    private void deleteStudent(int userID, final int index){
        Map<String,String> parameter=new HashMap<>();
        parameter.put("user_id",String.valueOf(userID));

        DeleteStudentRequest request=new DeleteStudentRequest(new SeverRequest.SeverRequestListener() {
            @Override
            public void completed(Object obj) {
                if(obj!=null){
                    Result res=(Result) obj;
                    if(res.getError()==0){
                        //Sucdess
                        userArrayList.remove(index);
                        notifyDataSetChanged();
                        Toast.makeText(context,"Success",Toast.LENGTH_LONG).show();

                    }else{
                        //Delete student error
                        Toast.makeText(context,"Delete error",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        request.execute(parameter);
    }
}
