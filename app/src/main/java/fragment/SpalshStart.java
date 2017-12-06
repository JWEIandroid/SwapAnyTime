package fragment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.swapanytime.MainActivity;
import com.example.swapanytime.R;

import base.baseFragment;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by weijie on 2017/9/24.
 */

public class SpalshStart extends baseFragment {

    private final String TAG = SpalshStart.class.getSimpleName();
    @Bind(R.id.splashstart_bg)
    ImageView splashstartBg;
    @Bind(R.id.btn_splashstart)
    Button btnSplashstart;
    @Bind(R.id.btn_jump)
    Button btn_jump;


    @Override
    public int getContentView() {
        return R.layout.fragment_splashstart;
    }

    @Override
    protected void initConfig(View view) {

    }

    @Override
    protected void initView(View view) {

        Glide.with(this.getActivity()).load(R.mipmap.smiles).centerCrop().into(splashstartBg);

        countDownTimer = new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                btn_jump.setText("跳过(" + millisUntilFinished / 1000 + "s)");
            }

            @Override
            public void onFinish() {
//                btn_jump.setText("跳过(" + 0 + "s)");
                goToActivity( MainActivity.class);

            }
        };
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {
        btn_jump.setOnClickListener(this);
    }



    private CountDownTimer countDownTimer;


    @Override
    protected void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
    }

    @Override
    protected void onFragmentVisibleChange(boolean IsVisible) {
        super.onFragmentVisibleChange(IsVisible);
        if (IsVisible) {
            startClock();
        }
    }

    private void startClock() {
        btn_jump.setVisibility(View.VISIBLE);
        countDownTimer.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_jump:
            case R.id.btn_splashstart:
                if (countDownTimer != null) {
                    countDownTimer.cancel();
                }
                goToActivity( MainActivity.class);
                break;
            default:
                break;
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
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
