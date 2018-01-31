package com.example.swapanytime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import api.GoodsAPI;
import base.MyApplication;
import base.baseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
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

    //根据type和userid查询数据


    private final int OPENTYPE_COLLECTION = 0X1000;
    private final int OPENTYPE_PUBLISH = 0X1001;
    private final int OPENTYPE_BUY = 0X1002;
    private final int OPENTYPE_SALE = 0X1003;
    private int type = -1;     //查询类别
    private int pagenum = 1;  //页码
    private int userid = -1;  //用户id

    private List<RecordResponse> list_recordresponse = new ArrayList<>();
    private List<SaleRecord> list_salerecord = new ArrayList<>(); //查询获取的卖出记录表
    private List<SaleRecord> list_buyrecord = new ArrayList<>(); //查询获取的购买记录表
    private List<SaleRecord> list_reportrecord = new ArrayList<>(); //查询获取的发布记录表
    private List<SaleRecord> list_forkrecord = new ArrayList<>(); //查询获取的收藏记录表
    private FragmentListener fragmentlistener = null;


    @Override
    public void initData() {

        Intent intent = getIntent();
        if (intent == null) {
            showToast("OPENTYPE IS NULL", ToastDuration.SHORT);
            goActivity(MainActivity.class);
        }
        type = intent.getIntExtra("type", -1);
        userid = intent.getIntExtra("userid", -1);

        if (type == -1 || userid == -1) {
            showToast("参数错误", ToastDuration.SHORT);
            goActivity(MainActivity.class);
        }


    }

    @Override
    public void initView() {

        Intent intent = getIntent();
        if (intent == null) {
            showToast("OPENTYPE IS NULL", ToastDuration.SHORT);
            goActivity(MainActivity.class);
        }
        titlebarTitle.setText(intent.getStringExtra("title"));

    }

    @Override
    public Object getContentView() {
        return R.layout.activity_minesort;
    }

    @Override
    public void initEvent() {


        //请求用户记录信息
        getGoodList(type, 1, userid, new FragmentListener() {
            @Override
            public void updateUI(List<?> list) {

                list_recordresponse = (List<RecordResponse>) list;

                switch (type) {
                    case 0:
                        for (RecordResponse recordResponse : list_recordresponse) {
                            if (recordResponse.getBuyrecord() != null) {
                                list_buyrecord.add(recordResponse);
                            }
                        }
                        if (list_buyrecord.size() < 1) {
                            showSnackBar("没有购买记录,", ToastDuration.SHORT, mineSortRv);
                            break;
                        }


                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    default:
                }
            }


            @Override
            public void appenddata(List<?> list) {

            }

        }, 0);

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
