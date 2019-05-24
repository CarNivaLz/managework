package com.dommy.tab.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dommy.tab.R;
import com.dommy.tab.adapter.AchievementsFragmentAdapter;
import com.dommy.tab.adapter.ProjectsFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * <p>展示居中位置的tab页卡</p>
 */
public class AchievementsFragment extends Fragment {
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.achievements_viewpager)
    ViewPager viewPager;

    private AchievementsFragmentAdapter adapter;
    private List<String> names;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_achievements, container, false);
        ButterKnife.bind(this, view);

        adapter = new AchievementsFragmentAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        // 更新适配器数据
        adapter.setList(names);
        return view;
    }

    private void initData() {
        names = new ArrayList<>();
        names.add("论文");
        names.add("专利");
        names.add("著作权");
    }

}
