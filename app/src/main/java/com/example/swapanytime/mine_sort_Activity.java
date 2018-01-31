package com.example.swapanytime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import adapter.RecordApapter;
import api.GoodsAPI;
import base.MyApplication;
import base.baseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import entiry.Buyrecord;
import entiry.ForkRecord;
import entiry.Goods;
import entiry.HttpDefault;
import entiry.RecordResponse;
import entiry.ReportRecord;
import entiry.SaleRecord;
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
 * Created by weijie on 2018/1/23.
 */

public class mine_sort_Activity extends baseActivity {


    @Bind(R.id.ic_back)
    ImageView icBack;
    @Bind(R.id.titlebar_title)
    TextView titlebarTitle;
    @Bind(R.id.mine_sort_rv)
    RecyclerView mineSortRv;
    @Bind(R.id.mine_sort_refreshlayout)
    SmartRefreshLayout mineSortRefreshlayout;


    private final int OPENTYPE_COLLECTION = 0X1000;
    private final int OPENTYPE_PUBLISH = 0X1001;
    private final int OPENTYPE_BUY = 0X1002;
    private final int OPENTYPE_SALE = 0X1003;
    private int type = -1;     //查询类别
    private int pagenum = 1;  //页码
    private int userid = -1;  //用户id

    private List<RecordResponse> list_recordresponse = new ArrayList<>();  //每次请求获取的结果集表
    private List<SaleRecord> list_salerecord = new ArrayList<>(); //查询获取的卖出记录表
    private List<Buyrecord> list_buyrecord = new ArrayList<>(); //查询获取的购买记录表
    private List<ReportRecord> list_reportrecord = new ArrayList<>(); //查询获取的发布记录表
    private List<ForkRecord> list_forkrecord = new ArrayList<>(); //查询获取的收藏记录表
    private FragmentListener fragmentlistener = null;
    private Intent intent  = null;

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

        /**
         * 请求第一页记录信息
         * 0：购买记录  1：卖出记录  2：发布记录 3：收藏记录
         */
        getGoodList(type, 1, userid, new FragmentListener() {
            @Override
            public void updateUI(List<?> list) {

                list_recordresponse = (List<RecordResponse>) list;
                if (list_recordresponse.size()<1){
                    showToast("服务器查询不到数据",ToastDuration.SHORT);
                    return;
                }

                switch (type) {
                    case 0:
                        if (getResultList(type,list_recordresponse)==null){
                            showToast("没有购买记录",ToastDuration.SHORT);
                        }
                        showToast("请求到的记录有"+list_buyrecord.size()+"条",ToastDuration.SHORT);

                        recordApapter = new RecordApapter(mine_sort_Activity.this,list_buyrecord,type);
                        mineSortRv.setLayoutManager(linearLayoutManager);
                        mineSortRv.setAdapter(recordApapter);
                        break;
                    case 1:
                        if (getResultList(type,list_recordresponse)==null){
                            showToast("没有卖出记录",ToastDuration.SHORT);
                        }
                        showToast("请求到的记录有"+list_salerecord.size()+"条",ToastDuration.SHORT);

                        recordApapter = new RecordApapter(mine_sort_Activity.this,list_salerecord,type);
                        mineSortRv.setLayoutManager(linearLayoutManager);
                        mineSortRv.setAdapter(recordApapter);
                        break;
                    case 2:
                        if (getResultList(type,list_recordresponse)==null){
                            showToast("没有发布记录",ToastDuration.SHORT);
                        }
                        showToast("请求到的记录有"+list_recordresponse.size()+"条",ToastDuration.SHORT);
                        recordApapter = new RecordApapter(mine_sort_Activity.this,list_reportrecord,type);
                        mineSortRv.setLayoutManager(linearLayoutManager);
                        mineSortRv.setAdapter(recordApapter);
                        break;
                    case 3:
                        if (getResultList(type,list_recordresponse)==null){
                            showToast("没有收藏记录",ToastDuration.SHORT);
                        }
                        showToast("请求到的记录有"+list_forkrecord.size()+"条",ToastDuration.SHORT);

                        recordApapter = new RecordApapter(mine_sort_Activity.this,list_forkrecord,type);
                        mineSortRv.setLayoutManager(linearLayoutManager);
                        mineSortRv.setAdapter(recordApapter);
                        break;
                    default:
                        showToast("请求类型参数错误",ToastDuration.SHORT);
                }
            }

             //加载更多数据
            @Override
            public void appenddata(List<?> list) {

            }

        }, 0);
    }

    /**
     * 根据请求类型和结果集返回所要展示的数据
     * @param type
     * @param data
     * @return
     */
    private List<?> getResultList(int type,List<RecordResponse> data){

        switch (type){
            case 0:
                for (RecordResponse recordResponse:data){
                    Buyrecord buyrecord = recordResponse.getBuyrecord();
                    list_buyrecord.add(buyrecord);
                }
                return list_buyrecord;
            case 1:
                for (RecordResponse recordResponse:data){
                    SaleRecord saleRecord = recordResponse.getSalerecord();
                    list_salerecord.add(saleRecord);
                }
                return list_salerecord;
            case 2:
                for (RecordResponse recordResponse:data){
                    ReportRecord reportRecord = recordResponse.getReportrecord();
                    list_reportrecord.add(reportRecord);
                }
                return list_reportrecord;
            case 3:
                for (RecordResponse recordResponse:data){
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
        MyApplication.getInstance().addActivity(mine_sort_Activity.this);
    }

    @Override
    protected void onDestroy() {
        MyApplication.getInstance().finishActivity(mine_sort_Activity.this);
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
