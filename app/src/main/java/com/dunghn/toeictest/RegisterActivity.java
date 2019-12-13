package com.dunghn.toeictest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.dunghn.toeictest.DAO.UserDAO;
import com.dunghn.toeictest.model.User;


public class RegisterActivity extends AppCompatActivity {
    private ImageView imgRegister,imgTxtRegister;
    private Toolbar toolbar;

    EditText medUserNameRegister, medPasswordRegister, medConfirmPassRegister, medFullNameRegister, medEmailRegister;
    Button mbtnRegister;
    UserDAO userDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Anhxa();
        onClick();

        mbtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sedUserNameRegister = medUserNameRegister.getText().toString();
                String sedPasswordRegister = medPasswordRegister.getText().toString();
                String sedFullnameRegister = medFullNameRegister.getText().toString();
                String sedEmailRegister = medEmailRegister.getText().toString();
                userDAO = new UserDAO(RegisterActivity.this);
                User user = new User(sedUserNameRegister, sedPasswordRegister, sedFullnameRegister, sedEmailRegister);
                try {
                    if (validateForm() > 0) {
                        if (userDAO.insertUser(user) > 0) {
                            Toast.makeText(getApplicationContext(), "Register Success", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RegisterActivity.this, "Account already exists\n" +
                                    "  Please enter another account" , Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "Something went wrong\n" +
                                "Please check the data input", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception ex) {
                    Log.e("Error", ex.toString());
                }
            }
        });
    }
    private void Anhxa() {
        imgRegister = (ImageView) findViewById(R.id.imgRegister);
        imgTxtRegister = (ImageView) findViewById(R.id.imgTxtRegister);

        medUserNameRegister = (EditText) findViewById(R.id.edUsernameRegister);
        medPasswordRegister = (EditText) findViewById(R.id.edPasswordRegister);
        medConfirmPassRegister = (EditText) findViewById(R.id.edConfirmPass);
        medFullNameRegister = (EditText) findViewById(R.id.edFullNameRegister);
        medEmailRegister = (EditText) findViewById(R.id.edEmailRegister);
        mbtnRegister = (Button) findViewById(R.id.btnRegister);
    }
    public int validateForm() {
        int check = 1;
        if (medUserNameRegister.getText().length() == 0 || medFullNameRegister.getText().length() == 0||medPasswordRegister.getText().length() == 0||medConfirmPassRegister.getText().length() == 0||medEmailRegister.getText().length() == 0) {
            Toast.makeText(getApplicationContext(), "Please complete all the information", Toast.LENGTH_SHORT).show();
            check = -1;
        } else {
            String sedPassword = medPasswordRegister.getText().toString();
            String sedRePassword = medConfirmPassRegister.getText().toString();
            if (!sedPassword.equals(sedRePassword)) {
                Toast.makeText(getApplicationContext(), "Password is not allowed to match", Toast.LENGTH_SHORT).show();
                check = -1;
            }
            if(sedPassword.length()<3||sedRePassword.length()<3){
                Toast.makeText(this, "You must enter at least 3 characters", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }
    private void onClick() {
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
