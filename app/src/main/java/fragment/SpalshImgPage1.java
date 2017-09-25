package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.swapanytime.R;

import base.baseFragment;
import utils.LogUtils;

/**
 * Created by weijie on 2017/9/24.
 */

public class SpalshImgPage1 extends baseFragment {

    private static final String TAG = SpalshImgPage1.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtils.d("TAG","isReuseView run");
        View view = inflater.inflate(R.layout.fragment_spalshpage1, container, false);
        return view;
    }

}
