package com.itsdl.androidtutorials.fragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().requestWindowFeature(Window.FEATURE_PROGRESS);
        getActivity().setProgressBarVisibility(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_lesson_content,container,false);


        //Bundle
        Bundle args = getArguments();
        lessonIDItem = 1;
        if(args!=null && args.containsKey("lesson_item_id")){
            lessonIDItem = args.getInt("lesson_item_id");
        }

        addControl(root);
        addEvents();
        return root;
    }

    /**
     * addControl: add controls with layout
     * @param root view contain control in layout*/
    private void addControl(View root) {
        //add bottomNavigationView
        //bottomNavigationView = root.findViewById(R.id.navbottomLesson);
        btnFeedbackLesson = root.findViewById(R.id.btnFeedbackLesson);
        btnNextLesson = root.findViewById(R.id.btnNextLesson);
        btnPrevLesson = root.findViewById(R.id.btnPrevLesson);

        wv_lesson_content = root.findViewById(R.id.wv_lesson_content);
        wv_lesson_content.setWebViewClient(new WebViewClient());
        wv_lesson_content.loadUrl(url + lessonIDItem);


        progressBarContent = root.findViewById(R.id.progressBarContent);
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
                    wv_lesson_content.setWebViewClient(new WebViewClient());
                    wv_lesson_content.loadUrl(url+lessonIDItem);

                    Toast.makeText(getContext(),String.valueOf(lessonIDItem),Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnFeedbackLesson:
                //TODO
                Toast.makeText(getContext(),"Feed",Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnNextLesson:
                Toast.makeText(getContext(),"Next",Toast.LENGTH_SHORT).show();
                if(lessonIDItem+1<=COUNT_LESSON){
                    lessonIDItem++;
                    wv_lesson_content.setWebViewClient(new WebViewClient());
                    wv_lesson_content.loadUrl(url+lessonIDItem);

                    Toast.makeText(getContext(),String.valueOf(lessonIDItem),Toast.LENGTH_SHORT).show();
                }
                break;
        }


    }
    public void onProgressChanged (WebView view, int newProgress){

    }

    public class WebViewClient extends android.webkit.WebViewClient
    {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            // TODO Auto-generated method stub
            view.loadUrl(url);
            return true;
        }
        @Override
        public void onPageFinished(WebView view, String url) {

            // TODO Auto-generated method stub

            super.onPageFinished(view, url);
            progressBarContent.setVisibility(View.GONE);

        }

    }
}
