package fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.swapanytime.R;

import base.baseFragment;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by weijie on 2017/9/24.
 */

public class SpalshImgPage1 extends baseFragment {

    private static final String TAG = SpalshImgPage1.class.getSimpleName();
    @Bind(R.id.splash1_bg)
    ImageView splash1Bg;

    @Override
    public int getContentView() {
        return R.layout.fragment_spalshpage1;
    }


    @Override
    protected void initConfig(View view) {

    }

    @Override
    protected void initView(View view) {
        Glide.with(this.getActivity()).load(R.mipmap.boy).diskCacheStrategy(DiskCacheStrategy.SOURCE).centerCrop().into(splash1Bg);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {

    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
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
