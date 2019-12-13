package com.dunghn.toeictest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.Html;
import android.view.View;
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
    List<String> allsentences;
    RecyclerView rv;
    String filename;
    boolean b = true;
    int count = 0;
    ConversationAdapter myad;
    TextToSpeech textToSpeech;
    Toolbar toolbar;

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
        listconversation = new ArrayList<>();
        allsentences = new ArrayList<>();
        myad = new ConversationAdapter(listconversation, this);
        rv.setAdapter(myad);
        myad.notifyDataSetChanged();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(linearLayoutManager);

        Toast.makeText(getApplicationContext(), "Turn On volume to hear the conversation", Toast.LENGTH_SHORT).show();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void showchat(View view) {

        if (!(count == allsentences.size() - 1)) {
            if (b == true) {
                listconversation.add(new Conversation(allsentences.get(count), "left"));
                b = false;
            } else {
                listconversation.add(new Conversation(allsentences.get(count), "right"));
                b = true;
            }
            sayIt(allsentences.get(count));
            count++;

            myad.notifyDataSetChanged();
            rv.scrollToPosition(listconversation.size() - 1);

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
                    allsentences.add(mLine);
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
