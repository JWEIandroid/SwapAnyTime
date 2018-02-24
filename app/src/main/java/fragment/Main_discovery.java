package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.swapanytime.GoodsDetailActivity;
import com.example.swapanytime.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import adapter.discoveryAdapter;
import api.GoodsAPI;
import base.baseFragment;
import butterknife.Bind;
import butterknife.ButterKnife;
import entiry.Goods;
import entiry.HttpDefault;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import minterface.FragmentListener;
import minterface.OnItemClickListener;
import utils.SwapNetUtils;

/**
 * Created by dell on 2017/10/8.
 */

public class Main_discovery extends baseFragment {


    @Bind(R.id.rv_discovery)
    RecyclerView rvDiscovery;
    @Bind(R.id.discover_smartlayout)
    SmartRefreshLayout discoverSmartlayout;

    private discoveryAdapter discoveryAdapter = null;
    private List<Goods> goodsList = null;
    private int pagenum = -1;
    private List<Goods> goodsList_temporary = null;

    @Override
    protected int getContentView() {

        return R.layout.fragment_main_discovery;
    }

    @Override
    protected void initConfig(View view) {

    }

    @Override
    protected void initView(View view) {

        discoverSmartlayout.setOnLoadmoreListener(onLoadmoreListener);
        discoverSmartlayout.setOnRefreshListener(onRefreshListener);


    }

    @Override
    protected void initData() {


        pagenum = 1;
        initGoodsData(pagenum,0);


    }

    private void initGoodsData(int pagenum,int way){

        RequestDiscoveryMsg(pagenum, new FragmentListener() {
            @Override
            public void updateUI(List<?> list) {
                goodsList_temporary = (List<Goods>) list;
                goodsList = (List<Goods>) list;
                discoveryAdapter = new discoveryAdapter(getContext(), goodsList);
                discoveryAdapter.addInitItemClickListener(initClickListener);
                rvDiscovery.setAdapter(discoveryAdapter);
                discoverSmartlayout.finishRefresh(1000);
            }

            @Override
            public void appenddata(List<?> list) {
                int positonstart = goodsList.size();
                goodsList_temporary = (List<Goods>) list;
                goodsList.addAll(goodsList_temporary);
                int count = goodsList.size() - positonstart;
                discoveryAdapter.notifyItemRangeInserted(positonstart + 1, count);
                discoverSmartlayout.finishLoadmore(1000);
            }
        }, way);
    }

    @Override
    protected void initEvent() {

    }

    /**
     * @param pagenum
     * @param fragmentListener
     * @param way              0加载第一页  1加载更多数据
     */
    private void RequestDiscoveryMsg(int pagenum, final FragmentListener fragmentListener, final int way) {

        Observable<HttpDefault<List<Goods>>> observable = SwapNetUtils.createAPI(GoodsAPI.class).QueryHotGoods(pagenum);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpDefault<List<Goods>>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull HttpDefault<List<Goods>> listHttpDefault) {

                        if (way == 0) {

                            if (listHttpDefault.getError_code() == 0) {
                                fragmentListener.updateUI(listHttpDefault.getData());
                            }
                        } else if (way == 1) {
                            if (listHttpDefault.getError_code() == 0) {
                                fragmentListener.appenddata(listHttpDefault.getData());
                            }
                        }

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        showSnackBar(e.getMessage(), ToastDuration.SHORT);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    OnItemClickListener initClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            Intent intent = new Intent(getContext(), GoodsDetailActivity.class);
            intent.putExtra("goodsmsg", goodsList.get(position));
            startActivity(intent);
        }
    };

    OnLoadmoreListener onLoadmoreListener = new OnLoadmoreListener() {
        @Override
        public void onLoadmore(RefreshLayout refreshlayout) {
            pagenum++;
            initGoodsData(pagenum,1);
        }
    };

    OnRefreshListener onRefreshListener = new OnRefreshListener() {
        @Override
        public void onRefresh(RefreshLayout refreshlayout) {
            pagenum = 1;
            initGoodsData(pagenum,0);
        }
    };



}
