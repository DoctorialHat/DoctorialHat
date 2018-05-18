/*
    一句话桌面数据
 */
package top.jacksonyang.doctorialhat.Gson;

import org.litepal.crud.DataSupport;


public class One extends DataSupport {
    private int Id;//一言的Id
    private String hitokoto;//一句话正文
    private String type;//一句话的类型
    private String from;//一句话出处
    private String creator;//一句话作者
    private String created_at;//一句话创作时间

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getHitokoto() {
        return hitokoto;
    }

    public void setHitokoto(String hitokoto) {
        this.hitokoto = hitokoto;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
