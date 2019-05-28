package com.dommy.tab.ui;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dommy.tab.BaseActivity;
import com.dommy.tab.R;
import com.dommy.tab.adapter.MyAssessmentListAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyAssessmentsActivity extends BaseActivity {
    private static final String TAG = MyAssessmentsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myassessment);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.my_assessment_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<String> mData = generateData(3);

        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextSize(20);
        paint.setColor(Color.BLACK);

        MyAssessmentListAdapter mAdapter = new MyAssessmentListAdapter(mData);


        //mItemDragAndSwipeCallback.setDragMoveFlags(ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.UP | ItemTouchHelper.DOWN);

//        mRecyclerView.addItemDecoration(new GridItemDecoration(this ,R.drawable.list_divider));

        mRecyclerView.setAdapter(mAdapter);
//        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
//            @Override
//            public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view, final int position) {
//                ToastUtils.showShortToast("点击了" + position);
//            }
//        });
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }

    private List<String> generateData(int size) {
        ArrayList<String> data = new ArrayList<>(size);

        data.add("李全福" );
        data.add("吴俭俭" );
        data.add("沈家华" );


        return data;
    }

}
