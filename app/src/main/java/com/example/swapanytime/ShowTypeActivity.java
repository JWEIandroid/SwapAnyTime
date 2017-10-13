package com.example.swapanytime;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

    List<String> list = new ArrayList<>();

    @BindView(R.id.left_)
    RecyclerView left;
    @BindView(R.id.right_)
    RecyclerView right;


    @Override
    public void initData() {

        for (int i =1;i<31;i++){
           list.add("种类 "+i);
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

    @Override
    public void initEvent() {
        Type_left_adapter left_adapter = new Type_left_adapter(ShowTypeActivity.this, list);
        Type_right_adapter right_adapter = new Type_right_adapter(ShowTypeActivity.this, list);

        left_adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                right.scrollToPosition(position);

            }
        });

        left.setLayoutManager(new LinearLayoutManager(this));
        right.setLayoutManager(new LinearLayoutManager(this));
        left.setAdapter(left_adapter);
        right.setAdapter(right_adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
