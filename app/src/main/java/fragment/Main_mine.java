package fragment;

import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baoyz.actionsheet.ActionSheet;
import com.bumptech.glide.Glide;
import com.example.swapanytime.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import api.UserAPI;
import base.baseFragment;
import butterknife.Bind;
import butterknife.ButterKnife;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import constant.NetConstant;
import entiry.HttpDefault;
import entiry.User;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import minterface.GalleryfinalActionListener;
import ui.CircleImageView;
import utils.LogUtils;
import utils.MGalleryFinalUtils;
import utils.SwapNetUtils;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by weijei on 2017/10/8.
 */

public class Main_mine extends baseFragment implements ActionSheet.ActionSheetListener {


    private static final long LOGIN_TIMEOUT = 30;
    private static MGalleryFinalUtils instance = null;

    @Bind(R.id.ic_forward)
    ImageView icForward;
    @Bind(R.id.mine_head)
    CircleImageView mineHead;
    @Bind(R.id.mine_name)
    TextView mineName;
    @Bind(R.id.mine_desc)
    TextView mineDesc;
    @Bind(R.id.mine_titlebar)
    RelativeLayout mineTitlebar;
    @Bind(R.id.title_name)
    TextView titleName;
    @Bind(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @Bind(R.id.app_bar)
    AppBarLayout appBar;

    //登陆状态
    private boolean islogin = false;


    @Override
    protected int getContentView() {
        return R.layout.fragment_main_mine;
    }

    @Override
    protected void initConfig(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {

    }


    private BAR_STATUS bar_status;

    // 标题栏状态 : 展开,折叠,中间
    private enum BAR_STATUS {
        EXPANDED, COLLAPSED, INTERNEDIATE
    }

    @Override
    protected void initEvent() {


        //监听标题栏
        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (verticalOffset == 0) {
                    if (bar_status != BAR_STATUS.EXPANDED) {
                        bar_status = BAR_STATUS.EXPANDED;
                        mineTitlebar.setVisibility(View.VISIBLE);
                        titleName.setVisibility(View.GONE);
                    }
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    if (bar_status != BAR_STATUS.COLLAPSED) {
                        bar_status = BAR_STATUS.COLLAPSED;
                        mineTitlebar.setVisibility(View.GONE);
                        titleName.setVisibility(View.VISIBLE);
                    } else {
                        if (bar_status != BAR_STATUS.INTERNEDIATE) {
                            bar_status = BAR_STATUS.INTERNEDIATE;
                            mineTitlebar.setVisibility(View.GONE);
                            titleName.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
        });

        mineHead.setOnClickListener(this);

        if (checkIsLogin()) {
            readLocaldata(token_read, userid_read);
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.mine_head:
                showActionsheet();
                break;

        }

    }


    private void showActionsheet() {

        ActionSheet.createBuilder(getActivity(), getActivity().getSupportFragmentManager())
                .setCancelButtonTitle("取消")
                .setOtherButtonTitles("拍照", "打开相册")
                .setCancelableOnTouchOutside(true)
                .setListener(this)
                .show();
    }

    @Override
    public void onDismiss(ActionSheet actionSheet, boolean isCancel) {

    }

    @Override
    public void onOtherButtonClick(ActionSheet actionSheet, int index) {

        if (instance == null) {
            instance = MGalleryFinalUtils.getInstance(getActivity());
        }

        switch (index) {
            case 0:
                instance.initGalleryFinal(true);
                instance.openCamera(new GalleryfinalActionListener() {
                    @Override
                    public void success(List<PhotoInfo> list) {
                        PhotoInfo photoInfo = list.get(0);
                        DisplayImageOptions options = initGalleryfinalActionConfig();
                        ImageLoader.getInstance().displayImage("file:/" + photoInfo.getPhotoPath(), mineHead, options);
                    }

                    @Override
                    public void failed(String msg) {

                    }
                });
                break;


            case 1:
                instance.initGalleryFinal(true);
                instance.openAlbumSingle(new GalleryfinalActionListener() {
                    @Override
                    public void success(List<PhotoInfo> list) {
                        PhotoInfo photoInfo = list.get(0);
                        DisplayImageOptions options = initGalleryfinalActionConfig();
                        ImageLoader.getInstance().displayImage("file:/" + photoInfo.getPhotoPath(), mineHead, options);
                    }

                    @Override
                    public void failed(String msg) {

                    }
                });
                break;


            default:
                break;
        }
    }

    //初始化GalleryFinal动作前的配置
    private DisplayImageOptions initGalleryfinalActionConfig() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnFail(R.drawable.ic_gf_default_photo)
                .showImageForEmptyUri(R.drawable.ic_gf_default_photo)
                .showImageOnLoading(R.drawable.ic_gf_default_photo).build();

        return options;
    }

    //本地数据读取登陆用户信息
    private static User user_data = null;

    //检查登陆状态
    private boolean checkIsLogin() {

        //查看是否已经登录
        SharedPreferences sharedPreferences = this.getContext().getSharedPreferences("base64", MODE_PRIVATE);
        token_read = sharedPreferences.getString("token", null);
        userid_read = Integer.parseInt(sharedPreferences.getString("userid", null));


        if (userid_read == 0 & token_read == null) {
            return false;
        }
        LogUtils.d("weijie", "本地登录信息：" + "token:" + token_read + "\n" + "userid:" + userid_read);
        if (userid_read != 0) {
            if (checktoken(token_read)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    //检查token是否过期
    private boolean checktoken(String token) {

        long now = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder(token);
        long time = Long.parseLong(sb.substring(sb.length() - 13, sb.length()));
        LogUtils.d("weijie", "token time:" + time);
        long pasttime = (now - time) / (60 * 1000L);
        LogUtils.d("weijie", "token还有：" + (LOGIN_TIMEOUT - pasttime) + "分钟过期");

        if (pasttime >= LOGIN_TIMEOUT) {
            LogUtils.d("weijie", "token 过期");
            return false;
        }

        return true;
    }


    private String token_read = null;
    private int userid_read = 0;
    Main_mine main_mine = this;


    //根据本地用户数据读取
    private User readLocaldata(String token, int userid) {
        if (token != null & userid != 0) {
            Observable<HttpDefault<User>> observable = SwapNetUtils.createAPI(UserAPI.class).getUserdata(token, userid);
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<HttpDefault<User>>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull HttpDefault<User> userHttpDefault) {
                            if (userHttpDefault.getError_code() == 0) {
                                user_data = userHttpDefault.getData();
                                LogUtils.d("weijie", "成功读取用户:"+user_data.getHeadimg());
                                LogUtils.d("weijie", "成功读取用户-1");
                                Glide.with(main_mine).load(user_data.getHeadimg()).asBitmap().centerCrop().into(mineHead);
                            }

                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            LogUtils.d(getTag(), e.getMessage());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });

        }
        return user_data;
    }

    //根据url读取图片
    private void loadPicData(String headimg) {



    }

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
