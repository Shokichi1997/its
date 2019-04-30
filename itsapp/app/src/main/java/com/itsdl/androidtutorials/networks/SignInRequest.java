package com.itsdl.androidtutorials.networks;


import com.google.gson.Gson;
import com.itsdl.androidtutorials.utils.ProfileUser;
import com.itsdl.androidtutorials.utils.Result;
import com.itsdl.androidtutorials.utils.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by nguyenhuyphuc on 11/2/18.
 */

public class SignInRequest extends SeverRequest {
    public SignInRequest(SeverRequestListener listener) {
        super(listener);
    }

    @Override
    protected Request prepare(Map<String, String> parameter) {
        String user = parameter.get("student_code");
        String password = parameter.get("password");


        RequestBody requestBody = new MultipartBody.Builder()
                .addFormDataPart("student_code", user)
                .addFormDataPart("password", password)
                .setType(MultipartBody.FORM)
                .build();
        Request request = new Request.Builder()
                .url(URL + "sign_in.php")
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
            JSONObject json = null;
            json = new JSONObject(data);
            JSONObject object= json.getJSONObject("data"); //Có thể bị Value null at data
            if(object!=null){
                ProfileUser profileUser=ProfileUser.getInstance();
                profileUser.setFull_name(object.getString("full_name"));
                // profileUser.setDate_create(object.getStr());
                profileUser.setEmail(object.getString("email"));
                profileUser.setUser_id(object.getInt("user_id"));
                profileUser.setStudent_code(object.getString("student_code"));
                profileUser.setRole(object.getInt("role"));
                profileUser.setPassword(object.getString("password"));
                res.setData(profileUser);
            }
            else{
                res.setData(null);
            }
            return res;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
