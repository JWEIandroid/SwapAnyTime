package com.example.swapanytime;


import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.baoyz.actionsheet.ActionSheet;
import com.hjm.bottomtabbar.BottomTabBar;

import java.util.List;

import base.MyApplication;
import base.baseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import fragment.Login;
import fragment.Main_discovery;
import fragment.Main_index;
import fragment.Main_message;
import fragment.Main_mine;
import minterface.GalleryfinalActionListener;
import utils.MGalleryFinalUtils;

import static com.example.swapanytime.R.mipmap.ic_mine;


public class MainActivity extends baseActivity implements BottomTabBar.OnTabChangeListener, ActionSheet.ActionSheetListener {


    @Bind(R.id.bottomTabBar)
    BottomTabBar bottomTabBar;
    @Bind(R.id.btn_addGoods)
    TextView btnAddGoods;
    private String[] titles;
    private int[] icons, icons_choosed;
    private  MGalleryFinalUtils instance;

    @Override
    public void initData() {
        titles = new String[]{"首页", "发现", "发布", "消息", "我的"};

        icons = new int[]{R.mipmap.ic_index,
                R.mipmap.ic_discovery,
                R.mipmap.blank,
                R.mipmap.ic_msg, ic_mine};

        icons_choosed = new int[]{R.mipmap.ic_index_choosed,
                R.mipmap.ic_discovery_choose,
                R.mipmap.ic_msg_choosed,
                R.mipmap.ic_mine_choosed};

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
                .setOtherButtonTitles("拍照", "打开相册")
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

        if (instance==null){
            instance = MGalleryFinalUtils.getInstance(MainActivity.this);
        }

        switch (index) {
            case 0:
                instance.initGalleryFinal(true);
                instance.openCamera(new GalleryfinalActionListener() {
                    @Override
                    public void success(List<PhotoInfo> list) {

                    }

                    @Override
                    public void failed(String msg) {

                    }
                });
                break;
            case 1:
                instance.initGalleryFinal(true);
                instance.openAlbumSingle(new GalleryfinalActionListener() {
                    @Override
                    public void success(List<PhotoInfo> list) {

                    }

                    @Override
                    public void failed(String msg) {

                    }
                });
                break;
            default:
                break;
        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getInstance().addActivity(MainActivity.this);
    }


    private long exittime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

            ConfirmLeave();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }


    private void ConfirmLeave() {

        if (System.currentTimeMillis() - exittime > 2000) {
            showToast("再按一次退出", ToastDuration.SHORT);
            exittime = System.currentTimeMillis();
        } else {
            MyApplication.getInstance().exit();
        }

    }


    @Override
    protected void onDestroy() {

        instance.clearCache();
        super.onDestroy();

    }
}
