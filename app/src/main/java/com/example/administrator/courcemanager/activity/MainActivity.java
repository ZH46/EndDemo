package com.example.administrator.courcemanager.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.example.administrator.courcemanager.R;
import com.example.administrator.courcemanager.vo.RoleInfo;
import com.example.administrator.courcemanager.utils.Utils;
import com.example.administrator.courcemanager.sql.MyPassWord;

/**
 * 登录界面类
 */
public class MainActivity extends Activity {

    String userName = "";//账号
    String passWord = "";//密码
    String roleStyle = "";//角色
    private int isRemenber = 0;//记住密码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bt = ( Button ) findViewById(R.id.BtLogin);//登录按钮
        CheckBox cb = ( CheckBox ) findViewById(R.id.remember);
        RadioGroup rg = ( RadioGroup ) findViewById(R.id.roleGroup);//角色集合按钮

        final EditText et1 = ( EditText ) findViewById(R.id.editText1);
        final EditText et2 = ( EditText ) findViewById(R.id.editText2);

        /**
         * 记住密码的操纵
         */
        SharedPreferences sharep = getSharedPreferences("data", Context.MODE_PRIVATE);
        int a = sharep.getInt("check", 0);
        if (a == 1) {
            if (sharep != null) {
                String number = sharep.getString("userName", "");
                String password = sharep.getString("pwd", "");
                et1.setText(number);
                et2.setText(password);
                cb.setChecked(true);
            }
        }

        TextView registerTV = ( TextView ) findViewById(R.id.register);
        registerTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = et1.getText().toString();
                passWord = et2.getText().toString();
                if (userName.equals("")) {
                    Toast.makeText(getApplicationContext(), "账号不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else if (passWord.equals("")) {
                    Toast.makeText(getApplicationContext(), "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else if (roleStyle.equals("")) {
                    Toast.makeText(getApplicationContext(), "身份不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    RoleInfo roleInfo= MyPassWord.queryData(getBaseContext(), "number=?", new String[]{userName});
                    boolean result=judge(roleInfo);
                    if(result==true){
                        if(roleStyle.equals("学生")){
                            Intent intent = new Intent(MainActivity.this, StudentActivity.class);
                            startActivity(intent);
                        }else if(roleStyle.equals("教师")){
                            Intent intent = new Intent(MainActivity.this, TeacherActivity.class);
                            startActivity(intent);
                        }else{

                        }
                    }
                }
            }
        });
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isRemenber = 1;
                    userName = et1.getText().toString();
                    passWord = et2.getText().toString();
                    Utils.saveUserInfo(getApplicationContext(), userName, passWord, isRemenber);
                } else {
                    isRemenber = 0;
                    Utils.saveUserInfo(getApplicationContext(), "", "", isRemenber);
                }
            }
        });

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = findViewById(checkedId);
                roleStyle=rb.getText().toString();
            }
        });
    }

    public boolean judge(RoleInfo roleInfo){
        boolean result=false;
        if(userName.equals(roleInfo.getNumber())){
            if(passWord.equals(roleInfo.getPassword())){
                if(roleStyle.equals(roleInfo.getRoleInfo())){
                    Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_SHORT).show();
                    result=true;
                }else{
                    Toast.makeText(getApplicationContext(), "身份不匹配!", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(getApplicationContext(), "密码错误!", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getApplicationContext(), "账号不存在!", Toast.LENGTH_SHORT).show();
        }
        return  result;
    }
}
