package com.dommy.tab.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dommy.tab.R;
import com.dommy.tab.module.Projects;

import java.util.List;

public class MyAssessmentListAdapter extends BaseItemDraggableAdapter<Projects, BaseViewHolder> {
    public MyAssessmentListAdapter(List data) {
        super(R.layout.item_assessment, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Projects item) {
        baseViewHolder.setText(R.id.projects_tittle,item.getTittle())
                .setText(R.id.projects_teacher,item.getTeacher())
                .setText(R.id.projects_member_num,item.getMember_num()+"人")
                .setText(R.id.projects_time_start,item.getTime_start());
        View view = baseViewHolder.getConvertView();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进入projectdetail页面逻辑WebActivity.runActivity(mContext, model.desc, model.url);
            }
        });
    }
}