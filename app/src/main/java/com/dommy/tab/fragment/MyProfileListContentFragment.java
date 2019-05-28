package com.dommy.tab.fragment;

import android.app.ProgressDialog;
import android.content.Context;
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
import com.dommy.tab.BaseFragment;
import com.dommy.tab.R;
import com.dommy.tab.adapter.AchievementsListAdapter;
import com.dommy.tab.adapter.CopyrightAdapter;
import com.dommy.tab.adapter.PaperAdapter;
import com.dommy.tab.adapter.PatentAdapter;
import com.dommy.tab.adapter.ProjectsListAdapter;
import com.dommy.tab.module.Copyright;
import com.dommy.tab.module.Paper;
import com.dommy.tab.module.Patent;
import com.dommy.tab.module.Projects;
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

import static com.dommy.tab.utils.ApiConfig.URL_MYCOPYRIGHT;
import static com.dommy.tab.utils.ApiConfig.URL_MYPAPER;
import static com.dommy.tab.utils.ApiConfig.URL_MYPATENT;
import static com.dommy.tab.utils.ApiConfig.URL_PROJECT;

public class MyProfileListContentFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.profile_recycle)
    RecyclerView recyclerView;

    private Context context;
    private PaperAdapter paperAdapter;
    private CopyrightAdapter copyrightAdapter;
    private PatentAdapter patentAdapter;

    private boolean isInitCache = false;
    public static MyProfileListContentFragment newInstance() {
        return new MyProfileListContentFragment();
    }

    List<Paper> paperList =new ArrayList<>();
    List<Patent> patentList =new ArrayList<>();
    List<Copyright> copyrightList =new ArrayList<>();

    private int name;
    private String url;
    private ProgressDialog progressDialog;
    public MyProfileListContentFragment(){}

//    @Override
//    public void onAttach(Context context) {
//        this.context = context;
//        super.onAttach(context);
//    }

//    @Override
//    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_myprofile_content, container, false);
//        recyclerView=(RecyclerView)view.findViewById(R.id.profile_recycle);
//        ButterKnife.bind(this, view);
//        init();
//        return view;
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myprofile_content, container, false);
        ButterKnife.bind(this, view);
        init();
        return view;
    }




    protected void init() {
        progressDialog = new ProgressDialog(getContext());//进度条
        progressDialog.setCancelable(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        paperAdapter = new PaperAdapter(null);
        paperAdapter.isFirstOnly(false);
        recyclerView.setAdapter(paperAdapter);
        switch (name){
            case 1:
                break;
            case 2:
                url=URL_MYPAPER;
                paperAdapter = new PaperAdapter(null);
                paperAdapter.isFirstOnly(false);
                recyclerView.setAdapter(paperAdapter);
                break;
            case 3:
                url=URL_MYPATENT;
                patentAdapter = new PatentAdapter(null);
                patentAdapter.isFirstOnly(false);
                recyclerView.setAdapter(patentAdapter);
                break;
            case 4:
                url=URL_MYCOPYRIGHT;
                copyrightAdapter = new CopyrightAdapter(null);
                copyrightAdapter.isFirstOnly(false);
                recyclerView.setAdapter(copyrightAdapter);
                break;
            default:
                Toast.makeText(getContext(),"error",Toast.LENGTH_SHORT).show();
        }
        refreshLayout.setColorSchemeColors(Color.RED, Color.BLUE, Color.GREEN);
        refreshLayout.setOnRefreshListener(this);
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
                .cacheKey("ProfileFragment_" + name)       //由于该fragment会被复用,必须保证key唯一,否则数据会发生覆盖
                .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)  //缓存模式先使用缓存,然后使用网络数据
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        hideDialog();
//                        List<Paper> results2 = new Gson().fromJson(response.body(), new TypeToken<List<Paper>>(){}.getType());
//                        paperAdapter.setNewData(results2);
//                                if (results2 != null) {
//                                    paperAdapter.setNewData(results2);
//                                }
                        switch (name){
                            case 1:
                                break;
                            case 2:
                                List<Paper> results2 = new Gson().fromJson(response.body(), new TypeToken<List<Paper>>(){}.getType());
                                if (results2 != null) {
                                    paperAdapter.setNewData(results2);
                                }
                                break;
                            case 3:
                                List<Patent> results3 = new Gson().fromJson(response.body(), new TypeToken<List<Patent>>(){}.getType());
                                if (results3 != null) {
                                    patentAdapter.setNewData(results3);
                                }
                                break;
                            case 4:
                                List<Copyright> results4 = new Gson().fromJson(response.body(), new TypeToken<List<Copyright>>(){}.getType());
                                if (results4 != null) {
                                    copyrightAdapter.setNewData(results4);
                                }
                                break;
                            default:
                                Toast.makeText(getContext(),"error",Toast.LENGTH_SHORT).show();
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
                        switch (name){
                            case 1:
                                break;
                            case 2:paperAdapter.removeAllFooterView();
                                break;
                            case 3:patentAdapter.removeAllFooterView();
                                break;
                            case 4:copyrightAdapter.removeAllFooterView();
                                break;
                        }
                       // 最后调用结束刷新的方法
                        setRefreshing(false);
                    }
                });
    }




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle =this.getArguments();//得到从Activity传来的数据
        if(bundle!=null) {
            name = bundle.getInt("type");
        }else {
            Toast.makeText(getContext(),"error",Toast.LENGTH_LONG).show();
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
