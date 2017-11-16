package com.example.swapanytime;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.LinearLayout;

import base.baseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import fragment.Login;

/**
 * Created by Administrator on 2017/11/16.
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
        transaction.add(R.id.main,new Login());
        transaction.commit();

    }

    @Override
    public Object getContentView() {
        return R.layout.activity_login;
    }

    @Override
    public void initEvent() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }
}
