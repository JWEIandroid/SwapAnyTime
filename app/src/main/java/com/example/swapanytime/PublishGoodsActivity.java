package com.example.swapanytime;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import base.baseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by weij on 2017/12/2.
 */

public class PublishGoodsActivity extends baseActivity {


    @Bind(R.id.ic_back)
    ImageView icBack;
    @Bind(R.id.titlebar_title)
    TextView titlebarTitle;
    @Bind(R.id.price_et)
    EditText priceEt;
    @Bind(R.id.price_lin)
    LinearLayout priceLin;
    @Bind(R.id.pricebf_et)
    EditText pricebfEt;
    @Bind(R.id.price_bf_lin)
    LinearLayout priceBfLin;
    @Bind(R.id.yunfei_et)
    EditText yunfeiEt;
    @Bind(R.id.yunfei_lin)
    LinearLayout yunfeiLin;
    @Bind(R.id.type_lin)
    LinearLayout typeLin;
    @Bind(R.id.btn_publish)
    TextView btnPublish;

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

        titlebarTitle.setText("发布商品");

    }

    @Override
    public Object getContentView() {
        return R.layout.activity_publishgoods;
    }

    @Override
    public void initEvent() {

    }

    @OnClick({R.id.ic_back,R.id.type_lin, R.id.btn_publish})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ic_back:
                goActivity(MainActivity.class);
                break;
            case R.id.type_lin:
                break;
            case R.id.btn_publish:
                break;
        }
    }
}
