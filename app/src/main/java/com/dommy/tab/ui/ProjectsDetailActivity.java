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
import com.dommy.tab.module.Assessments;
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

public class ProjectsDetailActivity extends AppCompatActivity {

    private static final String TAG = MyAssessmentsActivity.class.getSimpleName();
    List<ProjectDetail> projectDetailList = new ArrayList<>();

    private ProgressDialog progressDialog;
    private boolean isInitCache = false;
    private int projId;
    private TextView title;
    private TextView manager;
    private TextView progress;
    private TextView status;
    private TextView time;
    private TextView member;
    private TextView decoration;
    private TextView doc;
    private TextView achieve;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects_detail);
        Intent getIntent=getIntent();
        projId=getIntent.getIntExtra("id",-1);
        init();
        progressDialog = new ProgressDialog(this);//进度条
        progressDialog.setCancelable(false);
        onRefresh();
    }

    private void init(){
          title=findViewById(R.id.project_detail_title);
          manager=findViewById(R.id.project_detail_manager);
          progress=findViewById(R.id.project_detail_progress);
          status=findViewById(R.id.project_detail_status);
          time=findViewById(R.id.project_detail_time);
          member=findViewById(R.id.project_detail_member);
          decoration=findViewById(R.id.project_detail_dec);
          doc=findViewById(R.id.project_detail_doc);
          achieve=findViewById(R.id.project_detail_achieve);
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
    public void onRefresh() {
        progressDialog.setMessage("加载中...");
//        showDiaglog();
        OkGo.<String>get(URL_PROJECTDETAIL+"?id="+projId)//
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        hideDialog();
                        ProjectDetail results = new Gson().fromJson(response.body().toString(), ProjectDetail.class);
                        if (results != null) {
                         title.setText(results.getTitle());

                          manager.setText(results.getPeople_name());
                           progress.setText("进度：60%");
                           status.setText(results.getStatus()+"");
                          time.setText(results.getDate_start());
                          member.setText(results.memberToString());
                            decoration.setText(results.getDescription());
//                           doc;
//                           achieve;

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
