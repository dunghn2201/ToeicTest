package com.dunghn.toeictest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dunghn.toeictest.adapter.ConversationAdapter;
import com.dunghn.toeictest.model.Conversation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

public class ConversationActivity extends AppCompatActivity {
    List<Conversation> listconversation;
    List<String> allconversation;
    RecyclerView rv;
    String filename;
    boolean b = true;
    int count = 0;
    ConversationAdapter Adapterconversation;
    TextToSpeech textToSpeech;
    Toolbar toolbar;
Button btnShowchat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);

        String titleconversation = "Conversation Detail";
        toolbar = (Toolbar) (findViewById(R.id.toolbar));
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setTitle(titleconversation + "");
        toolbar.setTitle(Html.fromHtml("<font color='#ffffff'>" + titleconversation + " </font>"));

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.US);
                }
            }
        });

        filename = getIntent().getStringExtra("filename");

        new Thread(new loadChat()).start();
        rv = findViewById(R.id.rcchat);
        btnShowchat=findViewById(R.id.btnShowchat);
        listconversation = new ArrayList<>();
        allconversation = new ArrayList<>();
        Adapterconversation = new ConversationAdapter(listconversation, this);
        rv.setAdapter(Adapterconversation);
        Adapterconversation.notifyDataSetChanged();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(linearLayoutManager);

        Toast.makeText(getApplicationContext(), "Turn On volume to hear the conversation", Toast.LENGTH_SHORT).show();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnShowchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Showchat();
            }
        });
    }

    private void Showchat() {
        Log.d("PEP", String.valueOf(allconversation.size()));
        if (!(count == allconversation.size() - 1)) {
            if (b == true) {
                listconversation.add(new Conversation(allconversation.get(count), "left"));
                Log.d("PEP", String.valueOf(allconversation.get(count)));
                b = false;
            } else {
                listconversation.add(new Conversation(allconversation.get(count), "right"));
                b = true;
                Log.d("PEP", String.valueOf(allconversation.get(count)));
            }
            sayIt(allconversation.get(count));
            count++;

            Adapterconversation.notifyDataSetChanged();
            rv.scrollToPosition(listconversation.size() - 1);
            Log.d("PEO", String.valueOf(allconversation.size()-1));
        } else {
            Toast.makeText(getApplicationContext(), "Conversation Ended", Toast.LENGTH_SHORT).show();
        }

    }


    public void sayIt(String s) {
        if (textToSpeech.isSpeaking()) {
            textToSpeech.stop();
        } else {
        }
        textToSpeech.speak(s, TextToSpeech.QUEUE_FLUSH, null);

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (textToSpeech.isSpeaking()) {
            textToSpeech.stop();
        }

    }

    class loadChat implements Runnable {

        @Override
        public void run() {

            BufferedReader reader = null;
            try {
                reader = new BufferedReader(
                        new InputStreamReader(getAssets().open(filename), "UTF-8"));
                String mLine;
                listconversation.clear();
                while ((mLine = reader.readLine()) != null) {
                    allconversation.add(mLine);
                    Log.d("XEMTHOI", String.valueOf(mLine));
                }
            } catch (IOException e) {
                e.printStackTrace();

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
