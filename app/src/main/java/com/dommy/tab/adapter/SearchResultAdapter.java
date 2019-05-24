package com.dommy.tab.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dommy.tab.R;
import com.dommy.tab.module.Results;

import java.util.List;


public class SearchResultAdapter extends BaseQuickAdapter<Results,BaseViewHolder> {
    public SearchResultAdapter(int layoutResId, List data){
        super(layoutResId,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Results item) {
        helper.setText(R.id.result_tittle,item.getTittle())
                .setText(R.id.result_position_1,item.getTeacher())
                .setText(R.id.result_position_2,item.getMember_num())
                .setText(R.id.result_position_3,item.getTime_start());
    }
}
