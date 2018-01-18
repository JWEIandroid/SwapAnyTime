package com.example.swapanytime;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import api.GoodsAPI;
import base.baseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import entiry.Goods;
import entiry.HttpDefault;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import utils.SwapNetUtils;

/**
 * Created by weijie on 2018/1/18.
 */

public class SearchActivity extends baseActivity {


    @Bind(R.id.progressbar_search)
    ProgressBar progressbarSearch;
    @Bind(R.id.search_et)
    EditText searchEt;
    @Bind(R.id.btn_search)
    ImageButton btnSearch;
    @Bind(R.id.rv_search)
    RecyclerView rvSearch;
    @Bind(R.id.refreshlayout_main)
    SmartRefreshLayout refreshlayoutMain;

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

    }

    @Override
    public Object getContentView() {
        return R.layout.activity_search;
    }

    @Override
    public void initEvent() {

    }



    private void GoSearch(String content){

        Observable<HttpDefault<List<Goods>>> observable = SwapNetUtils.createAPI(GoodsAPI.class).SearchGoods_type(searchEt.getText().toString());
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpDefault<List<Goods>>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull HttpDefault<List<Goods>> listHttpDefault) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }



    @OnClick({R.id.btn_search, R.id.rv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_search:
                break;
            case R.id.rv_search:
                break;
        }
    }
}
