package com.dunghn.toeictest;

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

import com.dunghn.toeictest.DAO.QuestionPart2DAO;
import com.dunghn.toeictest.dialog.Dialog_Result;
import com.dunghn.toeictest.fragment.ToeicTestQuizFragment;
import com.dunghn.toeictest.model.QuestionPart2;
import com.dunghn.toeictest.model.Status;

import java.util.ArrayList;
import java.util.List;

public class Part2Activity extends AppCompatActivity implements View.OnClickListener {
    SeekBar seekBarPart2;
    ImageView imgPlay;
    Button btnStatus;
    TextView tvTime;
    Toolbar toolbarp2;
    //
    List<QuestionPart2> listQuestionP2 = new ArrayList<>();
    //
    MediaPlayer mediaPlayer;
    Handler seekHandler = new Handler();
    Runnable run = new Runnable() {
        @Override
        public void run() {
            seekUpdation();
        }
    };

    public void seekUpdation() {
        int second = mediaPlayer.getCurrentPosition() / 1000;
        seekBarPart2.setProgress(second);
        seekHandler.postDelayed(run, 1000);
        int realSecond = second % 60;
        int realFullSecond = (mediaPlayer.getDuration() / 1000) % 60;
        String strTime = String.valueOf(second / 60) + ":" + String.valueOf(realSecond);
        String strFullTime = String.valueOf(mediaPlayer.getDuration() / 1000 / 60) + ":" + String.valueOf(realFullSecond);
        tvTime.setText(strTime + "/" + strFullTime);
    }

    //
    boolean review = false;
    int count = 10; //editted 10 to 11
    TextView tvQuestion;
    RadioButton rbtnA, rbtnB, rbtnC;
    //Mang luu trang thai lam bai
    Status status = new Status();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part2);
        String titlepart2 = "Part 2: Question Response";
        toolbarp2 = (Toolbar) findViewById(R.id.toolbarp2);
        setSupportActionBar(toolbarp2);
        toolbarp2.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setTitle(toolbarp2 + "");
        toolbarp2.setTitle(Html.fromHtml("<font color='#ffffff'>" + titlepart2 + " </font>"));
        tvQuestion = (TextView) this.findViewById(R.id.tvQuestion_part2);
        tvTime = (TextView) findViewById(R.id.tvAudioTime);
        rbtnA = (RadioButton) findViewById(R.id.rbtnAPart2);
        rbtnB = (RadioButton) findViewById(R.id.rbtnBPart2);
        rbtnC = (RadioButton) findViewById(R.id.rbtnCPart2);
        btnStatus = (Button) findViewById(R.id.btnStatus);
        rbtnA.setOnClickListener(this);
        rbtnC.setOnClickListener(this);
        rbtnB.setOnClickListener(this);
        //----
        btnStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog_Result dialog_status = new Dialog_Result(status);
                dialog_status.show(getFragmentManager(), "");
            }
        });

        //get data
        QuestionPart2DAO questionPart2DAO = new QuestionPart2DAO(Part2Activity.this);
        listQuestionP2 = questionPart2DAO.getQuestion_Part2();
        Intent callIntent = getIntent();

        review = callIntent.getBooleanExtra("AllowWatchRes", false);
        if (!review)
            btnStatus.setVisibility(View.GONE);
        else
            btnStatus.setVisibility(View.VISIBLE);

        status = (Status) callIntent.getSerializableExtra("status");
        for (int i = 0; i < 30; i++) {
            status.setKey(10 + i, listQuestionP2.get(i).getCorrectAnswer());
            Log.d("XEMPART", "List Answer Part 2: " + "ID " + (10 + i) + "----" + listQuestionP2.size());
        }
        callIntent.getIntExtra("count", 11);
        tvQuestion.setText("Question " + (count+1));
        syncRbtnWithStatus();
        //views

        seekBarPart2 = (SeekBar) findViewById(R.id.seekBarPart2);
        imgPlay = (ImageView) findViewById(R.id.imgPlay_part2);
        //am thanh
        mediaPlayer = MediaPlayer.create(Part2Activity.this, R.raw.part2);
        seekBarPart2.setMax(mediaPlayer.getDuration() / 1000);
        seekUpdation();
        seekBarPart2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
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
        toolbarp2.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Part2Activity.this);
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
                if (count == 10) {
                    Intent intent = new Intent(getApplicationContext(), Part1Activity.class);
                    intent.putExtra("status", status);
                    intent.putExtra("AllowWatchRes", review);
                    intent.putExtra("count", 10);
                    startActivity(intent);
                    finish();
                    Toast.makeText(this, "Count: " + count, Toast.LENGTH_SHORT).show();
                } else {
                    count--;
                    tvQuestion.setText("Question " + (count+1));
                    syncRbtnWithStatus();
                    Toast.makeText(this, "Count: " + count, Toast.LENGTH_SHORT).show();
                }
                return true;
            case R.id.next:
                if (count < 39) {
                    count++;
                    tvQuestion.setText("Question " + (count+1));
                    syncRbtnWithStatus();
                    Toast.makeText(this, "Count: " + count, Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getApplicationContext(), Part3Activity.class);
                    intent.putExtra("status", status);
                    intent.putExtra("AllowWatchRes", review);
                    intent.putExtra("count", 40);
                    startActivity(intent);
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


    public void dialog_exit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Part2Activity.this);
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
    public void sendToMain(Status data, int resultCode)  //quay lai ToeicTestQuizFragment va mang theo trang thai
    {
        Intent intent = getIntent();
        intent.putExtra("status", data);
        setResult(resultCode, intent);
        finish();
    }

    public void setDisplayCheckAnwser(RadioButton rbtnX, int idImage)    //hien thi su kien nguoi dung chon dap an X
    {
        RadioButton[] listRdbtn = {rbtnA, rbtnB, rbtnC};
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
                case R.id.rbtnAPart2:
                    setDisplayCheckAnwser(rbtnA, R.drawable.a1);
                    status.setCustomerAnswer(count, 1);
                    return;
                case R.id.rbtnBPart2:
                    setDisplayCheckAnwser(rbtnB, R.drawable.b1);
                    status.setCustomerAnswer(count, 2);
                    return;
                case R.id.rbtnCPart2:
                    setDisplayCheckAnwser(rbtnC, R.drawable.c1);
                    status.setCustomerAnswer(count, 3);
                    return;
            }
    }

    public void resetRbtn() {
        rbtnA.setBackgroundResource(R.drawable.a);
        rbtnB.setBackgroundResource(R.drawable.b);
        rbtnC.setBackgroundResource(R.drawable.c);
        rbtnA.setChecked(false);
        rbtnB.setChecked(false);
        rbtnC.setChecked(false);
    }


}
