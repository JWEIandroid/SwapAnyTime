package com.example.swapanytime;


import android.animation.ObjectAnimator;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.baoyz.actionsheet.ActionSheet;
import com.hjm.bottomtabbar.BottomTabBar;

import java.util.List;

import base.MyApplication;
import base.baseActivity;
import base.baseFragment;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import cn.finalteam.toolsfinal.io.stream.StringBuilderWriter;
import entiry.User;
import fragment.Login;
import fragment.Main_discovery;
import fragment.Main_index;
import fragment.Main_message;
import fragment.Main_mine;
import minterface.GalleryfinalActionListener;
import utils.LogUtils;
import utils.MGalleryFinalUtils;

import static com.example.swapanytime.R.mipmap.ic_mine;


public class MainActivity extends baseActivity implements BottomTabBar.OnTabChangeListener, ActionSheet.ActionSheetListener {


    @Bind(R.id.bottomTabBar)
    BottomTabBar bottomTabBar;
    @Bind(R.id.btn_addGoods)
    TextView btnAddGoods;

    private String[] titles;
    private int[] icons, icons_choosed;
    //默认的登录超时时间为30分钟
    private final long LOGIN_TIMEOUT = 30;

    @Override
    public void initData() {

        //初始化底部导航栏数据
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
                .setFontSize(12)
                .addTabItem(titles[0], icons_choosed[0], icons[0], Main_index.class)
                .addTabItem(titles[1], icons_choosed[1], icons[1], Main_discovery.class)
                .addTabItem(titles[2], icons[2], null)
                .addTabItem(titles[3], icons_choosed[2], icons[3], Main_message.class)
                .addTabItem(titles[4], icons_choosed[3], icons[4], Main_mine.class);

        //中间按钮添加动画
        ObjectAnimator.ofFloat(btnAddGoods,"rotation",0f,360f,0f).setDuration(3000).start();


    }


    private void showActionsheet() {

        ActionSheet.createBuilder(MainActivity.this, getSupportFragmentManager())
                .setCancelButtonTitle("取消")
                .setOtherButtonTitles("发布商品")
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
                goActivity(PublishGoodsActivity.class);
                break;
        }

    }

    //是否登录
    private static boolean islogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getInstance().addActivity(MainActivity.this);

        islogin = checkIsLogin();

    }

    //检查登陆状态
    private boolean checkIsLogin(){

        //查看是否已经登录
        SharedPreferences sharedPreferences = this.getSharedPreferences("base64", MODE_PRIVATE);
        String token = sharedPreferences.getString("token", null);
        String userid_read = sharedPreferences.getString("userid", null);

        if (userid_read == null & token == null) {
            return false;
        }
        int userid = 0;
        LogUtils.d("weijie", "存在本地数据");
        userid = Integer.parseInt(userid_read);
        LogUtils.d("weijie", "本地登录信息：" + "token:" + token + "\n" + "userid:" + userid);
        if (userid != 0) {
            if (checktoken(token)) {
                return true;
            } else {
               return  false;
            }
        }
        return  false;
    }


//检查token是否过期
    private boolean checktoken(String token) {

        long now = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder(token);
        long time = Long.parseLong(sb.substring(sb.length() - 13, sb.length()));
        LogUtils.d("weijie", "token time:" + time);
        long pasttime  = (now - time) / (60 * 1000L);
        LogUtils.d("weijie", "token还有：" + (LOGIN_TIMEOUT-pasttime)+"分钟过期");

        if (pasttime>= LOGIN_TIMEOUT) {
            LogUtils.d("weijie", "token 过期");
            return false;
        }

        return true;
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            ConfirmLeave();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    private long exittime;

    private void ConfirmLeave() {

        if (System.currentTimeMillis() - exittime > 2000) {
            showToast("再按一次退出", ToastDuration.SHORT);
            exittime = System.currentTimeMillis();
        } else {
            MyApplication.getInstance().exit();
        }

    }


}
