package top.jacksonyang.doctorialhat.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import top.jacksonyang.doctorialhat.R;


public class MeActivity extends AppCompatActivity {

    private Button aboutUs;              //关于我们 按钮
    private Button myInformation;        //我的信息 按钮
    private Button setting;              //设置 按钮
    private Button bugReflect;           //问题反馈 按钮
    private Button login;                //登录 按钮
    private Button register;             //注册 按钮
    private CircleImageView load_picture; //上传头像 按钮

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);

        aboutUs = findViewById(R.id.about_us);
        myInformation= findViewById(R.id.my_information);
        setting= findViewById(R.id.setting);
        bugReflect= findViewById(R.id.bug_reflect);
        login= findViewById(R.id.jump_to_login);
        register= findViewById(R.id.jump_to_register);

        //进入 问题反馈 页面
        bugReflect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent meToBugReflect=new Intent(MeActivity.this,BugReflectActivity.class);
                startActivity(meToBugReflect);
            }
        });

        //进入 设置 页面
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent meToSetting=new Intent(MeActivity.this,SettingActivity.class);
                startActivity(meToSetting);
            }
        });

        //进入 我的信息 页面
        myInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent meToMyInfo=new Intent(MeActivity.this,MyInfomationActivity.class);
                startActivity(meToMyInfo);
            }
        });

        //进入 关于我们 页面
        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent meToAboutUs = new Intent(MeActivity.this,AboutUs.class);
                startActivity(meToAboutUs);
            }
        });

        //进入登录页面
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent meToLogin=new Intent(MeActivity.this,LoginActivity.class);
                startActivity(meToLogin);
            }
        });
        //进入注册页面
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent meToRegister=new Intent(MeActivity.this,RegisterActivity.class);
                startActivity(meToRegister);
            }
        });

    }



}
