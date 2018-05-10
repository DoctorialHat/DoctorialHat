package top.jacksonyang.doctorialhat.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import top.jacksonyang.doctorialhat.R;

public class MyInfomationActivity extends AppCompatActivity {

    private ImageButton back;//返回按钮
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_infomation);

        //初始化所有View
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
