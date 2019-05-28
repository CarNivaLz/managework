package com.dommy.tab.module;

import com.google.gson.annotations.SerializedName;

public class Patent {
    /**
     * abstract : 专利摘要！！！！专利摘要！！！！专利摘要！！！！专利摘要！！！！专利摘要！！！！专利摘要！！！！专利摘要！！！！
     * author : 11
     * date_acceptance : 2019-05-05
     * date_authorization : 2019-05-28
     * id : 30007
     * info : 专利信息！！！专利信息！！！专利信息！！！专利信息！！！专利信息！！！专利信息！！！专利信息！！！
     * name : 云计算专利5
     * num_acceptance : 5
     * num_authorization : 5
     * project : 10004
     * status : 4
     */

    @SerializedName("abstract")
    private String abstractX;
    private String author;
    private String date_acceptance;
    private String date_authorization;
    private int id;
    private String info;
    @SerializedName("name")
    private String title;
    private int num_acceptance;
    private int num_authorization;
    private int project;
    private int status;

    public String getAbstractX() {
        return abstractX;
    }

    public void setAbstractX(String abstractX) {
        this.abstractX = abstractX;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate_acceptance() {
        return date_acceptance;
    }

    public void setDate_acceptance(String date_acceptance) {
        this.date_acceptance = date_acceptance;
    }

    public String getDate_authorization() {
        return date_authorization;
    }

    public void setDate_authorization(String date_authorization) {
        this.date_authorization = date_authorization;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public int getNum_acceptance() {
        return num_acceptance;
    }

    public void setNum_acceptance(int num_acceptance) {
        this.num_acceptance = num_acceptance;
    }

    public int getNum_authorization() {
        return num_authorization;
    }

    public void setNum_authorization(int num_authorization) {
        this.num_authorization = num_authorization;
    }

    public int getProject() {
        return project;
    }

    public void setProject(int project) {
        this.project = project;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
