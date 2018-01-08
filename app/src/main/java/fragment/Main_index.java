package fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.example.swapanytime.R;
import com.example.swapanytime.ShowTypeActivity;

import java.util.ArrayList;
import java.util.Collection;
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

    private ContentUtils contentUtils;
    //全部商品信息
    private List<Goods> good_list;
    //一条商品信息的全部图片
    private ArrayList<String> imglist;

    private Context context;

    private final String imgurl = "https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=3208352253,560928408&fm=173&s=6F302AC24A7220942AA16C090300C092&w=218&h=146&img.JPEG";
    private final String headurl = "https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=480915072,3609081711&fm=173&s=A4D031C41416BA741EE1658903007081&w=218&h=146&img.JPEG";


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

        getGoodsmessage(new FragmentListener() {
            @Override
            public void updateUI(List<?> list) {
                good_list = (List<Goods>) list;


                LogUtils.d("weijie", good_list.size()+"");
                LogUtils.d("weijie", good_list.get(1).getName());
                LogUtils.d("weijie", good_list.get(1).getImgurl().get(0));
                //请求首页商品信息
//                good_list.add(0, null);

                for (int i = 0; i < good_list.size(); i++) {

                    User user = new User.Builder().name("用户" + i)
                            .headimg(headurl)
                            .build();

                    good_list.get(i).setUserid(user);
//                    Goods goods = new Goods.Builder().name("商品 " + good_list.get(i).getName())
//                            .description(good_list.get(i).getDescription())
//                            .imgurl(good_list.get(i).getImgurl())
//                            .user(user)
//                            .price_sale(good_list.get(i).getPrice_sale())
//                            .build();
                }

                item_goods_adapter imgAdapter = new item_goods_adapter(context, good_list);
                imgAdapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent = new Intent(getContext(), GoodsDetailActivity.class);
                        intent.putStringArrayListExtra("img_list", good_list.get(position).getImgurl());
                        startActivity(intent);
                    }
                });

                LogUtils.d(getTag(), "首页商品信息条数--------" + good_list.size());
                rv_goods.setLayoutManager(Linlayoutmanager);
                rv_goods.setAdapter(imgAdapter);
            }
        });


    }


    //请求全部首页展示商品
    private void getGoodsmessage(final FragmentListener listener) {

        Observable<HttpDefault<List<Goods>>> observable = SwapNetUtils.createAPI(GoodsAPI.class).QueryGoods();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpDefault<List<Goods>>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull HttpDefault<List<Goods>> goodsHttpDefault) {
                        good_list = goodsHttpDefault.getData();
                        listener.updateUI(good_list);
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


    //请求商品的图片
    private void getGoodsImgUrl(int goodid) {
        Observable<HttpDefault<ArrayList<String>>> observable = SwapNetUtils.createAPI(GoodsAPI.class).QueryGoodsAllImgs(goodid);
        observable.observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpDefault<ArrayList<String>>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull HttpDefault<ArrayList<String>> listHttpDefault) {
                        imglist = listHttpDefault.getData();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    //请求用户信息
    private User getUserMsg(int userid) {

        final User[] user = {null};

        Observable<HttpDefault<User>> observable = SwapNetUtils.createAPI(UserAPI.class).queryUser(userid);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpDefault<User>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull HttpDefault<User> userHttpDefault) {
                        user[0] = userHttpDefault.getData();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        if (user[0] != null) {
            return user[0];
        }
        return null;


    }


    @OnClick({R.id.icon_head, R.id.search_et, R.id.icon_search, R.id.icon_cancel, R.id.icon_type, R.id.list_good})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_head:
                goToActivity(LoginActivity.class);
                break;
            case R.id.search_et:
                break;
            case R.id.icon_search:
                break;
            case R.id.icon_cancel:
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
                iconSearch.setVisibility(View.GONE);
            } else {
                iconCancel.setVisibility(View.GONE);
                iconSearch.setVisibility(View.VISIBLE);
            }
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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