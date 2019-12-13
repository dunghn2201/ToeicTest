package com.dunghn.toeictest;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dunghn.toeictest.dialog.Dialog_Result;
import com.dunghn.toeictest.dialog.Dialog_getName;
import com.dunghn.toeictest.model.Result;
import com.dunghn.toeictest.model.Status;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ResultActivity extends AppCompatActivity {
    Button btnStatus, btnSaveName, btnReviewPart;
    TextView tvResult;
    ImageView imgRes;
    Status status = new Status();
    Result resultcommon = new Result();
    Toolbar toolbarresult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        String titleresult="CONGRATULATION";
        toolbarresult = (Toolbar) findViewById(R.id.toolbarresult);
        setSupportActionBar(toolbarresult);
        toolbarresult.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        getSupportActionBar().setTitle(titleresult + "");
        toolbarresult.setTitle(Html.fromHtml("<font color='#ffffff'>"+titleresult+" </font>"));

        tvResult = (TextView) findViewById(R.id.tvResult);
        imgRes = (ImageView) findViewById(R.id.imgRes);
        btnStatus = (Button) findViewById(R.id.btnXemDapAn);
        btnSaveName = (Button) findViewById(R.id.btnSaveName);
        btnReviewPart = (Button) findViewById(R.id.btnReviewPart);
        //-------
        btnSaveName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultcommon.setScore(getTotalPoint(status));

                Date today = new Date(System.currentTimeMillis());
                SimpleDateFormat timeFormat = new SimpleDateFormat();
                String s = timeFormat.format(today.getTime());
                resultcommon.setTime(s);
                Dialog_getName Dialog_getName = new Dialog_getName(resultcommon);
                Dialog_getName.show(getFragmentManager(), "");

            }
        });
        btnReviewPart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Part1Activity.class);
                intent.putExtra("status", status);
                intent.putExtra("AllowWatchRes", true);
                startActivity(intent);
            }
        });
        //-------
        btnStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog_Result dialog_status = new Dialog_Result(status);
                dialog_status.show(getFragmentManager(), "");
            }
        });
        //
        Intent callIntent = getIntent();
        status = (Status) callIntent.getSerializableExtra("status");
        //
        tvResult.setText(String.valueOf(getTotalPoint(status)));
        toolbarresult.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private int getTotalPoint(Status status) {
                int count = 0, point = 0;
        for (int i = 0; i < 100; i++)
            if (status.getCustomerAnwser(i) != 0 && status.getCustomerAnwser(i) == status.getKey()[i])
                count++;
        int[] checkPoint = new int[101];
        for (int i = 0; i <= 6; i++)
            checkPoint[i] = 5;
        for (int i = 7; i <= 53; i++)
            checkPoint[i] = 5 + (i - 6) * 5;
        for (int i = 54; i <= 87; i++)
            checkPoint[i] = 270 + (i - 54) * 5;
        for (int i = 88; i <= 92; i++)
            checkPoint[i] = 470 + (i - 88) * 5;
        for (int i = 93; i <= 100; i++)
            checkPoint[i] = 495;
        return checkPoint[count];
    }
}
