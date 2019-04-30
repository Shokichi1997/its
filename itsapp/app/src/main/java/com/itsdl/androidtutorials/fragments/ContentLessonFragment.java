package com.itsdl.androidtutorials.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.itsdl.androidtutorials.R;
import com.itsdl.androidtutorials.utils.DrawerLocker;
import com.itsdl.androidtutorials.utils.ProfileUser;


public class ContentLessonFragment extends Fragment {
    private WebView wv_lesson_content;
    private int lessonIDItem;
    private String lesson_item_name;
    private final String url = "https://itstutorials.000webhostapp.com/7-2?lesson=";
    ProgressBar progressBarContent;
    private  View root;
    String showOrHideWebViewInitiaUse = "show";
    private Toolbar toolbar;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.feedback_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_feedback:
                // Not implemented here
                composeEmail(ProfileUser.getInstance().getEmail(),"Hoi Ve Noi Dung Bai Hoc",lesson_item_name);
                Toast.makeText(getContext(),"hi",Toast.LENGTH_LONG).show();
                return false;
            default:
                break;
        }

        return false;
    }

    private void sendFeedback() {
        //Sử dụng loại MIME để thực hiện thao tác gửi là một ý tưởng tồi, bởi vì về cơ bản,
        //bạn đang hướng dẫn Android cung cấp danh sách các ứng dụng hỗ trợ gửi tệp loại message/rfc822
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, "emailaddress@emailaddress.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        intent.putExtra(Intent.EXTRA_TEXT, "I'm email body.");
        startActivity(Intent.createChooser(intent, "Send Email"));
    }
    public void composeEmail(String addresses, String subject,String lesson_item_name) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:its@example.com"));; // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, lesson_item_name +
                                                "\n"+
                                                "------------NOT DELETE----------"
                                                +"\n"
                      );
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        }
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
        if(getActivity()!=null){
            ((DrawerLocker ) getActivity()).setDrawerEnabled(true);
        }
        //get Bundle
        Bundle args = getArguments();
        lessonIDItem = 1;
        if(args!=null && args.containsKey("lesson_item_id")){
            lessonIDItem = args.getInt("lesson_item_id");
            lesson_item_name =args.getString("lesson_item_name");
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

    public class WebViewClientCustomer extends android.webkit.WebViewClient implements com.itsdl.androidtutorials.fragments.WebViewClientCustomer {
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
