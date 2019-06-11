package com.example.administrator.courcemanager.vo;

/**
 * 成绩类
 */
public class Score {
    public String studentid;
    public String score;
    public String course;

    public Score(String studentid, String score, String course) {
        this.studentid = studentid;
        this.score = score;
        this.course = course;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
