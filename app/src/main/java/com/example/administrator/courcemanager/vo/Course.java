package com.example.administrator.courcemanager.vo;

/**
 * 课程表类
 */
public class Course {
    private String name, room, teach, id;//课程名称，上课教室，教师，课程编号
    int start, step;//开始上课节次，节次
    int begin,end;//上课起始周，结束周

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public Course(String name, String room, int start, int step,
                  String teach, String id,int begin,int end) {
        super();
        this.name = name;
        this.room = room;
        this.start = start;
        this.step = step;
        this.teach = teach;
        this.id = id;
        this.begin=begin;
        this.end=end;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getTeach() {
        return teach;
    }

    public void setTeach(String teach) {
        this.teach = teach;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

}
