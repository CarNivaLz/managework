package com.dommy.tab.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dommy.tab.MainActivity;
import com.dommy.tab.R;
import com.dommy.tab.adapter.ProjectsListAdapter;
import com.dommy.tab.module.Projects;
import com.dommy.tab.module.UserBean;
import com.dommy.tab.ui.LoginActivity;
import com.dommy.tab.ui.ProjectsDetailActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.cookie.store.CookieStore;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Cookie;
import okhttp3.HttpUrl;

import static com.dommy.tab.utils.ApiConfig.URL_LOGIN;
import static com.dommy.tab.utils.ApiConfig.URL_Projects;


public class ProjectsContentFragment extends Fragment {


    @BindView(R.id.projects_recycle)
    RecyclerView recyclerView;
    int flag=0;
    private String name;
    List<Projects> projectsItemList =new ArrayList<>();
    private String request_status;
    private ProgressDialog progressDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        name = bundle.getString("name");
        if (name == null) {
            name = "参数非法";
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_projects_content, container, false);
        ButterKnife.bind(this, view);
        initView();

        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        ProjectsListAdapter adapter=new ProjectsListAdapter(R.layout.fragment_projects_item,projectsItemList);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(new Intent(getActivity(),ProjectsDetailActivity.class));

            }
        });
        return view;
    }

    private void initView(){
        progressDialog = new ProgressDialog(getContext());//进度条
        progressDialog.setCancelable(false);
        if (flag==0){
            initDatas();
            flag+=1;
        }

    }

    private void initDatas() {
        //由name决定请求的项目状态类型
        request_status=null;
        switch (name){
            case "报名中":
                request_status="1";
                requestServer(request_status);
                break;
            case "进行中":
                request_status="2";
                requestServer(request_status);
                break;
            case "已完成":
                request_status="3";
                requestServer(request_status);
                break;
            default:
                break;
        }


//        for (int i=0;i<=2;i++){
//
//        }

//
//     Projects news1=new Projects(list.get(0).getTime_start(),list.get(0).getId(),list.get(0).getTittle(),list.get(0).getTeacher());
//        Projects news2=new Projects("2","绿色网络关键技术研究","老师:苏俊","成员：6人","2019.4.11");
////        Projects news3=new Projects("3","信息传输分系统研制","老师:李明","成员：4人","2019.4.26");
////        Projects news4=new Projects("4","车用无线自组织网络安全告警信息传输策略研究","老师:赵茜珊","成员：7人","2019.4.29");
////        Projects news5=new Projects("5","龙门山地震带小流域滑坡泥石流灾害监测预警技术研究与示范","老师:琴发华","成员：5人","2019.5.06");
////
//       projectsItemList.add(news1);
////        projectsItemList.add(news2);
////        projectsItemList.add(news3);
////        projectsItemList.add(news4);
////        projectsItemList.add(news5);
    }

    private void requestServer(final String status){
        progressDialog.setMessage("加载中...");
        showDiaglog();

        OkGo.<String>get(URL_Projects+"?status="+status)
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        hideDialog();
                        projectsItemList= new Gson().fromJson(response.body(), new TypeToken<List<Projects>>(){}.getType());
                        Toast.makeText(getContext(),projectsItemList.get(0).toString(),Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onError(Response<String> response) {

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
