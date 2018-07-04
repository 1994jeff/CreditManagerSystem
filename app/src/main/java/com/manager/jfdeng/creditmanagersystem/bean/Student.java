package com.manager.jfdeng.creditmanagersystem.bean;

import java.io.Serializable;

/**
 * Created by yf on 18-7-4.
 */

public class Student implements Serializable{

    String id;
    String sNo;
    String name;
    String sClass;

    public Student(String id, String sNo, String name, String sClass) {
        this.id = id;
        this.sNo = sNo;
        this.name = name;
        this.sClass = sClass;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", sNo='" + sNo + '\'' +
                ", name='" + name + '\'' +
                ", sClass='" + sClass + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getsNo() {
        return sNo;
    }

    public void setsNo(String sNo) {
        this.sNo = sNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getsClass() {
        return sClass;
    }

    public void setsClass(String sClass) {
        this.sClass = sClass;
    }
}
