package com.example.swapanytime;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import adapter.Splashpageradapter;
import fragment.SpalshImgPage1;
import fragment.SpalshImgPage2;
import fragment.SpalshStart;

/**
 * Created by weijie on 2017/9/24.
 * 闪屏页
 */

public class SplashActivity extends FragmentActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_splash);
        initView();
        initData();
        initEvent();
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
        Splashpageradapter splashpageradapter = new Splashpageradapter(getSupportFragmentManager(),fragmentList);
        splashViewPager.setAdapter(splashpageradapter);
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
