package com.dommy.tab.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.dommy.tab.R;
import com.dommy.tab.module.Projects;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;


public class ProjectsListAdapter extends BaseQuickAdapter<Projects,BaseViewHolder> {
    public ProjectsListAdapter(int layoutResId, List data){
        super(layoutResId,data);
    }

    @Override


    protected void convert(BaseViewHolder helper, Projects item) {
        helper.setText(R.id.projects_tittle,item.getTittle())
                .setText(R.id.projects_teacher,item.getTeacher())
//                .setText(R.id.projects_member_num,item.getMember_num())
                .setText(R.id.projects_time_start,item.getTime_start());
    }
}
