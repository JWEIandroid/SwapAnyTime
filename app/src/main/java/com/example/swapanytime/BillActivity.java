package com.example.swapanytime;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import api.GoodsAPI;
import base.MyApplication;
import base.baseActivity;
import base.baseFragment;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import entiry.Bill;
import entiry.HttpDefault;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.PieChartView;
import utils.LogUtils;
import utils.SwapNetUtils;

/**
 * Created by weij on 2018/2/7.
 */

public class BillActivity extends baseActivity {


    @Bind(R.id.ic_back)
    ImageView icBack;
    @Bind(R.id.titlebar_title)
    TextView titlebarTitle;
    @Bind(R.id.chart)
    PieChartView chart;
    @Bind(R.id.chart_income)
    PieChartView chart_income;
    @Bind(R.id.change_chart)
    TextView change_chart;

    private List<PieChartData> chart_data = null;
    private PieChartData pieChartData = null;
    private List<SliceValue> sliceValueList = null;  //没有数据时展示的数据
    private Intent intent = null;
    private List<Bill> billList = null;  // 要展示的数据
    private int SHOW_TYPE = 0;  //饼状图展示的类型  0：支出  1：收入
    private List<Bill> temporary_list = null;

    // TODO: 2018/2/8   接受两个bill数据的list

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 0x1000:
                    temporary_list = (List<Bill>) msg.obj;
                    break;
            }
            return false;
        }
    });

    @Override
    public void initData() {

        showChartPay();
        RequestBill(1, MyApplication.getInstance().getLoginUserid(BillActivity.this));

    }

    //展示支出表
    private void showChartPay() {

        chart.setVisibility(View.VISIBLE);
        chart_income.setVisibility(View.GONE);

        intent = getIntent();
        billList = intent.getParcelableArrayListExtra("billist");
//        Bundle bundle = intent.getExtras();
        if (billList == null || billList.size() <= 0) {
            billList = new ArrayList<>();
            billList.add(new Bill().setType("null").setPercent(30));
        }


        chart_data = new ArrayList<>();
        sliceValueList = new ArrayList<>();
        for (int i = 0; i < billList.size(); ++i) {
            SliceValue sliceValue = new SliceValue(billList.get(i).getPercent(), ChartUtils.pickColor());
            sliceValueList.add(sliceValue);
            sliceValue.setLabel(billList.get(i).getType() + "\r" + "(" + billList.get(i).getPercent() + "%)");
        }
        pieChartData = new PieChartData(sliceValueList);
        pieChartData.setCenterText1("支出");
        pieChartData.setCenterText2("共消费  8888888元");
        pieChartData.setHasLabels(true);
        pieChartData.setHasLabelsOnlyForSelected(true);
        pieChartData.setHasLabelsOutside(false);
        pieChartData.setHasCenterCircle(true);
        pieChartData.setSlicesSpacing(24);
        chart.setCircleFillRatio(0.9f);
        chart.setValueSelectionEnabled(true);
        chart.setPieChartData(pieChartData);
        ObjectAnimator.ofFloat(chart, "rotation", 0f, 180f, 0f).setDuration(1000).start();
    }

    //展示收入表
    public void showChartIncome() {

        chart.setVisibility(View.GONE);
        chart_income.setVisibility(View.VISIBLE);

//        intent = getIntent();
//        Bundle bundle = intent.getExtras();
        if (temporary_list == null || temporary_list.size() <= 0) {
            billList = new ArrayList<>();
            billList.add(new Bill().setType("null").setPercent(100));
        }

        billList = temporary_list;
        chart_data = new ArrayList<>();
        sliceValueList = new ArrayList<SliceValue>();
        for (int i = 0; i < billList.size(); ++i) {
            SliceValue sliceValue = new SliceValue(billList.get(i).getPercent(), ChartUtils.pickColor());
            sliceValueList.add(sliceValue);
            sliceValue.setLabel(billList.get(i).getType() + "\n(" + billList.get(i).getPercent() + "%)");
        }
        pieChartData = new PieChartData(sliceValueList);
        pieChartData.setCenterText1("收入");
        pieChartData.setCenterText2("共收入  123456");
        pieChartData.setHasLabels(true);
        pieChartData.setHasLabelsOnlyForSelected(true);
        pieChartData.setHasLabelsOutside(false);
        pieChartData.setHasCenterCircle(true);
        pieChartData.setSlicesSpacing(24);
        chart_income.setCircleFillRatio(0.9f);
        chart_income.setValueSelectionEnabled(true);
        chart_income.setPieChartData(pieChartData);
        chart_income.setOnValueTouchListener(new PieChartOnValueSelectListener() {
            @Override
            public void onValueSelected(int i, SliceValue sliceValue) {
                Toast.makeText(BillActivity.this, "Selected: " + sliceValue, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onValueDeselected() {

            }
        });

        ObjectAnimator.ofFloat(chart_income, "rotation", 0f, -180f, 0f).setDuration(1000).start();


    }

    //请求用户账单
    private void RequestBill(int requesttype, int userid) {

        Observable<HttpDefault<List<Bill>>> observable = SwapNetUtils.createAPI(GoodsAPI.class).getBill(requesttype, userid);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpDefault<List<Bill>>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull HttpDefault<List<Bill>> listHttpDefault) {

                        Message message = new Message();
                        message.obj = listHttpDefault.getData();
                        message.what = 0x1000;
                        handler.sendMessage(message);


                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Snackbar.make(change_chart, e.getMessage(), Snackbar.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void initView() {

        titlebarTitle.setText("账单");

    }

    @Override
    public Object getContentView() {
        return R.layout.activity_bill;
    }

    @Override
    public void initEvent() {

    }


    @OnClick({R.id.ic_back, R.id.change_chart})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ic_back:
                goActivity(MainActivity.class);
                break;
            case R.id.change_chart:

                if (SHOW_TYPE == 0) {
                    SHOW_TYPE = 1;
                    showChartIncome();
                    break;
                }
                SHOW_TYPE = 0;
                showChartPay();
                break;
        }
    }
}
