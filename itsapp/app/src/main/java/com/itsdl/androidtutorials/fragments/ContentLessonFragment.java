package com.itsdl.androidtutorials.fragments;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.itsdl.androidtutorials.R;


public class ContentLessonFragment extends Fragment{
    private WebView wv_lesson_content;
    private int lessonIDItem;
    private final String url = "https://itstutorials.000webhostapp.com/7-2?lesson=";
    ProgressBar progressBarContent;
    private  View root;
    String showOrHideWebViewInitiaUse = "show";
    private Toolbar toolbar;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_lesson_content,container,false);
        addControl();
        addEvents();
        return root;
    }

    /**
     * addControl: add controls with layout
     *  root view contain control in layout*/
    private void addControl() {
        //get Bundle
        Bundle args = getArguments();
        lessonIDItem = 1;
        if(args!=null && args.containsKey("lesson_item_id")){
            lessonIDItem = args.getInt("lesson_item_id");
        }
        toolbar = root.findViewById(R.id.contentLesson_toolbar);
        ((AppCompatActivity ) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Lesson");
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressBarContent = root.findViewById(R.id.progressBarContent);
        progressBarContent.setMax(100);
        progressBarContent.setProgress(1);
        progressBarContent.incrementProgressBy(2);

        wv_lesson_content = root.findViewById(R.id.wv_lesson_content);
        wv_lesson_content.setWebViewClient(new WebViewClientCustomer());
        WebSettings settings = wv_lesson_content.getSettings();
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setJavaScriptEnabled(true);
        wv_lesson_content.loadUrl(url + lessonIDItem);
    }

    /**
     * addEvents: add events to handle event click*/
    private void addEvents() {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getFragmentManager().getBackStackEntryCount() > 0) {
                    getFragmentManager().popBackStack();
                }
            }
        });
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
