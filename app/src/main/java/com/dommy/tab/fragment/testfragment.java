package com.dommy.tab.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dommy.tab.BaseFragment;
import com.dommy.tab.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class testfragment extends BaseFragment {
    @BindView(R.id.test)
    TextView textView;
    @Override
    protected View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.test, container, false);
        ButterKnife.bind(this, view);
        textView.setText("你好");
        return view;
    }
    @Override
    public void initData(){
        textView.setText("你好");
    }
}
