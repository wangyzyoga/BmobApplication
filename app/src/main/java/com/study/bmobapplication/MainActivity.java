package com.study.bmobapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.study.bmobapplication.constants.Conf;
import com.study.bmobapplication.entity.JKUser;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXTextObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.listener.SaveListener;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private EditText et_name, et_password;
    private Button btn_login, btn_reg, btn_share;
    private IWXAPI api;
    public static final String APP_ID = "wx2b1efc2acbd11ac0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        api = WXAPIFactory.createWXAPI(this, APP_ID);
        api.registerApp(APP_ID);

        Bmob.initialize(this, Conf.APP_ID);
        initView();
    }

    private void initView() {
        et_name = (EditText) findViewById(R.id.et_name);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_reg = (Button) findViewById(R.id.btn_reg);
        btn_share = (Button) findViewById(R.id.btn_share);
        btn_login.setOnClickListener(this);
        btn_reg.setOnClickListener(this);
        btn_share.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                JKUser user = new JKUser();
                user.setUsername(et_name.getText().toString().trim());
                user.setPassword(et_password.getText().toString().trim());
                user.login(this, new SaveListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(MainActivity.this, WelActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        Toast.makeText(MainActivity.this, "登录失败" + s, Toast.LENGTH_LONG).show();
                    }
                });
                break;
            case R.id.btn_reg:
                Intent intent = new Intent(this, RegActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_share:
                // 初始化一个WXTextObject对象
                WXTextObject textObj = new WXTextObject();
                textObj.text = "text微信分享";
                // 用WXTextObject对象初始化一个WXMediaMessage对象
                WXMediaMessage msg = new WXMediaMessage();
                msg.mediaObject = textObj;
                msg.description = "description微信分享";
                // 构造一个Req
                SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.transaction = String.valueOf(System.currentTimeMillis());
                req.message = msg;
                req.scene = SendMessageToWX.Req.WXSceneTimeline;
                // 调用api接口发送数据到微信
                api.sendReq(req);
                Log.i("WeiXin", ">>>>>>>>>>>>>>>>WeiXin!");
                break;
            default:
                break;
        }
    }
}
