package com.dommy.tab.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dommy.tab.R;
import com.dommy.tab.module.Achievements;
import com.dommy.tab.module.Projects;
import com.dommy.tab.ui.AchievementsDetailActivity;
import com.dommy.tab.ui.ProjectsDetailActivity;

import java.util.List;



public class AchievementsListAdapter extends BaseQuickAdapter<Achievements,BaseViewHolder> {
    public AchievementsListAdapter(List<Achievements> data){
        super(R.layout.fragment_achievements_item,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Achievements item) {
        baseViewHolder.setText(R.id.achievements_tittle,item.getTittle())
                .setText(R.id.achievements_position1,"姓名："+item.getTeacher())
                .setText(R.id.achievements_position2,"状态："+item.getMember_num())
                .setText(R.id.achievements_position3,item.getTime_start());
        View view = baseViewHolder.getConvertView();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进入projectdetail页面逻辑WebActivity.runActivity(mContext, model.desc, model.url);
                AchievementsDetailActivity.runActivity(mContext,item.getId(),item.getAchieve_type());
            }
        });
    }



}