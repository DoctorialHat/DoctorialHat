package top.jacksonyang.doctorialhat.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.List;

import top.jacksonyang.doctorialhat.Gson.User;
import top.jacksonyang.doctorialhat.R;
import top.jacksonyang.doctorialhat.Utils.encodeBySHA;

public class LoginActivity extends AppCompatActivity {
    private Button login;//登录
    private EditText phone;//手机号码
    private EditText password;//密码
    private String mphone;
    private String mpassword;
    private String encodePasswd;
    private CheckBox saveuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login=(Button)findViewById(R.id.button);
        phone=(EditText)findViewById(R.id.editText);
        password=(EditText)findViewById(R.id.editText2);
        mphone=phone.getText().toString();
        mpassword=password.getText().toString();

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
                encodeBySHA encode=new encodeBySHA();
                encodePasswd=encode.encodeBySHA(mpassword);
                List<User> users= DataSupport.findAll(User.class);
                if(!(users.get(1).equals(mphone))||!(users.get(2).equals(encodePasswd))){
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
