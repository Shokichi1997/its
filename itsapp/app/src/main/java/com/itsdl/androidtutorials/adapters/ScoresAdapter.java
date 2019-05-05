package com.itsdl.androidtutorials.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
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
import com.itsdl.androidtutorials.networks.GetProblemRequest;
import com.itsdl.androidtutorials.networks.SeverRequest;
import com.itsdl.androidtutorials.networks.UpdateScoreRequest;
import com.itsdl.androidtutorials.utils.Result;
import com.itsdl.androidtutorials.utils.Scores;
import com.itsdl.androidtutorials.utils.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ScoresAdapter extends BaseAdapter  {

    public Context context;
    public ArrayList<Scores> scoresArrayList;


    public ScoresAdapter(Context context, ArrayList<Scores> scoresArrayList) {
        super();
        this.context = context;
        this.scoresArrayList = scoresArrayList;
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return scoresArrayList.size();
    }
    @Override
    public Object getItem(int position) {
        return scoresArrayList.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView==null){
            listItemView = LayoutInflater.from(context).inflate(R.layout.list_score_item,parent,false);
        }
        final Scores current = (Scores) getItem(position);

        ImageView imgChapterIcon = listItemView.findViewById(R.id.imgIconChapter);
        TextView lblUsername = listItemView.findViewById(R.id.lblLesonNameScore);
        lblUsername.setText(current.getLesson_name());

        final EditText edtScore =listItemView.findViewById(R.id.edtScores);
        String score=String.valueOf(current.getScore());
        edtScore.setText(score);
        final ImageButton imgSaveScore=listItemView.findViewById(R.id.imgSaveScore);
        imgSaveScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!edtScore.getText().toString().isEmpty()){
                    //thay 1=current.getUser_id()
                    updateScore(1,edtScore.getText().toString(),current.getLesson_id(),position);
                }else{
                    Toast.makeText(context,"Score invalid",Toast.LENGTH_LONG).show();
                }

            }
        });
        return listItemView;
    }



    private void updateScore(int userID, final String score, int lesson_id, final int index){
        Map<String,String> parameter=new HashMap<>();
        parameter.put("lesson_id",String.valueOf(lesson_id));
        parameter.put("user_id",String.valueOf(userID));
        parameter.put("score",String.valueOf(score));
        UpdateScoreRequest request=new UpdateScoreRequest(new SeverRequest.SeverRequestListener() {
            @Override
            public void completed(Object obj) {
                if(obj!=null){
                    Result res=(Result) obj;
                    if(res.getError()==0){
                        //Sucdess
                       // scoresArrayList.remove(index);
                        notifyDataSetChanged();
                        Toast.makeText(context,"Success",Toast.LENGTH_LONG).show();
                        for(int i=0;i<scoresArrayList.size();i++){
                            if(i==index){
                                //xu ly ca nhat giao dien
                                scoresArrayList.get(i).setScore(Double.parseDouble(score));
                                return;
                            }
                        }

                    }else{
                        //Delete student error
                        Toast.makeText(context,"Update error",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        request.execute(parameter);
    }


}
