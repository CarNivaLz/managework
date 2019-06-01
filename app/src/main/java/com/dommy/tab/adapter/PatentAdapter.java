package com.dommy.tab.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dommy.tab.R;
import com.dommy.tab.module.Paper;
import com.dommy.tab.module.Patent;
import com.dommy.tab.ui.AchievementsDetailActivity;

import java.util.List;


public class PatentAdapter extends BaseQuickAdapter<Patent,BaseViewHolder> {
    public PatentAdapter(List<Patent> data){
        super(R.layout.fragment_myprofile_item,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Patent item) {
        String status="";
        switch (item.getStatus()){
            case 1:status="已申请";
                break;
            case 2:status="已受理";
                break;
            case 3:status="已公布";
                break;
            case 4:status="已授权";
                break;
            case 0:status="失败";
                break;
            default:status="无";
                break;
        }
        baseViewHolder.setText(R.id.tittle,item.getTitle())
                .setText(R.id.position1,"专利号："+item.getNum_acceptance())
                .setText(R.id.position2,"状态："+status)
                .setText(R.id.position3,item.getDate_acceptance());
        View view = baseViewHolder.getConvertView();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进入projectdetail页面逻辑WebActivity.runActivity(mContext, model.desc, model.url);
                AchievementsDetailActivity.runActivity(mContext,item.getId(),3);
            }
        });
    }



}