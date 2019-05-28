package com.dommy.tab.module;

import com.google.gson.annotations.SerializedName;

public class Project {
    /**
     * date_start : 2019-01-01
     * id : 10002
     * name : 物联网项目
     * need_stu_count : 9
     * type : 1
     */

    private String date_start;
    private int id;
    @SerializedName("name")
    private String title;
    private int need_stu_count;
    private String type;

    public String getDate_start() {
        return date_start;
    }

    public void setDate_start(String date_start) {
        this.date_start = date_start;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public int getNeed_stu_count() {
        return need_stu_count;
    }

    public void setNeed_stu_count(int need_stu_count) {
        this.need_stu_count = need_stu_count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
