package com.dommy.tab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import com.dommy.tab.utils.AppManager;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.d("BaseActivity",getClass().getSimpleName());
        AppManager.getAppManager().addActivity(this);
    }
}

