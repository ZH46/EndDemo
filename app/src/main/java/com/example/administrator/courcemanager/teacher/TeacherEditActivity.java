package com.example.administrator.courcemanager.teacher;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.administrator.courcemanager.R;
import com.example.administrator.courcemanager.sql.MyPassWord;
import com.example.administrator.courcemanager.vo.Student;
import com.example.administrator.courcemanager.vo.Teacher;


public class TeacherEditActivity extends Activity {

    private EditText nameet;
    private EditText professionalet;
    private EditText courseet;
    private EditText phoneet;
    private String roleaa="教师";
    private boolean bb=false;
    private Button btok;
    private String tid;
    private static boolean isback=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_edit);

        SharedPreferences sharep = getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        tid=sharep.getString("userName","");

        Teacher teacher= MyPassWord.queryTeacher(getBaseContext(),tid);

        nameet=findViewById(R.id.tnameET);
        professionalet=findViewById(R.id.tprofessionalET);
        courseet=findViewById(R.id.tcourseET);
        phoneet=findViewById(R.id.tphoneET);
        btok=findViewById(R.id.s_editbt_ok);

        nameet.setText(teacher.getName());
        professionalet.setText(teacher.getProfessional());
        courseet.setText(teacher.getCourse());
        phoneet.setText(teacher.getPhone());

        btok=findViewById(R.id.t_editbt_ok);
        DisplayMetrics metrics=getResources().getDisplayMetrics();//获取屏幕宽度
        btok.setWidth(metrics.widthPixels);
        btok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=nameet.getText().toString().trim();
                String professional=professionalet.getText().toString().trim();
                String course=courseet.getText().toString().trim();
                String phone=phoneet.getText().toString().trim();
                Teacher teac=new Teacher(tid,name,roleaa,course,professional,phone);
                bb=MyPassWord.updateTeacherInfo(getBaseContext(),teac);

                if(bb==true){
                    Toast.makeText(getApplicationContext(),"编辑成功!",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(TeacherEditActivity.this, TeacherActivity.class);
                    isback=true;
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
    public static boolean back(){
        return isback;
    }

}
