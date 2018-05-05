package top.jacksonyang.doctorialhat.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import top.jacksonyang.doctorialhat.R;


public class MeActivity extends AppCompatActivity {

    private Button aboutUs;
    private Button myInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);

        aboutUs = (Button) findViewById(R.id.guanyuwomen);
        myInformation=(Button)findViewById(R.id.myinformation);

        myInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent meToMyInfo=new Intent(MeActivity.this,MyInfomationActivity.class);
                startActivity(meToMyInfo);
            }
        });

        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent meToAboutUs = new Intent(MeActivity.this,AboutUs.class);
                startActivity(meToAboutUs);
            }
        });

    }



}
