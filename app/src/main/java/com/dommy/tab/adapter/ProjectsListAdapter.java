package com.dommy.tab.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.dommy.tab.R;
import com.dommy.tab.module.Projects;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;


public class ProjectsListAdapter extends BaseQuickAdapter<Projects,BaseViewHolder> {
    public ProjectsListAdapter(List<Projects> data){
        super(R.layout.fragment_projects_item,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Projects item) {
        baseViewHolder.setText(R.id.projects_tittle,item.getTittle())
                .setText(R.id.projects_teacher,"负责人："+item.getTeacher())
                .setText(R.id.projects_member_num,"人数："+item.getMember_num())
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
