package fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.swapanytime.GoodsDetailActivity;
import com.example.swapanytime.LoginActivity;
import com.example.swapanytime.MainActivity;
import com.example.swapanytime.R;
import com.example.swapanytime.SearchActivity;
import com.example.swapanytime.ShowTypeActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import adapter.item_goods_adapter;
import api.GoodsAPI;
import api.UserAPI;
import base.baseFragment;
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
import minterface.OnItemClickListener;
import utils.ContentUtils;
import utils.LogUtils;
import utils.SwapNetUtils;

/**
 * Created by weijie on 2017/10/8.
 */

public class Main_index extends baseFragment {


    @Bind(R.id.icon_head)
    ImageButton iconHead;
    @Bind(R.id.search_et)
    EditText searchEt;
    @Bind(R.id.icon_search)
    ImageButton iconSearch;
    @Bind(R.id.icon_cancel)
    ImageButton iconCancel;
    @Bind(R.id.icon_type)
    ImageButton iconType;
    @Bind(R.id.list_good)
    RecyclerView rv_goods;
    @Bind(R.id.refreshlayout_main)
    SmartRefreshLayout smartRefreshLayout;

    private ContentUtils contentUtils;
    //全部商品信息
    private List<Goods> good_list;
    //一条商品信息的全部图片
    private ArrayList<String> imglist;

    private Context context;
    private static int pagenum = 1;

    private final String imgurl = "https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=3208352253,560928408&fm=173&s=6F302AC24A7220942AA16C090300C092&w=218&h=146&img.JPEG";
    private final String headurl = "https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=480915072,3609081711&fm=173&s=A4D031C41416BA741EE1658903007081&w=218&h=146&img.JPEG";

    private item_goods_adapter imgAdapter = null;
    private LinearLayoutManager Linlayoutmanager = new LinearLayoutManager(this.getContext());

    @Override
    protected int getContentView() {
        return R.layout.fragment_main_index;
    }

    @Override
    protected void initConfig(View view) {
    }

    @Override
    protected void initView(View view) {


    }

    @Override
    protected void initData() {

        context = this.getContext();
        contentUtils = ContentUtils.getInstance();
        good_list = new ArrayList<>();
        imglist = new ArrayList<String>();

        getGoodsmessage(fragmentListener, pagenum, 1);


    }


    /**
     * 请求全部首页展示商品
     *
     * @param listener
     * @param pagenum
     * @param type     类型 1：刷新  2：加载更多
     */
    private void getGoodsmessage(final FragmentListener listener, int pagenum, final int type) {

        Observable<HttpDefault<List<Goods>>> observable = SwapNetUtils.createAPI(GoodsAPI.class).QueryGoods(pagenum);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpDefault<List<Goods>>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull HttpDefault<List<Goods>> goodsHttpDefault) {
                        good_list = goodsHttpDefault.getData();
                        if (type == 1) {
                            listener.updateUI(good_list);
                        } else if (type == 2) {
                            listener.appenddata(good_list);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        LogUtils.d("weijie", "error:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    /**
     * 点击事件
     *
     * @param view
     */
    @OnClick({R.id.icon_head, R.id.search_et, R.id.icon_search, R.id.icon_cancel, R.id.icon_type, R.id.list_good})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_head:
                goToActivity(LoginActivity.class);
                break;
            case R.id.search_et:
                break;
            case R.id.icon_search:
                Intent intent = new Intent(getContext(), SearchActivity.class);
                intent.putExtra("content", searchEt.getText().toString());
                startActivity(intent);
                break;
            case R.id.icon_cancel:
                searchEt.setText("");
                break;
            case R.id.icon_type:
                goToActivity(ShowTypeActivity.class);
                break;
            case R.id.list_good:
                break;
        }
    }


    // Banner+栏状态 : 展开,折叠,中间
    private enum BAR_STATUS {
        EXPANDED, COLLAPSED, INTERNEDIATE
    }

    private BAR_STATUS bar_status;


    @Override
    protected void initEvent() {

        searchEt.addTextChangedListener(textWatcher);
        smartRefreshLayout.setOnRefreshListener(refreshListener);
        smartRefreshLayout.setOnLoadmoreListener(loadmoreListener);
    }


    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (contentUtils.isContentLegal(s.toString())) {
                iconCancel.setVisibility(View.VISIBLE);
                iconSearch.setVisibility(View.VISIBLE);
            } else {
                iconCancel.setVisibility(View.GONE);
                iconSearch.setVisibility(View.VISIBLE);
            }
        }
    };


    private FragmentListener fragmentListener = new FragmentListener() {

        private List<Goods> savedata_list;

        //返回刷新的list
        @Override
        public void updateUI(final List<?> list) {

            good_list = (List<Goods>) list;
            savedata_list = good_list;
            LogUtils.d(getmTag(), "刷新得到商品信息条数：" + good_list.size());

            imgAdapter = new item_goods_adapter(context, good_list);
            imgAdapter.notifyDataSetChanged();
            imgAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    Intent intent = new Intent(getContext(), GoodsDetailActivity.class);
                    intent.putExtra("goodsmsg", good_list.get(position));
                    startActivity(intent);
                }
            });

            rv_goods.setLayoutManager(Linlayoutmanager);
            rv_goods.setAdapter(imgAdapter);
            smartRefreshLayout.finishRefresh(1000);
        }

        //返回加载固定页码的list
        @Override
        public void appenddata(List<?> list) {

            for (Goods goods : (List<Goods>) list) {
                LogUtils.d(getmTag(), goods.getName());
            }

            if (savedata_list == null) {
                return;
            }
            int positionstart = savedata_list.size();
            savedata_list.addAll((List<Goods>) list);
            good_list = savedata_list;
            int itemcount = savedata_list.size() - positionstart;
            imgAdapter.notifyItemRangeInserted(positionstart + 1, itemcount);
            smartRefreshLayout.finishLoadmore(500);

        }
    };


    private OnRefreshListener refreshListener = new OnRefreshListener() {

        @Override
        public void onRefresh(RefreshLayout refreshlayout) {
            LogUtils.d("refreshlayout", "onrefresh");
            pagenum = 1;
            good_list = new ArrayList<>();
            getGoodsmessage(fragmentListener, pagenum, 1);
        }
    };

    private OnLoadmoreListener loadmoreListener = new OnLoadmoreListener() {
        @Override
        public void onLoadmore(RefreshLayout refreshlayout) {
            LogUtils.d("refreshlayout", "loadmore");
            List<Goods> list = good_list;
            pagenum++;
            LogUtils.d(getmTag(), "pagenum:" + pagenum);
            getGoodsmessage(fragmentListener, pagenum, 2);
//            int positonstart = list.size();
//            list.addAll(good_list);
//            int itemcount = list.size() - positonstart;
//            imgAdapter.notifyItemRangeInserted(positonstart + 1, itemcount);


        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) /**/ {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}