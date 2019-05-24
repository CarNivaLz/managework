package com.dommy.tab.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dommy.tab.R;
import com.dommy.tab.module.Achievements;

import java.util.List;


public class AchievementsListAdapter extends BaseQuickAdapter<Achievements,BaseViewHolder> {
    public AchievementsListAdapter(int layoutResId, List data){
        super(layoutResId,data);
    }

    @Override


    protected void convert(BaseViewHolder helper, Achievements item) {
        helper.setText(R.id.achievements_tittle,item.getTittle())
                .setText(R.id.achievements_teacher,item.getTeacher())
                .setText(R.id.achievements_member_num,item.getMember_num())
                .setText(R.id.achievements_time_start,item.getTime_start());
    }
}