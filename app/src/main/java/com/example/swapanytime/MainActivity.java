package com.example.swapanytime;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.baoyz.actionsheet.ActionSheet;
import com.hjm.bottomtabbar.BottomTabBar;

import base.baseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fragment.Main_discovery;
import fragment.Main_index;
import fragment.Main_message;
import fragment.Main_mine;

import static com.example.swapanytime.R.mipmap.ic_mine;


public class MainActivity extends baseActivity implements BottomTabBar.OnTabChangeListener, ActionSheet.ActionSheetListener {


    @Bind(R.id.bottomTabBar)
    BottomTabBar bottomTabBar;
    @Bind(R.id.btn_addGoods)
    TextView btnAddGoods;
    private String[] titles;
    private int[] icons, icons_choosed;

    @Override
    public void initData() {
        titles = new String[]{"首页", "发现", "发布", "消息", "我的"};
        icons = new int[]{R.mipmap.ic_index, R.mipmap.ic_discovery, R.mipmap.blank, R.mipmap.ic_msg, ic_mine};
        icons_choosed = new int[]{R.mipmap.ic_index_choosed, R.mipmap.ic_discovery_choose, R.mipmap.ic_msg_choosed, R.mipmap.ic_mine_choosed};

    }

    @Override
    public void initView() {
        ButterKnife.bind(MainActivity.this);
    }

    @Override
    public Object getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void initEvent() {


        bottomTabBar.init(getSupportFragmentManager())
                .setTabPadding(15, 6, 10)
                .addTabItem(titles[0], icons_choosed[0], icons[0], Main_index.class)
                .addTabItem(titles[1], icons_choosed[1], icons[1], Main_discovery.class)
                .addTabItem(titles[2], icons[2], Main_discovery.class)
                .addTabItem(titles[3], icons_choosed[2], icons[3], Main_message.class)
                .addTabItem(titles[4], icons_choosed[3], icons[4], Main_mine.class);


    }


    private void showActionsheet() {

        ActionSheet.createBuilder(MainActivity.this, getSupportFragmentManager())
                .setCancelButtonTitle("取消")
                .setOtherButtonTitles("1", "2", "3")
                .setCancelableOnTouchOutside(true)
                .setListener(this)
                .show();
    }


    @OnClick({R.id.bottomTabBar, R.id.btn_addGoods})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bottomTabBar:
                break;
            case R.id.btn_addGoods:
                showActionsheet();
                break;
        }
    }


    @Override
    public void onTabChange(int position, String name) {

    }

    @Override
    public void onDismiss(ActionSheet actionSheet, boolean isCancel) {
    }

    @Override
    public void onOtherButtonClick(ActionSheet actionSheet, int index) {
        switch (index) {
            case 0:
//                showToast(""+index,ToastDuration.SHORT);
//                goActivity(ShowTypeActivity.class);
                break;
            case 1:
                break;
            case 2:
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
