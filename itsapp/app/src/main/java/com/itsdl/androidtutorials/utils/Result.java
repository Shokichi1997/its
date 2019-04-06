package com.itsdl.androidtutorials.utils;
import com.google.gson.annotations.SerializedName;
public class Result {
    @SerializedName("error")
    private int error;
    @SerializedName("message")
    private String message;
    @SerializedName("data")
    private Object data;

    public Result(int error, String message, Object data) {
        this.error = error;
        this.message = message;
        this.data = data;
    }

    public int getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public void setError(int error) {
        this.error = error;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
