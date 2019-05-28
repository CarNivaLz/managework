package com.dommy.tab.module;

import com.google.gson.annotations.SerializedName;

public class Copyright {
    /**
     * author : 11
     * date : 1556726400000
     * id : 6
     * name : 物联网著作权1
     * number : ZZQ6
     * project : 10004
     * status : 1
     */

    private String author;
    private String date;
    private int id;
    @SerializedName("name")
    private String title;
    private String number;
    private int project;
    private int status;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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
