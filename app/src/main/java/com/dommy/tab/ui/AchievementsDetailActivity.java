package com.dommy.tab.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.dommy.tab.R;
import com.dommy.tab.adapter.ProjectDetailItemAdapter;
import com.dommy.tab.adapter.ProjectHistoryItemAdapter;
import com.dommy.tab.module.Copyright;
import com.dommy.tab.module.Document;
import com.dommy.tab.module.Paper;
import com.dommy.tab.module.Patent;
import com.dommy.tab.module.ProjectDetail;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import static com.dommy.tab.utils.ApiConfig.URL_COPYRIGHTDETAIL;
import static com.dommy.tab.utils.ApiConfig.URL_MYPAPER;
import static com.dommy.tab.utils.ApiConfig.URL_MYPATENT;
import static com.dommy.tab.utils.ApiConfig.URL_PAPERDETAIL;
import static com.dommy.tab.utils.ApiConfig.URL_PATENTDETAIL;
import static com.dommy.tab.utils.ApiConfig.URL_PROJECTDETAIL;
import static com.dommy.tab.utils.ApiConfig.URL_ProjectDoc;


public class AchievementsDetailActivity extends AppCompatActivity {

    private static final String TAG = MyAssessmentsActivity.class.getSimpleName();

    private ProgressDialog progressDialog;
    private String achieveId;
    private int achieveType;
    private TextView title;
    private TextView project;
    private TextView status;
    private TextView time;
    private TextView member1;
    private TextView member2;
    private TextView member3;
    private TextView member4;
    private TextView member5;
    private TextView member6;
    private TextView member7;
    private TextView member8;
    private TextView detail_p1;
    private TextView detail_p2;
    private TextView detail_p3;
    private TextView detail_p4;
    private TextView detail_p5;
    private TextView achieve_abstract;
    private TextView doc;
    private String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievement_detail);
        Intent getIntent=getIntent();
        achieveId=getIntent.getStringExtra("id");
        achieveType=getIntent.getIntExtra("type",-1);

        init();

        onFresh();
    }

    private void init(){
        progressDialog = new ProgressDialog(this);//进度条
        progressDialog.setCancelable(false);


        title=(TextView) findViewById(R.id.achieve_detail_title);
        project=(TextView) findViewById(R.id.achieve_detail_project);
        status=(TextView) findViewById(R.id.achieve_detail_status);
        time=(TextView) findViewById(R.id.achieve_detail_time);
        member1=(TextView) findViewById(R.id.achieve_detail_member1);
        member2=(TextView) findViewById(R.id.achieve_detail_member2);
        member3=(TextView) findViewById(R.id.achieve_detail_member3);
        member4=(TextView) findViewById(R.id.achieve_detail_member4);
        member5=(TextView) findViewById(R.id.achieve_detail_member5);
        member6=(TextView) findViewById(R.id.achieve_detail_member6);
        member7=(TextView) findViewById(R.id.achieve_detail_member7);
        member8=(TextView) findViewById(R.id.achieve_detail_member8);
        detail_p1=(TextView) findViewById(R.id.achieve_detail_position1);
        detail_p2=(TextView) findViewById(R.id.achieve_detail_position2);
        detail_p3=(TextView) findViewById(R.id.achieve_detail_position3);
        detail_p4=(TextView) findViewById(R.id.achieve_detail_position4);
        detail_p5=(TextView) findViewById(R.id.achieve_detail_position5);

        achieve_abstract=(TextView) findViewById(R.id.achieve_detail_abstract);
        doc=(TextView) findViewById(R.id.achieve_detail_doc);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Activity销毁时，取消网络请求
        OkGo.getInstance().cancelTag(this);
    }
    public static void runActivity(Context context, String id,int type) {
        Intent intent = new Intent(context, AchievementsDetailActivity.class);
        intent.putExtra("id",id);
        intent.putExtra("type",type);
        context.startActivity(intent);
    }
    public void onFresh() {
        progressDialog.setMessage("加载中...");
        showDiaglog();
        switch (achieveType){
            case 1:
                url=URL_PAPERDETAIL;
                OkGo.<String>get(url+"?id="+achieveId)//
                        .tag(this)
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                progressDialog.hide();
                                Paper results = new Gson().fromJson(response.body().toString(), Paper.class);
                                if (results != null) {
                                    title.setText(results.getTitle());
                                    project.setText(results.getProject_name());
                                    String s="";
                                    switch (results.getStatus()){
                                        case 1:s="已投递";
                                            break;
                                        case 2:s="已过审";
                                            break;
                                        case 3:s="已发表";
                                            break;
                                        case 4:s="已检索";
                                            break;
                                        default:s="无";
                                            break;
                                    }
                                    status.setText(s);
                                    time.setText(results.getDate_deliver());
                                    member1.setText(""+results.getAuthor1_name());
                                    member2.setText(""+results.getAuthor2_name());
                                    member3.setText(""+results.getAuthor3_name());
                                    member4.setText(""+results.getAuthor4_name());
                                    member5.setText(""+results.getAuthor5_name());
                                    member6.setText(""+results.getAuthor6_name());
                                    member7.setText(""+results.getAuthor7_name());
                                    member8.setText(""+results.getAuthor8_name());
                                    detail_p1.setText(""+results.getJournal());
                                    detail_p2.setText(""+results.getDate_deliver());
                                    detail_p3.setText(""+results.getDate_pass());
                                    detail_p4.setText(""+results.getDate_pub());
                                    detail_p5.setText(""+results.getIndex_number());

                                    achieve_abstract.setText(results.getAbstractX());
                                    doc.setText(results.getDoc_name());
                                }
                            }
                            @Override
                            public void onError(Response<String> response) {
                                //网络请求失败的回调,一般会弹个Toast
                                Toast.makeText(getApplicationContext(),"网络请求失败",Toast.LENGTH_LONG).show();
                            }
                        });
                break;
//            case 2:
//                url=URL_PATENTDETAIL;
//                OkGo.<String>get(url+"?id="+achieveId)//
//                        .tag(this)
//                        .execute(new StringCallback() {
//                            @Override
//                            public void onSuccess(Response<String> response) {
//                                Patent results = new Gson().fromJson(response.body().toString(), Patent.class);
//                                if (results != null) {
//                                    title.setText();
//                                    project.setText();
//                                    status.setText();
//                                    time.setText();
//                                    member.setText();
//                                    decoration.setText();
//                                    achieve_abstract.setText();
//                                    doc.setText();
//                                }
//                            }
//                            @Override
//                            public void onError(Response<String> response) {
//                                //网络请求失败的回调,一般会弹个Toast
//                                Toast.makeText(getApplicationContext(),"网络请求失败",Toast.LENGTH_LONG).show();
//                                title.setText();
//                                project.setText();
//                                status.setText();
//                                time.setText();
//                                member.setText();
//                                decoration.setText();
//                                achieve_abstract.setText();
//                                doc.setText();
//                            }
//                        });
//                break;
//            case 3:
//                url=URL_COPYRIGHTDETAIL;
//                OkGo.<String>get(url+"?id="+achieveId)//
//                        .tag(this)
//                        .execute(new StringCallback() {
//                            @Override
//                            public void onSuccess(Response<String> response) {
//                                Copyright results = new Gson().fromJson(response.body().toString(), Copyright.class);
//                                if (results != null) {
//
//                                }
//                            }
//                            @Override
//                            public void onError(Response<String> response) {
//                                //网络请求失败的回调,一般会弹个Toast
//                                Toast.makeText(getApplicationContext(),"网络请求失败",Toast.LENGTH_LONG).show();
//                            }
//                        });
//                break;
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

}
