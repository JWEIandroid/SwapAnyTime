package base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.swapanytime.LoginActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.ThemeConfig;
import entiry.User;
import minterface.LoginListener;
import utils.GildeImageLoader;
import utils.LogUtils;

/**
 * Created by weijie on 2017/9/22.
 */

public class MyApplication extends Application {

    private static Context context = null;

    private static MyApplication myApplication;

    private List<Activity> activities = new ArrayList<Activity>();


    public static MyApplication getInstance() {

        if (myApplication == null) {
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
        OpenImmersionModel();
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


    //登录超时时间  单位：分钟
    private static final long LOGIN_TIMEOUT = 30;
    //从本地数据读取的token id headimg;
    private static String token_read = null;
    private static int userid_read = 0;
    private String user_name_read = null; //已经登录的用户的名字
    private String user_adress_read = null; //已经登录的用户的地址
    private String user_tel_read = null; //已经登录的用户的电话
    private String user_headimg_read = null; //已经登录的用户的头像地址
    private int userid_login = -1;     //已经登录的用户ID


    public int getLoginUserid(Context context1) {

        checkIsLogin(null, context1);
        return userid_login;
    }

    public Map<String, String> getLoginedUserData(Context context1) {
        checkIsLogin(null, context1);
        Map<String, String> result = new HashMap<>();
        if (user_name_read == null) {
            user_name_read = "error";
        }
        if (user_headimg_read == null) {
            user_headimg_read = "file/download/?filename=nodata.png&type=0";
        }
        if (user_adress_read == null) {
            user_adress_read = "error";
        }
        if (user_tel_read == null) {
            user_tel_read = " tel error";
        }
        result.put("username", user_name_read);
        result.put("headimg", user_headimg_read);
        result.put("adress", user_adress_read);
        result.put("tel", user_tel_read);
        return result;

    }

    //检查登陆状态
    public boolean checkIsLogin(LoginListener loginListener, Context context) {

        userid_login = -1;

        //检查是否存在本地数据
        SharedPreferences sharedPreferences = context.getSharedPreferences("base64", MODE_PRIVATE);
        token_read = sharedPreferences.getString("token", null);
        String userid_data = sharedPreferences.getString("userid", null);
        String user_name = sharedPreferences.getString("username", null);
        String user_headimg = sharedPreferences.getString("headimg", null);
        String user_adress = sharedPreferences.getString("adress", null);
        String user_tel = sharedPreferences.getString("tel", null);
        if (userid_read == 0 & token_read == null) {
            loginListener.not_login("你还没有登录");
            return false;
        }
        if (userid_data != null) {
            userid_read = Integer.parseInt(sharedPreferences.getString("userid", null));
        }

        LogUtils.d("weijie", "本地登录信息：" + "token:" + token_read
                + "\n" + "userid:" + userid_read);
        //检查登录信息是否过期
        if (checktoken(token_read)) {
            //如果Token没有过期，读取本地文件的用户ID
            userid_login = userid_read;

            if (user_name != null) {
                user_name_read = user_name;
            }
            if (user_headimg != null) {
                user_headimg_read = user_headimg;
            }
            if (user_adress != null) {
                user_adress_read = user_adress;
            }
            if (user_tel != null) {
                user_tel_read = user_tel;
            }

            return true;
        } else {
            //token过期
            userid_login = -1;
            user_name = null;
            user_headimg = null;
            user_adress = null;
            user_tel = null;
            return false;
        }

    }


    /**
     * 检查token是否过期
     *
     * @param token
     * @return
     */
    private boolean checktoken(String token) {

        long now = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder(token);
        long time = Long.parseLong(sb.substring(sb.length() - 13, sb.length()));
        LogUtils.d("weijie", "token :" + time);
        long pasttime = (now - time) / (60 * 1000L);
        LogUtils.d("weijie", "token还有：" + (LOGIN_TIMEOUT - pasttime) + "分钟过期");

        if (pasttime >= LOGIN_TIMEOUT) {
            LogUtils.d("weijie", "token 过期");
            return false;
        }
        return true;
    }


    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public void finishActivity(Activity activity) {
        if (activity != null) {
            activities.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    public void exit() {
        for (Activity activity : activities)
            if (activity != null) {
                activity.finish();
            }
        System.exit(0);
    }


    /**
     * 退出应用
     */
    public void finishActivities() {
        for (Activity activity : activities) {
            if (activity != null) {
                activity.finish();
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
    }


}
