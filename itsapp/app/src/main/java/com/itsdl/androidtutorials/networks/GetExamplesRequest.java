package com.itsdl.androidtutorials.networks;

import android.util.Base64;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.itsdl.androidtutorials.utils.Answer;
import com.itsdl.androidtutorials.utils.Example;
import com.itsdl.androidtutorials.utils.Question;
import com.itsdl.androidtutorials.utils.Result;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

import okhttp3.Request;

public class GetExamplesRequest extends SeverRequest {
    public GetExamplesRequest(SeverRequestListener listener) {
        super(listener);
    }

    @Override
    protected Request prepare(Map<String, String> parameter) {
        return new Request.Builder()
                .url(URL + "getExamples.php")
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
            ArrayList<Example> examples=new ArrayList<>();
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonExample=jsonArray.getJSONObject(i);
                Example example = gson.fromJson(String.valueOf(jsonExample),Example.class);
                examples.add(example);
            }
            res.setData(examples);
            return res;

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
