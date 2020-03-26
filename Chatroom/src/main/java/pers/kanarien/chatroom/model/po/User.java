package pers.kanarien.chatroom.model.po;


import java.io.Serializable;
import java.util.Date;


public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String userId;
    private String userName;
    private Date loginFirstDate;

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setLoginFirstDate(Date loginFirstDate) {
        this.loginFirstDate = loginFirstDate;
    }

    public int getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public Date getLoginFirstDate() {
        return loginFirstDate;
    }
}
