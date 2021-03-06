package com.example.swapanytime;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.baoyz.actionsheet.ActionSheet;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import adapter.ChoosePhotoListAdapter;
import adapter.SimpleRecycleViewAdapter;
import api.GoodsAPI;
import api.UserAPI;
import base.MyApplication;
import base.baseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import cn.finalteam.galleryfinal.widget.HorizontalListView;
import entiry.Goods;
import entiry.HttpDefault;
import entiry.User;
import fragment.mBottomFragment;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import minterface.GalleryfinalActionListener;
import minterface.OnItemClickListener;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import utils.DialogUtil;
import utils.LogUtils;
import utils.MGalleryFinalUtils;
import utils.SwapNetUtils;

/**
 * Created by weij on 2017/12/2.
 */

public class PublishGoodsActivity extends baseActivity implements ActionSheet.ActionSheetListener {


    @Bind(R.id.ic_back)
    ImageView icBack;
    @Bind(R.id.titlebar_title)
    TextView titlebarTitle;
    @Bind(R.id.publish_goods_imgs)
    RecyclerView publishGoodsImgs;
    @Bind(R.id.price)
    TextView price;
    @Bind(R.id.price_et)
    EditText priceEt;
    @Bind(R.id.price_lin)
    LinearLayout priceLin;
    @Bind(R.id.price_bf)
    TextView priceBf;
    @Bind(R.id.pricebf_et)
    EditText pricebfEt;
    @Bind(R.id.price_bf_lin)
    LinearLayout priceBfLin;
    @Bind(R.id.yunfei)
    TextView yunfei;
    @Bind(R.id.yunfei_et)
    EditText yunfeiEt;
    @Bind(R.id.yunfei_lin)
    LinearLayout yunfeiLin;
    @Bind(R.id.type)
    TextView type;
    @Bind(R.id.ic_type)
    ImageView ic_type;
    @Bind(R.id.type_lin)
    LinearLayout typeLin;
    @Bind(R.id.btn_publish)
    TextView btnPublish;
    @Bind(R.id.goods_descriptionEt)
    AppCompatEditText goods_descriptionEt;
    @Bind(R.id.goodsnameEt)
    AppCompatEditText goodsnameEt;
    @Bind(R.id.login_progressbar)
    ProgressBar login_progressbar;
    @Bind(R.id.publishing_area)
    FrameLayout publishing_area;


    private List<String> data_left;
    private List<String> data_left_show;
    private List<String> data_right;
    private List<PhotoInfo> photo_list = null; //最终展示的列表
    private List<PhotoInfo> photo_list_temporary = null; //最终展示的列表
    private static MGalleryFinalUtils instance = null;
    private ChoosePhotoListAdapter choosephotolistadapter = null;
    private LinearLayoutManager linearlayoutmanager = null;
    private GridLayoutManager gridlayoutmanager = null;
    private Context context = null;
    private Dialog dialog = null;
    private int userid = -1;
    private DisplayImageOptions options = null;


    @Override
    public void initData() {

        MyApplication.getInstance().addActivity(PublishGoodsActivity.this);

        //显示选择商品图片的rv
        gridlayoutmanager = new GridLayoutManager(this, 4);
        publishGoodsImgs.setLayoutManager(gridlayoutmanager);
        context = this;
        data_left = new ArrayList<String>();
        data_left_show = new ArrayList<String>();
        data_right = new ArrayList<String>();

        //从xml文件读取数据
        String[] array_left = this.getResources().getStringArray(R.array.left);
        String[] array_rig_data = this.getResources().getStringArray(R.array.right);
        String[][] array_right = getTwoDimensionalArray(array_rig_data);

        //初始化左边数据
        for (String leftitem : array_left) {
            data_left.add(leftitem);
        }
        //初始化右边数据
        for (int i = 0; i < data_left.size(); i++) {
            data_right.add("");
            data_left_show.add(data_left.get(i));
            for (int j = 0; j < array_right.length - 1; j++) {
                if (array_right[i][j] != null) {
                    data_right.add(array_right[i][j]);
                    data_left_show.add("");
                }
            }
        }

        photo_list = new ArrayList<>();
        options = initGalleryfinalActionConfig();
        choosephotolistadapter = new ChoosePhotoListAdapter(PublishGoodsActivity.this, photo_list, options);
        choosephotolistadapter.addCameraListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                showActionsheet();
            }
        });
        publishGoodsImgs.setAdapter(choosephotolistadapter);

    }


    /**
     * 按设定规则解析一维数组为二维数组
     *
     * @param array
     * @return
     */
    private String[][] getTwoDimensionalArray(String[] array) {
        String[][] twoDimensionalArray = null;
        for (int i = 0; i < array.length; i++) {
            String[] tempArray = array[i].split(",");
            if (twoDimensionalArray == null) {
                twoDimensionalArray = new String[array.length][11];
            }
            for (int j = 0; j < tempArray.length; j++) {
                twoDimensionalArray[i][j] = tempArray[j];
            }
        }
        return twoDimensionalArray;
    }


    @Override
    public void initView() {

        titlebarTitle.setText("发布商品");

    }

    @Override
    public Object getContentView() {
        return R.layout.activity_publishgoods;
    }

    @Override
    public void initEvent() {

    }

    @OnClick({R.id.ic_back, R.id.type_lin, R.id.btn_publish})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ic_back:
                goActivity(MainActivity.class);
                break;
            case R.id.type_lin:
                showBottomDialog();
                break;
            case R.id.btn_publish:
                publishing_area.setVisibility(View.VISIBLE);
//                showProgressbar(1);
                PostGoods();
//                showProgressbar(0);
//                goActivity(MainActivity.class);
                break;
        }
    }


    /**
     * @return 提交用户填写的商品信息
     */
    private void PostGoods() {

        if (TextUtils.isEmpty(goodsnameEt.getText().toString())
                || TextUtils.isEmpty(yunfeiEt.getText().toString())
                || TextUtils.isEmpty(pricebfEt.getText().toString())
                || TextUtils.isEmpty(priceEt.getText().toString())
                || TextUtils.isEmpty(goods_descriptionEt.getText().toString())) {
            showToast("请完善信息",ToastDuration.LONG);
            publishing_area.setVisibility(View.GONE);
            return;
        }

        final Goods good = new Goods.Builder()
                .name(goodsnameEt.getText().toString())
                .description(goods_descriptionEt.getText().toString())
                .express(Integer.parseInt(yunfeiEt.getText().toString()))
                .price_before(Integer.parseInt(pricebfEt.getText().toString()))
                .price_sale(Integer.parseInt(priceEt.getText().toString()))
                .status("在库")
                .type(type.getText().toString())
                .build();


        String user_data = getSharedPreferences("base64", MODE_PRIVATE).getString("userid", null);
        if (user_data != null) {
            userid = Integer.parseInt(user_data);
        }

        final int[] goodid = {0};

        Observable<HttpDefault<Goods>> observable = SwapNetUtils.createAPI(GoodsAPI.class).
                addgoods(
                        good.getName(),
                        good.getStatus(),
                        good.getType(),
                        good.getPrice_before(),
                        good.getPrice_sale(),
                        (String) good.getDescription(),
                        good.getExpress(),
                        userid
                );
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpDefault<Goods>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull HttpDefault<Goods> userHttpDefault) {

                        goodid[0] = userHttpDefault.getData().getId();
                        uploadPics(goodid[0], photo_list);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        LogUtils.d("weijie", "postgoods error" + e.getMessage());
                        publishing_area.setVisibility(View.GONE);
                        showToast("发布商品出错",ToastDuration.SHORT);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    /****
     * 显示Actionsheet
     */
    private void showActionsheet() {

        ActionSheet.createBuilder(this, this.getSupportFragmentManager())
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
            instance = MGalleryFinalUtils.getInstance(this);
        }

        switch (index) {
            case 0:
                //拍照
                instance.initGalleryFinal(true, 1);
                instance.openCamera(new GalleryfinalActionListener() {
                    @Override
                    public void success(List<PhotoInfo> list) {
//                        publishIcCamera.setVisibility(View.GONE);
//                        publishTxt.setVisibility(View.GONE);
                        if (list == null) {
                            showSnackBar("没有选中图片..", ToastDuration.SHORT, btnPublish);
                            return;
                        }
                        photo_list_temporary = list;
                        int positionstart = photo_list_temporary.size();
                        photo_list.addAll(photo_list_temporary);
                        int count = photo_list.size() - photo_list_temporary.size();
                        publishGoodsImgs.setVisibility(View.VISIBLE);
                        choosephotolistadapter.notifyItemChanged(positionstart + 1, count);
                    }

                    @Override
                    public void failed(String msg) {
                        LogUtils.d("weijie", "打开相机Error:" + msg);
                    }
                });
                break;
            case 1:
                //打开相册
                instance.initGalleryFinal(false, 10);
                instance.openAlbumMore(new GalleryfinalActionListener() {
                    @Override
                    public void success(List<PhotoInfo> list) {
//                        publishIcCamera.setVisibility(View.GONE);
//                        publishTxt.setVisibility(View.GONE);
//                        publishGoodsImgs.setVisibility(View.VISIBLE);
//                        DisplayImageOptions options = initGalleryfinalActionConfig();
//                        choosephotolistadapter = new ChoosePhotoListAdapter(context, list, options);
//                        publishGoodsImgs.setAdapter(choosephotolistadapter);
//                        photo_list = list;
                        if (list == null) {
                            showSnackBar("没有选中图片..", ToastDuration.SHORT, btnPublish);
                            return;
                        }
                        photo_list_temporary = list;
                        int positionstart = photo_list_temporary.size();
                        photo_list.addAll(photo_list_temporary);
                        int count = photo_list.size() - photo_list_temporary.size();
                        publishGoodsImgs.setVisibility(View.VISIBLE);
//                        options = initGalleryfinalActionConfig();
//                        if (choosephotolistadapter==null){
//                            choosephotolistadapter = new ChoosePhotoListAdapter(context, list, options);
//                        }
                        choosephotolistadapter.notifyItemChanged(positionstart + 1, count);
                    }

                    @Override
                    public void failed(String msg) {
                        LogUtils.d("weijie", "打开相册Error:" + msg);
                    }
                });
                break;
            default:
                break;
        }
    }


    //初始化GalleryFinal动作前的配置
    private DisplayImageOptions initGalleryfinalActionConfig() {
        options = new DisplayImageOptions.Builder()
                .showImageOnFail(R.drawable.ic_gf_default_photo)
                .showImageForEmptyUri(R.drawable.ic_gf_default_photo)
                .showImageOnLoading(R.drawable.ic_gf_default_photo).build();

        return options;
    }


    //显示分类Dialog
    public void showBottomDialog() {

        this.dialog = DialogUtil.showBottomDialog(R.layout.bottomdialog_type, R.style.ActionButtomDialogStyle, PublishGoodsActivity.this, new DialogUtil.IDialogInitListener() {

            @Override
            public void initDialogView(View view) {
                RecyclerView left = (RecyclerView) view.findViewById(R.id.bottomdialog_type_left);
                RecyclerView right = (RecyclerView) view.findViewById(R.id.bottomdialog_type_right);
                LinearLayoutManager linearLayout1 = new LinearLayoutManager(PublishGoodsActivity.this);
                LinearLayoutManager linearLayout2 = new LinearLayoutManager(PublishGoodsActivity.this);
                SimpleRecycleViewAdapter simpleRecycleViewAdapter1 = new SimpleRecycleViewAdapter(PublishGoodsActivity.this, data_left_show);
                SimpleRecycleViewAdapter simpleRecycleViewAdapter2 = new SimpleRecycleViewAdapter(PublishGoodsActivity.this, data_right);

                simpleRecycleViewAdapter2.addTextClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        TextView textview = (TextView) view;
                        String click_data = textview.getText().toString();
                        type.setText(click_data);
                        ic_type.setVisibility(View.GONE);
                        if (dialog != null) {
                            dialog.dismiss();
                        }

                    }
                });

                left.setLayoutManager(linearLayout1);
                left.setAdapter(simpleRecycleViewAdapter1);
                right.setLayoutManager(linearLayout2);
                right.setAdapter(simpleRecycleViewAdapter2);
            }
        });

        dialog.show();

    }


    // TODO: 2018/1/15   上传图片   有出现图片上传报500的情况
    public void uploadPics(int goodid, List<PhotoInfo> list) {


        for (int i = 0; i < list.size(); i++) {
            File file = new File(list.get(i).getPhotoPath());
            if (!file.exists()) {
                LogUtils.d("weijie", "找不到图片：" + i);
                continue;
            }

            //图片存在，开始上传图片
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
            String description_title = "";
            RequestBody description = RequestBody.create(
                    MediaType.parse("multipart/form-data"), description_title);
            LogUtils.d("weijie", "开始上传第：" + i + "张图片...");
            Observable<HttpDefault<List<String>>> observable = SwapNetUtils.createAPI(GoodsAPI.class).uploadPics(goodid, body, 1);
            sleep(500L);
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<HttpDefault<List<String>>>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull HttpDefault<List<String>> stringHttpDefault) {

                            LogUtils.d("weijie", "上传结果:" + stringHttpDefault.getMessage());

                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            LogUtils.d("weijie", "postpics error" + e.getMessage());
                        }

                        @Override
                        public void onComplete() {
                        }
                    });
        }
        publishing_area.setVisibility(View.GONE);
        showToast("发布成功",ToastDuration.SHORT);
        goActivity(MainActivity.class);

    }


    private void showProgressbar(int way) {

        if (login_progressbar.getVisibility() == View.VISIBLE) {
            login_progressbar.setVisibility(View.GONE);
        }

        if (way == 1) {
            login_progressbar.setVisibility(View.VISIBLE);
        }
        if (way == 0) {
            login_progressbar.setVisibility(View.GONE);
        }

    }


    @Override
    protected void onDestroy() {
        MGalleryFinalUtils.getInstance(context).clearCache();
        super.onDestroy();
    }


    public void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
