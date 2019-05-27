package com.dommy.tab.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dommy.tab.BaseActivity;
import com.dommy.tab.R;
import com.dommy.tab.adapter.ProjectsListAdapter;
import com.dommy.tab.adapter.SearchResultListAdapter;
import com.dommy.tab.module.Achievements;
import com.dommy.tab.module.Results;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.dommy.tab.utils.ApiConfig.URL_SEARCH;


public class SearchResultsActivity extends BaseActivity {

    @BindView(R.id.result_recycle) RecyclerView recyclerView;
    List<Results> resultsList =new ArrayList<>();
    private SearchResultListAdapter searchResultListAdapter;
    private ProgressDialog progressDialog;

    private String input_date_sta;
    private String input_date_fin;
    private String input_name;
    private String input_member;
    private ArrayList<Integer> input_category;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_search_result);
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

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        searchResultListAdapter = new SearchResultListAdapter(null);
        searchResultListAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        searchResultListAdapter.isFirstOnly(false);
        recyclerView.setAdapter(searchResultListAdapter);


        initDatas();
    }


    private void initDatas() {
        progressDialog = new ProgressDialog(getApplicationContext());//进度条
        progressDialog.setCancelable(false);
        String cat_num="";
        for (Integer i:input_category){
            cat_num="&type="+i;
        }
        showDiaglog();
        OkGo.<String>get(URL_SEARCH+"?"+"people_name="+input_member+"&startTime="+input_date_sta+"&endTime="+input_date_fin+cat_num)//
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        hideDialog();
                        List<Results> results = new Gson().fromJson(response.body(), new TypeToken<List<Results>>(){}.getType());
                        if (results != null) {
//                            currentPage = 2;
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

}







