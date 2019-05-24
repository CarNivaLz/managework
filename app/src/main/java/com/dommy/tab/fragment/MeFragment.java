package com.dommy.tab.fragment;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.dommy.tab.R;

/**
 * 我
 */
public class MeFragment extends Fragment implements View.OnClickListener {
    private LinearLayout me_project, me_paper, me_patent, me_copyright;
    private RelativeLayout me_assessment, me_setting;
    private View view;

    private CollapsingToolbarLayout collapsingToolbarLayout;
    public MeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_me, container, false);
        initView(view);
        return view;
    }

    private void initView(View v){
        me_project = (LinearLayout) v.findViewById(R.id.me_project);
        me_paper = (LinearLayout) v.findViewById(R.id.me_paper);
        me_patent = (LinearLayout) v.findViewById(R.id.me_patent);
        me_copyright = (LinearLayout) v.findViewById(R.id.me_copyright);
        me_assessment = (RelativeLayout) v.findViewById(R.id.me_assessment);
        me_setting = (RelativeLayout) v.findViewById(R.id.me_setting);
        setListener();

    }
    private void setListener() {
        me_project.setOnClickListener(this);
        me_paper.setOnClickListener(this);
        me_patent.setOnClickListener(this);
        me_copyright.setOnClickListener(this);
        me_assessment.setOnClickListener(this);
        me_setting.setOnClickListener(this);
    }
@Override
    public void onClick(View v) {
    switch (v.getId()) {
        case R.id.me_project:
//            Intent calendarIntent = new Intent(getActivity(), CalendarActivity.class);
//            startActivity(calendarIntent);
//            break;
        }
    }
}


