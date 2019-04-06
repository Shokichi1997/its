package com.itsdl.androidtutorials.networks;

import java.util.Map;

import okhttp3.Request;

public class GetLessonRequest extends SeverRequest {
    public GetLessonRequest(SeverRequestListener listener) {
        super(listener);
    }
    @Override
    protected Request prepare(Map<String, String> parameter) {
        return null;
    }
    @Override
    protected Object process(String data) {
        return null;
    }
}
