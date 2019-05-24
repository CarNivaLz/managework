package com.dommy.tab.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.dommy.tab.fragment.AchievementsFragment;
import com.dommy.tab.fragment.SearchFragment;
import com.dommy.tab.fragment.MeFragment;
import com.dommy.tab.fragment.ProjectsFragment;

/**
 * 主界面底部菜单适配器
 */
public class MainFragmentAdapter extends FragmentPagerAdapter {
    public MainFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;
        switch (i) {
            case 0:
                fragment = new ProjectsFragment();
                break;
            case 1:
                fragment = new AchievementsFragment();
                break;
            case 2:
                fragment = new SearchFragment();
                break;
            case 3:
                fragment = new MeFragment();
                break;
            default:
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }

}
