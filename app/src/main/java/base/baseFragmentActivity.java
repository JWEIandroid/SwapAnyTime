package base;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.swapanytime.R;

/**
 * Created by weijie on 2017/9/26.
 */

public abstract class baseFragmentActivity extends FragmentActivity {

    private static final String TAG = baseFragmentActivity.class.getSimpleName();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != getIntent()) {
            handleIntent(getIntent());
        }
        if (getContentView() != null) {
            if (getContentView() instanceof Integer) {
                setContentView((Integer) getContentView());
            } else if (getContentView() instanceof View) {
                setContentView((View) getContentView());
            } else {
                View view = new View(this);
                view.setBackgroundColor(getResources().getColor(R.color.transparent));
                setContentView(view);
            }
        }

        SetStatusBarVisibilityGone();

    }


    //获取Intent
    protected void handleIntent(Intent intent) {
    }


    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        initView();
        initData();
        initEvent();
    }


    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        initView();
        initData();
        initEvent();
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract void initEvent();

    protected abstract Object getContentView();


    protected void showToast(String text, baseActivity.ToastDuration toastDuration) {
        switch (toastDuration) {
            case LONG:
                Toast.makeText(this, text, Toast.LENGTH_LONG).show();
                break;
            case SHORT:
                Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }


    protected void hideActionBar() {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
    }


    //透明状态栏
    protected boolean SetStatusBarVisibilityGone() {
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            return true;
        }
        return false;
    }

    protected void setTranstion() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
    }


    //沉浸模式
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        boolean Isneed = MyApplication.getImmersionModelStatus();
        if (Isneed && hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }


}
