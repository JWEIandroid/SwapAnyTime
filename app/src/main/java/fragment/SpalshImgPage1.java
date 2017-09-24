package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.swapanytime.R;

import base.baseFragment;

/**
 * Created by dell on 2017/9/24.
 */

public class SpalshImgPage1 extends baseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_spalshpage1, container, false);
        return view;
    }
}
