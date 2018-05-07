package top.jacksonyang.doctorialhat.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import top.jacksonyang.doctorialhat.R;

public class LoginActivity extends AppCompatActivity {
    private Button login;//登录
    private EditText phone;//手机号码
    private EditText password;//密码
    private String mphone;
    private String mpassword;
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
                //if()
            }
        });
    }
}
