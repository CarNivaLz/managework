package com.dommy.tab.module;


import com.google.gson.annotations.SerializedName;

public class Achievements {
        /**
         * id : 57c83777421aa97cbd81c74d
         * tittle : 团队项目网络管理系统
         * teacher : 马立香
         * member_num : 5
         * time_start : 2019.3.30
         */

        private String id;
        @SerializedName("name")
        private String title;
        @SerializedName("author_name")
        private String teacher;
        @SerializedName("status")
        private String member_num;
        @SerializedName("date")
        private String time_start;

        public String getId() {
        return id;
        }

        public String getTittle() {
        return title;
        }

        public String getTeacher() {
        return teacher;
        }

        public String getMember_num() {
        return member_num;
        }

        public String getTime_start() {
        return time_start;
        }

        public Achievements(String id,String title, String teacher, String member_num, String time_start) {
        this.id=id;
        this.title = title;
        this.teacher = teacher;
        this.member_num = member_num;
        this.time_start = time_start;
        }
        }


