package com.example.administrator.courcemanager.teacher;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.AutoText;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.example.administrator.courcemanager.R;
import com.example.administrator.courcemanager.manager.StudentManageActivity;
import com.example.administrator.courcemanager.sql.MyPassWord;
import com.example.administrator.courcemanager.vo.Attend;
import com.example.administrator.courcemanager.vo.Student;
import com.example.administrator.courcemanager.vo.Teacher;

import java.util.ArrayList;
import java.util.List;

public class TeacherAttendActivity extends Activity {

    private EditText eT1;
    private EditText eT2;
    public Button button;
    private List<Attend> list=new ArrayList<Attend>();
    private AttendAdapter adapter;
    private ListView listView;
    private String tid;
    private String course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_attend);
        eT1 = findViewById(R.id.stu_searchattend);
        eT2 = findViewById(R.id.stu_attendstyle);
        button = findViewById(R.id.ssa);
        listView = findViewById(R.id.lv_stuattend);

        SharedPreferences sharep = getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        tid = sharep.getString("userName", "");

        Teacher teacher = MyPassWord.queryTeacher(getBaseContext(), tid);
        course = teacher.getCourse();

        if(course!=null){
            list = MyPassWord.queryAttend(getBaseContext(), course,0);
        }
        if(list.size()==0){
            Toast.makeText(getBaseContext(),"暂无学生缺勤情况!",Toast.LENGTH_SHORT).show();
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stuid = eT1.getText().toString().trim();
                String style = eT2.getText().toString().trim();
                Attend attend = new Attend(stuid, style, course);
                MyPassWord.addAttend(getBaseContext(), attend);
                list.add(attend);
                adapter.notifyDataSetChanged();
                eT1.setText("");
                eT2.setText("");
            }
        });
        adapter = new AttendAdapter();
        listView.setAdapter(adapter);
    }

    private class AttendAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View item = convertView != null ? convertView : View.inflate(getApplicationContext(), R.layout.item_attend, null);
            TextView idTV = item.findViewById(R.id.idTVV);
            TextView otherTV = item.findViewById(R.id.otherTVV);
            final Attend a = list.get(position);
            idTV.setText(a.getStudentID());
            otherTV.setText(a.getTruancy());
            return item;
        }
    }
}
