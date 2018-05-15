package top.jacksonyang.doctorialhat.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import static top.jacksonyang.doctorialhat.Utils.WebUtils.sendPostOkHttpRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Timer;
import java.util.TimerTask;

//import cn.jpush.sms.SMSSDK;
//import cn.jpush.sms.listener.SmscheckListener;
//import cn.jpush.sms.listener.SmscodeListener;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;
import top.jacksonyang.doctorialhat.Gson.User;
import top.jacksonyang.doctorialhat.R;
import top.jacksonyang.doctorialhat.Utils.Constants;
import top.jacksonyang.doctorialhat.Utils.encodeBySHA;

public class RegisterActivity extends AppCompatActivity {

    private Button register;//注册按钮
    private Button SendCaptcha;//获取验证码按钮
    private EditText NewPasswd;//新密码
    private EditText phone;//手机号码
    private EditText captcha;//验证码
    private Timer timer;//定时器
    private TimerTask timerTask;//定时器任务

    private boolean flag = true;
    private int remainTime;//定时器任务剩余时间
    private String mNewPasswd;
    private String mPhone;
    private String mChapcha;
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
                //获取输入
                mNewPasswd = NewPasswd.getText().toString();
                mPhone = phone.getText().toString();
//                mChapcha = captcha.getText().toString();

                //判断是否为空和错误
                if (TextUtils.isEmpty(mPhone)) {
                    Toast.makeText(RegisterActivity.this, "手机号不能为空！", Toast.LENGTH_SHORT).show();
                    phone.getText().clear();
                    phone.requestFocus();
                }
                if (!isPhoneNo(mPhone)) {
                    Toast.makeText(RegisterActivity.this, "请输入正确的手机号！", Toast.LENGTH_SHORT).show();
                    phone.getText().clear();
                    phone.requestFocus();
                }

                //密码最低6位，由字母和数字组合
                if (TextUtils.isEmpty(mNewPasswd)) {
                    Toast.makeText(RegisterActivity.this, "密码不能为空！", Toast.LENGTH_SHORT).show();
                    NewPasswd.getText().clear();
                    NewPasswd.requestFocus();
                }
                if (!isPassword(mNewPasswd)) {
                    Toast.makeText(RegisterActivity.this, "请按正确格式输入密码！", Toast.LENGTH_SHORT).show();
                    NewPasswd.getText().clear();
                    NewPasswd.requestFocus();
                }
//                if (TextUtils.isEmpty(mChapcha)) {
//                    Toast.makeText(RegisterActivity.this, "验证码不能为空！", Toast.LENGTH_SHORT).show();
//                    return;
//                }

//                //验证验证码
//                checkCaptcha(mPhone,mChapcha);

                //加密密码
                encodeBySHA encode = new encodeBySHA();
                String encodePassword = encode.encodeBySHA(mNewPasswd);
                //封装手机号密码到JSON中
                JSONObject requestBody = new JSONObject();
                try{
                    requestBody.put("phone",mPhone);
                    requestBody.put("password",encodePassword);
                } catch (JSONException e){
                    e.printStackTrace();
                }

                //发送HTTP请求
                sendPostOkHttpRequest(Constants.SERVER + "/user", requestBody, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.d("Error","注册用户名发生错误,请重试");
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RegisterActivity.this,"注册用户名发生错误,请重试",Toast.LENGTH_SHORT).show();
                            }
                        });
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        final String responseText = response.body().string();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Log.d("debug","返回包为"+responseText);
                                try{
                                    JSONObject JSONResponse = new JSONObject(responseText);
                                    User newUser = new User();
                                    newUser.setPhone(JSONResponse.getString("phone"));
                                    newUser.setPassword(JSONResponse.getString("password"));
                                    newUser.setNickName(JSONResponse.getString("nickName"));
                                    newUser.setDescription(JSONResponse.getString("description"));
                                    newUser.setRank(JSONResponse.getInt("rank"));
                                    newUser.setQuestionNum(JSONResponse.getInt("questionNum"));
                                    newUser.setWinRating(JSONResponse.getDouble("winRating"));
                                    newUser.save();
                                    Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                                    Intent toMe = new Intent(RegisterActivity.this,MeActivity.class);
                                    startActivity(toMe);
                                } catch (JSONException e){
                                    e.printStackTrace();
                                }
                            }
                        });

                    }
                });
            }
        });

//        //点击获取验证码按钮，服务端发送短信给手机，用户获取验证码
//        SendCaptcha.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mPhone = phone.getText().toString();
//                if(!TextUtils.isEmpty(mPhone)){
//                    getCaptcha();
//                } else{
//                    Toast.makeText(RegisterActivity.this,"请输入手机号",Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//            }
//        });
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

//    //获取验证码的函数
//    private void getCaptcha(){
//        SendCaptcha.setClickable(false);
//        startTimer();
//        SMSSDK.getInstance().getSmsCodeAsyn(mPhone, 1 + "", new SmscodeListener() {
//            @Override
//            public void getCodeSuccess(String s) {
//                Toast.makeText(RegisterActivity.this,"获取验证码成功",Toast.LENGTH_SHORT).show();
//                Log.d("Success","手机号"+mPhone+"用户获取验证码成功,验证码为"+s);
//            }
//
//            @Override
//            public void getCodeFail(int i, String s) {
//                stopTimer();
//                Toast.makeText(RegisterActivity.this,"获取验证码失败,请重试",Toast.LENGTH_SHORT).show();
//                Log.e("Error","手机号"+mPhone+"用户获取验证码失败,失败号"+i+"失败原因为"+s);
//            }
//        });
//    }
//
//    //检验验证码的函数
//    private void checkCaptcha(String phone, String captcha){
//        SMSSDK.getInstance().checkSmsCode(phone, captcha, new SmscheckListener() {
//            @Override
//            public void checkCodeSuccess(final String code) {
//                Log.d("Success","验证码正确");
//            }
//
//            @Override
//            public void checkCodeFail(int errCode, final String errMsg) {
//                Log.d("Error","验证码错误"+"错误代码"+errCode+"错误原因"+errMsg);
//                Toast.makeText(RegisterActivity.this,"错误原因"+errMsg,Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    //开启定时器任务
//    private void startTimer(){
//        remainTime = (int) SMSSDK.getInstance().getIntervalTime()/1000;
//        SendCaptcha.setText(remainTime+"s");
//        if(timerTask == null){
//            timerTask = new TimerTask() {
//                @Override
//                public void run() {
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            remainTime--;
//                            if(remainTime<=0){
//                                stopTimer();
//                                return;
//                            }
//                            SendCaptcha.setText(remainTime+"s");
//                        }
//                    });
//                }
//            };
//        }
//        if(timer == null){
//            timer = new Timer();
//        }
//        timer.schedule(timerTask,1000,1000);
//    }
//
//    //关闭定时器任务
//    private void stopTimer(){
//        if(timerTask!=null){
//            timerTask.cancel();
//            timerTask = null;
//        }
//        if(timer!=null){
//            timer.cancel();
//            timer = null;
//        }
//        SendCaptcha.setText("获取验证码");
//        SendCaptcha.setClickable(true);
//    }
}


