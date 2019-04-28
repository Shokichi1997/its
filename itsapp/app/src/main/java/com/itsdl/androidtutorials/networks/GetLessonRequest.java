package com.itsdl.androidtutorials.networks;

import com.google.gson.Gson;
import com.itsdl.androidtutorials.utils.Lesson;
import com.itsdl.androidtutorials.utils.LessonItems;
import com.itsdl.androidtutorials.utils.Result;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

public class GetLessonRequest extends SeverRequest {
    public GetLessonRequest(SeverRequestListener listener) {
        super(listener);
    }
    @Override
    protected Request prepare(Map<String, String> parameter) {
        String chapter_id = parameter.get("chapter_id");
        String user_id = parameter.get("user_id");
        RequestBody requestBody = new MultipartBody.Builder()
                .addFormDataPart("chapter_id", chapter_id)
                .addFormDataPart("user_id",user_id)
                .setType(MultipartBody.FORM)
                .build();
        Request request = new Request.Builder()
                .url("https://androidtutorialsits.herokuapp.com/api/test.php")
                .post(requestBody)
                .addHeader("Content-Type", "application/json")
                .build();
        return request;
    }
    @Override
    protected Object process(String data) {
        try {
            Gson gson=new Gson();
            Result res=gson.fromJson(data,Result.class);

            JSONObject json=new JSONObject(data);
            JSONArray jsonArray = json.getJSONArray("data");
            ArrayList<Lesson> lessons =new ArrayList<>();
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonLesson =jsonArray.getJSONObject(i);
                Lesson lesson = gson.fromJson(String.valueOf(jsonLesson),Lesson.class);
                JSONArray lessonItemJson =  jsonLesson.getJSONArray("lesson_item_list");
                ArrayList<LessonItems> lessonItemList = new ArrayList<>();
                for(int j=0;j<lessonItemJson.length();j++){
                    JSONObject jsonLessonItem =lessonItemJson.getJSONObject(j);
                    LessonItems lessonItems = gson.fromJson(String.valueOf(jsonLessonItem),LessonItems.class);
                    lessonItemList.add(lessonItems);
                }
                lesson.setLesson_item_list(lessonItemList);
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
