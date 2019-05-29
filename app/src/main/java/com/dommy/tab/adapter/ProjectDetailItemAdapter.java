package com.dommy.tab.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dommy.tab.R;
import com.dommy.tab.module.Achievements;
import com.dommy.tab.module.Document;

import java.util.List;


public class ProjectDetailItemAdapter extends BaseQuickAdapter<Document,BaseViewHolder> {
    public ProjectDetailItemAdapter(List<Document> data){
        super(R.layout.project_detail_recyle_item,data);
        }
    @Override
    protected void convert(BaseViewHolder baseViewHolder, Document item) {
        baseViewHolder.setText(R.id.doc_item,"   "+item.getName());
        View view = baseViewHolder.getConvertView();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进入projectdetail页面逻辑WebActivity.runActivity(mContext, model.desc, model.url);
            }
        });
    }

}
