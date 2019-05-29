package com.dommy.tab.ui;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dommy.tab.BaseActivity;
import com.dommy.tab.R;
import com.dommy.tab.adapter.MyAssessmentListAdapter;
import com.dommy.tab.adapter.ProjectsListAdapter;
import com.dommy.tab.module.Assessments;
import com.dommy.tab.module.Projects;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import static com.dommy.tab.utils.ApiConfig.URL_MYASSESSMENT;
import static com.dommy.tab.utils.ApiConfig.URL_PROJECT;

public class MyAssessmentsActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener{
    private static final String TAG = MyAssessmentsActivity.class.getSimpleName();
    List<Assessments> assessmentsList = new ArrayList<>();
    private MyAssessmentListAdapter myAssessmentListAdapter;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout refreshLayout;
    private boolean isInitCache = false;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Activity销毁时，取消网络请求
        OkGo.getInstance().cancelTag(this);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myassessment);

         mRecyclerView = (RecyclerView) findViewById(R.id.my_assessment_list);
        refreshLayout= (SwipeRefreshLayout) findViewById(R.id.refreshLayout);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAssessmentListAdapter = new MyAssessmentListAdapter(null);
        myAssessmentListAdapter.isFirstOnly(false);
        mRecyclerView.setAdapter(myAssessmentListAdapter);

        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(20);
        paint.setColor(Color.BLACK);
        refreshLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
        refreshLayout.setOnRefreshListener(this);

        setRefreshing(true);
        onRefresh();


    }
    /** 下拉刷新 */
    @Override
    public void onRefresh() {
//        progressDialog.setMessage("加载中...");
//        showDiaglog();
        OkGo.<String>get(URL_MYASSESSMENT)//
                .cacheKey("TabFragment_" + TAG)       //由于该fragment会被复用,必须保证key唯一,否则数据会发生覆盖
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)  //缓存模式先使用缓存,然后使用网络数据
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
//                        hideDialog();
                        List<Assessments> results = new Gson().fromJson(response.body(), new TypeToken<List<Assessments>>(){}.getType());
                        if (results != null) {
//
                            myAssessmentListAdapter.setNewData(results);
                        }
                    }

                    @Override
                    public void onCacheSuccess(Response<String> response) {
                        //一般来说,只需呀第一次初始化界面的时候需要使用缓存刷新界面,以后不需要,所以用一个变量标识
                        if (!isInitCache) {
                            //一般来说,缓存回调成功和网络回调成功做的事情是一样的,所以这里直接回调onSuccess
                            onSuccess(response);
                            isInitCache = true;
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        //网络请求失败的回调,一般会弹个Toast
                        Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFinish() {
                        //可能需要移除之前添加的布局
                        myAssessmentListAdapter.removeAllFooterView();
                        //最后调用结束刷新的方法
                        setRefreshing(false);
                    }
                });
    }
//    //显示进度条
//    private void showDiaglog() {
//        if (!progressDialog.isShowing()) {
//            progressDialog.show();
//        }
//    }
//
//    //隐藏进度条
//    private void hideDialog() {
//        if (progressDialog.isShowing()) {
//            progressDialog.hide();
//        }
//    }
    public void setRefreshing(final boolean refreshing) {
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(refreshing);
            }
        });
    }
}
