package com.dommy.tab.module;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProjectDetail {

    /**
     * date_end : 2019-05-31
     * date_start : 2019-05-24
     * description : 车联网项目的描述内容！车联网项目的描述内容！车联网项目的描述内容！车联网项目的描述内容！
     * fund : 333
     * id : 10003
     * level : 1
     * name : 车联网项目
     * need_stu_count : 10
     * part_stu_name : ["陈奕迅","华罗庚","周杰伦"]
     * part_tea_name : ["李老师"]
     * people_name : 赵老师
     * status : 3
     */

    private String date_end;
    private String date_start;
    private String description;
    private int fund;
    private int id;
    private int level;
    @SerializedName("name")
    private String title;
    private int need_stu_count;
    private String people_name;
    private int status;
    private List<String> part_stu_name;
    private List<String> part_tea_name;

    public String getDate_end() {
        return date_end;
    }

    public void setDate_end(String date_end) {
        this.date_end = date_end;
    }

    public String getDate_start() {
        return date_start;
    }

    public void setDate_start(String date_start) {
        this.date_start = date_start;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFund() {
        return fund;
    }

    public void setFund(int fund) {
        this.fund = fund;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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

    public String getPeople_name() {
        return people_name;
    }

    public void setPeople_name(String people_name) {
        this.people_name = people_name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<String> getPart_stu_name() {
        return part_stu_name;
    }

    public void setPart_stu_name(List<String> part_stu_name) {
        this.part_stu_name = part_stu_name;
    }

    public List<String> getPart_tea_name() {
        return part_tea_name;
    }

    public void setPart_tea_name(List<String> part_tea_name) {
        this.part_tea_name = part_tea_name;
    }

    public String memberToString() {
//        return "LzyResponse{\n" +//
//                "\tid=" + id + "\n" +//
//                "\tname='" + name + "\'\n" +//
//                "\tpn=" + people_name + "\n" +//
//                "\ttime=" + date_start + "\n" +//
//                '}';
        String member="";
        for (String str1:part_tea_name){
            member=member+str1+"  ";
        }
        for (String str2:part_stu_name){
            member=member+str2+"  ";
        }
        return member;
    }
}
