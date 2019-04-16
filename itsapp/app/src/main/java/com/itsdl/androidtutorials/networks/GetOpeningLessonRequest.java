package com.itsdl.androidtutorials.networks;

import com.google.gson.Gson;
import com.itsdl.androidtutorials.utils.Lesson;
import com.itsdl.androidtutorials.utils.Result;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

import okhttp3.Request;

public class GetOpeningLessonRequest extends SeverRequest {
    public GetOpeningLessonRequest(SeverRequestListener listener) {
        super(listener);
    }

    @Override
    protected Request prepare(Map<String, String> parameter) {
        String user_id = parameter.get("user_id");
        return new Request.Builder()
                .url(URL + "getOpeningLesson.php?user_id="+user_id)
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
            ArrayList<Lesson> lessons=new ArrayList<>();
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonLesson=jsonArray.getJSONObject(i);
                Lesson lesson = gson.fromJson(String.valueOf(jsonLesson),Lesson.class);
                lessons.add(lesson);
            }
            res.setData(lessons);
            return res;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
