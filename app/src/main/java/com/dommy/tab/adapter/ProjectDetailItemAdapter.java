package com.dommy.tab.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.dommy.tab.R;
import com.dommy.tab.module.Achievements;
import com.dommy.tab.module.Document;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import java.io.File;
import java.util.List;

import static com.dommy.tab.utils.ApiConfig.URL_DOCDOWNLOAD;


public class ProjectDetailItemAdapter extends BaseQuickAdapter<Document,BaseViewHolder> {
    public ProjectDetailItemAdapter(List<Document> data){
        super(R.layout.project_detail_recyle_item,data);
        }

//    public void fileDownload(View v,String url) {
//        OkGo.<File>get(url)
//                .tag(this)
//                .execute(new FileCallback("OkGo.apk") {
//
//                    @Override
//                    public void onStart(Request<File, ? extends Request> request) {
//
//                    }
//
//                    @Override
//                    public void onSuccess(Response<File> response) {
//
//
//                    }
//
//                    @Override
//                    public void onError(Response<File> response) {
//
//                    }
//
//                });
//    }
    @Override
    protected void convert(BaseViewHolder baseViewHolder, Document item) {
        baseViewHolder.setText(R.id.doc_item,"   "+item.getName());
        View view = baseViewHolder.getConvertView();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进入projectdetail页面逻辑WebActivity.runActivity(mContext, model.desc, model.url);
//                fileDownload(v,URL_DOCDOWNLOAD+"?id="+item.getId());
            }
        });
    }

}
