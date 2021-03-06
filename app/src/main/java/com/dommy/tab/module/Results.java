package com.dommy.tab.module;

import com.google.gson.annotations.SerializedName;

public class Results {
    /**
     * id : 57c83777421aa97cbd81c74d
     * tittle : 团队项目网络管理系统
     * teacher : 马立香
     * member_num : 5
     * time_start : 2019.3.30
     */

    private int id;
    @SerializedName("name")
    private String tittle;
    @SerializedName("type")
    private int position1;
    @SerializedName("people_name")
    private String position2;
    @SerializedName("date")
    private String position3;

    public int getId() {
        return id;
    }

    public String getTittle() {
        return tittle;
    }

    public int getPosition1() {
        return position1;
    }

    public String getPosition2() {
        return position2;
    }

    public String getPosition3() {
        return position3;
    }




}