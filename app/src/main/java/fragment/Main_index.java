package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.swapanytime.R;
import com.example.swapanytime.ShowTypeActivity;

import base.baseFragment;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by weijie on 2017/10/8.
 */

public class Main_index extends baseFragment {


    @Bind(R.id.icon_head)
    ImageButton iconHead;
    @Bind(R.id.icon_search)
    ImageButton iconSearch;
    @Bind(R.id.icon_cancel)
    ImageButton iconCancel;
    @Bind(R.id.icon_type)
    ImageButton iconType;

    @Override
    protected int getContentView() {
        return R.layout.fragment_main_index;
    }

    @Override
    protected void initConfig(View view) {
        ButterKnife.bind(this,view);
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {
         iconType.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_type:
                goToActivity(getContext(), ShowTypeActivity.class);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
