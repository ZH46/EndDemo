package com.example.administrator.courcemanager.sql;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.example.administrator.courcemanager.vo.RoleInfo;

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
     * 查询数据
     *
     * @param context
     * @param selection
     * @param selectionArgs
     * @return
     */
    public static RoleInfo queryData(Context context, String selection, String[] selectionArgs) {
        String numberString=null;
        String passwordString=null;
        String rolestyle=null;
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

    public static boolean findNumber(Context context, String selection, String[] selectionArgs){
        boolean result=false;
        SQLiteDatabase db = getDatabase(context);
        Cursor cursor = db.query(false, "password", null,
                selection, selectionArgs, null, null, null, null);
        if (cursor.getCount() > 0) {
            result=true;
        }
        cursor.close();
        db.close();
        return result;
    }

}
