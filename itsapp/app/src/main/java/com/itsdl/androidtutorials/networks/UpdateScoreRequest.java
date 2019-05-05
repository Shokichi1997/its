package com.itsdl.androidtutorials.networks;

import com.google.gson.Gson;
import com.itsdl.androidtutorials.utils.Result;

import java.util.Map;

import okhttp3.Request;

public class UpdateScoreRequest extends SeverRequest {

    public UpdateScoreRequest(SeverRequestListener listener) {
        super(listener);
    }

    @Override
    protected Request prepare(Map<String, String> parameter) {
        String lessonID = parameter.get("lesson_id");
        String user_id = parameter.get("user_id");
        String score = parameter.get("score");
        return new Request.Builder()
                .url(URL + "update_score.php?lesson_id="+lessonID+"&user_id="+user_id+"&score="+score)
                .get()
                .build();
    }

    @Override
    protected Object process(String data) {
        try {
            Gson gson=new Gson();
            Result res=gson.fromJson(data,Result.class);
            return res;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
