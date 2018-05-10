package top.jacksonyang.doctorialhat.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import top.jacksonyang.doctorialhat.R;

public class SettingActivity extends AppCompatActivity {

    private ImageButton back;      //返回 按钮
    private TextView newMessage;     //新消息通知 按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        //返回 我的 页面
        back= findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //进入 新消息通知
        newMessage= findViewById(R.id.newmessage);
        newMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingToNewMessage=new Intent(SettingActivity.this,NewMessageActivity.class);
                startActivity(settingToNewMessage);
            }
        });
    }
}
