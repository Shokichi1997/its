package com.itsdl.androidtutorials.networks;

import com.google.gson.Gson;
import com.itsdl.androidtutorials.utils.Answer;
import com.itsdl.androidtutorials.utils.ChapterLesson;
import com.itsdl.androidtutorials.utils.Question;
import com.itsdl.androidtutorials.utils.Result;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

import okhttp3.Request;

public class GetProblemRequest extends SeverRequest {
    public GetProblemRequest(SeverRequestListener listener) {
        super(listener);
    }

    @Override
    protected Request prepare(Map<String, String> parameter) {
        String lessonID = parameter.get("lessonID");
        return new Request.Builder()
                .url(URL + "getProblem.php?lesson_id="+lessonID)
                .get()
                .build();
    }

    @Override
    protected Object process(String data) {
        try {
            Gson gson=new Gson();
            Result res=gson.fromJson(data,Result.class);

            JSONObject json=new JSONObject(data);
            JSONObject object= json.getJSONObject("data");
            Question question = gson.fromJson(String.valueOf(object), Question.class);
            JSONArray jsonArray = object.getJSONArray("answers");
            ArrayList<Answer> answers=new ArrayList<>();
                for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonAnswer=jsonArray.getJSONObject(i);
                Answer answer = gson.fromJson(String.valueOf(jsonAnswer),Answer.class);
                answers.add(answer);
            }
            question.setAnswers(answers);
            res.setData(question);
            return res;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
