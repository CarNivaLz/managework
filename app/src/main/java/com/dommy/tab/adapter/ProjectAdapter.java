package com.dommy.tab.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dommy.tab.R;
import com.dommy.tab.module.Patent;
import com.dommy.tab.module.Project;

import java.util.List;


public class ProjectAdapter extends BaseQuickAdapter<Project,BaseViewHolder> {
    public ProjectAdapter(List<Project> data){
        super(R.layout.fragment_myprofile_item,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Project item) {
        baseViewHolder.setText(R.id.tittle,item.getTitle())
                .setText(R.id.position1,item.getType()+"个人状态")
                .setText(R.id.position2,item.getPeople()+"人数")
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