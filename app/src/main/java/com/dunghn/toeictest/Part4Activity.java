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

public class Part4Activity extends AppCompatActivity {
    RecyclerView recyclerViewPart4;
    SeekBar seekBarPart4;
    ImageView imgPlay;
    Toolbar toolbarp4;
    TextView tvQuestion, tvTime;
    EditText edtAnswer4;
    Button btnStatus;
    String value;
    List<QuestionPart3_4> question_parts;
    QuestionPart3_4 question;
    QuestionAdapter questionAdapter;
    MediaPlayer mediaPlayer;
    Handler seekHandler = new Handler();
    Runnable run = new Runnable() {
        @Override
        public void run() {
            seekUpdation();
            //lay answer tu adapter
            SharedPreferences sharedPreferences = getSharedPreferences("myKey", MODE_PRIVATE);
            value = sharedPreferences.getString("value", "");
            edtAnswer4.setText(value);
        }
    };

    public void seekUpdation() {
        int second = mediaPlayer.getCurrentPosition() / 1000;
        seekBarPart4.setProgress(second);
        seekHandler.postDelayed(run, 1000);
        int realSecond = second % 60;
        int realFullSecond = (mediaPlayer.getDuration() / 1000) % 60;
        String strTime = String.valueOf(second / 60) + ":" + String.valueOf(realSecond);
        String strFullTime = String.valueOf(mediaPlayer.getDuration() / 1000 / 60) + ":" + String.valueOf(realFullSecond);
        tvTime.setText(strTime + "/" + strFullTime);
    }

    //
    List<QuestionPart3_4> listquestionPart4 = new ArrayList<>();
    int ID = 2;
    int count = 70;
    boolean review = false;
    //Mang luu trang thai lam bai
    Status status = new Status();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part4);
        String titlepart4 = "Part 4: Short Talks";
        toolbarp4 = (Toolbar) findViewById(R.id.toolbarp4);
        setSupportActionBar(toolbarp4);
        toolbarp4.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setTitle(toolbarp4 + "");
        toolbarp4.setTitle(Html.fromHtml("<font color='#ffffff'>" + titlepart4 + " </font>"));

        edtAnswer4 = (EditText) findViewById(R.id.edtAnswer);
        tvQuestion = (TextView) findViewById(R.id.tvQs);
        tvTime = (TextView) findViewById(R.id.tvAudioTime);
        recyclerViewPart4 = (RecyclerView) findViewById(R.id.recyclerViewPart4);
        seekBarPart4 = (SeekBar) findViewById(R.id.seekBarPart4);
        imgPlay = (ImageView) findViewById(R.id.imgPlayPart4);
        btnStatus = (Button) findViewById(R.id.btnStatus);
        //----
        btnStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog_Result dialog_status = new Dialog_Result(status);
                dialog_status.show(getFragmentManager(), "");
            }
        });
        edtAnswer4.setText(value);
        //----
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.part4);
        seekBarPart4.setMax(mediaPlayer.getDuration() / 1000);
        seekUpdation();
        seekBarPart4.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b)
                    mediaPlayer.seekTo(i * 1000);
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
        //data
        final QuestionPart3_4DAO questionPart4DAO = new QuestionPart3_4DAO(Part4Activity.this);
        listquestionPart4 = questionPart4DAO.getQuestion_Part4();
        //tiền xử lí status
        Intent callingIntent = getIntent();
        review = callingIntent.getBooleanExtra("AllowWatchRes", false);
        if (!review)
            btnStatus.setVisibility(View.GONE);
        else
            btnStatus.setVisibility(View.VISIBLE);
        status = (Status) callingIntent.getSerializableExtra("status");
        for (int i = 0; i < 30; i++) {
            status.setKey(70 + i, listquestionPart4.get(i).getCorrectAnswer());
            Log.d("XEMPART", "List Answer Part 4: "+"ID "+(70+i)+"----"+listquestionPart4.size());
        }
        question_parts = new ArrayList<>();
        question = new QuestionPart3_4();
        question = listquestionPart4.get(0);
        question_parts.add(question);
        question = new QuestionPart3_4();
        question = listquestionPart4.get(1);
        question_parts.add(question);
        question = new QuestionPart3_4();
        question = listquestionPart4.get(2);
        question_parts.add(question);
        questionAdapter = new QuestionAdapter(Part4Activity.this, question_parts, count, status);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);
        recyclerViewPart4.setHasFixedSize(true);
        recyclerViewPart4.setLayoutManager(layoutManager);
        recyclerViewPart4.setAdapter(questionAdapter);
        //xu li list khi nguoi dung chon dap an

        edtAnswer4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    String note = edtAnswer4.getText().toString();
                    int choice = Integer.parseInt(note.substring(0, note.indexOf("|"))); //lua chon cua nguoi dung
                    int idQuestion = Integer.parseInt(note.substring(note.indexOf("|") + 1, note.length())); //thu tu cua cau hoi
                    status.setCustomerAnswer(idQuestion, choice);
                } catch (Exception ex) {

                }
//                Toast.makeText(getApplicationContext(), "value: " + value, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        toolbarp4.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Part4Activity.this);
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
                if (count == 70) {
                    Intent intent = new Intent(getApplicationContext(), Part3Activity.class);
                    intent.putExtra("status", status);
                    intent.putExtra("AllowWatchRes", review);
                    startActivity(intent);
                    finish();
                    mediaPlayer.stop();
                } else {
                    count = count - 3;
                    int k = count + 3;
                    tvQuestion.setText("Question " + (count + 1) + "-" + k);
                    List<QuestionPart3_4> question_parts = new ArrayList<>();
                    question = new QuestionPart3_4();
                    ID = ID - 5;
                    question = listquestionPart4.get(ID);
                    question_parts.add(question);
                    question = new QuestionPart3_4();
                    question = listquestionPart4.get(ID + 1);
                    question_parts.add(question);
                    question = new QuestionPart3_4();
                    question = listquestionPart4.get(ID + 2);
                    ID = ID + 2;
                    question_parts.add(question);
                    QuestionAdapter questionAdapter = new QuestionAdapter(Part4Activity.this, question_parts, count, status);
                    recyclerViewPart4.setAdapter(questionAdapter);
                }

                return true;
            case R.id.next:
                if (count <= 94) {
                    count = count + 3;
                    int k = count + 3;
                    tvQuestion.setText("Question " + (count + 1) + "-" + k);
                    List<QuestionPart3_4> question_parts = new ArrayList<>();
                    question = new QuestionPart3_4();
                    question = listquestionPart4.get(ID + 1);
                    question_parts.add(question);
                    question = new QuestionPart3_4();
                    question = listquestionPart4.get(ID + 2);
                    question_parts.add(question);
                    question = new QuestionPart3_4();
                    question = listquestionPart4.get(ID + 3);
                    ID = ID + 3;
                    question_parts.add(question);
                    QuestionAdapter questionAdapter = new QuestionAdapter(Part4Activity.this, question_parts, count, status);
                    recyclerViewPart4.setAdapter(questionAdapter);

                } else {
                    if (review) {
                        mediaPlayer.stop();
                        finish();
                    } else {
                        Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                        intent.putExtra("status", status);
                        startActivity(intent);
                        finish();
                        mediaPlayer.stop();
                    }
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
        AlertDialog.Builder builder = new AlertDialog.Builder(Part4Activity.this);
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
