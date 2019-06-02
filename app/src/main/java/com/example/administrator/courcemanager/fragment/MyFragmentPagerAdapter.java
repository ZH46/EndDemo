package com.example.administrator.courcemanager.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * 界面适配器
 */
public class MyFragmentPagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> list;

    public MyFragmentPagerAdapter(FragmentManager fm,List<Fragment> list) {
        super(fm);
        this.list=list;
    }

    @Override
    public Fragment getItem(int i) {
        return list.get(i);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
