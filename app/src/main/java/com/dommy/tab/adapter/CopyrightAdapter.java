package com.dommy.tab.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dommy.tab.R;
import com.dommy.tab.module.Copyright;
import com.dommy.tab.module.Paper;
import com.dommy.tab.ui.AchievementsDetailActivity;

import java.util.List;


public class CopyrightAdapter extends BaseQuickAdapter<Copyright,BaseViewHolder> {
    public CopyrightAdapter(List<Copyright> data){
        super(R.layout.fragment_myprofile_item,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Copyright item) {
        String status="";
        switch (item.getStatus()){
            case 0: status="未发表";
                break;
            case 1:status="已发表";
                break;
            default:status="无";
                break;
        }
        baseViewHolder.setText(R.id.tittle,item.getTitle())
                .setText(R.id.position1,"版权号："+item.getNumber())
                .setText(R.id.position2,"状态："+item.getStatus())
                .setText(R.id.position3,item.getDate());
        View view = baseViewHolder.getConvertView();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进入projectdetail页面逻辑WebActivity.runActivity(mContext, model.desc, model.url);
                AchievementsDetailActivity.runActivity(mContext,item.getId(),2);
            }
        });
    }



}