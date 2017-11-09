package com.example.swapanytime;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import adapter.Splashpageradapter;
import base.MyApplication;
import base.baseFragmentActivity;
import fragment.SpalshImgPage1;
import fragment.SpalshImgPage2;
import fragment.SpalshIndex;
import fragment.SpalshStart;
import utils.LogUtils;

/**
 * Created by weijie on 2017/9/24.
 * 闪屏页
 */

public class SplashActivity extends baseFragmentActivity {

    private static final String TAG = SplashActivity.class.getSimpleName();
    private static boolean IsFirstTimeIn = MyApplication.QueryIsFirstTimeInApp();

    private ViewPager splashViewPager;
    private List<Fragment> fragmentList;
    private SpalshImgPage1 spalshImgPage1;
    private SpalshImgPage2 spalshImgPage2;
    private SpalshStart spalshStart;
    private SpalshIndex spalshnormal;


    @Override
    protected void initData() {
        fragmentList = new ArrayList<Fragment>();
        if (IsFirstTimeIn) {
            fragmentList.add(spalshImgPage1);
            fragmentList.add(spalshImgPage2);
            fragmentList.add(spalshStart);
//        splashViewPager.setOffscreenPageLimit(3);
            LogUtils.d(TAG, "initData");
        } else {
            fragmentList.add(spalshnormal);
        }
        Splashpageradapter splashpageradapter = new Splashpageradapter(getSupportFragmentManager(), fragmentList);
        splashViewPager.setAdapter(splashpageradapter);
    }

    @Override
    protected void initView() {
        splashViewPager = (ViewPager) findViewById(R.id.splashviewpager);
//
        if (IsFirstTimeIn) {
            spalshImgPage1 = new SpalshImgPage1();
            spalshImgPage2 = new SpalshImgPage2();
            spalshStart = new SpalshStart();
        } else {
            spalshnormal = new SpalshIndex();
        }
    }

    @Override
    protected void initEvent() {

    }


    @Override
    protected Object getContentView() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getInstance().addActivity(SplashActivity.this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.d(TAG,TAG+"  destroy time:"+System.currentTimeMillis());
    }


}
