package top.jacksonyang.doctorialhat.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;

import top.jacksonyang.doctorialhat.R;

public class NewMessageActivity extends AppCompatActivity {

    private ImageButton back;           //返回 按钮
    private Switch allowMessage;        //消息提醒
    private Switch allowViberate;       //震动提醒
    private Switch allowSound;          //声音提醒

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_message);

        //初始化View组件
        back= findViewById(R.id.back);
        allowMessage = findViewById(R.id.allow_message);
        allowSound = findViewById(R.id.allow_sound);
        allowViberate = findViewById(R.id.allow_viberate);

        //返回 设置 页面
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
