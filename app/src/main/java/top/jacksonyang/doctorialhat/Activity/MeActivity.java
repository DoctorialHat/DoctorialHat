package top.jacksonyang.doctorialhat.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;

import okhttp3.internal.Util;
import top.jacksonyang.doctorialhat.R;


public class MeActivity extends AppCompatActivity {

    private Button aboutUs;              //关于我们 按钮
    private Button myInformation;        //我的信息 按钮
    private Button setting;              //设置 按钮
    private Button bugReflect;           //问题反馈 按钮
    private Button login;                //登录 按钮
    private Button register;             //注册 按钮
    private CircleImageView load_picture; //上传头像 按钮
    //上传头像时需要的变量
    protected static final int choosePicture=0;
    protected static final int takePicture=1;
    protected static final int cropPicture=3;
    protected static Uri pictureUri;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);

        aboutUs = findViewById(R.id.about_us);
        myInformation= findViewById(R.id.my_information);
        setting= findViewById(R.id.setting);
        bugReflect= findViewById(R.id.bug_reflect);
        login= findViewById(R.id.jump_to_login);
        register= findViewById(R.id.jump_to_register);
        load_picture=findViewById(R.id.load_picture);

        //进入 问题反馈 页面
        bugReflect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent meToBugReflect=new Intent(MeActivity.this,BugReflectActivity.class);
                startActivity(meToBugReflect);
            }
        });

        //进入 设置 页面
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent meToSetting=new Intent(MeActivity.this,SettingActivity.class);
                startActivity(meToSetting);
            }
        });

        //进入 我的信息 页面
        myInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent meToMyInfo=new Intent(MeActivity.this,MyInfomationActivity.class);
                startActivity(meToMyInfo);
            }
        });

        //进入 关于我们 页面
        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent meToAboutUs = new Intent(MeActivity.this,AboutUs.class);
                startActivity(meToAboutUs);
            }
        });

        //进入登录页面
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent meToLogin=new Intent(MeActivity.this,LoginActivity.class);
                startActivity(meToLogin);
            }
        });
        //进入注册页面
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent meToRegister=new Intent(MeActivity.this,RegisterActivity.class);
                startActivity(meToRegister);
            }
        });
        //上传头像
        load_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //显示选择页面
                showChooseDialog();
            }
        });

    }
    /**显示修改头像的对话框**/
    protected void showChooseDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("头像设置");
        String[] items={"图库","相机"};
        builder.setNegativeButton("取消",null);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case choosePicture://图库选择
                        Intent openGalleryIntent=new Intent(Intent.ACTION_GET_CONTENT);
                        openGalleryIntent.setType("image/*");
                        startActivityForResult(openGalleryIntent,choosePicture);
                        break;
                    case takePicture://相机

                        Intent openCameraIntent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        pictureUri=Uri.fromFile(new File(Environment.getExternalStorageDirectory(),"image.jpg"));
                        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,pictureUri);
                        startActivityForResult(openCameraIntent,takePicture);

                        break;
                }
            }
        });
        builder.create().show();
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode==RESULT_OK){
            switch (requestCode){
                case takePicture:
                    startPictureZoom(pictureUri);//开始图片裁剪
                    break;
                case choosePicture:
                    startPictureZoom(data.getData());
                    break;
                case cropPicture:
                    if(data!=null){
                        setImageToView(data);//显示裁剪的图片
                    }
                    break;
            }
        }
    }

    /**图片裁剪处理**/
    protected void startPictureZoom(Uri uri){
        if(uri==null){
            Log.i("tag","The uri is no exist!");
        }
        pictureUri=uri;
        Intent intent=new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri,"image/*");
        //设置裁剪
        intent.putExtra("crop","true");
        //aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent,cropPicture);
    }

    /**显示裁剪图片**/
    protected void setImageToView(Intent data){
        Bundle extras=data.getExtras();
        if(extras!=null){
            Bitmap picture=extras.getParcelable("data");
            //picture=Utils.toRoundBitmap(picture,pictureUri);
            load_picture.setImageBitmap(picture);
            //uploadPicture(picture);//上传到服务器
        }

    }

    /*上传到服务器*/
    private void uploadPicture(Bitmap bitmap){
        // 上传至服务器
        // ... 可以在这里把Bitmap转换成file，然后得到file的url，做文件上传操作
        // 注意这里得到的图片已经是圆形图片了
        // bitmap是没有做个圆形处理的，但已经被裁剪了
        //String imagePath=
    }







}
