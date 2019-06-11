package com.example.administrator.courcemanager.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 记住密码的操作类
 */
public class Utils {
    public static void saveUserInfo(Context context, String number, String password){
        SharedPreferences sp=context.getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("userName",number);
        editor.putString("pwd",password);
        editor.commit();
    }
    public static void saveUserPassword(Context context, String number, String password,int status){
        SharedPreferences sp=context.getSharedPreferences("data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("userName",number);
        editor.putString("pwd",password);
        editor.putInt("check",status);
        editor.commit();
    }
}
