package fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.example.swapanytime.LoginActivity;
import com.example.swapanytime.R;
import com.example.swapanytime.ShowTypeActivity;

import java.util.ArrayList;
import java.util.List;

import base.baseFragment;
import butterknife.Bind;
import butterknife.ButterKnife;
import utils.CBViewCreator;
import utils.LogUtils;

/**
 * Created by weijie on 2017/10/8.
 */

public class Main_index extends baseFragment implements OnItemClickListener{


    @Bind(R.id.icon_head)
    ImageButton iconHead;
    @Bind(R.id.icon_search)
    ImageButton iconSearch;
    @Bind(R.id.icon_cancel)
    ImageButton iconCancel;
    @Bind(R.id.icon_type)
    ImageButton iconType;
    @Bind(R.id.index_banner)
    ConvenientBanner indexBanner;

    private List<Integer> imglist;
    private int[] Imgsrc = {R.mipmap.banner1, R.mipmap.banner2, R.mipmap.banner1, R.mipmap.banner2};


    @Override
    protected int getContentView() {
        return R.layout.fragment_main_index;
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

        imglist = new ArrayList<>();
        imglist.add(R.mipmap.banner1);
        imglist.add(R.mipmap.banner2);
        imglist.add(R.mipmap.banner1);
        imglist.add(R.mipmap.banner2);

    }

    @Override
    protected void initEvent() {
        iconType.setOnClickListener(this);
        iconHead.setOnClickListener(this);


        ConvenientBanner banner = indexBanner.setPages(new CBViewHolderCreator<CBViewCreator>() {
            @Override
            public CBViewCreator createHolder() {
                return new CBViewCreator();
            }
        }, imglist)
                .setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused})
                //设置指示器的方向
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnItemClickListener(this)
                .startTurning(5*1000L);
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
           switch (position){
               case 1:
                   showSnackBar("1", ToastDuration.SHORT);
                   break;
           }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        LogUtils.e(getTag(),"onCreateView");
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        LogUtils.d(getTag(),"onCreateView");
        return rootView;

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
