package com.dommy.tab.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dommy.tab.R;
import com.dommy.tab.module.Paper;
import com.dommy.tab.module.Projects;

import java.lang.reflect.Type;
import java.util.List;


public class PaperAdapter extends BaseQuickAdapter<Paper,BaseViewHolder> {
    public PaperAdapter(List<Paper> data){
        super(R.layout.fragment_myprofile_item,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Paper item) {
        baseViewHolder.setText(R.id.tittle,item.getTitle())
                .setText(R.id.position1,item.getJournal())
                .setText(R.id.position2,item.getStatus()+"状态")
                .setText(R.id.position3,item.getDate_deliver());
        View view = baseViewHolder.getConvertView();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进入projectdetail页面逻辑WebActivity.runActivity(mContext, model.desc, model.url);
            }
        });
    }



}