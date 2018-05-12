package top.jacksonyang.doctorialhat.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.math.BigInteger;
import java.security.MessageDigest;

import top.jacksonyang.doctorialhat.Gson.User;
import top.jacksonyang.doctorialhat.R;
import top.jacksonyang.doctorialhat.Utils.encodeBySHA;

public class RegisterActivity extends AppCompatActivity {

    private Button register;//注册按钮
    private Button SendCaptcha;//获取验证码按钮
    private EditText NewPasswd;//新密码
    private EditText phone;//手机号码
    private EditText captcha;//验证码
    private String mNewPasswd;
    private String mPhone;
    private String mChapcha;
    private String encodePasswd;//加密后的密码
    //快速登录
    private ImageButton wechat;//微信
    private ImageButton oicq;//QQ
    private ImageButton mblog;//新浪微博

    private ImageButton back;//返回 按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //初始化
        SendCaptcha = findViewById(R.id.get_captcha);
        register = findViewById(R.id.register);
        phone = findViewById(R.id.register_phone);
        NewPasswd = findViewById(R.id.register_password);
        captcha = findViewById(R.id.register_captcha);
        wechat = findViewById(R.id.wechat_login);
        oicq = findViewById(R.id.oicq_login);
        mblog = findViewById(R.id.mblog_login);

        mNewPasswd = NewPasswd.getText().toString();
        mPhone = phone.getText().toString();
        mChapcha = captcha.getText().toString();


        //返回按钮
        back= findViewById(R.id.back) ;
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //点击注册按钮
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mPhone)) {
                    Toast.makeText(RegisterActivity.this, "手机号不能为空！", Toast.LENGTH_SHORT).show();
                    register.setClickable(false);
                }
                if (isPhoneNo(mPhone)) {
                    Toast.makeText(RegisterActivity.this, "请输入正确的手机号！", Toast.LENGTH_SHORT).show();
                    register.setClickable(false);
                }
                //密码最低6位，由字母和数字组合
                if (TextUtils.isEmpty(mNewPasswd)) {
                    Toast.makeText(RegisterActivity.this, "密码不能为空！", Toast.LENGTH_SHORT).show();
                    register.setClickable(false);
                }
                if (isPassword(mNewPasswd)) {
                    Toast.makeText(RegisterActivity.this, "请按正确格式输入密码！", Toast.LENGTH_SHORT).show();
                    register.setClickable(false);
                }
                if (TextUtils.isEmpty(mChapcha)) {
                    Toast.makeText(RegisterActivity.this, "验证码不能为空！", Toast.LENGTH_SHORT).show();
                    register.setClickable(false);
                }
                if (isChapcha(mChapcha)) {
                    Toast.makeText(RegisterActivity.this, "请输入6位验证码！", Toast.LENGTH_SHORT).show();
                    register.setClickable(false);
                }
                //注册成功后，保存用户的手机密码到服务端
                //此版本保存到本地
                User user = new User();
                user.setPhone(mPhone);
                encodeBySHA encode = new encodeBySHA();
                encodePasswd = encode.encodeBySHA(mNewPasswd);
                user.setPassword(encodePasswd);
                user.save();
            }
        });
        SendCaptcha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击获取验证码按钮，服务端发送短信给手机，用户获取验证码
                //代码
            }
        });
        //快速登录部分
        //代码
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

    public static boolean isPassword(String password) {
        String pasRege = "^[a-zA-Z0-9]{6,16}$";
        return password.matches(pasRege);
    }

    public static boolean isChapcha(String chapcha) {
        String chaRege = "(?<![0-9])([0-9]{" + 6 + "})(?![0-9])";
        return chapcha.matches(chaRege);
    }
}


