package com.example.testforcoders;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.view.textclassifier.TextLinks;
import android.webkit.WebView;

public class studycourse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studycourse);
        WebView webView=findViewById(R.id.webview1);
        webView.loadUrl("file:///android_assets/course.html");

    }
}