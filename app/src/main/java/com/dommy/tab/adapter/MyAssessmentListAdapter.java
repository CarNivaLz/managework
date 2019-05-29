package com.dommy.tab.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dommy.tab.R;
import com.dommy.tab.module.Assessments;


import java.util.List;

public class MyAssessmentListAdapter extends BaseItemDraggableAdapter<Assessments, BaseViewHolder> {
    public MyAssessmentListAdapter(List data) {
        super(R.layout.item_assessment, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Assessments item) {
        baseViewHolder.setText(R.id.assessment_title,"老师:"+item.getTea_name()+"   学生:"+item.getStu_name())
                .setText(R.id.assessment_project,"项目："+item.getProj_name())
                .setText(R.id.assessment_score,"分数："+item.getScore())
                .setText(R.id.assessment_evaluate,"评价："+item.getContent());
        View view = baseViewHolder.getConvertView();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进入projectdetail页面逻辑WebActivity.runActivity(mContext, model.desc, model.url);
            }
        });
    }
}