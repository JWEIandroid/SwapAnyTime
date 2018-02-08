package com.example.swapanytime;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import base.baseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import entiry.Bill;
import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.PieChartView;
import utils.LogUtils;

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
    private int SHOW_TYPE = 0;  //目前展示的类型  0：支出  1：收入

    // TODO: 2018/2/8   接受两个bill数据的list

    @Override
    public void initData() {

        showChartPay();

    }


    private void showChartPay() {

        chart.setVisibility(View.VISIBLE);
        chart_income.setVisibility(View.GONE);

        intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle == null || bundle.getSerializable("billist") == null) {
            billList = new ArrayList<>();
            billList.add(new Bill().setType("null").setPercent(30));
        } else {
            billList = (List<Bill>) bundle.getSerializable("billist");
        }

        chart_data = new ArrayList<>();
        sliceValueList = new ArrayList<>();
        for (int i = 0; i < billList.size(); ++i) {
            SliceValue sliceValue = new SliceValue(billList.get(i).getPercent(), ChartUtils.pickColor());
            sliceValueList.add(sliceValue);
            sliceValue.setLabel(billList.get(i).getType() + "\n(" + billList.get(i).getPercent() + "元)");
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


    public void showChartIncome() {

        chart.setVisibility(View.GONE);
        chart_income.setVisibility(View.VISIBLE);

        intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle == null || bundle.getSerializable("billist_income") == null) {
            billList = new ArrayList<>();
            billList.add(new Bill().setType("null").setPercent(100));
        } else {
            billList = (List<Bill>) bundle.getSerializable("billist_income");
        }

        chart_data = new ArrayList<>();
        sliceValueList = new ArrayList<SliceValue>();
        for (int i = 0; i < billList.size(); ++i) {
            SliceValue sliceValue = new SliceValue(billList.get(i).getPercent(), ChartUtils.pickColor());
            sliceValueList.add(sliceValue);
            sliceValue.setLabel(billList.get(i).getType() + "\n(" + billList.get(i).getPercent() + "元)");
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
                    LogUtils.d("weijie","show chart income");
                    break;
                }
                SHOW_TYPE = 0;
                showChartPay();
                LogUtils.d("weijie","show chart pay");
                break;
        }
    }
}
