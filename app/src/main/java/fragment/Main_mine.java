package fragment;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baoyz.actionsheet.ActionSheet;
import com.bumptech.glide.Glide;
import com.example.swapanytime.BillActivity;
import com.example.swapanytime.LoginActivity;
import com.example.swapanytime.MainActivity;
import com.example.swapanytime.R;
import com.example.swapanytime.Record_Activity;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import api.GoodsAPI;
import api.UserAPI;
import base.MyApplication;
import base.baseFragment;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import entiry.Bill;
import entiry.HttpDefault;
import entiry.User;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import minterface.GalleryfinalActionListener;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import ui.CircleImageView;
import utils.DialogUtil;
import utils.LogUtils;
import utils.MGalleryFinalUtils;
import utils.SwapNetUtils;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by weijei on 2017/10/8.
 */

public class Main_mine extends baseFragment implements ActionSheet.ActionSheetListener {


    private static final long LOGIN_TIMEOUT = 30;   //token超时时间


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
    @Bind(R.id.txt_publish_buyreord)
    TextView txtPublishBuyreord;
    @Bind(R.id.txt_sale_record)
    TextView txtSaleRecord;
    @Bind(R.id.txt_buy_record)
    TextView txtBuyRecord;
    @Bind(R.id.txt_shoucang)
    TextView txtShoucang;
    @Bind(R.id.txt_mine_pay)
    TextView txtMinePay;
    @Bind(R.id.txt_change_account)
    TextView txtChangeAccount;
    @Bind(R.id.txt_exit)
    TextView txtExit;
    @Bind(R.id.txt_persondata)
    TextView txt_persondata;
    @Bind(R.id.txt_resetpsd)
    TextView txt_resetpsd;
    @Bind(R.id.uploading_area)
    FrameLayout uploading_area;


    private User user_read = null;   //用户信息

    //登陆状态
    private boolean islogin = false;
    //拍照、打开相册取的的文件
    File file_result = null;
    Main_mine main_mine = this;


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

    @OnClick({R.id.ic_forward, R.id.mine_head, R.id.mine_name, R.id.mine_desc, R.id.mine_titlebar,R.id.txt_resetpsd,
            R.id.txt_persondata, R.id.title_name, R.id.collapsingToolbarLayout, R.id.app_bar, R.id.txt_publish_buyreord,
            R.id.txt_sale_record, R.id.txt_buy_record, R.id.txt_shoucang, R.id.txt_mine_pay, R.id.txt_change_account, R.id.txt_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ic_forward:
                break;
            case R.id.mine_head:
                showActionsheet();
                break;
            case R.id.mine_name:
                break;
            case R.id.mine_desc:
                break;
            case R.id.mine_titlebar:
                break;
            case R.id.title_name:
                break;
            case R.id.collapsingToolbarLayout:
                break;
            case R.id.app_bar:
                break;
            case R.id.txt_persondata:
                showPersonDataDialog(user_read);
                break;
            case R.id.txt_publish_buyreord:
                Intent intent = new Intent(getContext(), Record_Activity.class);
                intent.putExtra("type", 0);
                intent.putExtra("userid", userid_read);
                intent.putExtra("title", txtPublishBuyreord.getText().toString());
                startActivity(intent);
                break;
            case R.id.txt_sale_record:
                Intent intent1 = new Intent(getContext(), Record_Activity.class);
                intent1.putExtra("type", 1);
                intent1.putExtra("userid", userid_read);
                intent1.putExtra("title", txtSaleRecord.getText().toString());
                startActivity(intent1);
                break;
            case R.id.txt_buy_record:
                Intent intent2 = new Intent(getContext(), Record_Activity.class);
                intent2.putExtra("type", 2);
                intent2.putExtra("userid", userid_read);
                intent2.putExtra("title", txtBuyRecord.getText().toString());
                startActivity(intent2);
                break;
            case R.id.txt_shoucang:
                Intent intent3 = new Intent(getContext(), Record_Activity.class);
                intent3.putExtra("type", 3);
                intent3.putExtra("userid", userid_read);
                intent3.putExtra("title", txtShoucang.getText().toString());
                startActivity(intent3);
                break;
            case R.id.txt_mine_pay:
//                goToActivity(BillActivity.class);
                RequestBill(0, userid_read);
                break;
            case R.id.txt_change_account:
                goToActivity(LoginActivity.class);
                break;
            case R.id.txt_exit:
                MyApplication.getInstance().exit();
                break;
            case R.id.txt_resetpsd:
                showResetPsdDialog();
                break;

        }
    }


    //请求用户账单
    private void RequestBill(int requesttype, int userid) {

        Observable<HttpDefault<List<Bill>>> observable = SwapNetUtils.createAPI(GoodsAPI.class).getBill(requesttype, userid);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpDefault<List<Bill>>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull HttpDefault<List<Bill>> listHttpDefault) {

                        Intent intent = new Intent(getContext(), BillActivity.class);
                        intent.putParcelableArrayListExtra("billist", (ArrayList<? extends Parcelable>) listHttpDefault.getData());
                        startActivity(intent);

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

//
//        if (checkIsLogin()) {
//            readLocaldata(token_read, userid_read);
//        }

    }

    @Override
    public void onStart() {
        super.onStart();
        if (checkIsLogin()) {
            readLocaldata(token_read, userid_read, getdatalistener);
        }
    }

    getDataListener getdatalistener = new getDataListener() {
        @Override
        public void getData(Object obj) {
            user_read = (User) obj;
        }

        @Override
        public void updateui(Object obj) {

        }
    };


    /**
     * 展示菜单
     */
    private void showActionsheet() {

        ActionSheet.createBuilder(getActivity(), getActivity().getSupportFragmentManager())
                .setCancelButtonTitle("取消")
                .setOtherButtonTitles("拍照", "打开相册")
                .setCancelableOnTouchOutside(true)/**/
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
                //拍照
                instance.initGalleryFinal(true, 1);
                instance.openCamera(new GalleryfinalActionListener() {
                    @Override
                    public void success(List<PhotoInfo> list) {
                        PhotoInfo photoInfo = list.get(0);
                        DisplayImageOptions options = initGalleryfinalActionConfig();
                        ImageLoader.getInstance().displayImage("file:/" + photoInfo.getPhotoPath(), mineHead, options);

                        file_result = new File(photoInfo.getPhotoPath());
                        uploading_area.setVisibility(View.VISIBLE);
                        uploadFile(userid_read, file_result, "file");
                    }

                    @Override
                    public void failed(String msg) {

                    }
                });
                break;
            case 1:
                //打开相册
                instance.initGalleryFinal(true, 1);
                instance.openAlbumSingle(new GalleryfinalActionListener() {
                    @Override
                    public void success(List<PhotoInfo> list) {
                        PhotoInfo photoInfo = list.get(0);
                        DisplayImageOptions options = initGalleryfinalActionConfig();
                        ImageLoader.getInstance().displayImage("file:/" + photoInfo.getPhotoPath(), mineHead, options);

                        file_result = new File(photoInfo.getPhotoPath());
                        uploadFile(userid_read, file_result, "file");
                    }

                    @Override
                    public void failed(String msg) {
                        LogUtils.d(getmTag(), "打开相册Error:" + msg);
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

    //本地数据读取登陆用户信息:本地数据包括token和id
    private static User user_data = null;


    //检查登陆状态
    private boolean checkIsLogin() {

        //检查是否存在本地数据
        SharedPreferences sharedPreferences = this.getContext().getSharedPreferences("base64", MODE_PRIVATE);
        token_read = sharedPreferences.getString("token", null);
        String userid_data = sharedPreferences.getString("userid", null);
        if (userid_read == 0 & token_read == null) {
            showToast("您还未登录", ToastDuration.SHORT);
            goToActivity(LoginActivity.class);
            return false;
        }
        if (userid_data != null) {
            userid_read = Integer.parseInt(sharedPreferences.getString("userid", null));
        }
        LogUtils.d("weijie", "本地登录信息：" + "token:" + token_read
                + "\n" + "userid:" + userid_read);

        //检查登录信息是否过期
        if (checktoken(token_read)) {
            return true;
        } else {
            showToast("用户信息过期，请重新登录", ToastDuration.SHORT);
            goToActivity(LoginActivity.class);
            return false;
        }

    }


    //从本地数据读取的token id headimg;
    private String token_read = null;
    private int userid_read = 0;

    //检查token是否过期
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


    //根据token,id请求客户信息
    private User readLocaldata(String token, final int userid, final getDataListener listener) {
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
                                //请求到的用户
                                user_data = userHttpDefault.getData();
                                LogUtils.d("weijie", "成功读取用户:" + user_data.getTel());
                                //更新头像
                                Glide.with(main_mine)
                                        .load(SwapNetUtils.getBaseURL() + user_data.getHeadimg())
                                        .asBitmap()
                                        .error(R.mipmap.pic_error)
                                        .placeholder(R.mipmap.pic_loading)
                                        .centerCrop()
                                        .into(mineHead);
                                mineName.setText(user_data.getName());
                                titleName.setText(user_data.getName());
                                if (user_data.getDescription() == null) {
                                    mineDesc.setText("咩都无/**/....");
                                } else {
                                    mineDesc.setText(user_data.getDescription());
                                }
                            }
                            WriteUserData(user_data);
                            listener.getData(userHttpDefault.getData());
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            LogUtils.d(getTag(), "请求用户信息出错");
                            LogUtils.d(getTag(), e.getMessage());
                        }

                        @Override
                        public void onComplete() {
                        }
                    });

        }
        return user_data;
    }


    //上传文件到服务器
    private boolean uploadFile(int userid, File file, String paramsname) {

        if (!file.exists()) {
            uploading_area.setVisibility(View.GONE);
            return false;
        }
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData(paramsname, file.getName(), requestBody);

        String description_title = "";
        RequestBody description = RequestBody.create(
                MediaType.parse("multipart/form-data"), description_title);


        Observable<HttpDefault<String>> observable = SwapNetUtils.createAPI(UserAPI.class).updateHeadImg(userid, body);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpDefault<String>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull HttpDefault<String> stringHttpDefault) {

                        LogUtils.d(getmTag(), "upload status" + stringHttpDefault.getMessage());
                        uploading_area.setVisibility(View.GONE);
                        showToast("上传完成",ToastDuration.SHORT);

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        LogUtils.d(getmTag(), "upload error:" + e.getMessage());
                        uploading_area.setVisibility(View.GONE);
                        showToast("上传失败",ToastDuration.SHORT);

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        return true;
    }


    /**
     * 把用户数据写进本地
     *
     * @return
     */
    private boolean WriteUserData(User user) {

        if ((user == null) || (user.getId() <= 0)) {
            showSnackBar("写入本地用户数据出错", ToastDuration.SHORT);
            SharedPreferences sharedPreferences = getContext().getSharedPreferences("base64", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("username", "");
            editor.putString("headimg", "file/download/?filename=normal.png&type=0");
            return false;
        } else {
            SharedPreferences sharedPreferences = getContext().getSharedPreferences("base64", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("username", user.getName());
            editor.putString("headimg", user.getHeadimg());
            editor.putString("adress", user.getAdress());
            editor.putString("tel", user.getTel());
            editor.commit();
            return true;
        }

    }

    /**
     * 弹出修改个人信息窗口
     */
    Dialog dialog = null;

    private void showPersonDataDialog(final User data) {

        dialog = DialogUtil.showBottomDialog(R.layout.view_personaldata,
                R.style.ActionButtomDialogStyle,
                getContext(),
                new DialogUtil.IDialogInitListener() {
                    @Override
                    public void initDialogView(View view) {
                        final EditText name = (EditText) view.findViewById(R.id.et_person_name);
                        final EditText desc = (EditText) view.findViewById(R.id.et_person_desc);
                        final EditText phone = (EditText) view.findViewById(R.id.et_person_phone);
                        final EditText sex = (EditText) view.findViewById(R.id.et_person_sex);
                        Button btn_commit = (Button) view.findViewById(R.id.btn_commit_personaldata);
                        if (data != null) {
                            name.setText(data.getName());
                            desc.setText(data.getDescription());
                            phone.setText(data.getTel());
                            sex.setText(data.getSex());
                        }

                        btn_commit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                data.setName(name.getText().toString());
                                data.setDescription(desc.getText().toString());
                                data.setTel(phone.getText().toString());
                                data.setSex(sex.getText().toString());
                                UpdateUserDataByBottmSheetDialog(data);
                            }
                        });


                    }
                });
        dialog.show();
    }


    /**
     * 展示重设密码弹窗
     */
    private void showResetPsdDialog() {


        dialog = DialogUtil.showBottomDialog(R.layout.view_reset_psd,
                R.style.ActionButtomDialogStyle,
                getContext(),
                new DialogUtil.IDialogInitListener() {
                    @Override
                    public void initDialogView(View view) {
                        final EditText old_psd = (EditText) view.findViewById(R.id.et_old_psd);
                        final EditText new_psd = (EditText) view.findViewById(R.id.et_new_psd);
                        Button btn_commit = (Button) view.findViewById(R.id.btn_commit_psd);


                        btn_commit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ResetPsd(MyApplication.getInstance().getLoginUserid(getContext()),
                                        old_psd.getText().toString(),
                                        new_psd.getText().toString());
                            }
                        });


                    }
                });
        dialog.show();
    }


    /**
     * 重设密码
     *
     * @param s
     * @param s1
     */
    private void ResetPsd(int id, String s, String s1) {

        Observable<HttpDefault<User>> observable = SwapNetUtils.createAPI(UserAPI.class).resetPassword(id, s, s1);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpDefault<User>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull HttpDefault<User> userHttpDefault) {
                        showSnackBar(userHttpDefault.getMessage(),ToastDuration.SHORT);
                        dialog.dismiss();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                           showSnackBar(e.getMessage(),ToastDuration.SHORT);
                           dialog.dismiss();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    /**
     * 修改个人信息后更新界面
     *
     * @param data
     */
    public void UpdateUserDataByBottmSheetDialog(final User data) {

        Observable<HttpDefault<User>> observable = SwapNetUtils.createAPI(UserAPI.class).updateuser(
                data.getId(),
                data.getName(),
                data.getDescription(),
                data.getTel(),
                data.getSex());
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpDefault<User>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull HttpDefault<User> userHttpDefault) {

                        if (userHttpDefault.getData().getTel() != null) {
                            mineName.setText(data.getName());
                            titleName.setText(data.getName());
                            mineDesc.setText(data.getDescription());
                            showSnackBar("更新成功", ToastDuration.SHORT);
                            dialog.dismiss();
                        } else {
                            showSnackBar(userHttpDefault.getMessage(), ToastDuration.SHORT);
                            dialog.dismiss();
                        }

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        showSnackBar("服务器出问题了: Cause By" + e.getMessage(), ToastDuration.SHORT);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

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


    private interface getDataListener {
        void getData(Object obj);

        void updateui(Object obj);
    }

}
