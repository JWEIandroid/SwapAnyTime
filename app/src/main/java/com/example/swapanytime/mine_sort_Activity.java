package com.example.swapanytime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import base.MyApplication;
import base.baseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/1/23.
 */

public class mine_sort_Activity extends baseActivity {


    @Bind(R.id.ic_back)
    ImageView icBack;
    @Bind(R.id.titlebar_title)
    TextView titlebarTitle;
    @Bind(R.id.mine_sort_rv)
    RecyclerView mineSortRv;
    @Bind(R.id.mine_sort_refreshlayout)
    SmartRefreshLayout mineSortRefreshlayout;


    private final int OPENTYPE_COLLECTION = 0X1000;
    private final int OPENTYPE_PUBLISH = 0X1001;
    private final int OPENTYPE_BUY = 0X1002;
    private final int OPENTYPE_SALE = 0X1003;

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

        Intent intent = getIntent();
        if (intent==null){
            showToast("OPENTYPE IS NULL",ToastDuration.SHORT);
            goActivity(MainActivity.class);
        }
        titlebarTitle.setText(intent.getStringExtra("title"));

    }

    @Override
    public Object getContentView() {
        return R.layout.activity_minesort;
    }

    @Override
    public void initEvent() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getInstance().addActivity(mine_sort_Activity.this);
    }

    @Override
    protected void onDestroy() {
        MyApplication.getInstance().finishActivity(mine_sort_Activity.this);
        super.onDestroy();
    }

    @OnClick({R.id.ic_back, R.id.mine_sort_refreshlayout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ic_back:
                goActivity(MainActivity.class);
                break;
            case R.id.mine_sort_refreshlayout:
                break;
        }
    }
}
