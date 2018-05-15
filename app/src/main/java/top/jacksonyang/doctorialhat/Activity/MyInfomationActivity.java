package top.jacksonyang.doctorialhat.Activity;

import android.content.Intent;
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
        changeNickName = findViewById(R.id.change_information);
        changePhoneNumber = findViewById(R.id.change_phone_number);
        changePassword = findViewById(R.id.change_password);

        //跳转修改个人信息活动
        changeNickName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toChangeInfo = new Intent(MyInfomationActivity.this,ChangeInformationActivity.class);
                startActivity(toChangeInfo);
            }
        });

        //跳转修改手机号活动
        changePhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toChangePhone = new Intent(MyInfomationActivity.this,ChangePhoneNumberActivity.class);
                startActivity(toChangePhone);
            }
        });

        //跳转修改密码活动
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toChangeCode = new Intent(MyInfomationActivity.this,ChangeCodeActivity.class);
                startActivity(toChangeCode);
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
