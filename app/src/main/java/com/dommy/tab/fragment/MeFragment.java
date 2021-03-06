package com.dommy.tab.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.dommy.tab.R;
import com.dommy.tab.ui.LoginActivity;
import com.dommy.tab.ui.MyAssessmentsActivity;
import com.dommy.tab.ui.MyProfileActivity;


import static android.content.Context.MODE_PRIVATE;

/**
 * 我
 */
public class MeFragment extends Fragment implements View.OnClickListener {
    private LinearLayout me_project, me_paper, me_patent, me_copyright;
    private RelativeLayout me_assessment, me_setting,me_logout;
    private View view;

    private SharedPreferences sharedPreferences;
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
        me_logout=(RelativeLayout) v.findViewById(R.id.me_logout);
        setListener();

    }
    private void setListener() {
        me_project.setOnClickListener(this);
        me_paper.setOnClickListener(this);
        me_patent.setOnClickListener(this);
        me_copyright.setOnClickListener(this);
        me_assessment.setOnClickListener(this);
        me_setting.setOnClickListener(this);
        me_logout.setOnClickListener(this);
    }
@Override
    public void onClick(View v) {
    switch (v.getId()) {
        case R.id.me_logout:
            sharedPreferences = getActivity().getSharedPreferences("loginInfo", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("haslogged", "false");
            editor.commit();
            startActivity(new Intent(getContext(),LoginActivity.class));
            onDetach();
            break;

        case R.id.me_project:

            Intent intent1=new Intent(getContext(),MyProfileActivity.class);
            intent1.putExtra("from",1);
            startActivity(intent1);
            break;
        case R.id.me_paper:
            Intent intent2=new Intent(getContext(),MyProfileActivity.class);
            intent2.putExtra("from",2);
            startActivity(intent2);
            break;
        case R.id.me_patent:
            Intent intent3=new Intent(getContext(),MyProfileActivity.class);
            intent3.putExtra("from",3);
            startActivity(intent3);
            break;
        case R.id.me_copyright:
            Intent intent4=new Intent(getContext(),MyProfileActivity.class);
            intent4.putExtra("from",4);
            startActivity(intent4);
            break;
        case R.id.me_assessment:
            startActivity(new Intent(getContext(),MyAssessmentsActivity.class));
            break;
        }
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
    }
}


