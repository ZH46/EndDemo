package com.example.administrator.courcemanager.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.RadioGroup;
import com.example.administrator.courcemanager.R;
import com.example.administrator.courcemanager.fragment.*;

import java.util.ArrayList;
import java.util.List;

public class TeacherActivity extends FragmentActivity {
    private ViewPager viewPager;//界面切换器
    private RadioGroup radioGroup;
    private List<Fragment> fragmentList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        viewPager=(ViewPager)findViewById(R.id.view_pager_t);
        radioGroup=(RadioGroup)findViewById(R.id.radio_group_t);

        CourceFragment courceFragment=new CourceFragment();
        InfoFragment infoFragment=new InfoFragment();
        TPersonalFragment tpersonalFragment=new TPersonalFragment();
        fragmentList.add(courceFragment);
        fragmentList.add(infoFragment);
        fragmentList.add(tpersonalFragment);

        viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(),fragmentList));

        // ViewPager页面切换监听器
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        radioGroup.check(R.id.teacher_cource);
                        break;
                    case 1:
                        radioGroup.check(R.id.teacher_Info);
                        break;
                    case 2:
                        radioGroup.check(R.id.teacher_personal);
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
                    case R.id.teacher_cource:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.teacher_Info:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.teacher_personal:
                        viewPager.setCurrentItem(2);
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
