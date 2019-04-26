package com.itsdl.androidtutorials.networks;

import com.google.gson.JsonObject;
import com.itsdl.androidtutorials.utils.ChapterLesson;
import com.itsdl.androidtutorials.utils.Lesson;
import com.itsdl.androidtutorials.utils.LessonItems;

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
                .url("https://androidtutorialsits.herokuapp.com/api/get_lesson.php")
                .post(requestBody)
                .addHeader("Content-Type", "application/json")
                .build();
        return request;
    }
    @Override
    protected Object process(String data) {
        try {
            ArrayList<Object> arrData=new ArrayList<Object>();
            ArrayList<Lesson> arr_lesson=new ArrayList<Lesson>();
            ArrayList<LessonItems> arr_items=new ArrayList<LessonItems>();
            JSONObject json=new JSONObject(data);

            JSONArray array_data=json.getJSONArray("data") ;
            for(int i=0;i<array_data.length();i++){
                JSONArray ob=array_data.getJSONArray(i);
                for(int j=0;j<ob.length();j++){
                    if (i == 0) {
                        JSONObject l=ob.getJSONObject(j);
                        Lesson ls=new Lesson(
                                l.getInt("chapter_id"),
                                l.getInt("lesson_id"),
                               "",
                                l.getString("lesson_name")
                        );
                        arr_lesson.add(ls);
                    }
                    if(i==1){
                        JSONObject l=ob.getJSONObject(j);
                        LessonItems les_items=new LessonItems(
                                l.getInt("lesson_id"),
                                l.getInt("lesson_item_id"),
                                l.getString("lesson_item_name")
                        );
                        arr_items.add(les_items);
                    }
                }

            }
            arrData.add(arr_lesson);
            arrData.add(arr_items);
            return  arrData;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
