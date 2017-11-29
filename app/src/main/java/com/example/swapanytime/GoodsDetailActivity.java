package com.example.swapanytime;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import adapter.GoodsDetail_imgAdapter;
import base.baseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import ui.CircleImageView;

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
    @Bind(R.id.goodsdetail_desc)
    TextView goodsdetailDesc;
    @Bind(R.id.goodsdetail_imgs)
    RecyclerView goodsdetailImgs;
    @Bind(R.id.user_head)
    CircleImageView userHead;
    @Bind(R.id.goodsdetail_username)
    TextView goodsdetailUsername;
    @Bind(R.id.comment)
    RelativeLayout comment;
    @Bind(R.id.goodsdetail_comment)
    RecyclerView goodsdetailComment;


    private List<String> list;
    private GoodsDetail_imgAdapter goodsDetail_imgAdapter;
    private GridLayoutManager gridLayoutManager;


    @Override
    public void initData() {

        if (getIntent() != null) {
            list = getIntent().getStringArrayListExtra("img_list");
        }

        if (gridLayoutManager == null) {
            gridLayoutManager = new GridLayoutManager(GoodsDetailActivity.this, 3);
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    return position <= 2 ? 3 : 1;
                }
            });
        }
        goodsDetail_imgAdapter = new GoodsDetail_imgAdapter(list, GoodsDetailActivity.this);
        goodsdetailImgs.setLayoutManager(gridLayoutManager);
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
