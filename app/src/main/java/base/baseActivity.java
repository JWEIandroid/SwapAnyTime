package base;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

/**
 * Created by weijie on 2017/9/22.
 */

public abstract class baseActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != getIntent()) {
            handleIntent(getIntent());
        }
    }

    //获取Intent
    protected void handleIntent(Intent intent) {
    }

    protected <T extends View> T findView(int id) {
        return (T) findViewById(id);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        initView();
        initData();
    }

    public abstract void initData();

    public abstract void initView();


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

    protected void goActivity(Class<?> cs, Bundle bundle) {
        Intent intent = new Intent(this, cs);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }


    protected void hideActionBar() {
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        //requestWindowFeature(Window.FEATURE_NO_TITLE)
    }


}
