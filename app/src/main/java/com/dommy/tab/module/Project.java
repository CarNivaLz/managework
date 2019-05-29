package com.dommy.tab.module;

import com.google.gson.annotations.SerializedName;

public class Project {

    /**
     * date : 2019-05-28
     * id : 100015
     * info : 物联网硬件价格表
     * name : project10002.xls
     * path : /home/file/project/10002/file
     * people : 12
     * project : 10002
     * size : 21
     * type : 1
     */
    
    private String date;
    private int id;
    private String info;
    @SerializedName("name")
    private String title;
    private String path;
    private int people;
    private int project;
    private int size;
    private int type;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public int getProject() {
        return project;
    }

    public void setProject(int project) {
        this.project = project;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
