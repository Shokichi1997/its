package com.itsdl.androidtutorials.networks;

import com.google.gson.Gson;
import com.itsdl.androidtutorials.utils.Result;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

public class AddStudentRequest extends SeverRequest {
    public AddStudentRequest(SeverRequestListener listener) {
        super(listener);
    }

    @Override
    protected Request prepare(Map<String, String> parameter) {
       String student_code=parameter.get("student_code");
       String email       =parameter.get("email");
        RequestBody body =new MultipartBody.Builder()
                .addFormDataPart("student_code",student_code)
                .addFormDataPart("email",email)
                .setType(MultipartBody.FORM)
                .build();
        Request request =new Request.Builder()
                .url(URL+"add_student.php")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .build();
        return request;
    }

    @Override
    protected Object process(String data) {
        try{
            Gson gson=new Gson();
            Result res=gson.fromJson(data,Result.class);
            return  res;
        }catch (Exception e){
            return  null;
        }
    }
}
