package com.example.swapanytime;


import android.os.Bundle;
import android.support.annotation.Nullable;

import base.baseActivity;

public class MainActivity extends baseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideActionBar();
        setContentView(R.layout.activity_main);
        SetStatusBarVisibilityGone();
        showToast("Test",ToastDuration.LONG);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
    }

    @Override
    public void initEvent() {

    }
}
