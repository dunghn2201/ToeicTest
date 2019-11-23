package com.dunghn.toeictest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.webkit.WebView;

public class GrammarDetailActivity extends AppCompatActivity {
    WebView webview1;
    Toolbar toolbar;
    String title="Grammar rules";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar_detail);
        try {
            toolbar = (Toolbar) (findViewById(R.id.toolbarg));
            setSupportActionBar(toolbar);
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
            getSupportActionBar().setTitle(title);
            toolbar.setTitle(Html.fromHtml("<font color='#ffffff'>"+title+" </font>"));
        }catch (Exception ex){
            ex.printStackTrace();
        }

        String filename=getIntent().getStringExtra("filename");
        webview1 = (WebView) findViewById(R.id.webview);
        webview1.loadUrl("file:///android_asset/"+filename);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
}
