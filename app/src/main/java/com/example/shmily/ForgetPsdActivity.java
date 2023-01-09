package com.example.shmily;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ForgetPsdActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getName();
    private ImageView mshowCode;
    private EditText mCode;
    private EditText mEmail;
    //产生的验证码
    private String realCode;
    private TextView mUpdateCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_password_forget);
        mshowCode = findViewById(R.id.iv_showCode);
        //将验证码用图片的形式显示出来
        mshowCode.setImageBitmap(Code.getInstance().createBitmap());
        realCode = Code.getInstance().getCode().toLowerCase();
        mshowCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mshowCode.setImageBitmap(Code.getInstance().createBitmap());
                realCode = Code.getInstance().getCode().toLowerCase();
                Log.v(TAG, "realCode" + realCode);
            }
        });

        mCode = findViewById(R.id.fpsd_input_code);
        Button SendCaptcha = findViewById(R.id.send_code);
        SendCaptcha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneCode = mCode.getText().toString().toLowerCase();
                String msg = "生成的验证码：" + realCode + "输入的验证码:" + phoneCode;
                Toast.makeText(ForgetPsdActivity.this, msg, Toast.LENGTH_LONG).show();
                if (phoneCode.equals(realCode)) {
                    Toast.makeText(ForgetPsdActivity.this, phoneCode + "验证码正确", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(ForgetPsdActivity.this,PsdResetActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(ForgetPsdActivity.this, phoneCode + "验证码错误", Toast.LENGTH_SHORT).show();
                }

            }
        });
        mUpdateCode=findViewById(R.id.update_code);
        mUpdateCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mshowCode.setImageBitmap(Code.getInstance().createBitmap());
                realCode=Code.getInstance().getCode().toLowerCase();
            }
        });


    }
}