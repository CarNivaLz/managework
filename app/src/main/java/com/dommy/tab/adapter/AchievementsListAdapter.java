package com.dommy.tab.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dommy.tab.R;
import com.dommy.tab.module.Achievements;
import com.dommy.tab.module.Projects;

import java.util.List;



public class AchievementsListAdapter extends BaseQuickAdapter<Achievements,BaseViewHolder> {
    public AchievementsListAdapter(List<Achievements> data){
        super(R.layout.fragment_projects_item,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Achievements item) {
        baseViewHolder.setText(R.id.projects_tittle,item.getTittle())
                .setText(R.id.achievements_teacher,item.getTeacher())
                .setText(R.id.achievements_member_num,item.getMember_num())
                .setText(R.id.achievements_time_start,item.getTime_start());
        View view = baseViewHolder.getConvertView();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进入projectdetail页面逻辑WebActivity.runActivity(mContext, model.desc, model.url);
            }
        });
    }



}