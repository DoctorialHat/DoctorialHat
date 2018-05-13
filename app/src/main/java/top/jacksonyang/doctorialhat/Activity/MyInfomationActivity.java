package top.jacksonyang.doctorialhat.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import top.jacksonyang.doctorialhat.R;

public class MyInfomationActivity extends AppCompatActivity {

    private ImageButton back;//返回按钮
    private Button changeNickName; //修改昵称按钮
    private Button changePhoneNumber;//修改手机号码按钮
    private Button changePassword; //修改密码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_infomation);

        //初始化所有View
        back = findViewById(R.id.back);
        changeNickName = findViewById(R.id.change_nick_name);
        changePhoneNumber = findViewById(R.id.change_phone_number);
        changePassword = findViewById(R.id.change_password);

        //跳转修改昵称活动
        changeNickName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //跳转修改手机号活动
        changePhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //跳转修改密码活动
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
