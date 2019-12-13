package com.dunghn.toeictest;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
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

import com.dunghn.toeictest.DAO.UserDAO;
import com.dunghn.toeictest.model.User;


public class LoginActivity extends AppCompatActivity {
    private ImageView imgLogin, imgleft, imgright, imgTxtLogin;
    private ImageView imgRegister;
    private EditText medUsername,medPassword;
    Animation animation;
    int s3;
    Button btnlogin;
    String strUser, strPass;
    UserDAO db;
    User user;
    boolean res;
    String fullnamenavhead,emailnavhead;
    private ProgressDialog progressBar;
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
                db = new UserDAO(LoginActivity.this);


                user = new User(strUser, strPass);
                res = db.checkUser(user);
                if (strUser.isEmpty() || strPass.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập vào các trường", Toast.LENGTH_SHORT).show();
                } else if (strUser.equalsIgnoreCase("admin") && strPass.equalsIgnoreCase("admin")) {
                    SendIdByPreferences(new User("Administrator", strPass));
                    progressBar = new ProgressDialog(LoginActivity.this);//Create new object of progress bar type
                    progressBar.setCancelable(false);//Progress bar cannot be cancelled by pressing any where on screen
                    progressBar.setMessage("Please Wait...");//Title shown in the progress bar
                    progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);//Style of the progress bar
                    progressBar.setProgress(0);//attributes
                    progressBar.setMax(100);//attributes
                    progressBar.show();//show the progress bar
                    //This handler will add a delay of 3 seconds
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Intent start to open the navigation drawer activity
                            progressBar.cancel();//Progress bar will be cancelled (hide from screen) when this run function will execute after 3.5seconds
                            Intent intent2 = new Intent(LoginActivity.this, MainActivity.class);

                            Bundle b2 = new Bundle();
                            b2.putString("FULLNAMENAVHEAD","Administrator");
                            b2.putString("EMAILNAVHEAD", "");
                            intent2.putExtras(b2);
                            startActivity(intent2);
                        }
                    }, 1000);

                } else if (res == true) {
                    SendIdByPreferences(new User(strUser, strPass));
                    db = new UserDAO(LoginActivity.this);
                    s3 = db.getIdFromUserName(strUser);
                    Cursor rs=db.getDataFromId(s3);

                    rs.moveToFirst();
                    fullnamenavhead=rs.getString(rs.getColumnIndex(UserDAO.COL_FULLN));
                    emailnavhead=rs.getString(rs.getColumnIndex(UserDAO.COL_EMAIL));

                    if (!rs.isClosed())  {
                        rs.close();
                    }

//                    Toast.makeText(LoginActivity.this, ""+fullnamenavhead+" " +emailnavhead, Toast.LENGTH_SHORT).show();
                    progressBar = new ProgressDialog(LoginActivity.this);//Create new object of progress bar type
                    progressBar.setCancelable(false);//Progress bar cannot be cancelled by pressing any where on screen
                    progressBar.setMessage("Please Wait...");//Title shown in the progress bar
                    progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);//Style of the progress bar
                    progressBar.setProgress(0);//attributes
                    progressBar.setMax(100);//attributes
                    progressBar.show();//show the progress bar
                    //This handler will add a delay of 3 seconds
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            //Intent start to open the navigation drawer activity
                            progressBar.cancel();//Progress bar will be cancelled (hide from screen) when this run function will execute after 3.5seconds

                            Intent intent2  = new Intent(LoginActivity.this, MainActivity.class);
                            Bundle b2 = new Bundle();
                            b2.putString("FULLNAMENAVHEAD",fullnamenavhead);
                            b2.putString("EMAILNAVHEAD", emailnavhead);
                            intent2.putExtras(b2);

                            startActivity(intent2);
                            Toast.makeText(getApplicationContext(), "Login success", Toast.LENGTH_SHORT).show();
                        }
                    }, 1000);
                } else {
                    Toast.makeText(getApplicationContext(), "Username and password are incorrect", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    public void SendIdByPreferences(User u) {
        SharedPreferences pref = getSharedPreferences("USER_FILE_CHANGEPASS", MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        edit.putString("USERNAME", u.getUsername());
        edit.putString("PASSWORD", u.getPassword());

        //luu lai toan bo
        edit.commit();
    }

}
