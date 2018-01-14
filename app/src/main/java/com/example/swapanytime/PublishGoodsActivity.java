package com.example.swapanytime;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import adapter.SimpleRecycleViewAdapter;
import base.baseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.galleryfinal.widget.HorizontalListView;
import fragment.mBottomFragment;
import utils.DialogUtil;

/**
 * Created by weij on 2017/12/2.
 */

public class PublishGoodsActivity extends baseActivity {


    @Bind(R.id.ic_back)
    ImageView icBack;
    @Bind(R.id.titlebar_title)
    TextView titlebarTitle;
    @Bind(R.id.publish_ic_camera)
    ImageView publishIcCamera;
    @Bind(R.id.publish_txt)
    TextView publishTxt;
    @Bind(R.id.publish_goods_imgs)
    HorizontalListView publishGoodsImgs;
    @Bind(R.id.price)
    TextView price;
    @Bind(R.id.price_et)
    EditText priceEt;
    @Bind(R.id.price_lin)
    LinearLayout priceLin;
    @Bind(R.id.price_bf)
    TextView priceBf;
    @Bind(R.id.pricebf_et)
    EditText pricebfEt;
    @Bind(R.id.price_bf_lin)
    LinearLayout priceBfLin;
    @Bind(R.id.yunfei)
    TextView yunfei;
    @Bind(R.id.yunfei_et)
    EditText yunfeiEt;
    @Bind(R.id.yunfei_lin)
    LinearLayout yunfeiLin;
    @Bind(R.id.type)
    TextView type;
    @Bind(R.id.type_lin)
    LinearLayout typeLin;
    @Bind(R.id.btn_publish)
    TextView btnPublish;


    private List<String> data_left;
    private List<String> data_right;


    @Override
    public void initData() {

        data_left = new ArrayList<String>();
        data_right = new ArrayList<String>();

        for (int i = 1; i < 31; i++) {
            data_left.add("item" + i);
            data_right.add("result" + i);
        }

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

    @OnClick({R.id.ic_back, R.id.type_lin, R.id.btn_publish})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ic_back:
                goActivity(MainActivity.class);
                break;
            case R.id.type_lin:
//                openBottom();
                openBottomFragment();
                break;
            case R.id.btn_publish:
                break;
        }
    }

    //打开分类dialog
    private void openBottom() {

//        final BottomSheetDialog dialog = new BottomSheetDialog(this);
//        View view = LayoutInflater.from(this).inflate(R.layout.bottomdialog_type, publishGoodsImgs, false);
//        dialog.setContentView(view);
//
//        RecyclerView left = (ListView) RecyclerView.findViewById(R.id.bottomdialog_type_left);
//        RecyclerView right = (ListView) RecyclerView.findViewById(R.id.bottomdialog_type_right);
//
//
//
//
////        left.setItemAnimator(new DefaultItemAnimator());
////        right.setItemAnimator(new DefaultItemAnimator());
////        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data_left);
//        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data_right);
//        left.setAdapter(arrayAdapter);
//        right.setAdapter(arrayAdapter1);
//
//        dialog.show();

    }


    public void openBottomFragment() {


        DialogUtil.showBottomDialog(R.layout.bottomdialog_type,R.style.ActionButtomDialogStyle, PublishGoodsActivity.this, new DialogUtil.IDialogInitListener() {

            @Override
            public void initDialogView(View view) {
                RecyclerView left = (RecyclerView) view.findViewById(R.id.bottomdialog_type_left);
                RecyclerView right = (RecyclerView) view.findViewById(R.id.bottomdialog_type_right);
                LinearLayoutManager linearLayout1 = new LinearLayoutManager(PublishGoodsActivity.this);
                LinearLayoutManager linearLayout2 = new LinearLayoutManager(PublishGoodsActivity.this);
                SimpleRecycleViewAdapter simpleRecycleViewAdapter1 = new SimpleRecycleViewAdapter(PublishGoodsActivity.this, data_left, null);
                SimpleRecycleViewAdapter simpleRecycleViewAdapter2 = new SimpleRecycleViewAdapter(PublishGoodsActivity.this, data_right, null);

                left.setLayoutManager(linearLayout1);
                left.setAdapter(simpleRecycleViewAdapter1);
                right.setLayoutManager(linearLayout2);
                right.setAdapter(simpleRecycleViewAdapter2);
            }
        }).show();
//        mBottomFragment mBottomFragment = new mBottomFragment();
//        mBottomFragment.setParams(publishGoodsImgs, data_left, data_right);
//        mBottomFragment.show(getSupportFragmentManager(), "fragmentDialog");


    }

}
