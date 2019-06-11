package com.example.administrator.courcemanager.manager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.administrator.courcemanager.R;
import com.example.administrator.courcemanager.activity.MainActivity;

import java.util.Timer;
import java.util.TimerTask;

public class ManagerActivity extends Activity {

    private Button studentManage;
    private Button teacherManage;
    private Button reback;
    private static boolean mBackPress=false;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        studentManage=findViewById(R.id.s_manage);
        teacherManage=findViewById(R.id.t_manage);
        reback=findViewById(R.id.mmback);
        studentManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ManagerActivity.this, StudentManageActivity.class);
                startActivity(intent);
            }
        });
        teacherManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ManagerActivity.this, TeacherManageActivity.class);
                startActivity(intent);
            }
        });
        reback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ManagerActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    /**
     * 双击退出程序
     */
    @Override
    public void onBackPressed() {
        if(!mBackPress){
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mBackPress = true;
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    mBackPress=false;
                }
            },2000);
        }else{
            this.finish();
            System.exit(0);
        }
    }
}
