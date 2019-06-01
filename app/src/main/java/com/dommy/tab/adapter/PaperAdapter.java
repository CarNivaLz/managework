package com.dommy.tab.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dommy.tab.R;
import com.dommy.tab.module.Paper;
import com.dommy.tab.module.Projects;
import com.dommy.tab.ui.AchievementsDetailActivity;

import java.lang.reflect.Type;
import java.util.List;


public class PaperAdapter extends BaseQuickAdapter<Paper,BaseViewHolder> {
    public PaperAdapter(List<Paper> data){
        super(R.layout.fragment_myprofile_item,data);
    }
    @Override
    protected void convert(BaseViewHolder baseViewHolder, Paper item) {
        String status="";
        switch (item.getStatus()){
            case 1:status="已投递";
                break;
            case 2:status="已过审";
                break;
            case 3:status="已发表";
                break;
            case 4:status="已检索";
                break;
            default:status="无";
                break;
        }
        baseViewHolder.setText(R.id.tittle,item.getTitle())
                .setText(R.id.position1,"期刊："+item.getJournal())
                .setText(R.id.position2,"状态："+status)
                .setText(R.id.position3,item.getDate_deliver());
        View view = baseViewHolder.getConvertView();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进入projectdetail页面逻辑WebActivity.runActivity(mContext, model.desc, model.url);
                AchievementsDetailActivity.runActivity(mContext,item.getId(),1);
            }
        });
    }



}