package com.itsdl.androidtutorials.fragments;

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
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.itsdl.androidtutorials.R;
import com.itsdl.androidtutorials.utils.DrawerLocker;

public class ExampleContentFragment extends Fragment{
    private WebView wv_example_content;
    private ProgressBar progressBarExampleContent;
    private Toolbar example_toolbar;
    private int example_id;
    private final String url = "https://itstutorials.000webhostapp.com/examples?example_id=";
    String showOrHideWebViewInitiaUse = "show";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_example_content,container,false);
        addControls(root);
        return root;
    }

    private void addControls(View root) {
        if(getActivity()!=null){
            ((DrawerLocker ) getActivity()).setDrawerEnabled(true);
        }
        example_id = 1;
        Bundle args = getArguments();
        if(args!=null && args.containsKey("example_id")){
            example_id = args.getInt("example_id");
        }

        wv_example_content = root.findViewById(R.id.wv_example_content);
        example_toolbar = root.findViewById(R.id.example_toolbar);
        ((AppCompatActivity ) getActivity()).setSupportActionBar(example_toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Example");
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressBarExampleContent = root.findViewById(R.id.progressBarExampleContent);
        example_toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getFragmentManager().getBackStackEntryCount() > 0) {
                    getFragmentManager().popBackStack();
                }
            }
        });

        wv_example_content.setWebViewClient(new WebViewClientCustomer());
        //wv_lesson_content.setWebChromeClient(new WebChromeClient());
        WebSettings settings = wv_example_content.getSettings();
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        wv_example_content.loadUrl(url + example_id);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public class WebViewClientCustomer extends android.webkit.WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            progressBarExampleContent.setVisibility(View.VISIBLE);
            if (showOrHideWebViewInitiaUse.equals("show")) {
                view.setVisibility(wv_example_content.INVISIBLE);
            }
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            showOrHideWebViewInitiaUse = "hide";
            progressBarExampleContent.setVisibility(View.GONE);
            view.setVisibility(wv_example_content.VISIBLE);
            super.onPageFinished(view, url);
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError er) {
            handler.proceed();
        }
    }
}
