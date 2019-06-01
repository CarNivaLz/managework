package com.dommy.tab.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dommy.tab.R;
import com.dommy.tab.ui.SearchResultsActivity;
import com.fantasy.doubledatepicker.DoubleDateSelectDialog;
import com.yarolegovich.lovelydialog.LovelyChoiceDialog;
import com.yarolegovich.lovelydialog.LovelyTextInputDialog;

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

    private String input_member="";
    private String input_name="";
    private String input_date_sta="";
    private String input_date_fin="";
    private ArrayList<Integer> input_category=new ArrayList<>();

    private TextView show_times;
    private TextView show_timef;
    private TextView show_name;
    private TextView show_member;
    private TextView show_category;


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
        show_times=(TextView) v.findViewById(R.id.show_times) ;
        show_times.setText(input_date_sta);
//        show_times.setVisibility(View.GONE);
        show_timef=(TextView) v.findViewById(R.id.show_timef) ;
        show_timef.setText(input_date_fin);
//        show_timef.setVisibility(View.GONE);
        show_name=(TextView) v.findViewById(R.id.show_name) ;
        show_name.setText(input_name);
//        show_name.setVisibility(View.GONE);
        show_member=(TextView) v.findViewById(R.id.show_member) ;
        show_member.setText(input_member);
//        show_member.setVisibility(View.GONE);
        show_category=(TextView) v.findViewById(R.id.show_category) ;
//        show_category.setVisibility(View.GONE);
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
                        .setIcon(R.drawable.tittle_name)
                        .setConfirmButton(android.R.string.ok, new LovelyTextInputDialog.OnTextInputConfirmListener() {
                            @Override
                            public void onTextInputConfirmed(String text) {
                                input_name=text;
                                show_name.setText(text);
                                show_name.setVisibility(View.VISIBLE);
                            }
                        })
                        .setNegativeButton(android.R.string.no, null)

                        .show();

                break;
            case R.id.s_member:
                new LovelyTextInputDialog(this.getContext(), R.style.EditTextTintTheme)
                        .setTopColorRes(R.color.white)
                        .setTitle("请输入查询成员姓名")
                        .setIcon(R.drawable.tittle_member)
                        .setConfirmButton(android.R.string.ok, new LovelyTextInputDialog.OnTextInputConfirmListener() {
                            @Override
                            public void onTextInputConfirmed(String text) {
                                input_member=text;
                                show_member.setText(text);
                                show_member.setVisibility(View.VISIBLE);
                            }
                        })
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
//                                Toast.makeText(getContext(),TextUtils.join("\n", items)+input_category.size(),Toast.LENGTH_SHORT).show();
                                show_category.setText(TextUtils.join("\\", items));
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
                    show_times.setText(startTime);
                    show_times.setVisibility(View.VISIBLE);
                    show_timef.setText(endTime);
                    show_timef.setVisibility(View.VISIBLE);
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

