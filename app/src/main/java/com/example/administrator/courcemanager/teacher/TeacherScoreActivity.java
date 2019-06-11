package com.example.administrator.courcemanager.teacher;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.renderscript.ScriptC;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.example.administrator.courcemanager.R;
import com.example.administrator.courcemanager.sql.MyPassWord;
import com.example.administrator.courcemanager.vo.Attend;
import com.example.administrator.courcemanager.vo.Score;
import com.example.administrator.courcemanager.vo.Student;
import com.example.administrator.courcemanager.vo.Teacher;

import java.util.ArrayList;
import java.util.List;

public class TeacherScoreActivity extends Activity {

    private EditText eT1;
    private EditText eT2;
    public Button button;
    private List<Student> studentList=new ArrayList<Student>();
    private List<Score> list = new ArrayList<Score>();
    private ScoreAdapter adapter;
    private ListView listView;
    private String tid=null;
    private String course=null;
    private String professional=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_score);
        eT1 = findViewById(R.id.stu_searchscore);
        eT2 = findViewById(R.id.stu_scorestyle);
        button = findViewById(R.id.scorea);
        listView = findViewById(R.id.lv_stuscore);

        SharedPreferences sharep = getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        tid = sharep.getString("userName", "");

        Teacher teacher = MyPassWord.queryTeacher(getBaseContext(), tid);
        course = teacher.getCourse();
        professional = teacher.getProfessional();

        if (course != null) {
            studentList = MyPassWord.Allstudents(getBaseContext());
            for (Student student : studentList) {
                if (student.getProfessional().equals(professional)) {
                    list.add(new Score(student.getStuid(), null, course));
                }
            }
        }
        if(list.size()==0){
            Toast.makeText(getBaseContext(),"暂无学生数据",Toast.LENGTH_SHORT).show();
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stuid = eT1.getText().toString().trim();
                String style = eT2.getText().toString().trim();
                Score score = new Score(stuid, style, course);
                MyPassWord.addScore(getBaseContext(), score);
                list.add(score);
                adapter.notifyDataSetChanged();
                eT1.setText("");
                eT2.setText("");
            }
        });
        adapter = new ScoreAdapter();
        listView.setAdapter(adapter);
    }

    private class ScoreAdapter extends BaseAdapter {

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
            final Score s = list.get(position);
            idTV.setText(s.studentid);
            otherTV.setText(s.getScore());
            return item;
        }
    }
}
