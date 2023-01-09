package com.example.shmily;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    private Button mLoginButton;
    private EditText mEmail;
    private EditText mPassword;
    private TextView mForgetpsd;
    private TextView mSignup;
    public static ArrayList<User> mUserList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_login);

        mLoginButton=findViewById(R.id.login_button_login);
        mEmail=findViewById(R.id.login_editemail);
        mPassword=findViewById(R.id.login_editpsd);
        mForgetpsd=findViewById(R.id.login_forgetpsd);
        mSignup=findViewById(R.id.login_signup);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 获取用户输入的数据
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                // 判断用户是否登录成功（设置合法的用户名和密码--改）
                checkUser(email, password);

                mSignup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                        startActivity(intent);
                    }
                });

                mForgetpsd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(LoginActivity.this, ForgetPsdActivity.class);
                        startActivity(intent);
                    }
                });


            }

        });
    }
    private void checkUser(String email, String psd) {

        String userEmail;
        String userPsd;
        for (int i = 0; i < mUserList.size(); i++) {
            userEmail = mUserList.get(i).getEmail();
            userPsd = mUserList.get(i).getPassword();
            if (email.equals(userEmail) && psd.equals(userPsd)) {
                //登陆成功
                break;
            }
        }
        Toast.makeText(LoginActivity.this, "登录失败，用户名或密码错误", Toast.LENGTH_LONG).show();
        // 清空用户名和密码文本框
        mEmail.setText("请输入邮箱账号");
        mPassword.setText("请输入密码");
        // 让文本框获取焦点
        mEmail.requestFocus();
    }
}
