package com.dommy.tab.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dommy.tab.R;
import com.dommy.tab.adapter.AchievementsListAdapter;
import com.dommy.tab.adapter.ProjectsListAdapter;
import com.dommy.tab.module.Achievements;
import com.dommy.tab.module.Projects;
import com.dommy.tab.ui.ProjectsDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AchievementsContentFragment extends Fragment {
    //    @BindView(R.id.txt_content)
//    TextView tvContent;
    @BindView(R.id.achievements_recycle)
    RecyclerView recyclerView;
    int flag=0;
    private String name;
    List<Achievements> achievementsItemList =new ArrayList<>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        name = bundle.getString("name");
        if (name == null) {
            name = "参数非法";
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_achievements_content, container, false);
        ButterKnife.bind(this, view);

//
// tvContent.setText(name);
        if (flag==0){
            initDatas();
            flag+=1;
        }

        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        AchievementsListAdapter adapter=new AchievementsListAdapter(R.layout.fragment_achievements_item,achievementsItemList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                TextView a=(TextView)view.findViewById(R.id.title);
//                if(a.getText().toString().contains("产品力再升级！")){
//                    startActivity(new Intent(getActivity(),Newsdetail_activity.class));
//                };
                startActivity(new Intent(getActivity(),ProjectsDetailActivity.class));
            }
        });



        return view;
    }


    private void initDatas() {
        //由name决定请求的项目状态类型
        switch (name){

        }
        Achievements news1=new Achievements("1","这是专利","老师:南策名","成员：5人","2019.3.31");
        Achievements news2=new Achievements("2","绿色网络关键技术研究","老师:苏俊","成员：6人","2019.4.11");
        Achievements news3=new Achievements("3","信息传输分系统研制","老师:李明","成员：4人","2019.4.26");
        Achievements news4=new Achievements("4","车用无线自组织网络安全告警信息传输策略研究","老师:赵茜珊","成员：7人","2019.4.29");
        Achievements news5=new Achievements("5","龙门山地震带小流域滑坡泥石流灾害监测预警技术研究与示范","老师:琴发华","成员：5人","2019.5.06");

        achievementsItemList.add(news1);
        achievementsItemList.add(news2);
        achievementsItemList.add(news3);
        achievementsItemList.add(news4);
        achievementsItemList.add(news5);
    }
}
