package com.example.swapanytime;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;

import base.baseActivity;

public class MainActivity extends baseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    private ImageButton user, type, search, cancel;

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        user = findView(R.id.icon_head);
        type = findView(R.id.icon_type);
        search = findView(R.id.icon_search);
        cancel = findView(R.id.icon_cancel);

    }

    @Override
    public void initEvent() {
        user.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_head: {
                goActivity(SplashActivity.class);
            }
            break;
            case R.id.icon_cancel: {
            }
            break;
            case R.id.icon_search: {
            }
            break;
            case R.id.icon_type: {
            }
            break;
            default:
                break;
        }
    }
}
