package com.dommy.tab.module;

public class Assessments {
    private String tea_name;
    private String stu_name;
    private String proj_name;
    public String getTea_name() {
        return tea_name;
    }

    public String getStu_name() {
        return stu_name;
    }

    public String getProj_name() {
        return proj_name;
    }
    public Assessments(String tea_name, String stu_name, String proj_name) {
        this.tea_name = tea_name;
        this.stu_name = stu_name;
        this.proj_name = proj_name;
    }



}
