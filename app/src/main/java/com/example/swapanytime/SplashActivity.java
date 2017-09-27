package com.example.swapanytime;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import adapter.Splashpageradapter;
import base.baseFragmentActivity;
import fragment.SpalshImgPage1;
import fragment.SpalshImgPage2;
import fragment.SpalshStart;
import utils.LogUtils;

/**
 * Created by weijie on 2017/9/24.
 * 闪屏页
 */

public class SplashActivity extends baseFragmentActivity {

    private static final String TAG = SplashActivity.class.getSimpleName();



    @Override
    protected void initData() {
        fragmentList = new ArrayList<Fragment>();
        fragmentList.add(spalshImgPage1);
        fragmentList.add(spalshImgPage2);
        fragmentList.add(spalshStart);
        Splashpageradapter splashpageradapter = new Splashpageradapter(getSupportFragmentManager(), fragmentList);
        splashViewPager.setAdapter(splashpageradapter);
//        splashViewPager.setOffscreenPageLimit(3);
        LogUtils.d(TAG,"initData");
    }

    @Override
    protected void initView() {
        splashViewPager = (ViewPager) findViewById(R.id.splashviewpager);
        spalshImgPage1 = new SpalshImgPage1();
        spalshImgPage2 = new SpalshImgPage2();
        spalshStart = new SpalshStart();
    }

    @Override
    protected void initEvent() {

    }


    @Override
    protected Object getContentView() {
        return R.layout.activity_splash;
    }


    private ViewPager splashViewPager;
    private List<Fragment> fragmentList;
    private SpalshImgPage1 spalshImgPage1;
    private SpalshImgPage2 spalshImgPage2;
    private SpalshStart spalshStart;


}
