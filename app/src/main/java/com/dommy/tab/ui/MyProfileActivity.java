package com.dommy.tab.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.dommy.tab.BaseActivity;
import com.dommy.tab.R;
import com.dommy.tab.adapter.ProjectsListAdapter;
import com.dommy.tab.fragment.MyProfileListContentFragment;
import com.dommy.tab.fragment.ProjectsContentFragment;
import com.dommy.tab.utils.AppManager;
import com.lzy.okgo.OkGo;

public class MyProfileActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent=getIntent();
        int getNum=intent.getIntExtra("from",-1);//默认没取值为-1

        setContentView(R.layout.activity_myprofile);
        Toast.makeText(getApplicationContext(),getNum+"",Toast.LENGTH_SHORT).show();
        MyProfileListContentFragment fragment = new MyProfileListContentFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("type",getNum);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        fragment.setArguments(bundle);//数据传递到fragment中

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Activity销毁时，取消网络请求
        OkGo.getInstance().cancelTag(this);
    }
    @Override
    public void onStop(){
        super.onStop();
        AppManager.getAppManager().finishActivity(this);
    }
    @Override
    public void onBackPressed(){
        AppManager.getAppManager().finishActivity(this);
    }

}
