package com.xmcu.mynews.view.activity;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.xmcu.mynews.R;
import com.xmcu.mynews.util.Constant;

/**
 * 显示新闻详情
 */
public class NewsDetailActivity extends AppCompatActivity {

    /**
     * 使用webview加载地址，显示网页内容
     */
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        String url = getIntent().getStringExtra(Constant.URL);//获取传入的页面地址
        initView();
        loadData(url);
    }

    private void loadData(String url) {
        webView.loadUrl(url);//webview加载网页地址
    }

    private void initView() {
        webView = findViewById(R.id.webview_news);
    }
}
