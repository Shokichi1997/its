package com.itsdl.androidtutorials.networks;

import com.google.gson.Gson;
import com.itsdl.androidtutorials.utils.ChapterLesson;
import com.itsdl.androidtutorials.utils.Result;
import com.itsdl.androidtutorials.utils.Scores;
import com.itsdl.androidtutorials.utils.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

import okhttp3.Request;

public class ReadScoreRequest extends SeverRequest {

    public ReadScoreRequest(SeverRequestListener listener) {
        super(listener);
    }

    @Override
    protected Request prepare(Map<String, String> parameter) {
        String user_id = parameter.get("user_id");
        return new Request.Builder()
                .url(URL + "read_sorce.php?user_id="+user_id)
                .get()
                .build();
    }

    @Override
    protected Object process(String data) {
        try{
            Gson gson=new Gson();
            Result res=gson.fromJson(data,Result.class);
            JSONObject json=new JSONObject(data);
            //  JSONObject object= json.getJSONObject(data);
            JSONArray jsonArray = json.getJSONArray("data");
            ArrayList<Scores> arr_scores=new ArrayList<>();
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonScore=jsonArray.getJSONObject(i);
                Scores us = gson.fromJson(String.valueOf(jsonScore),Scores.class);
                arr_scores.add(us);
            }
            res.setData(arr_scores);
            return res;
        }catch(Exception e) {
            return  null;
        }
    }
}
