package base;

import android.app.Application;
import android.content.Context;

/**
 * Created by weijie on 2017/9/22.
 */

public class MyApplication extends Application {

    private static Context context = null;

    //沉浸模式默认关闭，后期保存本地
    private static boolean IsImmersionModelOpened = false;
    //是否第一次进入应用，不等于1就开启引导页,后期保存本地
    private static boolean IsFirstTimeIn = false;


    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }


    public static Context getContext() {
        return context;
    }


    //沉浸模式开关
    public static boolean OpenImmersionModel() {
        if (IsImmersionModelOpened == false) {
            IsImmersionModelOpened = true;
        }
        return IsImmersionModelOpened;
    }

    public static boolean CloseImmersionModel() {
        if (IsImmersionModelOpened == true) {
            IsImmersionModelOpened = false;
        }
        return IsImmersionModelOpened;
    }


    public static boolean getImmersionModelStatus() {
        return IsImmersionModelOpened;
    }


    //是否第一次进入应用
    public static boolean QueryIsFirstTimeInApp() {
        return IsFirstTimeIn;
    }


}
