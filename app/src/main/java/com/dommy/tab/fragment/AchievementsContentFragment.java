package com.dommy.tab.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dommy.tab.BaseFragment;
import com.dommy.tab.R;
import com.dommy.tab.adapter.AchievementsListAdapter;
import com.dommy.tab.module.Achievements;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.dommy.tab.utils.ApiConfig.URL_ACHIEVEMENT_PAPER;
import static com.dommy.tab.utils.ApiConfig.URL_ACHIEVEMENT_PATENT;
import static com.dommy.tab.utils.ApiConfig.URL_Achievements_COPYRIGHT;



public class AchievementsContentFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.refreshLayout) SwipeRefreshLayout refreshLayout;
    @BindView(R.id.achievements_recycle) RecyclerView recyclerView;

    private Context context;
    private AchievementsListAdapter achievementsListAdapter;
    private boolean isInitCache = false;
    public static AchievementsContentFragment newInstance() {
        return new AchievementsContentFragment();
    }

    private String name;
    List<Achievements> achievementsList =new ArrayList<>();
    private String url;
    private ProgressDialog progressDialog;

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_achievements_content, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void initData() {
        progressDialog = new ProgressDialog(getContext());//进度条
        progressDialog.setCancelable(false);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        achievementsListAdapter = new AchievementsListAdapter(null);
        achievementsListAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        achievementsListAdapter.isFirstOnly(false);
        recyclerView.setAdapter(achievementsListAdapter);

        refreshLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
        refreshLayout.setOnRefreshListener(this);
//        projectsListAdapter.setOnLoadMoreListener(this);

        url=null;
        switch (name){
            case "论文":
                url=URL_ACHIEVEMENT_PAPER;
                break;
            case "专利":
                url=URL_ACHIEVEMENT_PATENT;
                break;
            case "著作权":
                url=URL_Achievements_COPYRIGHT;
                break;
            default:
                break;
        }

        //开启loading,获取数据
        setRefreshing(true);
        onRefresh();
    }

    /** 下拉刷新 */
    @Override
    public void onRefresh() {
        progressDialog.setMessage("加载中...");
        showDiaglog();
        OkGo.<String>get(url)//
                .cacheKey("TabFragment_" + name)       //由于该fragment会被复用,必须保证key唯一,否则数据会发生覆盖
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)  //缓存模式先使用缓存,然后使用网络数据
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        hideDialog();
                        List<Achievements> results = new Gson().fromJson(response.body(), new TypeToken<List<Achievements>>(){}.getType());
                        if (results != null) {
//                            currentPage = 2;
                            achievementsListAdapter.setNewData(results);
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
                        Toast.makeText(getContext(),"error",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFinish() {
                        //可能需要移除之前添加的布局
                        achievementsListAdapter.removeAllFooterView();
                        //最后调用结束刷新的方法
                        setRefreshing(false);
                    }
                });
    }




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        name = bundle.getString("name");
        if (name == null) {
            name = "参数非法";
        }
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
    public void setRefreshing(final boolean refreshing) {
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(refreshing);
            }
        });
    }
    public void showToast(String msg) {
        Snackbar.make(recyclerView, msg, Snackbar.LENGTH_SHORT).show();
    }
}
