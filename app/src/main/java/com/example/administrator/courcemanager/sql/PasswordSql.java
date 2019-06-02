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
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
