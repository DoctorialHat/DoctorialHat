package top.jacksonyang.doctorialhat.Activity;

import android.Manifest;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import top.jacksonyang.doctorialhat.R;


public class MeActivity extends AppCompatActivity {

    //定义选择照片的返回码
    public static final int CHOOSE_PHOTO = 1;
    //定义获取外部存储的请求码
    private static final int WRITE_EXTERNAL_STORAGE = 100;

    private Button aboutUs;              //关于我们 按钮
    private Button myInformation;        //我的信息 按钮
    private Button setting;              //设置 按钮
    private Button bugReflect;           //问题反馈 按钮
    private Button login;                //登录按钮，跳转至登录的专属活动
    private Button register;             //注册按钮，跳转至注册的专属活动
    private ImageView loadPicture;       //上传头像按钮

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);

        //初始化
        aboutUs = findViewById(R.id.guanyuwomen);
        myInformation = findViewById(R.id.myinformation);
        setting = findViewById(R.id.setting);
        bugReflect = findViewById(R.id.bugreflect) ;
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        loadPicture =  findViewById(R.id.load_picture);

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

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent meToLogin = new Intent(MeActivity.this, LoginActivity.class);
                startActivity(meToLogin);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent meToRegister = new Intent(MeActivity.this,RegisterActivity.class);
                startActivity(meToRegister);
            }
        });

        loadPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //检查是否已经获取到权限
                if(PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(MeActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                    ActivityCompat.requestPermissions(MeActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},WRITE_EXTERNAL_STORAGE);
                } else{
                    openAlbum();
                }
            }
        });
    }

    //打开相册
    private void openAlbum(){
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent,CHOOSE_PHOTO);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case WRITE_EXTERNAL_STORAGE:
                if(grantResults.length > 0 && PackageManager.PERMISSION_GRANTED == grantResults[0]){
                    //请求成功
                    openAlbum();
                } else{
                    //二次请求权限
                    if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                        AlertDialog.Builder builder = new AlertDialog.Builder(MeActivity.this);
                        builder.setTitle("权限申请").setMessage("拒绝该权限将会影响您的体验");
                        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(MeActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},WRITE_EXTERNAL_STORAGE);
                                return;
                            }
                        });
                        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MeActivity.this,"请求权限被拒绝",Toast.LENGTH_SHORT).show();
                                return;
                            }
                        });
                        builder.setCancelable(false);
                        builder.create().show();
                    } else{
                        //三次以上的请求,打开设置
                    }
                }
                break;
            default:
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case CHOOSE_PHOTO:
                if(resultCode == RESULT_OK){
                    handleImage(data);
                }
        }
    }

    private void handleImage(Intent data){

        String imagePath = null;
        Uri uri = data.getData();
        if(DocumentsContract.isDocumentUri(this,uri)){
            //如果是文件类型(Document)的uri，则使用document id处理
            String documentId = DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())){
                //设置id
                try{
                    String id = documentId.split(":")[1];
                    String selection = MediaStore.Images.Media._ID + "=" + id;
                    imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
                } catch (NullPointerException e){
                    Log.e("Error","未知错误",e);
                    e.printStackTrace();
                }

            } else if("com.android.providers.downloads.documents".equals(uri.getAuthority())){
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(documentId));
                imagePath = getImagePath(contentUri,null);
            }
        } else if("content".equalsIgnoreCase(uri.getScheme())){
            //如果是content类型的Uri，则用普通方式获取
            try{
                imagePath = getImagePath(uri,null);
            } catch (NullPointerException e){
                Log.e("Error", "未知错误", e);
                e.printStackTrace();
            }

        } else if("file".equalsIgnoreCase(uri.getScheme())){
            //如果是file类型的Uri，直接获取文件路径
            imagePath = uri.getPath();
        }
        disPlayImage(imagePath);
    }

    private String getImagePath(Uri uri,String selection){

        String path = null;
        //通过uri和selection获取pure路径
        Cursor cursor = getContentResolver().query(uri,null,selection,null,null);
        if(cursor != null){
            if(cursor.moveToFirst()){
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void disPlayImage(String imagePath){

        if(imagePath != null){
            //用Bitmap解析
            //！！！还没有压缩图片，图片可能过大导致其他View组件变形
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            loadPicture.setImageBitmap(bitmap);
        } else{
            Toast.makeText(this,"图片不存在",Toast.LENGTH_SHORT).show();
        }
    }
}


