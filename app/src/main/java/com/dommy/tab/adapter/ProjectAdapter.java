package com.dommy.tab.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dommy.tab.R;
import com.dommy.tab.module.Patent;
import com.dommy.tab.module.Project;
import com.dommy.tab.ui.ProjectsDetailActivity;

import java.util.List;


public class ProjectAdapter extends BaseQuickAdapter<Project,BaseViewHolder> {
    public ProjectAdapter(List<Project> data){
        super(R.layout.fragment_myprofile_item,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Project item) {
        String status="";
        switch (item.getType()){
            case 0:status="待审核";
                break;
            case 1:status="已参加";
                break;
            case 2:status="负责人";
                break;
            case 3:status="参与老师";
                break;

            default:status="无";
                break;
        }
        baseViewHolder.setText(R.id.tittle,item.getTitle())
                .setText(R.id.position1,"个人状态:"+status)
                .setText(R.id.position2,"人数:"+item.getPeople())
                .setText(R.id.position3,item.getDate());
        View view = baseViewHolder.getConvertView();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProjectsDetailActivity.runActivity(mContext,item.getId(),item.getTitle());
            }
        });
    }



}