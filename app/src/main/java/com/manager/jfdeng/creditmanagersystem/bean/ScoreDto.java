package com.manager.jfdeng.creditmanagersystem.bean;

/**
 * Created by jeff on 18-7-5.
 */

public class ScoreDto extends Score{
    public ScoreDto(String id, String sno, String dianming1, String dianming2, String dianming3, String dianming4, String dianming5, String zuoye1, String zuoye2, String zuoye3, String zuoye4, String zuoye5, String shangji1, String shangji2, String shangji3, String shangji4, String shangji5) {
        super(id, sno, dianming1, dianming2, dianming3, dianming4, dianming5, zuoye1, zuoye2, zuoye3, zuoye4, zuoye5, shangji1, shangji2, shangji3, shangji4, shangji5);
    }

    public String name;
    public String aClass;

    public ScoreDto(String s, String sno, String dianming1, String dianming2, String dianming3, String dianming4, String dianming5, String zuoye1, String zuoye2, String zuoye3, String zuoye4, String zuoye5, String shangji1, String shangji2, String shangji3, String shangji4, String shangji5, String name, String aClass) {
        super(s, sno, dianming1, dianming2, dianming3, dianming4, dianming5, zuoye1, zuoye2, zuoye3, zuoye4, zuoye5, shangji1, shangji2, shangji3, shangji4, shangji5);
        this.name = name;
        this.aClass = aClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getaClass() {
        return aClass;
    }

    public void setaClass(String aClass) {
        this.aClass = aClass;
    }
}
