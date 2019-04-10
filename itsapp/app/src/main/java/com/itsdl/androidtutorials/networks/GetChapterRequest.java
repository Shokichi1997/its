package com.itsdl.androidtutorials.networks;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.itsdl.androidtutorials.utils.ChapterLesson;
import com.itsdl.androidtutorials.utils.Result;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

import okhttp3.Request;

public class GetChapterRequest extends SeverRequest {
    public GetChapterRequest(SeverRequestListener listener) {
        super(listener);
    }

    @Override
    protected Request prepare(Map<String, String> parameter) {
      Request request= new Request.Builder()
              .url("get_chapter.php")
              .get()
              .build();
      return  request;
    }

    @Override
    protected Object process(String data) {
        try {

            JSONObject json=new JSONObject(data);
            ArrayList<ChapterLesson> arrChapter=new ArrayList<ChapterLesson>();
            JSONArray array_chapter=json.getJSONArray("data") ;
            for(int i=0;i<array_chapter.length();i++){
                JSONObject ob=array_chapter.getJSONObject(i);
                arrChapter.add(new ChapterLesson(
                            0,
                             ob.getString("chapter_id"),
                            false,
                             ob.getInt("chapter_id")

                    ));
            }
            return  arrChapter;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
