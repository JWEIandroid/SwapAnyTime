package fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.swapanytime.R;

import base.baseFragment;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by weijie on 2017/9/24.
 */

public class SpalshImgPage2 extends baseFragment {

    @Bind(R.id.splash2_bg)
    ImageView splash2Bg;

    @Override
    public int getContentView() {
        return R.layout.fragment_splashpage2;
    }


    @Override
    protected void initConfig(View view) {

    }

    @Override
    protected void initView(View view) {
        Glide.with(this.getActivity()).load(R.mipmap.face).centerCrop().into(splash2Bg);
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
