package com.example.entity;

import java.io.Serializable;

/**
 * Created by wuhaochao on 2017/7/18.
 */
public class Student implements Serializable {
    private static final long serialVersionUID = 2120869894112984147L;
    private String user_no;
    private String user_name;
    private String user_sex;
    private String user_birth;
    private String id_no;

    public String getUser_no() {
        return user_no;
    }

    public void setUser_no(String user_no) {
        this.user_no = user_no;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(String user_sex) {
        this.user_sex = user_sex;
    }

    public String getUser_birth() {
        return user_birth;
    }

    public void setUser_birth(String user_birth) {
        this.user_birth = user_birth;
    }

    public String getId_no() {
        return id_no;
    }

    public void setId_no(String id_no) {
        this.id_no = id_no;
    }
}
