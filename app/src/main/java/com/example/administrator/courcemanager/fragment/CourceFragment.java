package com.example.administrator.courcemanager.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.administrator.courcemanager.R;
import com.example.administrator.courcemanager.vo.Course;

import java.util.ArrayList;
import java.util.List;

public class CourceFragment extends Fragment {

    LinearLayout weekPanels[] = new LinearLayout[7];
    List courseData[] = new ArrayList[7];
    int itemHeight;
    int marTop, marLeft;

    public CourceFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemHeight = getResources().getDimensionPixelSize(R.dimen.weekItemHeight);
        marTop = getResources().getDimensionPixelSize(R.dimen.weekItemMarTop);
        marLeft = getResources().getDimensionPixelSize(R.dimen.weekItemMarLeft);
        try {
            getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_cource, container, false);
        for (int i = 0; i < weekPanels.length; i++) {
            weekPanels[i] = ( LinearLayout ) view.findViewById(R.id.weekPanel_1 + i);
            initWeekPanel(weekPanels[i], courseData[i]);
        }
        return view;
    }

    public void getData() {
        List<Course> list1 = new ArrayList<Course>();
        Course c1 = new Course("软件工程", "A402", 1, 4, "典韦", "1002", 2, 14);
        list1.add(c1);
        list1.add(new Course("C语言", "A101", 6, 3, "甘宁", "1001", 2, 14));
        courseData[0] = list1;

        List<Course> list2 = new ArrayList<Course>();
        list2.add(new Course("计算机组成原理", "A106", 6, 3, "马超", "1001", 2, 14));
        courseData[1] = list2;

        List<Course> list3 = new ArrayList<Course>();
        list3.add(new Course("数据库原理", "A105", 2, 3, "孙权", "1008", 2, 14));
        list3.add(new Course("计算机网络", "A405", 6, 2, "司马懿", "1009", 2, 14));
        list3.add(new Course("电影赏析", "A112", 9, 2, "诸葛亮", "1039", 2, 14));
        courseData[2] = list3;

        List<Course> list4 = new ArrayList<Course>();
        list4.add(new Course("数据结构", "A223", 1, 3, "刘备", "1012", 2, 14));
        list4.add(new Course("操作系统", "A405", 6, 3, "曹操", "1014", 2, 14));
        courseData[3] = list4;

        List<Course> list5 = new ArrayList<Course>();
        list5.add(new Course("Android开发", "C120", 1, 4, "黄盖", "1250", 2, 14));
        list5.add(new Course("游戏设计原理", "C120", 8, 2, "陆逊", "1251", 2, 14));
        courseData[4] = list5;
    }

    public void initWeekPanel(LinearLayout ll, List<Course> data) {
        if (ll == null || data == null || data.size() < 1) return;
        Course pre = data.get(0);
        for (int i = 0; i < data.size(); i++) {
            Course c = data.get(i);
            TextView tv = new TextView(getContext());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    itemHeight * c.getStep() + marTop * (c.getStep() - 1));
            if (i > 0) {
                lp.setMargins(marLeft, (c.getStart() - (pre.getStart() + pre.getStep())) * (itemHeight + marTop) + marTop, 0, 0);
            } else {
                lp.setMargins(marLeft, (c.getStart() - 1) * (itemHeight + marTop) + marTop, 0, 0);
            }
            tv.setLayoutParams(lp);
            tv.setGravity(Gravity.TOP);
            tv.setGravity(Gravity.CENTER_HORIZONTAL);
            tv.setTextSize(12);
            tv.setTextColor(getResources().getColor(R.color.lightcoral));
            tv.setText(c.getName() + "\n" + c.getRoom() + "\n" + c.getTeach() + "\n"
                    + "第" + c.getBegin() + "--" + c.getEnd() + "周");
            tv.setBackgroundColor(getResources().getColor(R.color.linen));
            ll.addView(tv);
            pre = c;
        }
    }
}
