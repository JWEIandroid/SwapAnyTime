package fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.swapanytime.MsgBoardActivity;
import com.example.swapanytime.R;

import base.baseFragment;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dell on 2017/10/8.
 */

public class Main_message extends baseFragment {


    @Bind(R.id.ic_back)
    ImageView icBack;
    @Bind(R.id.titlebar_title)
    TextView titlebarTitle;
    @Bind(R.id.liuyanban)
    FrameLayout liuyanban;
    @Bind(R.id.comment)
    FrameLayout comment;

    @Override
    protected int getContentView() {
        return R.layout.fragment_main_message;
    }

    @Override
    protected void initConfig(View view) {

    }

    @Override
    protected void initView(View view) {

        icBack.setVisibility(View.GONE);
        titlebarTitle.setText("消息");

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {

    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({ R.id.liuyanban, R.id.comment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.liuyanban:
                goToActivity(MsgBoardActivity.class);
                break;
            case R.id.comment:
                goToActivity(MsgBoardActivity.class);
                break;
        }
    }
}
