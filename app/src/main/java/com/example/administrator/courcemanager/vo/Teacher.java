package com.example.administrator.courcemanager.vo;

/**
 * 教师类
 */
public class Teacher {
    private String teacherid, name, role, course, professional, phone;

    public Teacher(String teacherid, String name, String role, String course, String professional, String phone) {
        this.teacherid = teacherid;
        this.name = name;
        this.role = role;
        this.course = course;
        this.professional = professional;
        this.phone = phone;
    }

    public String getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(String teacherid) {
        this.teacherid = teacherid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getProfessional() {
        return professional;
    }

    public void setProfessional(String professional) {
        this.professional = professional;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
