/*
    此类解析所有的Json格式数据并且用Gson将其实例化
 */

package top.jacksonyang.doctorialhat.Utils;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import top.jacksonyang.doctorialhat.Gson.One;
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

    //解析一句话，并存到数据库缓存中
    public static boolean parseOne(String response){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONObject oneJson = new JSONObject(response);
                One one = new One();
                one.setId(oneJson.getInt("id"));
                one.setHitokoto(oneJson.getString("hitokoto"));
                one.setType(oneJson.getString("type"));
                one.setFrom(oneJson.getString("from"));
                one.setCreator(oneJson.getString("creator"));
                one.setCreated_at(oneJson.getString("created_at"));
                one.save();
                return true;
            } catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }
}
