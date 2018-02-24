package com.example.swapanytime;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
import android.os.Parcelable;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

import adapter.GoodsDetail_imgAdapter;
import api.GoodsAPI;
import api.MessageApi;
import base.MyApplication;
import base.baseActivity;
import base.baseFragment;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import entiry.Comment;
import entiry.Goods;
import entiry.HttpDefault;
import entiry.MessageBoard;
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
 * Created by weijie on 2017/11/28.
 */

public class GoodsDetailActivity extends baseActivity implements View.OnClickListener {


    @Bind(R.id.ic_back)
    ImageView icBack;
    @Bind(R.id.titlebar_title)
    TextView titlebarTitle;
    @Bind(R.id.goodsdetail_name)
    TextView goodsdetailName;
    @Bind(R.id.goodsdetail_price_bf)
    TextView goodsdetailPriceBf;
    @Bind(R.id.goodsdetail_price_at)
    TextView goodsdetailPriceAt;
    @Bind(R.id.goodsdetail_adress)
    TextView goodsdetailAdress;
    @Bind(R.id.goodsdetail_imgs)
    RecyclerView goodsdetailImgs;
    @Bind(R.id.btn_buy)
    TextView btnBuy;
    @Bind(R.id.btn_contract_shopper)
    TextView btn_contract_shopper;
    @Bind(R.id.ic_fork)
    ImageView ic_fork;


    private boolean isFork = false; //商品被收藏
    private List<String> img_data;
    private List<String> rv_data;
    private GoodsDetail_imgAdapter goodsDetail_imgAdapter;
    private Goods goods;

    private static final long LOGIN_TIMEOUT = 30;  //登录超时时间
    private int userid_read = 0;   //读取配置文件的用户id
    private List<Comment> comments_data = new ArrayList<>(); //评论表

    private static int userid = -1;
    private static int shopperid = -1;
    private static List<MessageBoard> messageBoards = null;


    @Override
    public void initData() {

        rv_data = new ArrayList<>();
        rv_data.add("");

        userid = MyApplication.getInstance().getLoginUserid(GoodsDetailActivity.this);

        if (getIntent() != null) {
            goods = (Goods) getIntent().getParcelableExtra("goodsmsg");
            if (goods != null) {
                goodsdetailName.setText(goods.getName());
                goodsdetailPriceBf.setText("￥ " + goods.getPrice_before());
                goodsdetailPriceAt.setText("￥ " + goods.getPrice_sale());
                goodsdetailAdress.setText(goods.getUser().getAdress());
                img_data = goods.getImgurl();

                shopperid = goods.getUser().getId();
            }
        }

        LogUtils.d("weijie", "请求商品id" + goods.getId());
        getComment(goods.getId());
        checkForked(goods.getId(), goods.getUser().getId());
    }

    @Override
    public void initView() {

        titlebarTitle.setText("商品详情");

    }

    @Override
    public Object getContentView() {
        return R.layout.activity_goodsdetail;
    }

    @Override
    public void initEvent() {

        icBack.setOnClickListener(this);
        btnBuy.setOnClickListener(this);
        ic_fork.setOnClickListener(this);
        btn_contract_shopper.setOnClickListener(this);


    }

    /**
     * 请求商品的全部评论
     *
     * @param goodsid
     */
    private void getComment(int goodsid) {
        Observable<HttpDefault<List<Comment>>> observable = SwapNetUtils.createAPI(MessageApi.class).SelectAllCommment(goodsid);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpDefault<List<Comment>>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull HttpDefault<List<Comment>> commentHttpDefault) {
                        LogUtils.d("weijie", "发起评论请求：" + commentHttpDefault.getError_code());
                        LogUtils.d("weijie", "发起评论请求：" + commentHttpDefault.getMessage());
                        requestCommentListener.updateUI(commentHttpDefault.getData());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        LogUtils.d("weijie", "发起评论请求：" + e.getMessage());
                        requestCommentListener.updateUI(null);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //请求评论回调
    FragmentListener requestCommentListener = new FragmentListener() {
        @Override
        public void updateUI(List<?> list) {

            comments_data = (List<Comment>) list;
            if (comments_data == null || comments_data.size() < 1) {
                Comment comment = new Comment.Builder().build();
                comments_data.add(comment);
            }
            if (goodsDetail_imgAdapter == null) {
                goodsDetail_imgAdapter = new GoodsDetail_imgAdapter(goods, img_data, comments_data, GoodsDetailActivity.this);
            }
            goodsdetailImgs.setAdapter(goodsDetail_imgAdapter);

        }

        @Override
        public void appenddata(List<?> list) {

        }
    };

    /**
     * 查看某商品是否被某用户收藏
     *
     * @param goodsid
     * @param userid
     */
    private void checkForked(final int goodsid, int userid) {

        Observable<HttpDefault<String>> observable = SwapNetUtils.createAPI(GoodsAPI.class).CheckForked(goodsid, userid);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpDefault<String>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull HttpDefault<String> stringHttpDefault) {

                        if (stringHttpDefault.getError_code() == 0) {
                            ic_fork.setImageResource(R.mipmap.like_1);
//                            Message msg = new Message();
//                            msg.what = 0;
//                            handler.sendMessage(msg);
                            isFork = true;

                        } else if (stringHttpDefault.getError_code() == -1) {
                            ic_fork.setImageResource(R.mipmap.like_0);
//                            Message msg = new Message();
//                            msg.what = 1;
//                            handler.sendMessage(msg);
                            isFork = false;
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        LogUtils.d("weijie", "查询是否被收藏出错:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    /**
     * 用户取消收藏或者收藏商品
     *
     * @param goodsid
     * @param userid
     * @param type    0表示取消，1表示收藏
     */
    private void ControlLike(int goodsid, int userid, final int type) {

        Observable<HttpDefault<String>> observable = null;

        isFork = false;

        if (type == 0) {
            observable = SwapNetUtils.createAPI(GoodsAPI.class).cancelForked(goodsid, userid);
        } else if (type == 1) {
            observable = SwapNetUtils.createAPI(GoodsAPI.class).saveChecked(goodsid, userid);
        } else {
            return;
        }

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpDefault<String>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull HttpDefault<String> stringHttpDefault) {

                        if (type == 0) {

                            switch (stringHttpDefault.getError_code()) {
                                case 0:

//                                    Message msg = new Message();
//                                    msg.what = 3;
//                                    handler.sendMessage(msg);
                                    isFork = false;
                                    ic_fork.setImageResource(R.mipmap.like_0);
                                    LogUtils.d("weijie", "取消收藏");
                                    break;
                                case -1:
                                default:
                                    isFork = true;
                                    break;
                            }

                        } else if (type == 1) {

                            switch (stringHttpDefault.getError_code()) {
                                case 0:
//                                    Message msg = new Message();
//                                    msg.what = 4;
//                                    handler.sendMessage(msg);
                                    isFork = true;
                                    ic_fork.setImageResource(R.mipmap.like_1);
                                    LogUtils.d("weijie", "成功收藏");
                                    break;
                                case -1:
                                case -2:
                                default:
                                    isFork = false;
                                    break;
                            }
                        }

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        LogUtils.d("weijie", "ContrloLike Error：" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    /**
     * 检查是否登录
     *
     * @return 用户id
     */
    public int CheckLoginStatus() {

        //检查是否存在本地数据
        SharedPreferences sharedPreferences = this.getSharedPreferences("base64", MODE_PRIVATE);
        String token_read = sharedPreferences.getString("token", null);
        String userid_data = sharedPreferences.getString("userid", null);
        if (userid_read == 0 & token_read == null) {
            showToast("您还未登录", ToastDuration.SHORT);
            goActivity(LoginActivity.class);
            return 0;
        }
        if (userid_data != null) {
            userid_read = Integer.parseInt(sharedPreferences.getString("userid", null));
        }
        LogUtils.d("weijie", "本地登录信息：" + "token:" + token_read
                + "\n" + "userid:" + userid_read);

        //检查登录信息是否过期
        if (checktoken(token_read)) {
            return userid_read;
        } else {
            showToast("用户信息过期，请重新登录", ToastDuration.SHORT);
            goActivity(LoginActivity.class);
            return 0;
        }


    }

    /**
     * 检查token是否过期
     *
     * @param token
     * @return
     */
    private boolean checktoken(String token) {

        long now = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder(token);
        long time = Long.parseLong(sb.substring(sb.length() - 13, sb.length()));
        LogUtils.d("weijie", "token :" + time);
        long pasttime = (now - time) / (60 * 1000L);
        LogUtils.d("weijie", "token还有：" + (LOGIN_TIMEOUT - pasttime) + "分钟过期");

        if (pasttime >= LOGIN_TIMEOUT) {
            LogUtils.d("weijie", "token 过期");
            return false;
        }

        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.ic_back:
                goActivity(MainActivity.class);
                break;
            case R.id.btn_buy:
                Intent intent = new Intent(GoodsDetailActivity.this, ConfirmOrderActivity.class);
                userid_read = CheckLoginStatus();
                if (userid_read != 0) {
                    intent.putExtra("userid", userid_read);
                }
                intent.putExtra("good", goods);
                startActivity(intent);
                break;
            case R.id.ic_fork:

                if (isFork) {
                    ControlLike(goods.getId(), goods.getUser().getId(), 0);
                } else {
                    ControlLike(goods.getId(), goods.getUser().getId(), 1);
                }
                break;
            case R.id.btn_contract_shopper:
                RequestMessage(userid,shopperid);
                break;

        }
    }


    private void RequestMessage(final int userid, final int receiverid) {

        Observable<HttpDefault<List<MessageBoard>>> observable = SwapNetUtils.createAPI(MessageApi.class).SelectAllMessage(userid, receiverid);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpDefault<List<MessageBoard>>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull HttpDefault<List<MessageBoard>> messageBoards) {

                        for (MessageBoard messageBoard : messageBoards.getData()) {
                            if (messageBoard.getUserid() == userid) {
                                messageBoard.setIsLeft(0);
                            } else {
                                messageBoard.setIsLeft(1);
                            }
                        }

                        Intent intent = new Intent(GoodsDetailActivity.this, MsgBoardActivity.class);
                        intent.putExtra("receiverid",receiverid);
                        intent.putExtra("userid",userid);
                        intent.putParcelableArrayListExtra("messageboard", (ArrayList<? extends Parcelable>) messageBoards.getData());
                        startActivity(intent);


                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

}
