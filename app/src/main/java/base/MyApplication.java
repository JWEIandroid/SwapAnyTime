package base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.ThemeConfig;
import utils.GildeImageLoader;

/**
 * Created by weijie on 2017/9/22.
 */

public class MyApplication extends Application {

    private static Context context = null;

    private static  MyApplication myApplication;

    private List<Activity> activities = new ArrayList<Activity>();


    public static MyApplication getInstance(){

        if (myApplication==null){
            myApplication = new MyApplication();
        }

        return myApplication;
    }


    //沉浸模式默认关闭
    // TODO: 2017/10/8  sharepreference保存 沉默模式状态，是否第一次进入应用
    private static boolean IsImmersionModelOpened = false;
    //是否第一次进入应用，不等于1就开启引导页
    private static boolean IsFirstTimeIn = true;


    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        initGalleryFinal();
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


    /***************************************
     * 初始化galleryfinal
     */
    private void initGalleryFinal() {

        //配置主题
        ThemeConfig theme = new ThemeConfig.Builder()
                .build();
        //配置功能
        FunctionConfig functionConfig = new FunctionConfig.Builder()
                .setEnableCamera(true)
                .setEnableEdit(true)
                .setEnableCrop(true)
                .setEnableRotate(true)
                .setCropSquare(true)
                .setEnablePreview(true)
                .build();

       //配置imageloader
        ImageLoader imageloader = new GildeImageLoader();
        //设置核心配置信息
        CoreConfig coreConfig = new CoreConfig.Builder(context, imageloader, theme)
                .setFunctionConfig(functionConfig)
                .build();
        GalleryFinal.init(coreConfig);

    }




    public void addActivity(Activity activity){
        activities.add(activity);
    }

    public void finishActivity(Activity activity){
        if (activity!=null){
            activities.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    public void exit(){
        for (Activity activity:activities)
          if (activity!=null){
              activity.finish();
          }
          System.exit(0);
    }

    public void finishActivities(){
        for (Activity activity:activities){
            if (activity!=null){
                activity.finish();
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
    }






}
