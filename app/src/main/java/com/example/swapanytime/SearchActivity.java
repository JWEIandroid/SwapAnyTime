package com.example.swapanytime;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.Collection;
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
import utils.LogUtils;
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
    private static String type = "";
    private static String content = "";
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
                searchEt.setText(type);
                GoSearch(type, fragmentListener, REQUEST_TYPE, 0);
            } else if (content != null) {
                searchEt.setText(content);
                GoSearch(content, fragmentListener, REQUEST_CONTENT, 0);
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
            if (!TextUtils.isEmpty(type)) {
                GoSearch(type, fragmentListener, REQUEST_TYPE, 0);
            } else {
                GoSearch(content, fragmentListener, REQUEST_CONTENT, 0);
            }

        }
    };

    private OnLoadmoreListener onLoadmoreListener = new OnLoadmoreListener() {
        @Override
        public void onLoadmore(RefreshLayout refreshlayout) {

            pagenum++;
            if (!TextUtils.isEmpty(type)) {
                GoSearch(type, fragmentListener, REQUEST_TYPE, 1);
            } else {
                GoSearch(content, fragmentListener, REQUEST_CONTENT, 1);
            }

        }
    };


    //下拉上拉回调
    private FragmentListener fragmentListener = new FragmentListener() {

        private List<Goods> save_datas;


        @Override
        public void updateUI(List<?> list) {
            LogUtils.d("weijie", "下拉商品数：" + list.size());

            for (Goods goods : (List<Goods>) list) {
                LogUtils.d("weijie", goods.getName());
            }


            goods_data = (List<Goods>) list;
            save_datas = goods_data;

            searchGoodsAdapter = new SearchGoodsAdapter(goods_data, SearchActivity.this, SearchGoodsAdapter.LAYOUT_TYPE);
            refreshlayoutMain.finishRefresh(1000);
            searchGoodsAdapter.notifyDataSetChanged();
            rvSearch.setLayoutManager(gridLayoutManager);
            rvSearch.setAdapter(searchGoodsAdapter);

        }

        @Override
        public void appenddata(List<?> list) {

            LogUtils.d("weijie", "上拉商品数：" + list.size());

            for (Goods goods : (List<Goods>) list) {
                LogUtils.d("weijie", goods.getName());
            }

            if (save_datas == null) {
                return;
            }

            int positionstart = save_datas.size();
            save_datas.addAll((List<Goods>) list);
            goods_data = save_datas;
            int itemcount = goods_data.size() - positionstart;
            refreshlayoutMain.finishLoadmore(1000);
            searchGoodsAdapter.notifyItemRangeInserted(positionstart, itemcount);


        }
    };


    /**
     * @param content          搜索内容
     * @param fragmentListener 接口回调
     * @param type             搜索类型（种类搜索，关键字搜索）
     * @param way              接口回调方式 0：第一次请求 1：加载更多
     */
    private void GoSearch(String content, final FragmentListener fragmentListener, int type, final int way) {
        Observable<HttpDefault<List<Goods>>> observable = null;
        if (type == REQUEST_TYPE) {
            observable = SwapNetUtils.createAPI(GoodsAPI.class).SearchGoods_type(content, pagenum);
        } else if (type == REQUEST_CONTENT) {
            LogUtils.d("weijie", "" + pagenum);
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

                        if (way == 0) {
                            fragmentListener.updateUI((listHttpDefault.getData()));
                        } else if (way == 1) {
                            fragmentListener.appenddata(listHttpDefault.getData());
                        }
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

                content = searchEt.getText().toString();

                if (!TextUtils.isEmpty(content)) {
                    GoSearch(searchEt.getText().toString(), fragmentListener, REQUEST_CONTENT, 0);
                } else {
                    showSnackBar("搜索内容不能为空", ToastDuration.SHORT, btnSearch);
                }

                break;
            case R.id.rv_search:
                break;
        }
    }
}
