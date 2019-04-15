package com.itsdl.androidtutorials.networks;

import com.google.gson.Gson;
import com.itsdl.androidtutorials.utils.Result;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

public class UpdateStudentRequest extends SeverRequest {
    public UpdateStudentRequest(SeverRequestListener listener) {
        super(listener);
    }

    @Override
    protected Request prepare(Map<String, String> parameter) {
        String user_id=parameter.get("user_id");
        String full_name=parameter.get("full_name");
        String email=parameter.get("email");
        String password=parameter.get("password");

        RequestBody body =new MultipartBody.Builder()
                .addFormDataPart("user_id",user_id)
                .addFormDataPart("password",password)
                .addFormDataPart("email",email)
                .addFormDataPart("full_name",full_name)
                .setType(MultipartBody.FORM)
                .build();
         Request request =new Request.Builder()
                 .url("update_user_info.php")
                 .post(body)
                 .addHeader("Content-Type", "application/json")
                 .build();

        return null;
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
