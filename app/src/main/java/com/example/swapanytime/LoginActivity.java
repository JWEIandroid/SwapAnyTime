package com.example.swapanytime;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import base.MyApplication;
import base.baseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import fragment.Login;

/**
 * Created by weijie on 2017/11/16.
 */

public class LoginActivity extends baseActivity {


    @Bind(R.id.main)
    LinearLayout main;

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.main, new Login());
        transaction.commit();


    }




    @Override
    public Object getContentView() {
        return R.layout.activity_login;
    }

    @Override
    public void initEvent() {

    }


    /***
     * 监听返回事件，返回则杀死这个activity
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            MyApplication.getInstance().finishActivity(LoginActivity.this);
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }




}
