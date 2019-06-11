package com.example.administrator.courcemanager.vo;

/**
 * 学生类
 */
public class Student {

    private String name, role, grade, professional, classes, stuid, phone;

    public Student(){}
    public Student(String stuid, String name, String role, String grade, String professional, String classes, String phone) {
        this.name = name;
        this.role = role;
        this.grade = grade;
        this.professional = professional;
        this.classes = classes;
        this.stuid = stuid;
        this.phone = phone;
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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getProfessional() {
        return professional;
    }

    public void setProfessional(String professional) {
        this.professional = professional;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getStuid() {
        return stuid;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
