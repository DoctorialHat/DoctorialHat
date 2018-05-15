/*
    此类解析所有的Json格式数据并且用Gson将其实例化
 */

package top.jacksonyang.doctorialhat.Utils;

import android.text.TextUtils;

import org.json.JSONException;
import org.json.JSONObject;

import top.jacksonyang.doctorialhat.Gson.User;

public class JsonCommand {

    //解析用户数据,用于在登录的时候验证
    public static boolean parseUserResponse(String response){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONObject UserResponse = new JSONObject(response);
                User user = new User();
                user.setPhone(UserResponse.getString("name"));
                user.setPassword(UserResponse.getString("password"));
                user.save();
                return true;
            } catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }

}
