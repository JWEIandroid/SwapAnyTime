package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.swapanytime.R;

import base.baseFragment;

/**
 * Created by weijie on 2017/9/24.
 */

public class SpalshStart extends baseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splashstart, container, false);
        return view;
    }
}
