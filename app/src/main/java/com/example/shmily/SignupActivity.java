package com.example.shmily;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {
    public EditText mEmail;
    private Button mSendCaptcha;
    public User mUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String email= mEmail.getText().toString().trim();
        User mUser=new User(email);
        LoginActivity.mUserList.add(mUser);

        mSendCaptcha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //发送验证码
                Toast.makeText(SignupActivity.this, "已发送验证码", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(SignupActivity.this,CaptchaActivity.class);
                startActivity(intent);

            }
        });

    }
}
