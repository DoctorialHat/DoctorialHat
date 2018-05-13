package top.jacksonyang.doctorialhat.Gson;

import com.google.gson.annotations.SerializedName;

import org.litepal.crud.DataSupport;

public class User extends DataSupport {
    private String phone;
    private String password;
    private String nickName;
    private String description;

    private int rank;
    private double winRating;
    private int questionNum;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getQuestionNum() {
        return questionNum;
    }

    public void setQuestionNum(int questionNum) {
        this.questionNum = questionNum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getWinRating() {
        return winRating;
    }

    public void setWinRating(double winRating) {
        this.winRating = winRating;
    }
}
