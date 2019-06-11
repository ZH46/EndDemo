package com.example.administrator.courcemanager.student;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.administrator.courcemanager.R;
import com.example.administrator.courcemanager.sql.MyPassWord;
import com.example.administrator.courcemanager.vo.Student;
import com.example.administrator.courcemanager.vo.Teacher;


public class StudentEditActivity extends Activity {
    private EditText nameet;
    private EditText gradeet;
    private EditText professionalet;
    private EditText classet;
    private EditText phoneet;
    private Button btok;
    private String roleaa="学生";
    private boolean aa=false;
    private String id;
    private static boolean isback=false;//判断确定按钮是否点击

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_edit);

        SharedPreferences sharep = getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        id=sharep.getString("userName","");

        Student student=MyPassWord.queryStudent(getBaseContext(),id);
        nameet=findViewById(R.id.snameET);
        gradeet=findViewById(R.id.sgradeET);
        professionalet=findViewById(R.id.sprofessionalET);
        classet=findViewById(R.id.sclassET);
        phoneet=findViewById(R.id.sphoneET);
        btok=findViewById(R.id.s_editbt_ok);

        nameet.setText(student.getName());
        gradeet.setText(student.getGrade());
        professionalet.setText(student.getProfessional());
        classet.setText(student.getClasses());
        phoneet.setText(student.getPhone());

        DisplayMetrics metrics=getResources().getDisplayMetrics();//获取屏幕宽度
        btok.setWidth(metrics.widthPixels);
        btok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=nameet.getText().toString().trim();
                String grade=gradeet.getText().toString().trim();
                String professional=professionalet.getText().toString().trim();
                String classed=classet.getText().toString().trim();
                String phone=phoneet.getText().toString().trim();
                Student stu=new Student(id,name,roleaa,grade,professional,classed,phone);
                aa=MyPassWord.updateStudentInfo(getBaseContext(),stu);

                if(aa=true){
                    Toast.makeText(getApplicationContext(),"编辑成功!",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(StudentEditActivity.this, StudentActivity.class);
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
