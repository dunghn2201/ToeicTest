package com.dunghn.toeictest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dunghn.toeictest.DAO.NguoiDungDAO;
import com.dunghn.toeictest.model.NguoiDung;


public class LoginActivity extends AppCompatActivity {
    private ImageView imgLogin, imgleft, imgright, imgTxtLogin;
    private ImageView imgRegister;
    private EditText medUsername,medPassword;
    Animation animation;
    int s3;
    Button btnlogin;
    String strUser, strPass;
    NguoiDungDAO db;
    NguoiDung user;
    boolean res;
    String fullnamenavhead,emailnavhead;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Anhxa();
        animationLogin();
        onClick();
    }
    private void Anhxa() {
        imgTxtLogin = (ImageView) findViewById(R.id.imgTxtLogin);
        imgLogin = (ImageView) findViewById(R.id.imgLogo);
        imgleft = (ImageView) findViewById(R.id.imgleft);
        imgright = (ImageView) findViewById(R.id.imgright);
        imgRegister = (ImageView) findViewById(R.id.imgRegister);
        medUsername = (EditText) findViewById(R.id.edUsername);
        medPassword = (EditText) findViewById(R.id.edPassword);

        btnlogin = (Button) findViewById(R.id.btnLogin);
        imgRegister = (ImageView) findViewById(R.id.imgRegister);

    }
    private void animationLogin() {
        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.push_down);
        imgTxtLogin.setAnimation(animation);

        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.push_left);
        medUsername.setAnimation(animation);

        animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.push_right);
        medPassword.setAnimation(animation);
    }

    private void onClick() {
        imgRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent main2activity = new Intent(LoginActivity.this, RegisterActivity.class);

                Pair[] pairs = new Pair[2];
                pairs[0] = new Pair<View, String>(imgLogin, "imgLogo");
                pairs[1] = new Pair<View, String>(imgTxtLogin, "imgTxtLogo");
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this, pairs);
                startActivity(main2activity, options.toBundle());
            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strUser = medUsername.getText().toString().trim();
                strPass = medPassword.getText().toString().trim();
                db = new NguoiDungDAO(LoginActivity.this);


                user = new NguoiDung(strUser, strPass);
                res = db.checkUser(user);
                if (strUser.isEmpty() || strPass.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập vào các trường", Toast.LENGTH_SHORT).show();
                } else if (strUser.equalsIgnoreCase("admin") && strPass.equalsIgnoreCase("admin")) {
                    Intent intent2 = new Intent(LoginActivity.this, MainActivity.class);

                    Bundle b2 = new Bundle();
                    b2.putString("FULLNAMENAVHEAD","Administrator");
                    b2.putString("EMAILNAVHEAD", "");
                    intent2.putExtras(b2);
                    startActivity(intent2);
                } else if (res == true) {
                    SendIdByPreferences(new NguoiDung(strUser, strPass));

                    Intent intent2  = new Intent(LoginActivity.this, MainActivity.class);
                    db = new NguoiDungDAO(LoginActivity.this);
                    s3 = db.getIdFromUserName(strUser);
                    Cursor rs=db.getDataFromId(s3);

                    rs.moveToFirst();
                    fullnamenavhead=rs.getString(rs.getColumnIndex(NguoiDungDAO.COL_FULLN));
                    emailnavhead=rs.getString(rs.getColumnIndex(NguoiDungDAO.COL_EMAIL));

                    if (!rs.isClosed())  {
                        rs.close();
                    }

                    Toast.makeText(LoginActivity.this, ""+fullnamenavhead+" " +emailnavhead, Toast.LENGTH_SHORT).show();

                    Bundle b2 = new Bundle();
                    b2.putString("FULLNAMENAVHEAD",fullnamenavhead);
                    b2.putString("EMAILNAVHEAD", emailnavhead);
                    intent2.putExtras(b2);

                    startActivity(intent2);
                    finish();
                    Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Tên đăng nhập và mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    public void SendIdByPreferences(NguoiDung nd) {
        SharedPreferences pref = getSharedPreferences("USER_FILE_CHANGEPASS", MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        edit.putString("USERNAME", nd.getUsername());
        edit.putString("PASSWORD", nd.getPassword());

        //luu lai toan bo
        edit.commit();
    }

}
