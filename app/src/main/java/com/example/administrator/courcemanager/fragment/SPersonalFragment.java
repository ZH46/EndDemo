package com.example.administrator.courcemanager.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.administrator.courcemanager.R;
import com.example.administrator.courcemanager.activity.MainActivity;
import com.example.administrator.courcemanager.sql.MyPassWord;
import com.example.administrator.courcemanager.student.StudentEditActivity;
import com.example.administrator.courcemanager.vo.Course;
import com.example.administrator.courcemanager.vo.Student;


public class SPersonalFragment extends Fragment {

    private String sid;
    private TextView tv_name;
    private TextView tv_role;
    private TextView tv_grade;
    private TextView tv_professional;
    private TextView tv_class;
    private TextView tv_stuid;
    private TextView tv_phone;
    private Button bt_edit;
    private Button bt_ok;

    public SPersonalFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharep = getActivity().getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        sid = sharep.getString("userName", "");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_personal_student, container, false);
        tv_name = view.findViewById(R.id.stu_name);
        tv_role = view.findViewById(R.id.stu_role);
        tv_grade = view.findViewById(R.id.stu_grade);
        tv_professional = view.findViewById(R.id.stu_professional);
        tv_class = view.findViewById(R.id.stu_class);
        tv_stuid = view.findViewById(R.id.stu_id);
        tv_phone = view.findViewById(R.id.stu_phone);
        tv_role.setText("学生");
        tv_stuid.setText(sid);

        bt_edit = view.findViewById(R.id.s_editbt);
        bt_ok = view.findViewById(R.id.s_backbt);
        DisplayMetrics metrics = getResources().getDisplayMetrics();//获取屏幕宽度
        bt_ok.setWidth(metrics.widthPixels);

        bt_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StudentEditActivity.class);
                startActivity(intent);
            }
        });
        bt_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        Student student = MyPassWord.queryStudent(getContext(), sid);
        initStudentText(student);
        return view;
    }

    public void initStudentText(Student student) {
        if (student.getName() != null) {
            tv_name.setText(student.getName());
        }
        if (student.getGrade() != null) {
            tv_grade.setText(student.getGrade());
        }
        if (student.getProfessional() != null) {
            tv_professional.setText(student.getProfessional());
        }
        if (student.getClasses() != null) {
            tv_class.setText(student.getClasses());
        }
        if (student.getPhone() != null) {
            tv_phone.setText(student.getPhone());
        }
    }
}
