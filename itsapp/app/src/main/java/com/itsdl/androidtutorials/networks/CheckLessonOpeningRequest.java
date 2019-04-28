package com.itsdl.androidtutorials.networks;

import com.google.gson.Gson;
import com.itsdl.androidtutorials.utils.Example;
import com.itsdl.androidtutorials.utils.Result;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

import okhttp3.Request;

public class CheckLessonOpeningRequest extends SeverRequest {
    public CheckLessonOpeningRequest(SeverRequestListener listener) {
        super(listener);
    }

    @Override
    protected Request prepare(Map<String, String> parameter) {
        String lesson_id = parameter.get("lesson_id");
        String user_id = parameter.get("user_id");
        return new Request.Builder()
                .url(URL + "checkLessonOpening.php?lesson_id="+lesson_id+"&user_id="+user_id)
                .get()
                .build();
    }

    @Override
    protected Object process(String data) {
        try {
            Gson gson=new Gson();
            Result res=gson.fromJson(data,Result.class);

            JSONObject json=new JSONObject(data);
            JSONArray jsonArray = json.getJSONArray("data");
            ArrayList<Integer> flag= new ArrayList<>();
            for(int i=0;i<jsonArray.length();i++){
                int check = jsonArray.getInt(i);
                flag.add(check);
            }
            res.setData(flag);
            return res;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
