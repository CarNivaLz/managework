package com.dommy.tab.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dommy.tab.R;
import com.dommy.tab.adapter.SearchResultAdapter;
import com.dommy.tab.module.Results;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchResultsActivity extends AppCompatActivity {


    List<Results> resultsList =new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_search_result);
        initDatas();
        LinearLayoutManager layoutManager =new LinearLayoutManager(this);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.result_recycle);
        recyclerView.setLayoutManager(layoutManager);
        SearchResultAdapter adapter=new SearchResultAdapter(R.layout.result_item, resultsList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                startActivity(new Intent(SearchResultsActivity.this,ProjectsDetailActivity.class));
            }
        });
    }



    private void initDatas() {
        //由name决定请求的项目状态类型

        Results news1=new Results("1","网络行为组织学研究","老师:南策名","成员：5人","2019.3.31");
        Results news2=new Results("2","绿色网络关键技术研究","老师:苏俊","成员：6人","2019.4.11");
        Results news3=new Results("3","信息传输分系统研制","老师:李明","成员：4人","2019.4.26");
        Results news4=new Results("4","车用无线自组织网络安全告警信息传输策略研究","老师:赵茜珊","成员：7人","2019.4.29");
        Results news5=new Results("5","龙门山地震带小流域滑坡泥石流灾害监测预警技术研究与示范","老师:琴发华","成员：5人","2019.5.06");

        resultsList.add(news1);
        resultsList.add(news2);
        resultsList.add(news3);
        resultsList.add(news4);
        resultsList.add(news5);
    }
}







