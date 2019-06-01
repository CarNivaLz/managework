package com.dommy.tab.module;


import com.google.gson.annotations.SerializedName;

public class Achievements {


        private int id;
        @SerializedName("name")
        private String title;
        @SerializedName("author_name")
        private String teacher;
        @SerializedName("status")
        private String member_num;
        @SerializedName("date")
        private String time_start;
        @SerializedName("achi_type")
        private int achieve_type;

        public int getId() {
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
        public int getAchieve_type(){return achieve_type;}

        public Achievements(int id,String title, String teacher, String member_num, String time_start,int achieve_type) {
        this.id=id;
        this.title = title;
        this.teacher = teacher;
        this.member_num = member_num;
        this.time_start = time_start;
        this.achieve_type=achieve_type;
        }
        }


