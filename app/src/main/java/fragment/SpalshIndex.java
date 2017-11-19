package fragment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.swapanytime.MainActivity;
import com.example.swapanytime.R;

import base.baseFragment;
import utils.LogUtils;

import static com.example.swapanytime.R.id.btn_jump;

/**
 * Created by weijie on 2017/9/24.
 */

public class SpalshIndex extends baseFragment {


    private static final String  TAG = SpalshIndex.class.getSimpleName();


    //计时器
    private CountDownTimer countDownTimer = new CountDownTimer(5000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            btn_jump_index.setText("跳过(" + millisUntilFinished / 1000 + "s)");
        }

        @Override
        public void onFinish() {
            btn_jump_index.setText("跳过(" + 0 + "s)");
            goToActivity(getContext(), MainActivity.class);

        }
    };

    //开始倒计时
    private void startClock() {
        btn_jump_index.setVisibility(View.VISIBLE);
        countDownTimer.start();
    }

    private Button btn_jump_index;

    @Override
    protected void initConfig(View view) {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        btn_jump_index = (Button) view.findViewById(R.id.btn_jump_splashindex);
    }

    @Override
    protected void initEvent() {
        btn_jump_index.setOnClickListener(this);
        startClock();

    }

    @Override
    public int getContentView() {
        return R.layout.fragment_normalspalsh;
    }

    @Override
    protected void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();

    }

    @Override
    protected void onFragmentVisibleChange(boolean IsVisible) {
        super.onFragmentVisibleChange(IsVisible);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_jump_splashindex:
                if (countDownTimer != null) {
                    countDownTimer.cancel();
                }
                goToActivity(getContext(), MainActivity.class);
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
}
