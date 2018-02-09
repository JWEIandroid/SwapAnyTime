package com.example.swapanytime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import adapter.ShowLinkManAdapter;
import base.baseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import entiry.User;
import utils.LogUtils;

/**
 * Created by weij on 2018/2/9.
 */

public class ShowLinkManMessasgeActivity extends baseActivity {


    @Bind(R.id.ic_back)
    ImageView icBack;
    @Bind(R.id.titlebar_title)
    TextView titlebarTitle;
    @Bind(R.id.rv_showlinkman)
    RecyclerView rvShowlinkman;

    private ShowLinkManAdapter showLinkManAdapter = null;
    private Intent intent = null;
    private List<User> user_data = null;

    @Override
    public void initData() {

        intent = getIntent();
//        user_data = (List<User>) intent.getSerializableExtra("user");
        user_data = (List<User>) new Gson().fromJson(intent.getStringExtra("user"),User.class);
        LogUtils.d("weijie","run");
        if (user_data == null || user_data.size() < 1) {
            return;
//            user_data = new ArrayList<>();
//            User user = new User.Builder().headimg("").name("try").build();
//            User user1 = new User.Builder().headimg("").name("catch").build();
//            user_data.add(user);
//            user_data.add(user1);
        }

        showLinkManAdapter = new ShowLinkManAdapter(ShowLinkManMessasgeActivity.this,user_data);
        rvShowlinkman.setAdapter(showLinkManAdapter);


    }

    @Override
    public void initView() {
        titlebarTitle.setText("留言板");


    }

    @Override
    public Object getContentView() {
        return R.layout.activity_showlinkmanmsg;
    }

    @Override
    public void initEvent() {

    }


    @OnClick(R.id.ic_back)
    public void onViewClicked() {

        goActivity(MainActivity.class);


    }
}
