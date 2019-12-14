package com.dunghn.toeictest;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.dunghn.toeictest.adapter.VocalAdapter;
import com.dunghn.toeictest.model.VocalDetail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class VocabularyActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView leveldetailrcv;
    List<VocalDetail> listVocalDetail;
    VocalAdapter AdaptervocalDetail;

    String filename,levelname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_detail);
        try {
            filename = getIntent().getStringExtra("filename");
            levelname = getIntent().getStringExtra("levelname");
            listVocalDetail = new ArrayList<>();
            toolbar = (Toolbar) (findViewById(R.id.toolbar));
            setSupportActionBar(toolbar);
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
            getSupportActionBar().setTitle(levelname + "");
            toolbar.setTitle(Html.fromHtml("<font color='#ffffff'>"+levelname+" </font>"));

            leveldetailrcv = (RecyclerView) findViewById(R.id.leveldetailrcv);
            leveldetailrcv.setNestedScrollingEnabled(false);
            AdaptervocalDetail = new VocalAdapter(listVocalDetail, getApplicationContext());
            leveldetailrcv.setAdapter(AdaptervocalDetail);

        } catch (Exception ae) {
            ae.printStackTrace();
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        leveldetailrcv.setLayoutManager(linearLayoutManager);

        new Thread(new loadLevelContent()).start();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              finish();
            }
        });
    }


    class loadLevelContent implements Runnable {

        @Override
        public void run() {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(
                        new InputStreamReader(getAssets().open(filename)));
                // đọc đến khi hết file
                String mLine;
                StringTokenizer st;
                listVocalDetail.clear();
                while ((mLine = reader.readLine()) != null) {
                    st = new StringTokenizer(mLine, ";");
                    String id = st.nextToken();
                    String title = st.nextToken();
                    String answer = st.nextToken();
                    String additional = st.nextToken();
                    String photo = st.nextToken();
                    listVocalDetail.add(new VocalDetail(id, title, answer, additional, photo));
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            AdaptervocalDetail.notifyDataSetChanged();
                        }
                    });
                }
            } catch (IOException e) {

            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {

                    }
                }
            }
        }
    }


}
