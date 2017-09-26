package com.example.swapanytime;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adapter.Splashpageradapter;
import fragment.SpalshImgPage1;
import fragment.SpalshImgPage2;
import fragment.SpalshStart;
import utils.LogUtils;

/**
 * Created by weijie on 2017/9/24.
 * 闪屏页
 */

public class SplashActivity extends FragmentActivity{

    private static final String TAG = SplashActivity.class.getSimpleName();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
        initData();
        initEvent();
    }


    private void showToast(String msg){
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    private ViewPager splashViewPager;
    private List<Fragment> fragmentList;
    private SpalshImgPage1 spalshImgPage1;
    private SpalshImgPage2 spalshImgPage2;
    private SpalshStart spalshStart;



    public void initData() {
        fragmentList = new ArrayList<Fragment>();
        fragmentList.add(spalshImgPage1);
        fragmentList.add(spalshImgPage2);
        fragmentList.add(spalshStart);
        Splashpageradapter splashpageradapter = new Splashpageradapter(getSupportFragmentManager(), fragmentList);
        splashViewPager.setAdapter(splashpageradapter);
//        splashViewPager.setOffscreenPageLimit(3);
        LogUtils.d(TAG,"initData");
    }


    public void initView() {
        splashViewPager = (ViewPager) findViewById(R.id.splashviewpager);
        spalshImgPage1 = new SpalshImgPage1();
        spalshImgPage2 = new SpalshImgPage2();
        spalshStart = new SpalshStart();
    }


    public void initEvent() {

    }


}
