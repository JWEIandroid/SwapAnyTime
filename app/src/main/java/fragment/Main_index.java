package fragment;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.example.swapanytime.LoginActivity;
import com.example.swapanytime.R;
import com.example.swapanytime.ShowTypeActivity;

import java.util.ArrayList;
import java.util.List;

import adapter.item_goods_adapter;
import base.baseFragment;
import butterknife.Bind;
import butterknife.ButterKnife;
import entiry.Goods;
import entiry.User;
import utils.CBViewCreator;
import utils.ContentUtils;
import utils.LogUtils;

/**
 * Created by weijie on 2017/10/8.
 */

public class Main_index extends baseFragment implements OnItemClickListener {


    @Bind(R.id.index_banner)
    ConvenientBanner indexBanner;
    @Bind(R.id.list_good)
    RecyclerView rv_goods;
    @Bind(R.id.app_bar_index)
    AppBarLayout appBarIndex;
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
    @Bind(R.id.lin_title)
    LinearLayout linTitle;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private List<Integer> bannder_imglist;
    private ContentUtils contentUtils;
    private List<Goods> good_list;
    private List<String> imglist;
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

        bannder_imglist = new ArrayList<>();
        bannder_imglist.add(R.mipmap.banner1);
        bannder_imglist.add(R.mipmap.banner2);
        bannder_imglist.add(R.mipmap.banner1);
        bannder_imglist.add(R.mipmap.banner2);

        contentUtils = ContentUtils.getInstance();

        good_list = new ArrayList<>();
        imglist = new ArrayList<>();


        // 初始化商品图片
        for (int j = 0; j < 10; j++) {
            imglist.add(imgurl);
        }


        //初始化每条商品信息
        for (int i = 0; i < 10; i++) {
            User user = new User.Builder().name("用户" + i).imgUrl(headurl).build();
            Goods goods = new Goods.Builder().name("商品 " + i)
                    .descrtption("商品描述")
                    .imgUrls(imglist)
                    .price_after(2000 + i)
                    .pulisher(user)
                    .build();

            good_list.add(goods);
        }
        LogUtils.d(getTag(), imglist.size() + "|||" + good_list.size());
        item_goods_adapter imgAdapter = new item_goods_adapter(this.getContext(), good_list);
        rv_goods.setLayoutManager(Linlayoutmanager);
        rv_goods.setAdapter(imgAdapter);
        LogUtils.d(getTag(), "work end");
    }


    // Banner+栏状态 : 展开,折叠,中间
    private enum BAR_STATUS {
        EXPANDED, COLLAPSED, INTERNEDIATE
    }

    private BAR_STATUS bar_status;


    @Override
    protected void initEvent() {
        iconType.setOnClickListener(this);
        iconHead.setOnClickListener(this);


        ConvenientBanner banner = indexBanner.setPages(new CBViewHolderCreator<CBViewCreator>() {
            @Override
            public CBViewCreator createHolder() {
                return new CBViewCreator();
            }
        }, bannder_imglist)
                .setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused})
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .startTurning(5 * 1000L);

        searchEt.addTextChangedListener(textWatcher);
        searchEt.setFocusable(false);


        //监听标题栏
        appBarIndex.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (verticalOffset == 0) {
                    if (bar_status != BAR_STATUS.EXPANDED) {
                        bar_status = BAR_STATUS.EXPANDED;
                        indexBanner.setVisibility(View.VISIBLE);
                        linTitle.setVisibility(View.VISIBLE);
                    }
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    if (bar_status != BAR_STATUS.COLLAPSED) {
                        bar_status = BAR_STATUS.COLLAPSED;
                        indexBanner.setVisibility(View.GONE);
                        linTitle.setVisibility(View.GONE);
                    } else {
                        if (bar_status != BAR_STATUS.INTERNEDIATE) {
                            indexBanner.setVisibility(View.GONE);
                            linTitle.setVisibility(View.GONE);
                        }
                    }
                }
            }
        });


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_type:
                goToActivity(getContext(), ShowTypeActivity.class);
                break;
            case R.id.icon_head:
                goToActivity(getContext(), LoginActivity.class);
            default:
                break;
        }
    }


    @Override
    public void onItemClick(int position) {
        switch (position) {
            case 1:
                showSnackBar("1", ToastDuration.SHORT);
                break;
        }
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
        LogUtils.e(getTag(), "onCreateView");
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        LogUtils.d(getTag(), "onCreateView");
        return rootView;

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
