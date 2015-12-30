package com.study.bmobapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.study.bmobapplication.entity.JKUser;

import cn.bmob.v3.listener.SaveListener;

public class RegActivity extends AppCompatActivity implements OnClickListener {

    private EditText et_name, et_password;
    private Button btn_login, btn_reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        initView();
    }

    private void initView() {
        et_name = (EditText) findViewById(R.id.et_name);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_reg = (Button) findViewById(R.id.btn_reg);
        btn_login.setOnClickListener(this);
        btn_reg.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_reg:
                JKUser user = new JKUser();
                user.setUsername(et_name.getText().toString().trim());
                user.setPassword(et_password.getText().toString().trim());
                user.setInfo("Bmob application");
                user.signUp(this, new SaveListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(RegActivity.this, "注册成功", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        Toast.makeText(RegActivity.this, "注册失败" + s, Toast.LENGTH_LONG).show();
                    }
                });
                break;
            default:
                break;
        }
    }
}
