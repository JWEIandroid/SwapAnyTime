package com.example.swapanytime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import adapter.RecordApapter;
import api.GoodsAPI;
import base.MyApplication;
import base.baseActivity;
import butterknife.Bind;
import butterknife.OnClick;
import entiry.Buyrecord;
import entiry.ForkRecord;
import entiry.HttpDefault;
import entiry.RecordResponse;
import entiry.ReportRecord;
import entiry.SaleRecord;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import minterface.FragmentListener;
import minterface.mcallback;
import utils.LogUtils;
import utils.SwapNetUtils;

/**
 * Created by weijie on 2018/1/23.
 */

public class Record_Activity extends baseActivity {


    @Bind(R.id.ic_back)
    ImageView icBack;
    @Bind(R.id.titlebar_title)
    TextView titlebarTitle;
    @Bind(R.id.mine_sort_rv)
    RecyclerView mineSortRv;
    @Bind(R.id.mine_sort_refreshlayout)
    SmartRefreshLayout mineSortRefreshlayout;

    //    private final int OPENTYPE_COLLECTION = 0X1000;
//    private final int OPENTYPE_PUBLISH = 0X1001;
//    private final int OPENTYPE_BUY = 0X1002;
//    private final int OPENTYPE_SALE = 0X1003;
    private int type = -1;     //查询类别
    private int pagenum = 1;  //页码
    private int userid = -1;  //用户id

    private List<RecordResponse> list_recordresponse = new ArrayList<>();  //每次请求获取的结果集表
    private List<SaleRecord> list_salerecord = new ArrayList<>(); //查询获取的卖出记录表
    private List<Buyrecord> list_buyrecord = new ArrayList<>(); //查询获取的购买记录表
    private List<ReportRecord> list_reportrecord = new ArrayList<>(); //查询获取的发布记录表
    private List<ForkRecord> list_forkrecord = new ArrayList<>(); //查询获取的收藏记录表
    private List<RecordResponse> list_temporary_data = new ArrayList<>();   //加载更多数据时，之前的数据保存表、

    private Intent intent = null;
    private RecordApapter recordApapter = null;
    private LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

    @Override
    public void initData() {

        intent = getIntent();
        if (intent == null) {
            showToast("OPENTYPE IS NULL", ToastDuration.SHORT);
            goActivity(MainActivity.class);
            return;
        }
        titlebarTitle.setText(intent.getStringExtra("title"));
        type = intent.getIntExtra("type", -1);
        userid = intent.getIntExtra("userid", -1);
        if (type == -1 || userid == -1) {
            showToast("参数错误", ToastDuration.SHORT);
            goActivity(MainActivity.class);
        }
    }

    @Override
    public void initView() {
    }

    @Override
    public Object getContentView() {
        return R.layout.activity_minesort;
    }

    @Override
    public void initEvent() {


        getGoodList(type, pagenum, userid, fragmentListener, 0);
        mineSortRefreshlayout.setOnRefreshListener(onRefreshListener);
        mineSortRefreshlayout.setOnLoadmoreListener(onLoadmoreListener);
    }


    OnLoadmoreListener onLoadmoreListener = new OnLoadmoreListener() {
        @Override
        public void onLoadmore(RefreshLayout refreshlayout) {

            pagenum++;
            /**
             * 加载更多数据
             */
            getGoodList(type, pagenum, userid, fragmentListener, 1);
            LogUtils.d("weijie","加载参数:");
            LogUtils.d("weijie","type:"+type);
            LogUtils.d("weijie","pagenum:"+pagenum);
            LogUtils.d("weijie","userid:"+userid);

        }
    };

    OnRefreshListener onRefreshListener = new OnRefreshListener() {
        @Override
        public void onRefresh(RefreshLayout refreshlayout) {

            pagenum = 1;
            /**
             * 请求第一页记录信息
             * 0：购买记录  1：卖出记录  2：发布记录 3：收藏记录
             */
            getGoodList(type, pagenum, userid, fragmentListener, 0);
            LogUtils.d("weijie","刷新参数:");
            LogUtils.d("weijie","type:"+type);
            LogUtils.d("weijie","pagenum:"+pagenum);
            LogUtils.d("weijie","userid:"+userid);
        }
    };


    FragmentListener fragmentListener = new FragmentListener() {
        @Override
        public void updateUI(List<?> list) {

            list_recordresponse = new ArrayList<>();
            list_temporary_data = new ArrayList<>();
            list_reportrecord = new ArrayList<>();
            list_forkrecord = new ArrayList<>();
            list_buyrecord = new ArrayList<>();
            list_salerecord = new ArrayList<>();

            list_recordresponse = (List<RecordResponse>) list;
            list_temporary_data = list_recordresponse;  //保存
            if (list_recordresponse.size() < 1) {
                showToast("服务器查询不到数据", ToastDuration.SHORT);
                LogUtils.d("weijie","刷新--首次加载没有数据");
                mineSortRefreshlayout.finishRefresh(1000);
                return;
            }
            switch (type) {
                case 0:

                    if (getResultList(type, list_recordresponse) == null) {
                        showToast("没有购买记录", ToastDuration.SHORT);
                    }
                    showToast("请求到的记录有" + list_buyrecord.size() + "条", ToastDuration.SHORT);

                    recordApapter = new RecordApapter(Record_Activity.this, list_buyrecord, type);
                    ItemTouchHelper.Callback callback0 = new mcallback(recordApapter);
                    ItemTouchHelper itemTouchHelper0 = new ItemTouchHelper(callback0);
                    itemTouchHelper0.attachToRecyclerView(mineSortRv);
                    mineSortRv.setLayoutManager(linearLayoutManager);
                    mineSortRv.setAdapter(recordApapter);
                    mineSortRefreshlayout.finishRefresh(1000);
                    break;
                case 1:
                    if (getResultList(type, list_recordresponse) == null) {
                        showToast("没有卖出记录", ToastDuration.SHORT);
                    }
                    showToast("请求到的记录有" + list_salerecord.size() + "条", ToastDuration.SHORT);

                    recordApapter = new RecordApapter(Record_Activity.this, list_salerecord, type);
                    ItemTouchHelper.Callback callback1 = new mcallback(recordApapter);
                    ItemTouchHelper itemTouchHelper1 = new ItemTouchHelper(callback1);
                    itemTouchHelper1.attachToRecyclerView(mineSortRv);

                    mineSortRv.setLayoutManager(linearLayoutManager);
                    mineSortRv.setAdapter(recordApapter);
                    mineSortRefreshlayout.finishRefresh(1000);

                    break;
                case 2:
                    if (getResultList(type, list_recordresponse) == null) {
                        showToast("没有发布记录", ToastDuration.SHORT);
                    }
                    showToast("请求到的记录有" + list_recordresponse.size() + "条", ToastDuration.SHORT);
                    recordApapter = new RecordApapter(Record_Activity.this, list_reportrecord, type);
                    ItemTouchHelper.Callback callback2 = new mcallback(recordApapter);
                    ItemTouchHelper itemTouchHelper2 = new ItemTouchHelper(callback2);
                    itemTouchHelper2.attachToRecyclerView(mineSortRv);
                    mineSortRv.setLayoutManager(linearLayoutManager);
                    mineSortRv.setAdapter(recordApapter);
                    mineSortRefreshlayout.finishRefresh(1000);
                    break;
                case 3:
                    if (getResultList(type, list_recordresponse) == null) {
                        showToast("没有收藏记录", ToastDuration.SHORT);
                    }
                    showToast("请求到的记录有" + list_forkrecord.size() + "条", ToastDuration.SHORT);

                    recordApapter = new RecordApapter(Record_Activity.this, list_forkrecord, type);
                    ItemTouchHelper.Callback callback3 = new mcallback(recordApapter);
                    ItemTouchHelper itemTouchHelper3 = new ItemTouchHelper(callback3);
                    itemTouchHelper3.attachToRecyclerView(mineSortRv);
                    mineSortRv.setLayoutManager(linearLayoutManager);
                    mineSortRv.setAdapter(recordApapter);
                    mineSortRefreshlayout.finishRefresh(1000);
                    break;
                default:
                    showToast("请求类型参数错误", ToastDuration.SHORT);
                    mineSortRefreshlayout.finishRefresh(1000);
            }
        }

        @Override
        public void appenddata(List<?> list) {

            if (list_temporary_data == null || list_temporary_data.size() < 1) {
                showToast("数据出错---Cause By list_temporary_data",ToastDuration.SHORT);
                mineSortRefreshlayout.finishLoadmore(1000);
                return;
            }

            //如果没有更多数据，返回
            List<RecordResponse> result = (List<RecordResponse>) list;
            if (result.get(0).getBuyrecord() == null
                    && result.get(0).getForkRecord() == null
                    && result.get(0).getSalerecord() == null
                    && result.get(0).getReportrecord() == null) {
                LogUtils.d("weijie", "没有更多数据");
                showToast("没有更多数据了",ToastDuration.SHORT);
                mineSortRefreshlayout.finishLoadmore(1000);
                return;
            }
            int positionstart = list_temporary_data.size();
            list_temporary_data.addAll((List<RecordResponse>) list);
            int itemcount = list_temporary_data.size() - positionstart;
            LogUtils.d("weijie", "插入条数数:" + itemcount);

            switch (type) {
                case 0:
                    for (RecordResponse recordResponse:result){
                        list_buyrecord.add(recordResponse.getBuyrecord());
                    }
                    LogUtils.d("weijie", "购买记录总共数目" + list_buyrecord.size());
                    recordApapter.notifyItemRangeInserted(positionstart + 1, itemcount);
                    mineSortRefreshlayout.finishLoadmore(1000);
                    break;
                case 1:
                    for (RecordResponse recordResponse:result){
                        list_salerecord.add(recordResponse.getSalerecord());
                    }
                    LogUtils.d("weijie", "卖出记录总共数目" + list_salerecord.size());
                    recordApapter.notifyItemRangeInserted(positionstart + 1, itemcount);
                    mineSortRefreshlayout.finishLoadmore(1000);
                    break;
                case 2:
                    for (RecordResponse recordResponse:result){
                        list_reportrecord.add(recordResponse.getReportrecord());
                    }
                    LogUtils.d("weijie", "发布记录总共数目:" + list_reportrecord.size());
                    recordApapter.notifyItemRangeInserted(positionstart + 1, itemcount);
                    mineSortRefreshlayout.finishLoadmore(1000);
                    break;
                case 3:
                    for (RecordResponse recordResponse:result){
                        list_forkrecord.add(recordResponse.getForkRecord());
                    }
                    LogUtils.d("weijie", "收藏记录总共数目:" + list_forkrecord.size());
                    recordApapter.notifyItemRangeInserted(positionstart + 1, itemcount);
                    mineSortRefreshlayout.finishLoadmore(1000);
                    break;
                default:
            }
        }
    };


    /**
     * 根据请求类型和结果集返回所要展示的数据
     *
     * @param type
     * @param data
     * @return
     */
    private List<?> getResultList(int type, List<RecordResponse> data) {

        switch (type) {
            case 0:
                for (RecordResponse recordResponse : data) {
                    Buyrecord buyrecord = recordResponse.getBuyrecord();
                    list_buyrecord.add(buyrecord);
                }
                return list_buyrecord;
            case 1:
                for (RecordResponse recordResponse : data) {
                    SaleRecord saleRecord = recordResponse.getSalerecord();
                    list_salerecord.add(saleRecord);
                }
                return list_salerecord;
            case 2:
                for (RecordResponse recordResponse : data) {
                    ReportRecord reportRecord = recordResponse.getReportrecord();
                    list_reportrecord.add(reportRecord);
                }
                return list_reportrecord;
            case 3:
                for (RecordResponse recordResponse : data) {
                    ForkRecord forkRecord = recordResponse.getForkRecord();
                    list_forkrecord.add(forkRecord);
                }
                return list_forkrecord;
            default:
                return null;

        }
    }

    /**
     * 发送请求获取商品记录表
     *
     * @param type     查询记录种类   0：购买记录  1：卖出记录  2：发布记录 3：收藏记录
     * @param pagenum
     * @param userid
     * @param listener
     * @param way      0:刷新加载 1：加载更多
     */
    private void getGoodList(int type, int pagenum, int userid, final FragmentListener listener, final int way) {

        this.type = type;

        Observable<HttpDefault<List<RecordResponse>>> observable = SwapNetUtils.createAPI(GoodsAPI.class).getRecords(type, pagenum, userid);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpDefault<List<RecordResponse>>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull HttpDefault<List<RecordResponse>> listHttpDefault) {

                        if (way == 0) {
                            listener.updateUI(listHttpDefault.getData());
                        } else {
                            listener.appenddata(listHttpDefault.getData());
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getInstance().addActivity(Record_Activity.this);
    }

    @Override
    protected void onDestroy() {
        MyApplication.getInstance().finishActivity(Record_Activity.this);
        super.onDestroy();
    }

    @OnClick({R.id.ic_back, R.id.mine_sort_refreshlayout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ic_back:
                goActivity(MainActivity.class);
                break;
            case R.id.mine_sort_refreshlayout:
                break;
        }
    }
}
