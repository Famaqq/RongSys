package com.ruoyi.village.domain;

import java.util.List;

public class PersonApi {
    private Integer userid; //用户id
    private String aid; //地区编号
    private String uname; //姓名
    private String sex; //姓名
    private String politics;//政治面貌
    private Integer pageSize;
    private Integer pageIndex;
    private List<String> listaid;
    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getPolitics() {
        return politics;
    }

    public void setPolitics(String politics) {
        this.politics = politics;
    }

    public List<String> getListaid() {
        return listaid;
    }

    public void setListaid(List<String> listaid) {
        this.listaid = listaid;
    }
}
