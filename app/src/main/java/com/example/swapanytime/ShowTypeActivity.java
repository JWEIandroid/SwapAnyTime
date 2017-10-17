package com.example.swapanytime;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import adapter.Type_left_adapter;
import adapter.Type_right_adapter;
import base.baseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import minterface.OnItemClickListener;

/**
 * Created by weijie on 2017/9/29.
 */

public class ShowTypeActivity extends baseActivity {

    private String TAG = getTAG();

    List<String> list = new ArrayList<>();

    @BindView(R.id.left_)
    RecyclerView left;
    @BindView(R.id.right_)
    RecyclerView right;


    @Override
    public void initData() {

        for (int i = 1; i < 31; i++) {
            list.add("种类 " + i);
        }
    }

    @Override
    public void initView() {

        ButterKnife.bind(this);

        left = findView(R.id.left_);
        right = findView(R.id.right_);

    }

    @Override
    public Object getContentView() {
        return R.layout.activity_showtype;
    }

    private final LinearLayoutManager leftmanager = new LinearLayoutManager(this);
    private LinearLayoutManager rightmanager = new LinearLayoutManager(this);
    private int move;
    @Override
    public void initEvent() {

        final Type_left_adapter left_adapter = new Type_left_adapter(ShowTypeActivity.this, list);
        Type_right_adapter right_adapter = new Type_right_adapter(ShowTypeActivity.this, list);
        final List<Boolean> list = new ArrayList<>();


        left.setLayoutManager(leftmanager);
        right.setLayoutManager(rightmanager);

        left_adapter.setOnItemClickListener(new Type_left_adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //获取屏幕可见的第一个和最后一个Item
                int firstItem = leftmanager.findFirstVisibleItemPosition();
                int lastItem = leftmanager.findLastVisibleItemPosition();
                right.scrollToPosition(position);
            }
        });
        left.setAdapter(left_adapter);
        right.setAdapter(right_adapter);
    }

    private void smoothMoveToPosition(RecyclerView rv,int n) {
        int firstItem = leftmanager.findFirstVisibleItemPosition();
        int lastItem = leftmanager.findLastVisibleItemPosition();
        if (n <= firstItem) {
            rv.scrollToPosition(n);
        } else if (n <= lastItem) {
            int top = rv.getChildAt(n - firstItem).getTop();
            rv.scrollBy(0, top);
        } else {
            rv.scrollToPosition(n);
            move = true;
        }
    }

    class RecyclerViewListener extends RecyclerView.OnScrollListener{


        private LinearLayoutManager linearLayoutManager;

        public RecyclerViewListener(LinearLayoutManager linearLayoutManager) {
            this.linearLayoutManager = linearLayoutManager;
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (move && newState == RecyclerView.SCROLL_STATE_IDLE ){
                move = false;
                int n = mIndex - linearLayoutManager.findFirstVisibleItemPosition();
                if ( 0 <= n && n < mRecyclerView.getChildCount()){
                    int top = mRecyclerView.getChildAt(n).getTop();
                    mRecyclerView.smoothScrollBy(0, top);
                }

            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (move && mRadioGroup.getCheckedRadioButtonId() == R.id.scroll){
                move = false;
                int n = mIndex - mLinearLayoutManager.findFirstVisibleItemPosition();
                if ( 0 <= n && n < mRecyclerView.getChildCount()){
                    int top = mRecyclerView.getChildAt(n).getTop();
                    mRecyclerView.scrollBy(0, top);
                }
            }
        }
    }






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
