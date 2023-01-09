package com.example.shmily;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SettingActivity extends AppCompatActivity {

    private Switch mGpsSwitch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage_setting);
        mGpsSwitch=findViewById(R.id.GPS_switch);

        mGpsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b== true) {
                    Toast.makeText(getBaseContext(), "开启定位", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getBaseContext(), "关闭定位", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
