package com.example.swapanytime;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import api.GoodsAPI;
import base.baseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import entiry.Goods;
import entiry.HttpDefault;
import entiry.User;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import minterface.FragmentListener;
import utils.LogUtils;
import utils.SwapNetUtils;

/**
 * Created by weijie on 2018/1/16.
 */

public class ConfirmOrderActivity extends baseActivity {


    @Bind(R.id.ic_back)
    ImageView icBack;
    @Bind(R.id.titlebar_title)
    TextView titlebarTitle;
    @Bind(R.id.txt_shouhuoren)
    TextView txtShouhuoren;
    @Bind(R.id.txt_confirm_name)
    TextView txtConfirmName;
    @Bind(R.id.txt_confirm_phone)
    TextView txtConfirmPhone;
    @Bind(R.id.txt_shouhuodizhi)
    TextView txtShouhuodizhi;
    @Bind(R.id.txt_confirm_adress)
    TextView txtConfirmAdress;
    @Bind(R.id.txt_zhifufangshi1)
    TextView txtZhifufangshi1;
    @Bind(R.id.txt_zhifufangshi)
    TextView txtZhifufangshi;
    @Bind(R.id.txt_weixinzhifu)
    TextView txtWeixinzhifu;
    @Bind(R.id.txt_shangpingzonge)
    TextView txtShangpingzonge;
    @Bind(R.id.txt_price)
    TextView txtPrice;
    @Bind(R.id.txt_express)
    TextView txtExpress;
    @Bind(R.id.txt_yunfei)
    TextView txtYunfei;
    @Bind(R.id.txt_confirm_order)
    TextView txtConfirmOrder;
    @Bind(R.id.price_confirm)
    TextView priceall;
    @Bind(R.id.pic)
    ImageView pic;
    @Bind(R.id.confirm_order_progressbar)
    ProgressBar confirm_order_progressbar;


    private Goods goods = null;
    private int userid = 0; //已登录的用户id

    @Override
    public void initData() {

        titlebarTitle.setText("订单信息");
    }

    @Override
    public void initView() {

        if (getIntent() != null) {
            goods = (Goods) getIntent().getParcelableExtra("good");
            userid = getIntent().getIntExtra("userid", 0);
            showToast("当前用户："+userid,ToastDuration.SHORT);
        }

        if (goods != null) {

            User user = goods.getUser();
            txtConfirmName.setText(user.getName());
            txtConfirmAdress.setText(user.getAdress());
            txtConfirmPhone.setText(user.getTel());
            txtPrice.setText("" + goods.getPrice_sale());
            txtExpress.setText("" + goods.getExpress());

            float price = Float.parseFloat(txtPrice.getText().toString()) +
                    Float.parseFloat(txtExpress.getText().toString());
            priceall.setText("" + price);
            Glide.with(this).load(SwapNetUtils.getBaseURL() + goods.getImgurl().get(0)).asBitmap().centerCrop().into(pic);
        }


    }

    @Override
    public Object getContentView() {
        return R.layout.activity_confirmorder;
    }

    @Override
    public void initEvent() {

    }

    /**
     * 提交订单信息
     */
    private void PostOrder(final FragmentListener fragmentListener) {


        Observable<HttpDefault<String>> observable = SwapNetUtils.createAPI(GoodsAPI.class).buy(
                userid,
                goods.getId(),
                txtConfirmName.getText().toString(),
                txtConfirmAdress.getText().toString(),
                txtConfirmPhone.getText().toString());

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpDefault<String>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull HttpDefault<String> stringHttpDefault) {

                        LogUtils.d("weijie","buy msg response: "+stringHttpDefault.getMessage());
                        showSnackBar(stringHttpDefault.getMessage(), ToastDuration.SHORT, txtConfirmOrder);
                        if (stringHttpDefault.getError_code() == 0){
                            List<String> list = new ArrayList<String>();
                            list.add(stringHttpDefault.getMessage());
                            fragmentListener.updateUI(list);
                        }

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                        LogUtils.d("weijie","post buy msg error: "+e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    @OnClick({R.id.ic_back, R.id.txt_confirm_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ic_back:
                break;
            case R.id.txt_confirm_order:

                PostOrder(new FragmentListener() {
                    @Override
                    public void updateUI(List<?> list) {
                        goActivity(MainActivity.class);
                    }

                    @Override
                    public void appenddata(List<?> list) {

                    }
                });

                break;
        }
    }
}
