package com.itsdl.androidtutorials.networks;

import com.google.gson.Gson;
import com.itsdl.androidtutorials.utils.Answer;
import com.itsdl.androidtutorials.utils.Question;
import com.itsdl.androidtutorials.utils.Result;
import com.itsdl.androidtutorials.utils.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

import okhttp3.Request;

public class GetListStudentRequest extends SeverRequest {
    public GetListStudentRequest(SeverRequestListener listener) {
        super(listener);
    }
    @Override
    protected Request prepare(Map<String, String> parameter) {
       Request request=new Request.Builder()
               .url(URL+"get_list_student.php")
               .get()
               .addHeader("Content-Type", "application/json")
               .build();
       return  request;
    }
    @Override
    protected Object process(String data) {
        try{
            Gson gson=new Gson();
            Result res=gson.fromJson(data,Result.class);
            JSONObject json=new JSONObject(data);
          //  JSONObject object= json.getJSONObject(data);
            JSONArray jsonArray = json.getJSONArray("data");
            ArrayList<User> arr_users=new ArrayList<>();
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonUser=jsonArray.getJSONObject(i);
                User us = gson.fromJson(String.valueOf(jsonUser),User.class);
                arr_users.add(us);
            }
            res.setData(arr_users);
            return res;
        }catch(Exception e) {
            return  null;
        }
    }
}
