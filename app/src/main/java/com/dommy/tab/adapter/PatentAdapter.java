package com.dommy.tab.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dommy.tab.R;
import com.dommy.tab.module.Paper;
import com.dommy.tab.module.Patent;

import java.util.List;


public class PatentAdapter extends BaseQuickAdapter<Patent,BaseViewHolder> {
    public PatentAdapter(List<Patent> data){
        super(R.layout.fragment_myprofile_item,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Patent item) {
        baseViewHolder.setText(R.id.tittle,item.getTitle())
                .setText(R.id.position1,item.getNum_acceptance()+"专利号")
                .setText(R.id.position2,item.getStatus()+"状态")
                .setText(R.id.position3,item.getDate_acceptance());
        View view = baseViewHolder.getConvertView();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进入projectdetail页面逻辑WebActivity.runActivity(mContext, model.desc, model.url);
            }
        });
    }



}