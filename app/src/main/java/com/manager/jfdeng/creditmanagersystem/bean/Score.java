package com.manager.jfdeng.creditmanagersystem.bean;

import java.io.Serializable;

/**
 * Created by jeff on 18-7-4.
 */

public class Score implements Serializable {

    String id;
    String sno;
    String dianming1;
    String dianming2;
    String dianming3;
    String dianming4;
    String dianming5;
    String zuoye1;
    String zuoye2;
    String zuoye3;
    String zuoye4;
    String zuoye5;
    String shangji1;
    String shangji2;
    String shangji3;
    String shangji4;
    String shangji5;

    public Score(String id, String sno, String dianming1, String dianming2, String dianming3, String dianming4, String dianming5, String zuoye1, String zuoye2, String zuoye3, String zuoye4, String zuoye5, String shangji1, String shangji2, String shangji3, String shangji4, String shangji5) {
        this.id = id;
        this.sno = sno;
        this.dianming1 = dianming1;
        this.dianming2 = dianming2;
        this.dianming3 = dianming3;
        this.dianming4 = dianming4;
        this.dianming5 = dianming5;
        this.zuoye1 = zuoye1;
        this.zuoye2 = zuoye2;
        this.zuoye3 = zuoye3;
        this.zuoye4 = zuoye4;
        this.zuoye5 = zuoye5;
        this.shangji1 = shangji1;
        this.shangji2 = shangji2;
        this.shangji3 = shangji3;
        this.shangji4 = shangji4;
        this.shangji5 = shangji5;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getDianming1() {
        return dianming1;
    }

    public void setDianming1(String dianming1) {
        this.dianming1 = dianming1;
    }

    public String getDianming2() {
        return dianming2;
    }

    public void setDianming2(String dianming2) {
        this.dianming2 = dianming2;
    }

    public String getDianming3() {
        return dianming3;
    }

    public void setDianming3(String dianming3) {
        this.dianming3 = dianming3;
    }

    public String getDianming4() {
        return dianming4;
    }

    public void setDianming4(String dianming4) {
        this.dianming4 = dianming4;
    }

    public String getDianming5() {
        return dianming5;
    }

    public void setDianming5(String dianming5) {
        this.dianming5 = dianming5;
    }

    public String getZuoye1() {
        return zuoye1;
    }

    public void setZuoye1(String zuoye1) {
        this.zuoye1 = zuoye1;
    }

    public String getZuoye2() {
        return zuoye2;
    }

    public void setZuoye2(String zuoye2) {
        this.zuoye2 = zuoye2;
    }

    public String getZuoye3() {
        return zuoye3;
    }

    public void setZuoye3(String zuoye3) {
        this.zuoye3 = zuoye3;
    }

    public String getZuoye4() {
        return zuoye4;
    }

    public void setZuoye4(String zuoye4) {
        this.zuoye4 = zuoye4;
    }

    public String getZuoye5() {
        return zuoye5;
    }

    public void setZuoye5(String zuoye5) {
        this.zuoye5 = zuoye5;
    }

    public String getShangji1() {
        return shangji1;
    }

    public void setShangji1(String shangji1) {
        this.shangji1 = shangji1;
    }

    public String getShangji2() {
        return shangji2;
    }

    public void setShangji2(String shangji2) {
        this.shangji2 = shangji2;
    }

    public String getShangji3() {
        return shangji3;
    }

    public void setShangji3(String shangji3) {
        this.shangji3 = shangji3;
    }

    public String getShangji4() {
        return shangji4;
    }

    public void setShangji4(String shangji4) {
        this.shangji4 = shangji4;
    }

    public String getShangji5() {
        return shangji5;
    }

    public void setShangji5(String shangji5) {
        this.shangji5 = shangji5;
    }
}
