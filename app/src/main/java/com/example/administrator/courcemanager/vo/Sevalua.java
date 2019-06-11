package com.example.administrator.courcemanager.vo;

/**
 * 评价类
 */
public class Sevalua {
    public String studentid;
    public String appraise;
    public String course;

    public Sevalua(String studentid, String appraise, String course) {
        this.studentid = studentid;
        this.appraise = appraise;
        this.course = course;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getAppraise() {
        return appraise;
    }

    public void setAppraise(String appraise) {
        this.appraise = appraise;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
