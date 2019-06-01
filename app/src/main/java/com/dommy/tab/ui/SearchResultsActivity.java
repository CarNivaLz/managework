package com.dommy.tab.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dommy.tab.BaseActivity;
import com.dommy.tab.R;
import com.dommy.tab.adapter.SearchResultListAdapter;
import com.dommy.tab.module.Results;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.dommy.tab.utils.ApiConfig.URL_SEARCH;


public class SearchResultsActivity extends BaseActivity {

    private RecyclerView recyclerView;

    private SearchResultListAdapter searchResultListAdapter;
    private ProgressDialog progressDialog;

    private String input_date_sta;
    private String input_date_fin;
    private String input_name;
    private String input_member;
    private ArrayList<Integer> input_category;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_search_result);
        recyclerView=(RecyclerView)findViewById(R.id.result_recycle);
//        intent.putExtra("date_s", input_date_sta);
//        intent.putExtra("date_f",input_date_fin);
//        intent.putExtra("name",input_name);
//        intent.putExtra("member",input_member);
//        intent.putIntegerArrayListExtra("category",input_category);
        Intent intent = getIntent();
         input_date_sta = intent.getStringExtra("date_s");
         input_date_fin = intent.getStringExtra("date_f");
         input_name = intent.getStringExtra("input_name");
         input_member = intent.getStringExtra("input_member");
         input_category = (ArrayList<Integer>) getIntent().getIntegerArrayListExtra("category");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchResultListAdapter = new SearchResultListAdapter(null);
        searchResultListAdapter.isFirstOnly(false);
        recyclerView.setAdapter(searchResultListAdapter);
        initDatas();
    }


    private void initDatas() {
        progressDialog = new ProgressDialog(this);//进度条
        progressDialog.setCancelable(false);
        String cat_num="";
        for (Integer i:input_category){
            cat_num=cat_num+"&type="+i;
        }
        showDiaglog();
        String url="";
        url=URL_SEARCH+"?"+"name="+input_name+"&people_name="+input_member+"&startTime="+input_date_sta+"&endTime="+input_date_fin+cat_num;
        url=url.replace("null","");
        OkGo.<String>get(url)//
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        hideDialog();
                        List<Results> results = new Gson().fromJson(response.body(), new TypeToken<List<Results>>(){}.getType());
                        if (results != null) {
                            searchResultListAdapter.setNewData(results);
                        }
                    }
                    @Override
                    public void onError(Response<String> response) {
                        //网络请求失败的回调,一般会弹个Toast
                        Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();
                    }
                });
    }


    //显示进度条
    private void showDiaglog() {
    if (!progressDialog.isShowing()) {
        progressDialog.show();
    }
}

    //隐藏进度条
    private void hideDialog() {
        if (progressDialog.isShowing()) {
            progressDialog.hide();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Activity销毁时，取消网络请求
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        OkGo.getInstance().cancelTag(this);
    }
}







