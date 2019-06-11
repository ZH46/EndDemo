package com.example.administrator.courcemanager.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 创建数据库
 */
public class PasswordSql extends SQLiteOpenHelper {
    public PasswordSql(Context context) {
        super(context,"courcepw.db",null,5);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table password(" +
                "number varchar(20) primary key," +
                "password varchar(20),"+
                "character varchar(20))");
        db.execSQL("create table studentinfo("+
                "stuid varchar(20) primary key,"+
                "stuname varchar(20),"+
                "sturole varchar(20),"+
                "stugrade varchar(20),"+
                "stuprofessional varchar(20),"+
                "stuclass varchar(20),"+
                "stuphone varchar(20))");
        db.execSQL("create table teacherinfo("+
                "teacid varchar(20) primary key,"+
                "teacname varchar(20),"+
                "teaccourse varchar(20),"+
                "teacrole varchar(20),"+
                "teacprofessional varchar(20),"+
                "teacphone varchar(20))");
        db.execSQL("create table attendexcel("+
                "student_id varchar(20) primary key,"+
                "truancy varchar(20),"+
                "teacher_course varchar(20))");
        db.execSQL("create table results("+
                "stud_id varchar(20) primary key,"+
                "score varchar(20),"+
                "teac_course varchar(20))");
        db.execSQL("create table sevalua("+
                "sd_id varchar(20) primary key,"+
                "appraise varchar(20),"+
                "teac_cs varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
