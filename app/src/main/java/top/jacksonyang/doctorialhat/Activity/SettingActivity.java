package top.jacksonyang.doctorialhat.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import top.jacksonyang.doctorialhat.R;

public class SettingActivity extends AppCompatActivity {

    private ImageButton back;      //返回 按钮
    private TextView newMessage;     //新消息通知 按钮
    private Switch friendVerify;    //加朋友的时候验证
    private Switch allowStranger;   //允许陌生人查看消息
    private Switch changeNightMode; //切换夜间模式
    private Button cleanCache;      //清理缓存按钮
    private Button logOut;          //退出登录


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        //初始化View组件
        back= findViewById(R.id.back);
        newMessage= findViewById(R.id.new_message_alert);
        friendVerify = findViewById(R.id.friend_verify);
        allowStranger = findViewById(R.id.allow_stranger);
        changeNightMode = findViewById(R.id.change_night_mode);
        cleanCache = findViewById(R.id.clean_cache);
        logOut = findViewById(R.id.log_out);

        //返回 我的 页面
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //进入 新消息通知
        newMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingToNewMessage=new Intent(SettingActivity.this,NewMessageActivity.class);
                startActivity(settingToNewMessage);
            }
        });
    }
}
