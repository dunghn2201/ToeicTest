package com.dunghn.toeictest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Locale;

public class VocalDetailActivity extends AppCompatActivity {
    ImageView detialPhoto;
    TextView detailTitle, detailAnswer, detailAdditional;
    ImageView speakerBtn;
    TextToSpeech textToSpeech;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocal_detail);
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int screenWidth = (int) (metrics.widthPixels * 0.98);
        getWindow().setLayout(screenWidth, LinearLayout.LayoutParams.WRAP_CONTENT);
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.US);
                }
            }
        });
        detailTitle = (TextView) (findViewById(R.id.vocaltitledetail));
        detailAnswer = (TextView) (findViewById(R.id.detailanswer));
        detailAdditional = (TextView) (findViewById(R.id.detailadditional));
        detialPhoto = (ImageView) (findViewById(R.id.vocalImageDetail));
        speakerBtn = (ImageButton) (findViewById(R.id.speakerbtn));

        Intent in = getIntent();
        final String title = in.getStringExtra("title");
        String answer = in.getStringExtra("answer");
        String photo = in.getStringExtra("photo");
        String additional = in.getStringExtra("additional");

        speakerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        speakerBtn.setImageResource(R.drawable.speaker);

                    }
                });
                textToSpeech.speak(title, TextToSpeech.QUEUE_FLUSH, null);
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        try {
                            Thread.sleep(1000);

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    speakerBtn.setImageResource(R.drawable.speaker1);
                                }
                            });

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }).start();
            }
        });

        if (!photo.equals("na")) {
            Log.d("MYMSG", "path--" + photo + "--");

            Glide.with(this).load(photo).override(300, 200).into(detialPhoto);
        } else {
            detialPhoto.setVisibility(View.GONE);
        }
        detailTitle.setText(title);
        detailAnswer.setText(answer);
        detailAdditional.setText(additional);

    }
}
