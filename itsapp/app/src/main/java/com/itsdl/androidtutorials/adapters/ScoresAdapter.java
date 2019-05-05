package com.itsdl.androidtutorials.adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
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
import com.itsdl.androidtutorials.utils.CustomDialog;
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

        TextView lblUsername = listItemView.findViewById(R.id.lblLesonNameScore);
        lblUsername.setText(current.getLesson_name());

        ImageButton btnSub = listItemView.findViewById(R.id.btnSubScore);
        ImageButton btnAdd = listItemView.findViewById(R.id.btnAddScore);

        final EditText edtScore = listItemView.findViewById(R.id.edtScore);
        edtScore.setText("0");
        edtScore.setEnabled(false);

        TextView lblScore = listItemView.findViewById(R.id.txtScores);
        String score = String.valueOf(current.getScore());
        lblScore.setText(score);

        final ImageButton imgSaveScore=listItemView.findViewById(R.id.imgSaveScore);
        imgSaveScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!edtScore.getText().toString().isEmpty()){
                    //thay 1=current.getUser_id()
                    final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Are you sure to want to change this student's score?")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    updateScore(1,edtScore.getText().toString(),current.getLesson_id(),position);
                                }
                            })
                            .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                }
                            });
                    // Create the AlertDialog object and return it
                    Dialog dialog = builder.create();
                    dialog.show();
                }else{
                    Toast.makeText(context,"Score invalid",Toast.LENGTH_LONG).show();
                }

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int score = Integer.parseInt(edtScore.getText().toString());
                if(score < 5){
                    edtScore.setText(String.valueOf(++score));
                }
            }
        });

        btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int score = Integer.parseInt(edtScore.getText().toString());
                if(score > -5){
                    edtScore.setText(String.valueOf(--score));
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
                                double sco = scoresArrayList.get(i).getScore();
                                sco += Double.parseDouble(score);
                                scoresArrayList.get(i).setScore(sco);
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
