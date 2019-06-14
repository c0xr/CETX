package com.csti.cetx.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.csti.cetx.R;

public class GameActivity extends BaseActivity {

    //WebViewClient主要帮助WebView处理各种通知、请求事件
    private WebViewClient webViewClient=new WebViewClient(){
        @Override
        public void onPageFinished(WebView view, String url) {//页面加载完成

        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {//页面开始加载

        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            return super.shouldOverrideUrlLoading(view, url);
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        WebView webView = findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://h.4399.com/play/159456.htm");
        webView.setWebViewClient(webViewClient);


    }

    public static void actionStart(Object activity){
        Intent intent = new Intent((BaseActivity)activity, GameActivity.class);
        ((BaseActivity)activity).startActivity(intent);
    }
}
