package com.example.administrator.courcemanager.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.example.administrator.courcemanager.R;
import com.example.administrator.courcemanager.vo.Course;

public class SPersonalFragment extends Fragment {

    Course course;
    private TextView tv_name;
    private TextView tv_role;
    private TextView tv_grade;
    private TextView tv_professional;
    private TextView tv_class;
    private TextView tv_stuid;
    private EditText tv_bir;
    private EditText tv_phone;

    public SPersonalFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            getData();
        } catch (Exception ee) {
            ee.getMessage();
        }
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
        tv_bir = view.findViewById(R.id.stu_bir);
        tv_phone = view.findViewById(R.id.stu_phone);
        initStudentText(course);
        return view;
    }
    public void getData(){

    }
    public void initStudentText(Course course){
        tv_name.setText("姓名");
        tv_role.setText("角色");
        tv_grade.setText("年级");
        tv_grade.setTextSize(20);
        tv_professional.setText("专业");
        tv_professional.setTextSize(20);
        tv_class.setText("班级");
        tv_class.setTextSize(20);
        tv_stuid.setText("学号");
        tv_stuid.setTextSize(20);
        tv_bir.setText("出生日期");
        tv_bir.setTextSize(20);
        tv_phone.setText("联系电话");
        tv_phone.setTextSize(20);
    }
}
