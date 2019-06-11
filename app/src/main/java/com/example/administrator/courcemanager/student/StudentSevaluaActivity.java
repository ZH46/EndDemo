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
import com.example.administrator.courcemanager.vo.Sevalua;

import java.util.ArrayList;
import java.util.List;

public class StudentSevaluaActivity extends Activity {

    private List<Sevalua> list=new ArrayList<Sevalua>();
    private StudentSevaluaAdapter adapter;
    private ListView listView;
    private String sid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sevalua);

        SharedPreferences sharep = getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        sid = sharep.getString("userName", "");

        list= MyPassWord.querySevalua(getBaseContext(),sid,1);

        adapter = new StudentSevaluaAdapter();
        listView.setAdapter(adapter);
    }
    public class StudentSevaluaAdapter extends BaseAdapter {

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
            final Sevalua s = list.get(position);
            idTV.setText(s.getCourse());
            otherTV.setText(s.getAppraise());
            return item;
        }
    }
}
