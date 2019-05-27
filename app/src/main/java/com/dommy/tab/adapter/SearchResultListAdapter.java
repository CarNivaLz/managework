package com.dommy.tab.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dommy.tab.R;
import com.dommy.tab.module.Projects;
import com.dommy.tab.module.Results;

import java.util.List;


public class SearchResultListAdapter extends BaseQuickAdapter<Results,BaseViewHolder> {
    public SearchResultListAdapter(List<Results> data){
        super(R.layout.result_item,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Results item) {
        baseViewHolder.setText(R.id.result_tittle,item.getTittle())
                .setText(R.id.result_position_1,item.getTeacher())
                .setText(R.id.result_position_2,item.getMember_num()+"人")
                .setText(R.id.result_position_3,item.getTime_start());
        View view = baseViewHolder.getConvertView();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进入projectdetail页面逻辑WebActivity.runActivity(mContext, model.desc, model.url);
            }
        });
    }



}