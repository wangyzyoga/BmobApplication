package com.study.bmobapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.study.bmobapplication.constants.TimeChoose;
import com.study.bmobapplication.entity.JKUser;

import cn.bmob.v3.BmobUser;

public class WelActivity extends AppCompatActivity {

    private TextView et_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wel);
        initView();
    }

    private void initView() {
        et_name = (TextView) findViewById(R.id.et_name);
        JKUser userInfo = BmobUser.getCurrentUser(this, JKUser.class);
        et_name.setText("尊敬的" + userInfo.getUsername() + "：欢迎您！");

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BmobUser.logOut(WelActivity.this);
                BmobUser currentUser = BmobUser.getCurrentUser(WelActivity.this);

                Intent intent = new Intent(WelActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


}
