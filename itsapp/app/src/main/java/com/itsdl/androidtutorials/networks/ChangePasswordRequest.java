package com.itsdl.androidtutorials.networks;

import com.google.gson.Gson;
import com.itsdl.androidtutorials.utils.Result;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

public class ChangePasswordRequest extends SeverRequest {
    public ChangePasswordRequest(SeverRequestListener listener) {
        super(listener);
    }

    @Override
    protected Request prepare(Map<String, String> parameter) {
        String user_id         = parameter.get("user_id");
        String password_new    = parameter.get("password_new");


        RequestBody body =new MultipartBody.Builder()
                .addFormDataPart("user_id",user_id)
                .addFormDataPart("password_new",password_new)

                .setType(MultipartBody.FORM)
                .build();
        Request request =new Request.Builder()
                .url(URL+"change_password.php")
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
