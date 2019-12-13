package com.dunghn.toeictest;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.dunghn.toeictest.DAO.QuestionPart1DAO;
import com.dunghn.toeictest.dialog.Dialog_Result;
import com.dunghn.toeictest.fragment.ToeicTestQuizFragment;
import com.dunghn.toeictest.model.QuestionPart1;
import com.dunghn.toeictest.model.Status;

import java.util.ArrayList;
import java.util.List;

public class Part1Activity extends AppCompatActivity implements View.OnClickListener {
    MediaPlayer mediaPlayer;
    SeekBar seekBarPart1;
    ImageView imgPlay;
    Toolbar toolbarp1;
    RadioButton rbtnA, rbtnB, rbtnC, rbtnD;
    ImageView imgQuestion;
    TextView tvQuestion, tvTime;
    Button btnStatus;
    List<QuestionPart1> listQuestionP1 = new ArrayList<>();
    Status status = new Status(); //Mang luu trang thai lam bai
    int count = 0;
    boolean review = false;
    Handler seekHandler = new Handler();
    Runnable run = new Runnable() {
        @Override
        public void run() {
            seekUpdation();
        }
    };

    public void seekUpdation() {
        int second = mediaPlayer.getCurrentPosition() / 1000;
        seekBarPart1.setProgress(second);
        seekHandler.postDelayed(run, 1000);
        int realSecond = second % 60;
        int realFullSecond = (mediaPlayer.getDuration() / 1000) % 60;
        String strTime = String.valueOf(second / 60) + ":" + String.valueOf(realSecond);
        String strFullTime = String.valueOf(mediaPlayer.getDuration() / 1000 / 60) + ":" + String.valueOf(realFullSecond);
        tvTime.setText(strTime + "/" + strFullTime);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part1);
        String titlepart1 = "Part 1: Photographs";
        toolbarp1 = (Toolbar) findViewById(R.id.toolbarp1);
        setSupportActionBar(toolbarp1);
        toolbarp1.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setTitle(titlepart1 + "");
        toolbarp1.setTitle(Html.fromHtml("<font color='#ffffff'>" + titlepart1 + " </font>"));

        tvQuestion = (TextView) findViewById(R.id.tvQuestion_part1);
        tvTime = (TextView) findViewById(R.id.tvAudioTime);
        seekBarPart1 = (SeekBar) findViewById(R.id.seekBarPart1);
        imgPlay = (ImageView) findViewById(R.id.imgPlayPart1);
        rbtnA = (RadioButton) findViewById(R.id.rbtnAPart1);
        rbtnB = (RadioButton) findViewById(R.id.rbtnBPart1);
        rbtnC = (RadioButton) findViewById(R.id.rbtnCPart1);
        rbtnD = (RadioButton) findViewById(R.id.rbtnDPart1);

        rbtnA.setOnClickListener(this);
        rbtnB.setOnClickListener(this);
        rbtnC.setOnClickListener(this);
        rbtnD.setOnClickListener(this);
        imgQuestion = (ImageView) findViewById(R.id.imgQuestion);
        btnStatus = (Button) findViewById(R.id.btnStatus);
        //----
        btnStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog_Result dialog_status = new Dialog_Result(status);
                dialog_status.show(getFragmentManager(), "");
            }
        });
        //get data
        QuestionPart1DAO questionPart1 = new QuestionPart1DAO(Part1Activity.this);
        listQuestionP1 = questionPart1.getQuestion_Part1();
        //
        Intent callIntent = getIntent();
        review = callIntent.getBooleanExtra("AllowWatchRes", false);
        if (!review)
            btnStatus.setVisibility(View.GONE);
        else
            btnStatus.setVisibility(View.VISIBLE);

        status = (Status) callIntent.getSerializableExtra("status");   //update
        for (int i = 0; i <= 9; i++) {      //input Key for status
            status.setKey(i, listQuestionP1.get(i).getCorrectAnswer());
            Log.d("XEMPART", "List Answer Part 1: " + "ID " + i + "----" + listQuestionP1.get(i).getCorrectAnswer());
        }
        //hien thi cau hoi
        callIntent.getIntExtra("count", 0);
        displayQuestion(count);
        syncRbtnWithStatus();       //display radio button same as status
        //
        mediaPlayer = MediaPlayer.create(Part1Activity.this, R.raw.part1);
        seekBarPart1.setMax(mediaPlayer.getDuration() / 1000);
        seekUpdation();
        seekBarPart1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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
                    mediaPlayer.start();
                    imgPlay.setImageResource(R.drawable.icon_stop);
                } else if (mediaPlayer.isPlaying()) {
                    imgPlay.setImageResource(R.drawable.icon_play);
                    mediaPlayer.pause();
                }
            }
        });
        toolbarp1.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Part1Activity.this);
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
                            finish();
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
                if (count == 0) {
                    dialog_exit();
                } else {
                    count--;
                    displayQuestion(count);
                    syncRbtnWithStatus();
                    Toast.makeText(this, "Count: " + count, Toast.LENGTH_SHORT).show();
                }
                return true;
            case R.id.next:
                if (count < 9) {
                    count++;
                    displayQuestion(count);
                    syncRbtnWithStatus();
                    Toast.makeText(this, "Count: " + count, Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getApplicationContext(), Part2Activity.class);
                    intent.putExtra("status", status);
                    intent.putExtra("AllowWatchRes", review);
                    intent.putExtra("count", 10);
                    startActivity(intent);
                    sendToMain(status, RESULT_CANCELED);
                    finish();
                    mediaPlayer.stop();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void syncRbtnWithStatus()    //dong bo trang thai cac rbtn vs status cua cau hoi hien tai
    {
        switch (status.getCustomerAnwser(count)) {
            case 1://A
                setDisplayCheckAnwser(rbtnA, R.drawable.a1);
                break;
            case 2:
                setDisplayCheckAnwser(rbtnB, R.drawable.b1);
                break;
            case 3:
                setDisplayCheckAnwser(rbtnC, R.drawable.c1);
                break;
            case 4:
                setDisplayCheckAnwser(rbtnD, R.drawable.d1);
                break;
            default:
                resetRbtn();
        }
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
        AlertDialog.Builder builder = new AlertDialog.Builder(Part1Activity.this);
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
                    finish();
                else
                    sendToMain(status, ToeicTestQuizFragment.IN_PROGESS);
            }
        });
        builder.show();

    }

    public void setDisplayCheckAnwser(RadioButton rbtnX, int idImage)    //hien thi su kien nguoi dung chon dap an X
    {
        RadioButton[] listRdbtn = {rbtnA, rbtnB, rbtnC, rbtnD};
        resetRbtn();
        for (RadioButton rbtnI : listRdbtn) {
            if (rbtnI == rbtnX) {
                rbtnI.setBackgroundResource(idImage);
                rbtnI.setChecked(true);
            }
        }
    }
    @Override
    public void onClick(View v) {
        if (!review) //khong phai dang xem lai bai lam, neu la review thi khong cho chon dap an
            switch (v.getId()) {
                case R.id.rbtnAPart1:
                    setDisplayCheckAnwser(rbtnA, R.drawable.a1);
                    status.setCustomerAnswer(count, 1);
                    return;
                case R.id.rbtnBPart1:
                    setDisplayCheckAnwser(rbtnB, R.drawable.b1);
                    status.setCustomerAnswer(count, 2);
                    return;
                case R.id.rbtnCPart1:
                    setDisplayCheckAnwser(rbtnC, R.drawable.c1);
                    status.setCustomerAnswer(count, 3);
                    return;
                case R.id.rbtnDPart1:
                    setDisplayCheckAnwser(rbtnD, R.drawable.d1);
                    status.setCustomerAnswer(count, 4);
                    return;
            }
    }


    public void resetRbtn() {
        rbtnA.setBackgroundResource(R.drawable.a);
        rbtnB.setBackgroundResource(R.drawable.b);
        rbtnC.setBackgroundResource(R.drawable.c);
        rbtnD.setBackgroundResource(R.drawable.d);
        rbtnA.setChecked(false);
        rbtnB.setChecked(false);
        rbtnC.setChecked(false);
        rbtnD.setChecked(false);
    }
    public void displayQuestion(int index) {
        tvQuestion.setText("Question " + (index + 1));
        String imgName = listQuestionP1.get(index).getImageName();
        int imgId = getApplicationContext().getResources().getIdentifier(imgName, "drawable", getApplicationContext().getPackageName());
        imgQuestion.setImageResource(imgId);
    }
}
