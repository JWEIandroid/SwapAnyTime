package base;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.swapanytime.R;

import butterknife.ButterKnife;

/**
 * Created by weijie on 2017/9/22.
 */

public abstract class baseActivity extends AppCompatActivity {

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
        ButterKnife.bind(this);
        initView();
        initData();
        initEvent();


        /**
         * 隐藏标题栏
         * 设置状态栏透明
         */
        hideActionBar();
        SetStatusBarVisibilityGone();

    }

    //获取Intent
    protected void handleIntent(Intent intent) {
    }

    protected <T extends View> T findView(int id) {
        return (T) findViewById(id);
    }

    public abstract void initData();

    public abstract void initView();

    public abstract Object getContentView();

    public abstract void initEvent();


    protected enum ToastDuration {
        LONG, SHORT
    }

    protected void showToast(String text, ToastDuration toastDuration) {
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


    protected void goActivity(Class<?> cs) {
        Intent intent = new Intent(this, cs);
        startActivity(intent);
    }

    public String getTAG(){
        return this.getClass().getSimpleName();
    }

    protected void goActivity(Class<?> cs, Bundle bundle) {
        Intent intent = new Intent(this, cs);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }


    protected void hideActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

//        requestWindowFeature(Window.FEATURE_NO_TITLE);
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
