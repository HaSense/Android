package com.example.webclient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.webclient.databinding.ActivityMainBinding;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private WebView myWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        myWebView = binding.webview;
        WebSettings set = myWebView.getSettings();
        set.setJavaScriptEnabled(true);
        set.setBuiltInZoomControls(true);
        myWebView.loadUrl("https://www.google.com");
        setContentView(binding.getRoot());

        binding.webview.setWebViewClient(new MyWebClient());

        binding.btnGo.setOnClickListener(view -> {
            String url = binding.edtUrlAddr.getText().toString();
            if (!url.startsWith("http://") && !url.startsWith("https://")) {
                url = "http://" + url;
            }
            myWebView.loadUrl(url);
        });
        binding.btnForword.setOnClickListener(view -> {
            if(myWebView.canGoForward()){
                myWebView.goForward();
            }
        });
        binding.btnBack.setOnClickListener(view -> {
            if(myWebView.canGoBack()){
                myWebView.goBack();
            }
        });
    }

    class MyWebClient extends WebViewClient
    {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                view.loadUrl(request.getUrl().toString());
            }
            return true;
        }

        // For backward compatibility (older than API 24)
//        @SuppressWarnings("deprecation")
//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            view.loadUrl(url);
//            return true;
//        }
    }


}
