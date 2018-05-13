package top.jacksonyang.doctorialhat.Utils;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class WebUtils {

    //OKHTTP GET传输协议实现
    public static void sendGetOkHttpRequest(String address, Callback callback){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        okHttpClient.newCall(request).enqueue(callback);
    }

    //OKHTTP Post传输协议实现
    public static void sendPostOkHttpRequest(String address, RequestBody requestBody, Callback callback){
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(address).post(requestBody).build();
        okHttpClient.newCall(request).enqueue(callback);
    }
}
