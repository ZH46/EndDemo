package com.example.administrator.courcemanager.vo;

/**
 * 出勤类
 */
public class Attend {
    public String studentID;
    public String truancy;
    public String teacherCourse;

    public Attend(String studentID, String truancy, String teacherCourse) {
        this.studentID = studentID;
        this.truancy = truancy;
        this.teacherCourse = teacherCourse;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getTruancy() {
        return truancy;
    }

    public void setTruancy(String truancy) {
        this.truancy = truancy;
    }

    public String getTeacherCourse() {
        return teacherCourse;
    }

    public void setTeacherCourse(String teacherCourse) {
        this.teacherCourse = teacherCourse;
    }
}
