package fragment;

import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;

import com.example.swapanytime.MainActivity;
import com.example.swapanytime.R;

import base.baseFragment;
import utils.LogUtils;

/**
 * Created by weijie on 2017/9/24.
 */

public class SpalshStart extends baseFragment {

    private final String TAG = SpalshStart.class.getSimpleName();


    @Override
    public int getContentView() {
        return R.layout.fragment_splashstart;
    }

    @Override
    protected void initConfig(View view) {

    }

    @Override
    protected void initView(View view) {

        btn_jump = (Button) getmView().findViewById(R.id.btn_jump);

        countDownTimer = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                btn_jump.setText("跳过(" + millisUntilFinished / 1000 + "s)");
            }

            @Override
            public void onFinish() {
                btn_jump.setText("跳过(" + 0 + "s)");
                goToActivity(getContext(), MainActivity.class);

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


    private Button btn_jump;

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
                LogUtils.d(TAG, "Click");
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
