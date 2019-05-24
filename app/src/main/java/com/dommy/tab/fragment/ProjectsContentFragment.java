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
import com.dommy.tab.adapter.ProjectsListAdapter;
import com.dommy.tab.module.Projects;
import com.dommy.tab.ui.ProjectsDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 消息内容页
 */
public class ProjectsContentFragment extends Fragment {
//    @BindView(R.id.txt_content)
//    TextView tvContent;
    @BindView(R.id.projects_recycle)
    RecyclerView recyclerView;
    int flag=0;
    private String name;
    List<Projects> projectsItemList =new ArrayList<>();
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
        View view = inflater.inflate(R.layout.fragment_projects_content, container, false);
        ButterKnife.bind(this, view);

//
// tvContent.setText(name);
        if (flag==0){
            initDatas();
            flag+=1;
        }

        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        ProjectsListAdapter adapter=new ProjectsListAdapter(R.layout.fragment_projects_item,projectsItemList);
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
        Projects news1=new Projects("1","TD-LTE协议栈实现","老师:南策名","成员：5人","2019.3.31");
        Projects news2=new Projects("2","绿色网络关键技术研究","老师:苏俊","成员：6人","2019.4.11");
        Projects news3=new Projects("3","信息传输分系统研制","老师:李明","成员：4人","2019.4.26");
        Projects news4=new Projects("4","车用无线自组织网络安全告警信息传输策略研究","老师:赵茜珊","成员：7人","2019.4.29");
        Projects news5=new Projects("5","龙门山地震带小流域滑坡泥石流灾害监测预警技术研究与示范","老师:琴发华","成员：5人","2019.5.06");

        projectsItemList.add(news1);
        projectsItemList.add(news2);
        projectsItemList.add(news3);
        projectsItemList.add(news4);
        projectsItemList.add(news5);
    }
}
