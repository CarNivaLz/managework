package com.dommy.tab.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.dommy.tab.MainActivity;
import com.dommy.tab.R;
import com.dommy.tab.module.SearchResults;
import com.dommy.tab.ui.ProjectsDetailActivity;
import com.dommy.tab.ui.SearchResultsActivity;
import com.fantasy.doubledatepicker.DoubleDateSelectDialog;
import com.yarolegovich.lovelydialog.LovelyChoiceDialog;
import com.yarolegovich.lovelydialog.LovelyTextInputDialog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * 发现
 * <p>展示平均分配位置的tab页卡</p>
 */
public class SearchFragment extends Fragment implements View.OnClickListener {

    private RelativeLayout s_time_sta, s_time_fin, s_name, s_member,s_category,s_send;
    private View view;
    private String[] items = {"项目","论文","专利","著作权"};

    private String allowedSmallestTime = "2012-1-1";
    private String allowedBiggestTime = "2025-12-12";

    private String input_member=null;
    private String input_name=null;
    private String input_date_sta=null;
    private String input_date_fin=null;
    private ArrayList<Integer> input_category=new ArrayList<>();

    private DoubleDateSelectDialog mDoubleTimeSelectDialog;




    public SearchFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_search, container, false);
        initView(view);
        return view;
    }
    private void initView(View v){
        s_time_sta = (RelativeLayout) v.findViewById(R.id.s_time_sta);
        s_time_fin = (RelativeLayout) v.findViewById(R.id.s_time_fin);
        s_name = (RelativeLayout) v.findViewById(R.id.s_name);
        s_member = (RelativeLayout) v.findViewById(R.id.s_member);
        s_category = (RelativeLayout) v.findViewById(R.id.s_category);
        s_send=(RelativeLayout) v.findViewById(R.id.s_send) ;
        setListener();
    }

    private void setListener(){
        s_time_sta.setOnClickListener(this);
        s_time_fin.setOnClickListener(this);
        s_name.setOnClickListener(this);
        s_member.setOnClickListener(this);
        s_category.setOnClickListener(this);
        s_send.setOnClickListener(this);
    }


    @Override
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.s_send:
                if(input_date_fin==null&&input_date_sta==null&&input_name==null&&input_member==null&&input_category.size()==0){

                    Toast.makeText(getContext(),"请先输入查询信息",Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(getContext(), SearchResultsActivity.class);
                    intent.putExtra("date_s", input_date_sta);
                    intent.putExtra("date_f",input_date_fin);
                    intent.putExtra("name",input_name);
                    intent.putExtra("member",input_member);
                    intent.putIntegerArrayListExtra("category",input_category);
                    startActivity(intent);
                }
                break;
            case R.id.s_time_sta:
                showCustomTimePicker();
            break;

            case R.id.s_time_fin:

                break;
            case R.id.s_name:
                new LovelyTextInputDialog(this.getContext(), R.style.EditTextTintTheme)
                        .setTopColorRes(R.color.white)
                        .setTitle("请输入查询名称")
//                        .setMessage(R.string.text_input_message)
                        .setIcon(R.drawable.tittle_name)
//                        .setInstanceStateHandler(ID_TEXT_INPUT_DIALOG, saveStateHandler)
                        .setInputFilter("请输入名称", new LovelyTextInputDialog.TextFilter() {
                            @Override
                            public boolean check(String text) {
                                input_name=null;
                                input_name=text;
                                return text.matches("\\w+");
                            }
                        })
                        .setConfirmButton(android.R.string.ok,text ->
                                Toast.makeText(
                                        getActivity(), text,
                                        Toast.LENGTH_SHORT)
                                        .show())
                        .setNegativeButton(android.R.string.no, null)
                        .show();
                break;
            case R.id.s_member:
                new LovelyTextInputDialog(this.getContext(), R.style.EditTextTintTheme)
                        .setTopColorRes(R.color.white)
                        .setTitle("请输入查询成员姓名")
//                        .setMessage(R.string.text_input_message)
                        .setIcon(R.drawable.tittle_member)
//                        .setInstanceStateHandler(ID_TEXT_INPUT_DIALOG, saveStateHandler)
                        .setInputFilter("请输入姓名", new LovelyTextInputDialog.TextFilter() {
                            @Override
                            public boolean check(String text) {
                                input_member=null;
                                input_member=text;
                                return text.matches("\\w+");
                            }
                        })
                        .setConfirmButton(android.R.string.ok,text ->
                                Toast.makeText(
                                        getActivity(), text,
                                        Toast.LENGTH_SHORT)
                                        .show())
                        .setNegativeButton(android.R.string.no, null)
                        .show();
                break;
            case R.id.s_category:
                new LovelyChoiceDialog(this.getContext(), R.style.CheckBoxTintTheme)
                        .setTopColorRes(R.color.white)
                        .setTitle("请选择查询内容（可以多选）")
                        .setIcon(R.drawable.tittle_category)
                        .setItemsMultiChoice(items, new LovelyChoiceDialog.OnItemsSelectedListener<String>() {
                            @Override
                            public void onItemsSelected(List<Integer> positions, List<String> items) {
                                input_category.clear();
                                input_category.addAll(positions);

                                Toast.makeText(getContext(),TextUtils.join("\n", items)+input_category.size(),Toast.LENGTH_SHORT).show();

                            }
                        })
                        .setConfirmButtonText("确认")
                        .show();
                break;

        }
    }

    public void showCustomTimePicker() {

        if (mDoubleTimeSelectDialog == null) {
            mDoubleTimeSelectDialog = new DoubleDateSelectDialog(getContext(), allowedSmallestTime, allowedBiggestTime);
            mDoubleTimeSelectDialog.setOnDateSelectFinished(new DoubleDateSelectDialog.OnDateSelectFinished() {
                @Override
                public void onSelectFinished(String startTime, String endTime) {
                    Toast.makeText(getActivity(),"您选择的时间范围为"+startTime+"到"+endTime,Toast.LENGTH_SHORT).show();
                    input_date_sta=startTime;
                    input_date_fin=endTime;
                }
            });

            mDoubleTimeSelectDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                }
            });
        }
        if (!mDoubleTimeSelectDialog.isShowing()) {
            mDoubleTimeSelectDialog.show();
        }
    }



}

