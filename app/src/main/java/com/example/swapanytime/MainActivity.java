package com.example.swapanytime;


import android.os.Bundle;
import android.support.annotation.Nullable;

import base.baseActivity;

public class MainActivity extends baseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        showToast("Test",ToastDuration.LONG);
        goActivity(SplashActivity.class);

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
