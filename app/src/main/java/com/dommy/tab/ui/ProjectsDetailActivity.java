package com.dommy.tab.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.dommy.tab.R;
import com.dommy.tab.adapter.MyAssessmentListAdapter;
import com.dommy.tab.adapter.ProjectDetailItemAdapter;
import com.dommy.tab.adapter.ProjectHistoryItemAdapter;
import com.dommy.tab.module.Assessments;
import com.dommy.tab.module.Document;
import com.dommy.tab.module.Project;
import com.dommy.tab.module.ProjectDetail;
import com.dommy.tab.module.UserBean;
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

import static com.dommy.tab.utils.ApiConfig.URL_MYASSESSMENT;
import static com.dommy.tab.utils.ApiConfig.URL_PROJECTDETAIL;
import static com.dommy.tab.utils.ApiConfig.URL_ProjectDoc;

public class ProjectsDetailActivity extends AppCompatActivity {

    private static final String TAG = MyAssessmentsActivity.class.getSimpleName();
    List<ProjectDetail> projectDetailList = new ArrayList<>();

    private ProgressDialog progressDialog;
    private int projId;
    private TextView title;
    private TextView manager;
    private TextView progress;
    private TextView status;
    private TextView time;
    private TextView Tmember;
    private TextView Smember;
    private TextView decoration;
    private TextView achieve;
    private RecyclerView docRecycle;
    private RecyclerView historyRecycle;
    private ProjectDetailItemAdapter projectDetailItemAdapter;
    private ProjectHistoryItemAdapter projectHistoryItemAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects_detail);
        Intent getIntent=getIntent();
        projId=getIntent.getIntExtra("id",-1);
        init();

        onFresh();
    }

    private void init(){
        progressDialog = new ProgressDialog(this);//进度条
        progressDialog.setCancelable(false);

        docRecycle=(RecyclerView)findViewById(R.id.doc_recycle) ;
        docRecycle.setLayoutManager(new LinearLayoutManager(this));
        historyRecycle=(RecyclerView)findViewById(R.id.history_recycle) ;
        historyRecycle.setLayoutManager(new LinearLayoutManager(this));
        projectDetailItemAdapter = new ProjectDetailItemAdapter(null);
        projectHistoryItemAdapter=new ProjectHistoryItemAdapter(null);
        docRecycle.setAdapter(projectDetailItemAdapter);
        historyRecycle.setAdapter(projectHistoryItemAdapter);

        title=(TextView) findViewById(R.id.project_detail_title);
        manager=(TextView) findViewById(R.id.project_detail_manager);
        progress=(TextView) findViewById(R.id.project_detail_progress);
        status=(TextView) findViewById(R.id.project_detail_status);
        time=(TextView) findViewById(R.id.project_detail_time);
        Tmember=(TextView) findViewById(R.id.project_detail_Tmember);
        Smember=(TextView)findViewById(R.id.project_detail_Smember) ;
        decoration=(TextView) findViewById(R.id.project_detail_dec);
        achieve=(TextView) findViewById(R.id.project_detail_achieve);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Activity销毁时，取消网络请求
        OkGo.getInstance().cancelTag(this);
    }
    public static void runActivity(Context context,  int projectId) {
        Intent intent = new Intent(context, ProjectsDetailActivity.class);
        intent.putExtra("id",projectId);
        context.startActivity(intent);
    }
    public void onFresh() {
        progressDialog.setMessage("加载中...");
        showDiaglog();
        OkGo.<String>get(URL_PROJECTDETAIL+"?id="+projId)//
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        ProjectDetail results = new Gson().fromJson(response.body().toString(), ProjectDetail.class);
                        if (results != null) {
                            String type="";
                            switch (results.getStatus()){
                                case 1: type="报名中";
                                    break;
                                case 2:type="进行中";
                                    break;
                                case 3:type="已完成";
                                    break;
                                default:type="无";
                                    break;
                            }


                             title.setText(results.getTitle());
                             manager.setText("负责人:"+results.getPeople_name());
                             progress.setText("进度:60%");
                             status.setText(type);
                             time.setText(results.getDate_start());
                             Tmember.setText("老师:"+results.TmemberToString());
                             Smember.setText("学生:"+results.SmemberToString());
                             decoration.setText(results.getDescription());
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        //网络请求失败的回调,一般会弹个Toast
                        Toast.makeText(getApplicationContext(),"网络请求失败"+projId,Toast.LENGTH_LONG).show();
                    }
                });

        OkGo.<String>get(URL_ProjectDoc+"?id="+projId)//
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {

                        hideDialog();
                        List<Document> results = new Gson().fromJson(response.body(), new TypeToken<List<Document>>(){}.getType());
                        if (results != null) {
                            projectDetailItemAdapter.setNewData(results);
                            projectHistoryItemAdapter.setNewData(results);
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        //网络请求失败的回调,一般会弹个Toast
                        Toast.makeText(getApplicationContext(),"网络请求失败"+projId,Toast.LENGTH_LONG).show();
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
