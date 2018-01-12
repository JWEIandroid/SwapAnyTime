package com.example.swapanytime;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import adapter.GoodsDetail_imgAdapter;
import base.baseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import entiry.Goods;

/**
 * Created by Administrator on 2017/11/28.
 */

public class GoodsDetailActivity extends baseActivity implements View.OnClickListener {


    @Bind(R.id.ic_back)
    ImageView icBack;
    @Bind(R.id.titlebar_title)
    TextView titlebarTitle;
    @Bind(R.id.goodsdetail_name)
    TextView goodsdetailName;
    @Bind(R.id.goodsdetail_price_bf)
    TextView goodsdetailPriceBf;
    @Bind(R.id.goodsdetail_price_at)
    TextView goodsdetailPriceAt;
    @Bind(R.id.goodsdetail_adress)
    TextView goodsdetailAdress;
    @Bind(R.id.goodsdetail_imgs)
    RecyclerView goodsdetailImgs;
    private List<String> list;
    private List<String> rv_data;
    private GoodsDetail_imgAdapter goodsDetail_imgAdapter;
    private Goods goods;


    @Override
    public void initData() {

        rv_data = new ArrayList<>();
        rv_data.add("");

        if (getIntent() != null) {

//            list = getIntent().getParcelableExtra("imglist");
            goods = (Goods) getIntent().getSerializableExtra("goodsmsg");
            if (goods!=null){
                goodsdetailName.setText(goods.getName());
                goodsdetailPriceBf.setText("￥ "+goods.getPrice_before());
                goodsdetailPriceAt.setText("￥ "+goods.getPrice_sale());
                goodsdetailAdress.setText(goods.getUser().getAdress());
                list = goods.getImgurl();

            }

        }

        goodsDetail_imgAdapter = new GoodsDetail_imgAdapter(goods, list, GoodsDetailActivity.this);
        goodsdetailImgs.setAdapter(goodsDetail_imgAdapter);

    }

    @Override
    public void initView() {

        titlebarTitle.setText("商品详情");

    }

    @Override
    public Object getContentView() {
        return R.layout.activity_goodsdetail;
    }

    @Override
    public void initEvent() {

        icBack.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.ic_back:
                goActivity(MainActivity.class);
                break;

        }
    }

}
