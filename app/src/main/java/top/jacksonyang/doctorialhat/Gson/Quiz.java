package top.jacksonyang.doctorialhat.Gson;

import org.litepal.crud.DataSupport;

import java.util.Date;
import java.util.List;

public class Quiz extends DataSupport{

    private String quiz;
    private List<String> options;
    private int num;
    private String school;
    private String type;
    private int typeID;
    private String contributor;
    private int partner;
    private Date endTime;
    private Date curTime;
    private MyBuff myBuff;

    public String getQuiz() {
        return quiz;
    }

    public void setQuiz(String quiz) {
        this.quiz = quiz;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public String getContributor() {
        return contributor;
    }

    public void setContributor(String contributor) {
        this.contributor = contributor;
    }

    public int getPartner() {
        return partner;
    }

    public void setPartner(int partner) {
        this.partner = partner;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCurTime() {
        return curTime;
    }

    public void setCurTime(Date curTime) {
        this.curTime = curTime;
    }

    public MyBuff getMyBuff() {
        return myBuff;
    }

    public void setMyBuff(MyBuff myBuff) {
        this.myBuff = myBuff;
    }
}
