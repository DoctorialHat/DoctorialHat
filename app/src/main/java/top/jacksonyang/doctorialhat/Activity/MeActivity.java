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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);

        aboutUs = (Button) findViewById(R.id.guanyuwomen);
        myInformation=(Button)findViewById(R.id.myinformation);
        setting=(Button)findViewById(R.id.setting);
        bugReflect=(Button)findViewById(R.id.bugreflect) ;

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

    }



}
