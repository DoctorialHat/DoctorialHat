package top.jacksonyang.doctorialhat.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import top.jacksonyang.doctorialhat.R;

public class RegisterActivity extends AppCompatActivity {
    private Button register;//注册按钮
    private Button SendCaptcha;//获取验证码按钮
    private EditText NewPasswd;//新密码
    private EditText phone;//手机号码
    private EditText captcha;//验证码
    private String mNewPasswd;
    private String mPhone;
    private String mChapcha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        SendCaptcha = (Button) findViewById(R.id.button2);
        register = (Button) findViewById(R.id.button);
        phone = (EditText) findViewById(R.id.editText);
        NewPasswd = (EditText) findViewById(R.id.editText4);
        captcha = (EditText) findViewById(R.id.editText2);

        mNewPasswd = NewPasswd.getText().toString();
        mPhone = phone.getText().toString();
        mChapcha = captcha.getText().toString();
        //点击注册按钮
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPhoneNo(mPhone)) {
                    Toast.makeText(RegisterActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                    register.setClickable(false);
                }
            }
        });
    }
    public static boolean isPhoneNo(String phone){
        /*
        13(老)号段：130、131、132、133、134、135、136、137、138、139
        14(新)号段：145、147
        15(新)号段：150、151、152、153、154、155、156、157、158、159
        17(新)号段：170、171、173、175、176、177、178
        18(3G)号段：180、181、182、183、184、185、186、187、188、189
         */
        String telRege="[1][34578]\\d{9}";
        if(TextUtils.isEmpty(phone)) return false;
        else return phone.matches(telRege);
    }
}
