package base;

import android.app.Application;
import android.content.Context;

/**
 * Created by weijie on 2017/9/22.
 */

public class MyApplication extends Application {

    private static Context context = null;

    //应用默认关闭，后期保存本地
    private static boolean IsImmersionModelOpened = false;

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


}
