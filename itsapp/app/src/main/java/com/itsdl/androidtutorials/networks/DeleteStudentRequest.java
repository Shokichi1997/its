package com.itsdl.androidtutorials.networks;

import com.google.gson.Gson;
import com.itsdl.androidtutorials.utils.Result;

import java.util.Map;

import okhttp3.Request;

public class DeleteStudentRequest extends SeverRequest {
    public DeleteStudentRequest(SeverRequestListener listener) {
        super(listener);
    }

    @Override
    protected Request prepare(Map<String, String> parameter) {
       String user_id=parameter.get("user_id");
       Request request=new Request.Builder()
               .url("delete_student.php?user_id="+user_id)
               .get()
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

        }
        return null;
    }
}
