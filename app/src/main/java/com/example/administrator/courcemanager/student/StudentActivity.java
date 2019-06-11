package com.example.administrator.courcemanager.student;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.example.administrator.courcemanager.R;
import com.example.administrator.courcemanager.fragment.CourceFragment;
import com.example.administrator.courcemanager.fragment.SInfoFragment;
import com.example.administrator.courcemanager.fragment.MyFragmentPagerAdapter;
import com.example.administrator.courcemanager.fragment.SPersonalFragment;
import com.example.administrator.courcemanager.student.StudentEditActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 学生界面类
 */
public class StudentActivity extends FragmentActivity {
    private ViewPager viewPager;//界面切换器
    private RadioGroup radioGroup;
    private List<Fragment> fragmentList=new ArrayList<>();
    private static boolean mBackPress=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        SharedPreferences sharep = getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        String sid=sharep.getString("userName","");

        viewPager=(ViewPager)findViewById(R.id.view_pager);
        radioGroup=(RadioGroup)findViewById(R.id.radio_group);

        CourceFragment courceFragment=new CourceFragment();
        SInfoFragment sInfoFragment =new SInfoFragment();
        SPersonalFragment spersonalFragment=new SPersonalFragment();

        fragmentList.add(courceFragment);
        fragmentList.add(sInfoFragment);
        fragmentList.add(spersonalFragment);

        viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(),fragmentList));

        boolean result= StudentEditActivity.back();
        if(result==true){
            viewPager.setCurrentItem(2);
        }

        // ViewPager页面切换监听器
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        radioGroup.check(R.id.student_cource);
                        break;
                    case 1:
                        radioGroup.check(R.id.student_Info);
                        break;
                    case 2:
                        radioGroup.check(R.id.student_personal);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // RadioGroup选中状态改变监听
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.student_cource:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.student_Info:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.student_personal:
                        viewPager.setCurrentItem(2);
                        break;
                    default:
                        break;
                }
            }
        });
    }
    /**
     * 双击退出程序,返回主菜单
     */
    @Override
    public void onBackPressed() {
        if(!mBackPress){
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mBackPress = true;
            viewPager.setCurrentItem(0);
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    mBackPress=false;
                }
            },2000);
        }else{
            this.finish();
            System.exit(0);
        }
    }
}
