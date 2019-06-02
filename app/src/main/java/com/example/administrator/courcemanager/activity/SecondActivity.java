package com.example.administrator.courcemanager.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.*;
import com.example.administrator.courcemanager.R;
import com.example.administrator.courcemanager.utils.RoleData;
import com.example.administrator.courcemanager.vo.RoleInfo;
import com.example.administrator.courcemanager.sql.MyPassWord;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 注册界面类
 */
public class SecondActivity extends Activity {
    private EditText useret;//账号输入框
    private EditText pwet;//密码输入框
    private EditText pwetok;//二次密码输入框
    private Button Register;//注册按钮
    List<String> infos = null;//解析XML得到的list
    private Spinner roleChoose;
    private ArrayList<String> roleList;
    private ArrayAdapter<String> roleAdapter;
    String roleName;//角色

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        useret = ( EditText ) findViewById(R.id.registerQQ);
        pwet = ( EditText ) findViewById(R.id.registerPW);
        pwetok = ( EditText ) findViewById(R.id.registerPWOk);
        Register = ( Button ) findViewById(R.id.registerButton);
        roleChoose = ( Spinner ) findViewById(R.id.role);

        try {
            infos = RoleData.getRoleInfos(getResources().openRawResource(R.raw.nations));
        } catch (Exception e) {
            e.printStackTrace();
        }

        roleList = new ArrayList<String>();
        for (int i = 0; i < infos.size(); i++) {
            roleList.add(infos.get(i));
        }
        roleAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, roleList);
        roleChoose.setAdapter(roleAdapter);

        roleChoose.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                roleName = parent.getItemAtPosition(position).toString().trim();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int OK = 0;
                String userNumber = useret.getText().toString();
                String password = pwet.getText().toString();
                String secondpassword = pwetok.getText().toString();
                Boolean isExist = MyPassWord.findNumber(getBaseContext(), "number=?", new String[]{userNumber});
                if (isExist==true) {
                    Toast.makeText(getApplicationContext(), "账号存在!", Toast.LENGTH_SHORT).show();
                    OK = 1;
                }
                if (password.equals("")) {
                    Toast.makeText(getApplicationContext(), "请输入密码!", Toast.LENGTH_SHORT).show();
                    OK = 2;
                } else if (secondpassword.equals("")) {
                    Toast.makeText(getApplicationContext(), "请再次输入密码!", Toast.LENGTH_SHORT).show();
                    OK = 3;
                } else if (!secondpassword.equals(password)) {
                    Toast.makeText(getApplicationContext(), "密码不一致!", Toast.LENGTH_SHORT).show();
                    OK = 4;
                }
                if (OK == 0) {
                    RoleInfo roleInfo = new RoleInfo(userNumber, password, roleName);
                    MyPassWord.addData(getBaseContext(), roleInfo);
                    Toast.makeText(getApplicationContext(), "注册成功!", Toast.LENGTH_SHORT).show();
                    TimerTask task = new TimerTask() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    };
                    Timer timer = new Timer();
                    timer.schedule(task, 1000);
                }
            }

        });
    }
}
