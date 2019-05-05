package com.itsdl.androidtutorials.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import com.itsdl.androidtutorials.R;
import com.itsdl.androidtutorials.adapters.ScoresAdapter;
import com.itsdl.androidtutorials.adapters.UserAdapter;
import com.itsdl.androidtutorials.networks.GetListStudentRequest;
import com.itsdl.androidtutorials.networks.ReadScoreRequest;
import com.itsdl.androidtutorials.networks.SeverRequest;
import com.itsdl.androidtutorials.utils.DrawerLocker;
import com.itsdl.androidtutorials.utils.Result;
import com.itsdl.androidtutorials.utils.Scores;
import com.itsdl.androidtutorials.utils.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScoresFragment extends Fragment {

    View root;
    Toolbar toolbar;
    int user_id=-1;
    ListView listScoresStudent;
    ScoresAdapter adapter;
    ArrayList<Scores> arrScores=null;
    public ScoresFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root= inflater.inflate(R.layout.fragment_scores, container, false);
        getViews();
        Bundle args = getArguments();
        if(args!=null && args.containsKey("UserID")){
           user_id=(args.getInt("UserID"));
        }
        arrScores=new ArrayList<>();
        getlistStudent();
        return root;
    }
    private void getViews() {
        if(getActivity()!=null){
            ((DrawerLocker) getActivity()).setDrawerEnabled(true);
        }
        //add tool bar
         toolbar = root.findViewById(R.id.score_toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Student Scores");
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getFragmentManager().getBackStackEntryCount() > 0) {
                    getFragmentManager().popBackStack();
                }
            }
        });
        //
        listScoresStudent=root.findViewById(R.id.listScoresStudent);
    }
    public void loadScores(ArrayList<Scores>data){

            //load danhs sach hoc vien vafo list view
            adapter = new ScoresAdapter(root.getContext(),data);
            listScoresStudent.setAdapter(adapter);
            listScoresStudent.setTextFilterEnabled(true);
            // event listviewStudent item click
    }

    //get lít student
    private void getlistStudent(){
        Map<String,String> parameter=new HashMap<>();
        parameter.put("user_id",String.valueOf(user_id));
        ReadScoreRequest request=new ReadScoreRequest(new SeverRequest.SeverRequestListener() {
            @Override
            public void completed(Object obj) {
                Result res=(Result) obj;
                if(res.getError()==0){
                    final ArrayList<Scores>  arr_scores=(ArrayList<Scores>) res.getData();
                    loadScores(arr_scores);
                }else{
                }
            }
        });
        request.execute(parameter);
    }

}
