package top.jacksonyang.doctorialhat.Utils;

import java.math.BigInteger;
import java.security.MessageDigest;

//对字符串进行SHA加密
public class encodeBySHA {
    public String encodeBySHA(String rawPassword){
        BigInteger SHAInteger = null;
        String password = null;

        if(rawPassword != null){
            byte[] rawPasswordData = rawPassword.getBytes();
            try{
                //创建信息摘要
                MessageDigest messageDigest = MessageDigest.getInstance("SHA");
                messageDigest.update(rawPasswordData);
                SHAInteger = new BigInteger(messageDigest.digest());
                password = SHAInteger.toString(32);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return password;
    }
}
