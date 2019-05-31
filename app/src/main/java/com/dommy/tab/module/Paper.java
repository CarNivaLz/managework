package com.dommy.tab.module;

import com.google.gson.annotations.SerializedName;

public class Paper {
    /**
     * abstract : 论文信息论文信息论文信息
     * author : 11
     * author1_name : 周杰伦
     * author2 : 18
     * author2_name : 华罗庚
     * author3 : 16
     * author3_name : 王老师
     * date_deliver : 2019-03-04
     * doc : 100024
     * doc_name : 云计算论文1.doc
     * id : 20004
     * index_number :
     * journal : 期刊1
     * project : 10004
     * project_name : 云计算项目
     * status : 1
     * title : 云计算论文1
     */

    @SerializedName("abstract")
    private String abstractX;
    private int author;
    private String author1_name;
    private int author2;
    private String author2_name;
    private int author3;
    private String author3_name;
    private int author4;
    private String author4_name;
    private int author5;
    private String author5_name;
    private int author6;
    private String author6_name;
    private int author7;
    private String author7_name;
    private int author8;
    private String author8_name;

    private String date_deliver;
    private String date_pass;
    private String date_pub;

    private int doc;
    private String doc_name;
    private int id;
    private String index_number;
    private String journal;
    private int project;
    private String project_name;
    private int status;
    private String title;

    public String getAbstractX() {
        return abstractX;
    }

    public void setAbstractX(String abstractX) {
        this.abstractX = abstractX;
    }

    public int getAuthor() {
        return author;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public String getAuthor1_name() {
        return author1_name;
    }

    public void setAuthor1_name(String author1_name) {
        this.author1_name = author1_name;
    }

    public int getAuthor2() {
        return author2;
    }

    public void setAuthor2(int author2) {
        this.author2 = author2;
    }

    public String getAuthor2_name() {
        return author2_name;
    }

    public void setAuthor2_name(String author2_name) {
        this.author2_name = author2_name;
    }

    public int getAuthor3() {
        return author3;
    }

    public void setAuthor3(int author3) {
        this.author3 = author3;
    }

    public String getAuthor3_name() {
        return author3_name;
    }

    public void setAuthor3_name(String author3_name) {
        this.author3_name = author3_name;
    }
    public String getAuthor4_name() {
        return author4_name;
    }
    public String getAuthor5_name() {
        return author5_name;
    }
    public String getAuthor6_name() {
        return author6_name;
    }
    public String getAuthor7_name() {
        return author7_name;
    }
    public String getAuthor8_name() {
        return author8_name;
    }

    public String getDate_deliver() {
        return date_deliver;
    }

    public void setDate_deliver(String date_deliver) {
        this.date_deliver = date_deliver;
    }
    public String getDate_pass() {
        return date_pass;
    }
    public String getDate_pub() {
        return date_pub;
    }

    public int getDoc() {
        return doc;
    }

    public void setDoc(int doc) {
        this.doc = doc;
    }

    public String getDoc_name() {
        return doc_name;
    }

    public void setDoc_name(String doc_name) {
        this.doc_name = doc_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIndex_number() {
        return index_number;
    }

    public void setIndex_number(String index_number) {
        this.index_number = index_number;
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public int getProject() {
        return project;
    }

    public void setProject(int project) {
        this.project = project;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
