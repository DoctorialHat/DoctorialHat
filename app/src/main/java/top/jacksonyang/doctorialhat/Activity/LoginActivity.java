package top.jacksonyang.doctorialhat.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.List;

import javax.security.auth.callback.Callback;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import top.jacksonyang.doctorialhat.Gson.User;
import top.jacksonyang.doctorialhat.R;
import top.jacksonyang.doctorialhat.Utils.WebUtils;
import top.jacksonyang.doctorialhat.Utils.encodeBySHA;

public class LoginActivity extends AppCompatActivity {
    private Button login;//登录
    private EditText phone;//手机号码
    private EditText password;//密码
    private String mphone;
    private String mpassword;
    private String encodePasswd;
    private CheckBox saveuser;
    private ImageButton back;//返回按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login= findViewById(R.id.login);
        phone= findViewById(R.id.login_phone);
        password= findViewById(R.id.login_password);

        mphone= phone.getText().toString();
        mpassword= password.getText().toString();

        //返回按钮
        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //点击登录按钮
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(mphone)){
                    Toast.makeText(LoginActivity.this,"手机号不能为空！",Toast.LENGTH_SHORT).show();
                    login.setClickable(false);
                }
                if (isPhoneNo(mphone)) {
                    Toast.makeText(LoginActivity.this, "请输入正确的手机号！", Toast.LENGTH_SHORT).show();
                    login.setClickable(false);
                }
                if(TextUtils.isEmpty(mpassword)){
                    Toast.makeText(LoginActivity.this,"密码不能为空！",Toast.LENGTH_SHORT).show();
                    login.setClickable(false);
                }

               //发Post(含phone)请求到服务器并接受返回
                RequestBody requestBody=new FormBody.Builder().add("phone",mphone).build();
                WebUtils.sendPostOkHttpRequest("address", requestBody, new okhttp3.Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.w("Login","传输失败！");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Gson gson=new Gson();
                        encodeBySHA encode=new encodeBySHA();
                        encodePasswd=encode.encodeBySHA(mpassword);
                        //List<User> users= DataSupport.findAll(User.class);
                        User users=gson.fromJson(response.body().string(),new TypeToken<User>(){}.getType());
                        if(!(users.equals(mphone))||!(users.equals(encodePasswd))){
                            Toast.makeText(LoginActivity.this,"输入的手机号或密码错误！",Toast.LENGTH_SHORT).show();
                            login.setClickable(false);
                        }
                        else{
                            Toast.makeText(LoginActivity.this,"登录成功！",Toast.LENGTH_SHORT).show();
                            Intent loginToMe =new Intent(LoginActivity.this,MeActivity.class);
                            startActivity(loginToMe);
                        }
                    }
                });


            }
        });
    }
    public static boolean isPhoneNo(String phone) {
        /*
        13(老)号段：130、131、132、133、134、135、136、137、138、139
        14(新)号段：145、147
        15(新)号段：150、151、152、153、154、155、156、157、158、159
        17(新)号段：170、171、173、175、176、177、178
        18(3G)号段：180、181、182、183、184、185、186、187、188、189
         */
        String telRege = "[1][34578]\\d{9}";
        return phone.matches(telRege);
    }
}
