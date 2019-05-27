package com.dommy.tab.module;

import com.google.gson.annotations.SerializedName;

//项目列表返回的JSON
public class Projects {


    /**
     * id : 57c83777421aa97cbd81c74d
     * tittle : 团队项目网络管理系统
     * teacher : 马立香
     * member_num : 5
     * time_start : 2019.3.30
     */
//    @SerializedName("id")
//    private String id;
//    @SerializedName("name")
//    private String tittle;
//    @SerializedName("people_name")
//    private String teacher;
//    @SerializedName("city")
//    private String member_num;
//    @SerializedName("date_start")
//    private String time_start;
//
//    public String getId() {
//        return id;
//    }
//
//    public String getTittle() {
//        return tittle;
//    }
//
//    public String getTeacher() {
//        return teacher;
//    }
//
//    public String getMember_num() {
//        return member_num;
//    }
//
//    public String getTime_start() {
//        return time_start;
//    }
//
//    public Projects(String id,String title, String teacher, String member_num, String time_start) {
//        this.id=id;
//        this.tittle = title;
//        this.teacher = teacher;
//        this.member_num = member_num;
//        this.time_start = time_start;
//    }

    private String date_start;

    private int id;

    private String name;
//    @SerializedName("city")
//    private String member_num;

    private String people_name;
    public int getId() {
        return id;
    }

    public String getTittle() {
        return name;
    }

    public String getTeacher() {
        return people_name;
    }

//    public String getMember_num() {
//        return member_num;
//    }

    public String getTime_start() {
        return date_start;
    }

    public Projects(String date_start,int id,String name, String people_name ) {
        this.id=id;
        this.name = name;
        this.people_name = people_name;
//        this.member_num = member_num;
        this.date_start = date_start;
    }
    @Override
    public String toString() {
        return "LzyResponse{\n" +//
                "\tid=" + id + "\n" +//
                "\tname='" + name + "\'\n" +//
                "\tpn=" + people_name + "\n" +//
                "\ttime=" + date_start + "\n" +//
                '}';
    }
}

