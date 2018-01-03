package fragment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.swapanytime.MainActivity;
import com.example.swapanytime.R;

import base.baseFragment;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by weijie on 2017/9/24.
 */

public class SpalshIndex extends baseFragment {


    private static final String TAG = SpalshIndex.class.getSimpleName();
    @Bind(R.id.btn_jump_splashindex)
    Button btnJumpSplashindex;


    //计时器
    private CountDownTimer countDownTimer = new CountDownTimer(5000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            btn_jump_index.setText("跳过(" + millisUntilFinished / 1000 + "s)");
        }

        @Override
        public void onFinish() {
            btn_jump_index.setText("跳过(" + 0 + "s)");
            goToActivity(MainActivity.class);

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

    @OnClick(R.id.btn_jump_splashindex)
    public void onViewClicked() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        goToActivity( MainActivity.class);
    }
}
