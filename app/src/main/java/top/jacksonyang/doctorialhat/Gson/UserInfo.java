package top.jacksonyang.doctorialhat.Gson;

import com.google.gson.annotations.SerializedName;

public class UserInfo {
    private User user;
    private String nickName;
    @SerializedName("description")
    private String des;
}
