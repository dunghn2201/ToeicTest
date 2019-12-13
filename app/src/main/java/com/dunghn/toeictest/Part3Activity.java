package com.dunghn.toeictest;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dunghn.toeictest.DAO.QuestionPart3_4DAO;
import com.dunghn.toeictest.adapter.QuestionAdapter;
import com.dunghn.toeictest.dialog.Dialog_Result;
import com.dunghn.toeictest.fragment.ToeicTestQuizFragment;
import com.dunghn.toeictest.model.QuestionPart3_4;
import com.dunghn.toeictest.model.Status;

import java.util.ArrayList;
import java.util.List;

public class Part3Activity extends AppCompatActivity {
    RecyclerView recyclerViewPart3;
    SeekBar seekBarPart3;
    ImageView imgPlay;
    Toolbar toolbarp3;
    TextView tvQuestion, tvTime;
    EditText edtAnwser3;
    Button btnStatus;
    QuestionAdapter questionAdapter;
    List<QuestionPart3_4> question_parts;
    QuestionPart3_4 question;
    String value;
    MediaPlayer mediaPlayer;
    Handler seekHandler = new Handler();
    Runnable run = new Runnable() {
        @Override
        public void run() {
            seekUpdation();
            //lay answer tu adapter
            SharedPreferences sharedPreferences = getSharedPreferences("myKey", MODE_PRIVATE);
            value = sharedPreferences.getString("value", "");
            edtAnwser3.setText(value);
        }
    };

    public void seekUpdation() {
        int second = mediaPlayer.getCurrentPosition() / 1000;
        seekBarPart3.setProgress(second);
        seekHandler.postDelayed(run, 1000);
        int realSecond = second % 60;
        int realFullSecond = (mediaPlayer.getDuration() / 1000) % 60;
        String strTime = String.valueOf(second / 60) + ":" + String.valueOf(realSecond);
        String strFullTime = String.valueOf(mediaPlayer.getDuration() / 1000 / 60) + ":" + String.valueOf(realFullSecond);
        tvTime.setText(strTime + "/" + strFullTime);
    }

    //
    List<QuestionPart3_4> listquestionPart3 = new ArrayList<>();
    int count = 40;   //index of first question in list (in group of three questions)
    int ID = 2;
    boolean review = false;
    //
    Status status = new Status();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part3);
        String titlepart3="Part 3: Short Conversations";
        toolbarp3 = (Toolbar) findViewById(R.id.toolbarp3);
        setSupportActionBar(toolbarp3);
        toolbarp3.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setTitle(toolbarp3 + "");
        toolbarp3.setTitle(Html.fromHtml("<font color='#ffffff'>"+titlepart3+" </font>"));

        seekBarPart3 = (SeekBar) findViewById(R.id.seekBarPart3);
        imgPlay = (ImageView) findViewById(R.id.imgPlayPart3);
        tvQuestion = (TextView) findViewById(R.id.tvQs);
        tvTime = (TextView) findViewById(R.id.tvAudioTime);
        edtAnwser3 = (EditText) findViewById(R.id.edtAnwser);
        btnStatus = (Button) findViewById(R.id.btnStatus);
        //----
        btnStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog_Result dialog_status = new Dialog_Result(status);
                dialog_status.show(getFragmentManager(), "");
            }
        });

        edtAnwser3.setText(value);
        //get data
        final QuestionPart3_4DAO questionPart3DAO = new QuestionPart3_4DAO(Part3Activity.this);  //lay database
        listquestionPart3 = questionPart3DAO.getQuestion_Part3();
        //tiền xử lí status
        Intent callingIntent = getIntent();
        review = callingIntent.getBooleanExtra("AllowWatchRes", false);
        if (!review)
            btnStatus.setVisibility(View.GONE);
        else
            btnStatus.setVisibility(View.VISIBLE);
        status = (Status) callingIntent.getSerializableExtra("status");
        for (int i = 0; i < 30; i++) {
            status.setKey(40 + i, listquestionPart3.get(i).getCorrectAnswer());
            Log.d("XEMPART", "List Answer Part 3: "+"ID "+(40+i)+"----"+listquestionPart3.size());
        }
        //list view
        recyclerViewPart3 = (RecyclerView) findViewById(R.id.recyclerviewPart3);
        question_parts = new ArrayList<>();
        question = new QuestionPart3_4();
        question = listquestionPart3.get(count - 40);
        question_parts.add(question);
        question = new QuestionPart3_4();
        question = listquestionPart3.get(count - 40 + 1);
        question_parts.add(question);
        question = new QuestionPart3_4();
        question = listquestionPart3.get(count - 40 + 2);
        question_parts.add(question);
        questionAdapter = new QuestionAdapter(Part3Activity.this, question_parts, count, status);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerViewPart3.setHasFixedSize(true);
        recyclerViewPart3.setLayoutManager(layoutManager);
        recyclerViewPart3.setAdapter(questionAdapter);
        //xu li list khi nguoi dung chon dap an

        edtAnwser3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    String note = edtAnwser3.getText().toString();
                    int choice = Integer.parseInt(note.substring(0, note.indexOf("|"))); //lua chon cua nguoi dung
                    int idQuestion = Integer.parseInt(note.substring(note.indexOf("|") + 1, note.length())); //thu tu cua cau hoi
                    status.setCustomerAnswer(idQuestion, choice);
                }catch (Exception ex){
                    ex.printStackTrace();
                }
//                Toast.makeText(getApplicationContext(), "value: " + value, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //am thanh
        mediaPlayer = MediaPlayer.create(Part3Activity.this, R.raw.part3);
        seekBarPart3.setMax(mediaPlayer.getDuration() / 1000);
        seekUpdation();
        seekBarPart3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser == true) {
                    mediaPlayer.seekTo(progress * 1000);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!mediaPlayer.isPlaying()) {
                    imgPlay.setImageResource(R.drawable.icon_stop);
                    mediaPlayer.start();
                } else {
                    imgPlay.setImageResource(R.drawable.icon_play);
                    mediaPlayer.pause();
                }
            }
        });
        toolbarp3.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Part3Activity.this);
                builder.setTitle("Thông báo");
                if (review)
                    builder.setMessage("Quay về trang chính?");
                else
                    builder.setMessage("Bạn muốn hủy bài làm?");
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        mediaPlayer.stop();
                        if (review)
                            onBackPressed();
                        else
                            sendToMain(status, ToeicTestQuizFragment.IN_PROGESS);
                    }
                });
                builder.show();
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.switch_question_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.back:
                if (count == 40) {
                    Intent intent = new Intent(getApplicationContext(), Part2Activity.class);
                    intent.putExtra("status", status);
                    intent.putExtra("AllowWatchRes", review);
                    intent.putExtra("count", 40);
                    startActivity(intent);
                    mediaPlayer.stop();
                    finish();
                } else {
                    count = count - 3;
                    int k = count + 3;
                    tvQuestion.setText("Question " + (count + 1) + "-" + k);
                    List<QuestionPart3_4> question_parts = new ArrayList<>();
                    question = new QuestionPart3_4();
                    ID = ID - 5;
                    question = listquestionPart3.get(ID);
                    question_parts.add(question);
                    question = listquestionPart3.get(ID + 1);
                    question_parts.add(question);
                    question = listquestionPart3.get(ID + 2);
                    ID = ID + 2;

                    question_parts.add(question);
                    QuestionAdapter questionAdapter = new QuestionAdapter(Part3Activity.this, question_parts, count, status);
                    recyclerViewPart3.setAdapter(questionAdapter);
                }
                return true;
            case R.id.next:
                if (count <= 64) {
                    count = count + 3;
                    int k = count + 3;
                    tvQuestion.setText("Question " + (count + 1) + " - " + k);

                    List<QuestionPart3_4> question_parts = new ArrayList<>();
                    question = listquestionPart3.get(ID + 1);
                    question_parts.add(question);
                    question = new QuestionPart3_4();
                    question = listquestionPart3.get(ID + 2);
                    question_parts.add(question);
                    question = new QuestionPart3_4();
                    question = listquestionPart3.get(ID + 3);
                    ID = ID + 3;
                    question_parts.add(question);
                    QuestionAdapter questionAdapter = new QuestionAdapter(Part3Activity.this, question_parts, count, status);
                    recyclerViewPart3.setAdapter(questionAdapter);
                } else {

                    Intent intent = new Intent(getApplicationContext(), Part4Activity.class);
                    intent.putExtra("status", status);
                    intent.putExtra("AllowWatchRes", review);
                    startActivity(intent);
                    finish();
                    mediaPlayer.stop();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {
            dialog_exit();
        }
        return super.onKeyDown(keyCode, event);
    }

    public void sendToMain(Status data, int resultCode)  //quay lai ToeicTestQuizFragment va mang theo trang thai
    {
        Intent intent = getIntent();
        intent.putExtra("status", data);
        setResult(resultCode, intent);
        finish();
    }

    public void dialog_exit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Part3Activity.this);
        builder.setTitle("Thông báo");
        if (review)
            builder.setMessage("Quay về trang chính?");
        else
            builder.setMessage("Bạn muốn hủy bài làm?");
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                mediaPlayer.stop();
                if (review)
                    onBackPressed();
                else
                    sendToMain(status, ToeicTestQuizFragment.IN_PROGESS);
            }
        });
        builder.show();

    }

}
