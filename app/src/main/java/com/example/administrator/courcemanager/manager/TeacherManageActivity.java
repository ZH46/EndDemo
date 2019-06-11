package com.example.administrator.courcemanager.manager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.example.administrator.courcemanager.R;
import com.example.administrator.courcemanager.sql.MyPassWord;
import com.example.administrator.courcemanager.vo.Course;
import com.example.administrator.courcemanager.vo.Teacher;

import java.util.List;

public class TeacherManageActivity extends Activity {

    private List<Teacher> list;
    private MyAdapter adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_teacher_manage);

        list = MyPassWord.Allteachers(getBaseContext());
        listView = findViewById(R.id.lvTeacher);
        adapter = new MyAdapter();
        listView.setAdapter(adapter);
    }

    private class MyAdapter extends BaseAdapter {

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
            View item = convertView != null ? convertView : View.inflate(getApplicationContext(), R.layout.item, null);
            TextView idTV = item.findViewById(R.id.idTV);
            TextView nameTV = item.findViewById(R.id.nameTV);
            TextView otherTV = item.findViewById(R.id.otherTV);
            ImageView deleteIV = item.findViewById(R.id.delete);
            final Teacher t = list.get(position);
            idTV.setText(t.getTeacherid());
            nameTV.setText(t.getName());
            otherTV.setText(t.getCourse());
            deleteIV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    android.content.DialogInterface.OnClickListener listener =
                            new android.content.DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    list.remove(t);
                                    boolean deleteSucc = MyPassWord.deleteNumber(getBaseContext(), t.getTeacherid());
                                    boolean isdelete = MyPassWord.deleteTeacher(getBaseContext(), t.getTeacherid());
                                    if (deleteSucc == true && isdelete == true) {
                                        Toast.makeText(getBaseContext(), "删除成功!", Toast.LENGTH_SHORT).show();
                                    }
                                    notifyDataSetChanged();
                                }
                            };
                    AlertDialog.Builder builder = new AlertDialog.Builder(TeacherManageActivity.this);
                    builder.setTitle("确定要删除吗？");
                    builder.setPositiveButton("确定", listener);
                    builder.setNegativeButton("取消", null);
                    builder.show();
                }
            });
            return item;
        }
    }
}
