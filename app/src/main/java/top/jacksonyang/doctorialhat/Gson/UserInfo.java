package top.jacksonyang.doctorialhat.Gson;

import com.google.gson.annotations.SerializedName;

import org.litepal.crud.DataSupport;

public class UserInfo extends DataSupport {
    private User user;
    private String nickName;
    @SerializedName("description")
    private String des;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
