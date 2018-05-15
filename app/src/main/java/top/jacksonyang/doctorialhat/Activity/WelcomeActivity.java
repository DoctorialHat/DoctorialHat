package top.jacksonyang.doctorialhat.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import cn.jpush.sms.SMSSDK;
import top.jacksonyang.doctorialhat.R;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
//        SMSSDK.getInstance().initSdk(WelcomeActivity.this);
        Intent intent=new Intent(WelcomeActivity.this,MeActivity.class);
        startActivity(intent);
    }
}
