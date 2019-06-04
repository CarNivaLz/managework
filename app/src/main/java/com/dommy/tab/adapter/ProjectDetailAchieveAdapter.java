package com.dommy.tab.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dommy.tab.R;
import com.dommy.tab.module.Document;
import com.dommy.tab.module.Results;

import java.util.List;


public class ProjectDetailAchieveAdapter extends BaseQuickAdapter<Results,BaseViewHolder> {
    public ProjectDetailAchieveAdapter(List<Results> data){
        super(R.layout.project_detail_achieve_item,data);
    }


    @Override
    protected void convert(BaseViewHolder baseViewHolder, Results item) {
        baseViewHolder.setText(R.id.achieve_item,"   "+item.getTittle());
        View view = baseViewHolder.getConvertView();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进入projectdetail页面逻辑WebActivity.runActivity(mContext, model.desc, model.url);
            }
        });
    }

}
