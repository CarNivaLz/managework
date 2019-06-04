package com.dommy.tab.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dommy.tab.R;
import com.dommy.tab.module.Projects;
import com.dommy.tab.module.Results;
import com.dommy.tab.ui.AchievementsDetailActivity;
import com.dommy.tab.ui.ProjectsDetailActivity;

import java.util.List;


public class SearchResultListAdapter extends BaseQuickAdapter<Results,BaseViewHolder> {
    public SearchResultListAdapter(List<Results> data){
        super(R.layout.result_item,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Results item) {
        String type="";
        switch (item.getPosition1()){
            case 0:
                type="项目";
                break;
            case 1:
                type="论文";
                break;
            case 2:
                type="专利";
                break;
            case 3:
                type="著作权";
                break;
        }
        baseViewHolder.setText(R.id.result_tittle,item.getTittle())
                .setText(R.id.result_position_1,"类型:"+type)
                .setText(R.id.result_position_2,"姓名："+item.getPosition2())
                .setText(R.id.result_position_3,"时间："+item.getPosition3());
        View view = baseViewHolder.getConvertView();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进入projectdetail页面逻辑WebActivity.runActivity(mContext, model.desc, model.url);
                switch (item.getPosition1()){
                    case 0:
                        ProjectsDetailActivity.runActivity(mContext,item.getId(),item.getTittle());
                        break;
                    case 1:
                        AchievementsDetailActivity.runActivity(mContext,item.getId(),item.getPosition1());
                        break;
                    case 2:
                        AchievementsDetailActivity.runActivity(mContext,item.getId(),item.getPosition1());
                        break;
                    case 3:
                        AchievementsDetailActivity.runActivity(mContext,item.getId(),item.getPosition1());
                        break;
                }
            }
        });
    }



}