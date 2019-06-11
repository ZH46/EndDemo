package com.example.administrator.courcemanager.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.renderscript.ScriptC;
import android.util.Log;
import com.example.administrator.courcemanager.vo.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据库操作类
 */
public class MyPassWord {

    private static PasswordSql helper;

    public static SQLiteDatabase getDatabase(Context context) {
        helper = new PasswordSql(context);
        return helper.getReadableDatabase();
    }

    /**
     * 增加数据
     *
     * @param context
     * @return
     */
    public static int addData(Context context, RoleInfo roleInfo) {
        int result = -1;
        SQLiteDatabase db = getDatabase(context);
        try {
            db.execSQL("insert into password values ('" + roleInfo.getNumber().trim() + "','" + roleInfo.getPassword().trim() + "','" + roleInfo.getRoleInfo().trim() + "')");
            result = 1;
        } catch (SQLException e) {
            result = -1;
        }
        db.close();
        return result;
    }

    /**
     * 查询账户数据
     *
     * @param context
     * @param selection
     * @param selectionArgs
     * @return
     */
    public static RoleInfo queryData(Context context, String selection, String[] selectionArgs) {
        String numberString = null;
        String passwordString = null;
        String rolestyle = null;
        SQLiteDatabase db = getDatabase(context);
        Cursor cursor = db.query(false, "password", null,
                selection, selectionArgs, null, null, null, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                numberString = cursor.getString(cursor.getColumnIndex("number"));
                passwordString = cursor.getString(cursor.getColumnIndex("password"));
                rolestyle = cursor.getString(cursor.getColumnIndex("character"));
            }
        }
        cursor.close();
        db.close();
        return new RoleInfo(numberString, passwordString, rolestyle);
    }

    public static boolean findNumber(Context context, String selection, String[] selectionArgs) {
        boolean result = false;
        SQLiteDatabase db = getDatabase(context);
        Cursor cursor = db.query(false, "password", null,
                selection, selectionArgs, null, null, null, null);
        if (cursor.getCount() > 0) {
            result = true;
        }
        cursor.close();
        db.close();
        return result;
    }

    /**
     * 删除账户数据
     *
     * @param context
     * @param id
     * @return
     */
    public static boolean deleteNumber(Context context, String id) {
        boolean result = false;
        SQLiteDatabase db = getDatabase(context);
        try {
            db.delete("password", "number=?", new String[]{id});
            result = true;
        } catch (SQLException e) {
        }
        db.close();
        return result;
    }

    /**
     * 添加学生
     *
     * @param context
     * @param id
     * @param role
     * @return
     */
    public static boolean addStudentID(Context context, String id, String role) {
        boolean result = false;
        SQLiteDatabase db = getDatabase(context);
        try {
            db.execSQL("insert into studentinfo(stuid,sturole) values(?,?)", new Object[]{id, role});
            result = true;
        } catch (SQLException e) {
            result = false;
        }
        return result;
    }

    /**
     * 更新学生信息
     *
     * @param context
     * @param student
     * @return
     */
    public static boolean updateStudentInfo(Context context, Student student) {
        boolean result = false;
        SQLiteDatabase db = getDatabase(context);
        ContentValues values = new ContentValues();
        values.put("stuname", student.getName());
        values.put("stugrade", student.getGrade());
        values.put("stuprofessional", student.getProfessional());
        values.put("stuclass", student.getClasses());
        values.put("stuphone", student.getPhone());
        try {
            db.update("studentinfo", values, "stuid=?", new String[]{student.getStuid()});
            result = true;
        } catch (SQLException e) {

        }
        return result;
    }

    /**
     * 查询学生信息
     *
     * @param id
     * @return
     */
    public static Student queryStudent(Context context, String id) {
        String sid = null;
        String srole = null;
        String sname = null;
        String sgrade = null;
        String sprofessional = null;
        String sclass = null;
        String sphone = null;
        SQLiteDatabase db = getDatabase(context);
        Cursor cursor = db.query("studentinfo", null, "stuid=?",
                new String[]{id}, null, null, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                sid = cursor.getString(cursor.getColumnIndex("stuid"));
                sname = cursor.getString(cursor.getColumnIndex("stuname"));
                srole = cursor.getString(cursor.getColumnIndex("sturole"));
                sgrade = cursor.getString(cursor.getColumnIndex("stugrade"));
                sprofessional = cursor.getString(cursor.getColumnIndex("stuprofessional"));
                sclass = cursor.getString(cursor.getColumnIndex("stuclass"));
                sphone = cursor.getString(cursor.getColumnIndex("stuphone"));
            }
        }
        cursor.close();
        db.close();
        return new Student(sid, sname, srole, sgrade, sprofessional, sclass, sphone);
    }

    public static List<Student> Allstudents(Context context) {
        String sid = null;
        String srole = null;
        String sname = null;
        String sgrade = null;
        String sprofessional = null;
        String sclass = null;
        String sphone = null;
        SQLiteDatabase db = getDatabase(context);
        Cursor cursor = db.query("studentinfo", null, null,
                null, null, null, null);
        List<Student> list = new ArrayList<Student>();
        while (cursor.moveToNext()) {
            sid = cursor.getString(cursor.getColumnIndex("stuid"));
            sname = cursor.getString(cursor.getColumnIndex("stuname"));
            srole = cursor.getString(cursor.getColumnIndex("sturole"));
            sgrade = cursor.getString(cursor.getColumnIndex("stugrade"));
            sprofessional = cursor.getString(cursor.getColumnIndex("stuprofessional"));
            sclass = cursor.getString(cursor.getColumnIndex("stuclass"));
            sphone = cursor.getString(cursor.getColumnIndex("stuphone"));
            list.add(new Student(sid, sname, srole, sgrade, sprofessional, sclass, sphone));
        }
        cursor.close();
        db.close();
        return list;
    }

    /**
     * 删除学生信息
     *
     * @param context
     * @param id
     * @return
     */
    public static boolean deleteStudent(Context context, String id) {
        boolean result = false;
        SQLiteDatabase db = getDatabase(context);
        try {
            db.delete("studentinfo", "stuid=?", new String[]{id});
            result = true;
        } catch (SQLException e) {

        }
        db.close();
        return result;
    }

    /**
     * 添加教师
     *
     * @param context
     * @param id
     * @param role
     * @return
     */
    public static boolean addTeacherID(Context context, String id, String role) {
        boolean result = false;
        SQLiteDatabase db = getDatabase(context);
        try {
            db.execSQL("insert into teacherinfo(teacid,teacrole) values(?,?)", new Object[]{id, role});
            result = true;
        } catch (SQLException e) {
            result = false;
        }
        return result;
    }

    /**
     * 更新教师信息
     *
     * @param context
     * @param teacher
     * @return
     */
    public static boolean updateTeacherInfo(Context context, Teacher teacher) {
        boolean result = false;
        SQLiteDatabase db = getDatabase(context);
        ContentValues values = new ContentValues();
        values.put("teacname", teacher.getName());
        values.put("teaccourse", teacher.getCourse());
        values.put("teacprofessional", teacher.getProfessional());
        values.put("teacphone", teacher.getPhone());
        try {
            db.update("teacherinfo", values, "teacid=?", new String[]{teacher.getTeacherid()});
            result = true;
        } catch (SQLException e) {
            result = false;
        }
        return result;
    }

    /**
     * 查询教师信息
     *
     * @param context
     * @param id
     * @return
     */
    public static Teacher queryTeacher(Context context, String id) {
        String tid = null;
        String trole = null;
        String tname = null;
        String tprofessional = null;
        String tcourse = null;
        String tphone = null;
        SQLiteDatabase db = getDatabase(context);
        Cursor cursor = db.query("teacherinfo", null, "teacid=?",
                new String[]{id}, null, null, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                tid = cursor.getString(cursor.getColumnIndex("teacid"));
                tname = cursor.getString(cursor.getColumnIndex("teacname"));
                trole = cursor.getString(cursor.getColumnIndex("teacrole"));
                tprofessional = cursor.getString(cursor.getColumnIndex("teacprofessional"));
                tcourse = cursor.getString(cursor.getColumnIndex("teaccourse"));
                tphone = cursor.getString(cursor.getColumnIndex("teacphone"));
            }
        }
        cursor.close();
        db.close();
        return new Teacher(tid, tname, trole, tcourse, tprofessional, tphone);
    }

    public static List<Teacher> Allteachers(Context context) {
        String tid = null;
        String trole = null;
        String tname = null;
        String tprofessional = null;
        String tcourse = null;
        String tphone = null;
        SQLiteDatabase db = getDatabase(context);
        List<Teacher> list = new ArrayList<Teacher>();
        Cursor cursor = db.query("teacherinfo", null, null,
                null, null, null, null);
        while (cursor.moveToNext()) {
            tid = cursor.getString(cursor.getColumnIndex("teacid"));
            tname = cursor.getString(cursor.getColumnIndex("teacname"));
            trole = cursor.getString(cursor.getColumnIndex("teacrole"));
            tprofessional = cursor.getString(cursor.getColumnIndex("teacprofessional"));
            tcourse = cursor.getString(cursor.getColumnIndex("teaccourse"));
            tphone = cursor.getString(cursor.getColumnIndex("teacphone"));
            list.add(new Teacher(tid, tname, trole, tcourse, tprofessional, tphone));
        }
        cursor.close();
        db.close();
        return list;
    }

    /**
     * 删除教师信息
     *
     * @param context
     * @param id
     * @return
     */
    public static boolean deleteTeacher(Context context, String id) {
        boolean result = false;
        SQLiteDatabase db = getDatabase(context);
        try {
            db.delete("teacherinfo", "teacid=?", new String[]{id});
            result = true;
        } catch (SQLException e) {

        }
        return result;
    }

    /**
     * 添加学生缺勤信息
     *
     * @param context
     * @return
     */
    public static boolean addAttend(Context context, Attend attend) {
        boolean result = false;
        SQLiteDatabase db = getDatabase(context);
        try {
            ContentValues values = new ContentValues();
            values.put("student_id", attend.getStudentID());
            values.put("truancy", attend.getTruancy());
            values.put("teacher_course", attend.getTeacherCourse());
            db.insert("attendexcel", null, values);
            result = true;
        } catch (SQLException e) {

        }
        db.close();
        return result;
    }

    /**
     * 查询学生缺勤信息
     *
     * @param context
     * @param id
     * @return
     */
    public static List<Attend> queryAttend(Context context, String id,int a) {
        String sssid = null;
        String struancy = null;
        String tc = null;
        Cursor cursor;
        List<Attend> attends = new ArrayList<Attend>();
        SQLiteDatabase db = getDatabase(context);
        try {
            if(a==1){
                cursor = db.query("attendexcel", null, "student_id=?", new String[]{id}
                        , null, null, null);
            }else{
                cursor = db.query("attendexcel", null, "teacher_course=?", new String[]{id}
                        , null, null, null);
            }
            while (cursor.moveToNext()) {
                sssid = cursor.getString(cursor.getColumnIndex("student_id"));
                struancy = cursor.getString(cursor.getColumnIndex("truancy"));
                tc = cursor.getString(cursor.getColumnIndex("teacher_course"));
                attends.add(new Attend(sssid, struancy, tc));
            }
            cursor.close();
            db.close();
        } catch (SQLException e) {

        }
        return attends;
    }

    /**
     * 添加学生成绩
     * @param context
     * @param score
     * @return
     */
    public static boolean addScore(Context context,Score score){
        boolean result = false;
        SQLiteDatabase db = getDatabase(context);
        try {
            ContentValues values = new ContentValues();
            values.put("stud_id", score.getStudentid());
            values.put("score", score.getScore());
            values.put("teac_course", score.getCourse());
            db.insert("results", null, values);
            result = true;
        } catch (SQLException e) {
        }
        db.close();
        return result;
    }

    /**
     * 查询学生成绩信息
     * @param context
     * @param id
     * @param a
     * @return
     */
    public static List<Score> queryScore(Context context, String id,int a) {
        String sssid = null;
        String struancy = null;
        String tc = null;
        Cursor cursor;
        List<Score> attends = new ArrayList<Score>();
        SQLiteDatabase db = getDatabase(context);
        try {
            if(a==1){
                cursor = db.query("results", null, "stud_id=?", new String[]{id}
                        , null, null, null);
            }else{
                cursor = db.query("results", null, "teac_course=?", new String[]{id}
                        , null, null, null);
            }
            while (cursor.moveToNext()) {
                sssid = cursor.getString(cursor.getColumnIndex("stud_id"));
                struancy = cursor.getString(cursor.getColumnIndex("score"));
                tc = cursor.getString(cursor.getColumnIndex("teac_course"));
                attends.add(new Score(sssid, struancy, tc));
            }
            cursor.close();
            db.close();
        } catch (SQLException e) {

        }
        return attends;
    }

    /**
     * 添加评价
     * @param context
     * @param sevalua
     * @return
     */
    public static boolean addSevalua(Context context,Sevalua sevalua){
        boolean result = false;
        SQLiteDatabase db = getDatabase(context);
        try {
            ContentValues values = new ContentValues();
            values.put("sd_id", sevalua.getStudentid());
            values.put("appraise", sevalua.getAppraise());
            values.put("teac_cs", sevalua.getCourse());
            db.insert("sevalua", null, values);
            result = true;
        } catch (SQLException e) {
        }
        db.close();
        return result;
    }
    public static List<Sevalua> querySevalua(Context context, String id,int a) {
        String sid = null;
        String appraise = null;
        String teac = null;
        Cursor cursor;
        List<Sevalua> attends = new ArrayList<Sevalua>();
        SQLiteDatabase db = getDatabase(context);
        try {
            if(a==1){
                cursor = db.query("sevalua", null, "sd_id=?", new String[]{id}
                        , null, null, null);
            }else{
                cursor = db.query("sevalua", null, "teac_cs=?", new String[]{id}
                        , null, null, null);
            }
            while (cursor.moveToNext()) {
                sid = cursor.getString(cursor.getColumnIndex("sd_id"));
                appraise = cursor.getString(cursor.getColumnIndex("appraise"));
                teac = cursor.getString(cursor.getColumnIndex("teac_cs"));
                attends.add(new Sevalua(sid, appraise, teac));
            }
            cursor.close();
            db.close();
        } catch (SQLException e) {

        }
        return attends;
    }
}
