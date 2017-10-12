package com.example.swapanytime;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import adapter.Type_left_adapter;
import adapter.Type_right_adapter;
import base.baseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by weijie on 2017/9/29.
 */

public class ShowTypeActivity extends baseActivity {

    String[] type_all;
    List<Integer> list;


    @BindView(R.id.left_)
    RecyclerView left;
    @BindView(R.id.right_)
    RecyclerView right;

    @Override
    public void initData() {

      type_all = new String[]{"1","2","3","4","5","6"};


    }

    @Override
    public void initView() {

        ButterKnife.bind(this);

        left = findView(R.id.left_);
        right = findView(R.id.right_);

        Type_left_adapter left_adapter = new Type_left_adapter(ShowTypeActivity.this, type_all);
        Type_right_adapter right_adapter = new Type_right_adapter(ShowTypeActivity.this, type_all);
        left.setAdapter(left_adapter);
        right.setAdapter(right_adapter);

    }

    @Override
    public Object getContentView() {
        return R.layout.activity_showtype;
    }

    @Override
    public void initEvent() {

    }

}
