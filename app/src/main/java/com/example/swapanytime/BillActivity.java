package com.example.swapanytime;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import base.baseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.PieChartView;

/**
 * Created by Administrator on 2018/2/7.
 */

public class BillActivity extends baseActivity {


    @Bind(R.id.ic_back)
    ImageView icBack;
    @Bind(R.id.titlebar_title)
    TextView titlebarTitle;
    @Bind(R.id.chart)
    PieChartView chart;

    private List<PieChartData> chart_data = null;
    private PieChartData pieChartData = null;
    private List<SliceValue> sliceValueList = null;

    @Override
    public void initData() {

        chart_data = new ArrayList<>();
        sliceValueList = new ArrayList<SliceValue>();
        for (int i = 0; i < 6; ++i) {
            SliceValue sliceValue = new SliceValue((float) Math.random() * 30 + 15, ChartUtils.pickColor());
            sliceValueList.add(sliceValue);
        }
        pieChartData = new PieChartData(sliceValueList);
        pieChartData.setCenterText1("Helloworld");
        pieChartData.setHasLabels(true);
        pieChartData.setHasLabelsOnlyForSelected(true);
        pieChartData.setHasLabelsOutside(true);
        pieChartData.setHasCenterCircle(true);

        chart.setPieChartData(pieChartData);


    }

    @Override
    public void initView() {

    }

    @Override
    public Object getContentView() {
        return R.layout.activity_bill;
    }

    @Override
    public void initEvent() {

    }


    @OnClick({R.id.ic_back, R.id.chart})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ic_back:
                goActivity(MainActivity.class);
                break;
            case R.id.chart:
                break;
        }
    }
}
