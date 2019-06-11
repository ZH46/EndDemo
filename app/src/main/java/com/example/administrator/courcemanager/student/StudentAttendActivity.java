package com.example.administrator.courcemanager.student;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.example.administrator.courcemanager.R;
import com.example.administrator.courcemanager.sql.MyPassWord;
import com.example.administrator.courcemanager.teacher.TeacherAttendActivity;
import com.example.administrator.courcemanager.vo.Attend;

import java.util.ArrayList;
import java.util.List;

public class StudentAttendActivity extends Activity {

    private List<Attend> list=new ArrayList<Attend>();
    private StudentAttendAdapter adapter;
    private ListView listView;
    private String sid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_attend);
        listView = findViewById(R.id.lv_attend);

        SharedPreferences sharep = getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        sid = sharep.getString("userName", "");

        list= MyPassWord.queryAttend(getBaseContext(),sid,1);

        adapter = new StudentAttendAdapter();
        listView.setAdapter(adapter);

    }

    public class StudentAttendAdapter extends BaseAdapter {

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
            idTV.setText(a.getTeacherCourse());
            otherTV.setText(a.getTruancy());
            return item;
        }
    }
}
