package com.dommy.tab.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dommy.tab.R;
import com.dommy.tab.module.Copyright;
import com.dommy.tab.module.Paper;

import java.util.List;


public class CopyrightAdapter extends BaseQuickAdapter<Copyright,BaseViewHolder> {
    public CopyrightAdapter(List<Copyright> data){
        super(R.layout.fragment_myprofile_item,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Copyright item) {
        baseViewHolder.setText(R.id.tittle,item.getTitle())
                .setText(R.id.position1,item.getNumber()+"版权号")
                .setText(R.id.position2,item.getStatus()+"状态")
                .setText(R.id.position3,item.getDate());
        View view = baseViewHolder.getConvertView();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进入projectdetail页面逻辑WebActivity.runActivity(mContext, model.desc, model.url);
            }
        });
    }



}