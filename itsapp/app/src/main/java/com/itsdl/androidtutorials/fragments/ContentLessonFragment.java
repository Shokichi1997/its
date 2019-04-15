package com.itsdl.androidtutorials.fragments;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.itsdl.androidtutorials.R;


public class ContentLessonFragment extends Fragment implements View.OnClickListener{
    private WebView wv_lesson_content;
    private int lessonIDItem;
    private final String url = "https://itstutorials.000webhostapp.com/7-2?lesson=";
    private final int COUNT_LESSON = 3;
    private Button btnPrevLesson, btnNextLesson, btnFeedbackLesson;
    ProgressBar progressBarContent;
    private  View root;
    String showOrHideWebViewInitiaUse = "show";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_lesson_content,container,false);



        //get Bundle
        Bundle args = getArguments();
        lessonIDItem = 1;
        if(args!=null && args.containsKey("lesson_item_id")){
            lessonIDItem = args.getInt("lesson_item_id");
        }
        addControl();
        addEvents();
        return root;
    }

    /**
     * addControl: add controls with layout
     *  root view contain control in layout*/
    private void addControl() {
        //add bottomNavigationView
        //bottomNavigationView = root.findViewById(R.id.navbottomLesson);
        btnFeedbackLesson = root.findViewById(R.id.btnFeedbackLesson);
        btnNextLesson = root.findViewById(R.id.btnNextLesson);
        btnPrevLesson = root.findViewById(R.id.btnPrevLesson);
        progressBarContent = root.findViewById(R.id.progressBarContent);

        wv_lesson_content = root.findViewById(R.id.wv_lesson_content);
        wv_lesson_content.setWebViewClient(new WebViewClientCustomer());
        //wv_lesson_content.setWebChromeClient(new WebChromeClient());
        WebSettings settings = wv_lesson_content.getSettings();
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        wv_lesson_content.loadUrl(url + lessonIDItem);
    }

    /**
     * addEvents: add events to handle event click*/
    private void addEvents() {
        btnPrevLesson.setOnClickListener(this);
        btnNextLesson.setOnClickListener(this);
        btnFeedbackLesson.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnPrevLesson:
                Toast.makeText(getContext(),"Prev",Toast.LENGTH_SHORT).show();
                if(lessonIDItem-1>=1){
                    lessonIDItem--;
                    wv_lesson_content.loadUrl(url+lessonIDItem);
                }
                break;
            case R.id.btnFeedbackLesson:
                //TODO
                break;
            case R.id.btnNextLesson:
                if(lessonIDItem+1<=COUNT_LESSON) {
                    lessonIDItem++;
                    wv_lesson_content.loadUrl(url + lessonIDItem);
                }
                break;
        }


    }

    public class WebViewClientCustomer extends android.webkit.WebViewClient
    {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon){
            super.onPageStarted(view, url, favicon);
            progressBarContent.setVisibility(View.VISIBLE);
            if(showOrHideWebViewInitiaUse.equals("show")){
                view.setVisibility(wv_lesson_content.INVISIBLE);
            }
        }

        @Override
        public void onPageFinished(WebView view, String url){
            showOrHideWebViewInitiaUse = "hide";
            progressBarContent.setVisibility(View.GONE);
            view.setVisibility(wv_lesson_content.VISIBLE);
            super.onPageFinished(view,url);
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError er) {
            handler.proceed();
        }

    }
}
