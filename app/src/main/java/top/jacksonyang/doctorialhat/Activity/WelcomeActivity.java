package top.jacksonyang.doctorialhat.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.mob.MobSDK;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import top.jacksonyang.doctorialhat.R;

import static top.jacksonyang.doctorialhat.Utils.Constants.ONE_SERVER;
import static top.jacksonyang.doctorialhat.Utils.JsonCommand.*;
import static top.jacksonyang.doctorialhat.Utils.WebUtils.*;

public class WelcomeActivity extends AppCompatActivity {

    private TextView oneSentence;//一句话
    private Button login;//登录
    private Button register;//注册

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //全屏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcome);

        //初始化
        oneSentence = findViewById(R.id.one_sentence);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);

        //显示一句话
        loadOne();

        //设置按钮跳转界面
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toLogin = new Intent(WelcomeActivity.this,LoginActivity.class);
                startActivity(toLogin);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toRegister = new Intent(WelcomeActivity.this,RegisterActivity.class);
                startActivity(toRegister);
            }
        });
    }

    private void loadOne(){
        sendGetOkHttpRequest(ONE_SERVER, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(WelcomeActivity.this,"获取one信息失败",Toast.LENGTH_SHORT).show();
                    }
                });
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseJSON = response.body().string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(parseOne(responseJSON)){
                            try{
                                JSONObject oneJSON = new JSONObject(responseJSON);
                                oneSentence.setText("“ "+oneJSON.getString("hitokoto")+" ”");
                            } catch (JSONException e){
                                e.printStackTrace();
                            }
                        }

                    }
                });
            }
        });
    }
}
