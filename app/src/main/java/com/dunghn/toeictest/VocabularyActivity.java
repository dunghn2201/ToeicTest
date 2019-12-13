package com.dunghn.toeictest;

import android.os.Bundle;
import android.text.Html;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.dunghn.toeictest.adapter.VocalDetailAdapter;
import com.dunghn.toeictest.model.VocalDetail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class VocabularyActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView leveldetailrcv;
    ArrayList<VocalDetail> al;
    VocalDetailAdapter myad;

    String filename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_detail);
        try {
            filename = getIntent().getStringExtra("filename");
            String levelname = getIntent().getStringExtra("levelname");
            al = new ArrayList<>();
            toolbar = (Toolbar) (findViewById(R.id.toolbar));
            setSupportActionBar(toolbar);
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
            getSupportActionBar().setTitle(levelname + "");
            toolbar.setTitle(Html.fromHtml("<font color='#ffffff'>"+levelname+" </font>"));

            leveldetailrcv = (RecyclerView) findViewById(R.id.leveldetailrcv);
            leveldetailrcv.setNestedScrollingEnabled(false);
            myad = new VocalDetailAdapter(al, getApplicationContext());
            leveldetailrcv.setAdapter(myad);


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


    @Override
    protected void onStart() {
        super.onStart();
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
                al.clear();
                while ((mLine = reader.readLine()) != null) {
                    st = new StringTokenizer(mLine, ";");
                    String id = st.nextToken();
                    String title = st.nextToken();
                    String answer = st.nextToken();
                    String additional = st.nextToken();
                    String photo = st.nextToken();
                    al.add(new VocalDetail(id, title, answer, additional, photo));

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            myad.notifyDataSetChanged();

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
