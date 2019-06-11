package com.example.administrator.courcemanager.student;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.example.administrator.courcemanager.R;
import com.example.administrator.courcemanager.sql.MyPassWord;
import com.example.administrator.courcemanager.vo.Attend;
import com.example.administrator.courcemanager.vo.Score;

import java.util.ArrayList;
import java.util.List;

public class StudentScoreActivity extends Activity {

    private List<Score> list=new ArrayList<Score>();
    private StudentScoreAdapter adapter;
    private ListView listView;
    private String sid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_score);
        listView = findViewById(R.id.lv_score);

        SharedPreferences sharep = getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        sid = sharep.getString("userName", "");

        list= MyPassWord.queryScore(getBaseContext(),sid,1);

        adapter = new StudentScoreAdapter();
        listView.setAdapter(adapter);
    }
    public class StudentScoreAdapter extends BaseAdapter{
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
            idTV.setText(s.getCourse());
            otherTV.setText(s.getScore());
            return item;
        }
    }
}
