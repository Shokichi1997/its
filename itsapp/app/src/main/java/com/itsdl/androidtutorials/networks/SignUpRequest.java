package com.itsdl.androidtutorials.networks;
import com.google.gson.Gson;
import com.itsdl.androidtutorials.utils.Result;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SignUpRequest extends SeverRequest {
    public SignUpRequest(SeverRequestListener listener) {
        super(listener);
    }

    @Override
    protected Request prepare(Map<String, String> parameter) {

        String student_code = parameter.get("student_code");
        Request request = new Request.Builder()
                .url(URL + "add_student.php?student_code="+student_code)
                .get()
                .addHeader("Content-Type", "application/json")
                .build();
        return request;
    }

    @Override
    protected Object process(String data) {
        try {
            Gson gson=new Gson();
            Result res=gson.fromJson(data,Result.class);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
