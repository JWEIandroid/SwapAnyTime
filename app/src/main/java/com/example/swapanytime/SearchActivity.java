package com.example.swapanytime;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import adapter.SearchGoodsAdapter;
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
import minterface.FragmentListener;
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

    //请求页码
    private int pagenum = 1;
    //
    private String type = "";
    private String content = "";
    //展示的商品
    private List<Goods> goods_data = new ArrayList<>();

    //布局管理器
    private GridLayoutManager gridLayoutManager = null;
    private LinearLayoutManager linearLayoutManager = null;

    private SearchGoodsAdapter searchGoodsAdapter = null;

    //搜索类型  按种类搜索
    private int REQUEST_TYPE = 1000;
    //搜索类型  按关键字搜索
    private int REQUEST_CONTENT = 1001;


    @Override
    public void initData() {

        if (getIntent() != null) {

            type = getIntent().getStringExtra("type");
            content = getIntent().getStringExtra("content");

            if (type != null) {
                GoSearch(type, fragmentListener_down, REQUEST_TYPE);
            } else if (content != null) {
                GoSearch(content, fragmentListener_down, REQUEST_CONTENT);
            }

        }
    }

    @Override
    public void initView() {

        gridLayoutManager = new GridLayoutManager(SearchActivity.this, 2);
        linearLayoutManager = new LinearLayoutManager(SearchActivity.this, LinearLayoutManager.VERTICAL, false);

    }

    @Override
    public Object getContentView() {
        return R.layout.activity_search;
    }

    @Override
    public void initEvent() {

        refreshlayoutMain.setOnRefreshListener(onRefreshListener);
        refreshlayoutMain.setOnLoadmoreListener(onLoadmoreListener);

    }

    private OnRefreshListener onRefreshListener = new OnRefreshListener() {
        @Override
        public void onRefresh(RefreshLayout refreshlayout) {
            pagenum = 1;
            GoSearch(type, fragmentListener_down, REQUEST_TYPE);
        }
    };

    private OnLoadmoreListener onLoadmoreListener = new OnLoadmoreListener() {
        @Override
        public void onLoadmore(RefreshLayout refreshlayout) {

            pagenum++;
            GoSearch(type, fragmentListener_up, REQUEST_TYPE);

        }
    };


    //下拉加载回调
    private FragmentListener fragmentListener_down = new FragmentListener() {
        @Override
        public void updateUI(List<?> list) {

            goods_data = (List<Goods>) list;
            searchGoodsAdapter = new SearchGoodsAdapter(goods_data, SearchActivity.this, SearchGoodsAdapter.LAYOUT_TYPE);
            rvSearch.setLayoutManager(gridLayoutManager);
            rvSearch.setAdapter(searchGoodsAdapter);
            refreshlayoutMain.finishRefresh(1000);

        }

        @Override
        public void appenddata(List<?> list) {

        }
    };

    //上拉刷新回调
    private FragmentListener fragmentListener_up = new FragmentListener() {
        @Override
        public void updateUI(List<?> list) {
            goods_data = (List<Goods>) list;


        }

        @Override
        public void appenddata(List<?> list) {

        }
    };


    private void GoSearch(String content, final FragmentListener fragmentListener, int type) {
        Observable<HttpDefault<List<Goods>>> observable = null;
        if (type == REQUEST_TYPE) {
            observable = SwapNetUtils.createAPI(GoodsAPI.class).SearchGoods_type(content, pagenum);
        } else if (type == REQUEST_CONTENT) {
            observable = SwapNetUtils.createAPI(GoodsAPI.class).SearchGoods(content, pagenum);
        } else {
            return;
        }

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpDefault<List<Goods>>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull HttpDefault<List<Goods>> listHttpDefault) {

                        fragmentListener.updateUI((listHttpDefault.getData()));
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
