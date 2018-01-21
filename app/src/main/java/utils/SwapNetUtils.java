package utils;

import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import api.GoodsAPI;
import api.UserAPI;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.cache.CacheInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by weij on 2017/12/23.
 */


public class SwapNetUtils {

    private final String TAG = "weijie";
    private static Retrofit retrofit = null;
    private static SwapNetUtils instance = null;

    //    private static final String IP = "172.168.5.72";
    private static String ip_config = "sdcard/Pictures/Swap_config.txt";
    private static String IP = "";
    private static String BASEURL = "";
    private static OkHttpClient mOkHttpClient;
    private static Cache cache;


    static {
        initIp();
        BASEURL = "http://" + IP + ":8080/";
        initOkHttpClient();
        LogUtils.d("weijie", "ip adress is : " + IP);
    }

    private static void initIp() {

        createFile(ip_config);
        IP = readFileByBytes(ip_config);
        LogUtils.d("weijie","ip地址："+IP);
    }

    public static boolean createFile(String destFileName) {
        File file = new File(destFileName);
        if (file.exists()) {
            LogUtils.d("weijie", "创建单个文件" + destFileName + "失败，目标文件已存在！");
            return false;
        }
        if (destFileName.endsWith(File.separator)) {
            LogUtils.d("weijie", "创建单个文件" + destFileName + "失败，目标文件不能为目录！");
            return false;
        }
        //判断目标文件所在的目录是否存在
        if (!file.getParentFile().exists()) {
            //如果目标文件所在的目录不存在，则创建父目录
            LogUtils.d("weijie", "目标文件所在目录不存在，准备创建它！");
            if (!file.getParentFile().mkdirs()) {
                System.out.println("创建目标文件所在目录失败！");
                return false;
            }
        }
        //创建目标文件
        try {
            if (file.createNewFile()) {
                LogUtils.d("weijie", "创建单个文件" + destFileName + "成功！");
                return true;
            } else {
                LogUtils.d("weijie", "创建单个文件" + destFileName + "失败！");
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            LogUtils.d("weijie", "创建单个文件" + destFileName + "失败！" + e.getMessage());
            return false;
        }
    }


    public static String readFileByBytes(String fileName) {

        StringBuilder sb  = new StringBuilder("");

        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            String tempbyte = null;
            while ((tempbyte =reader.readLine()) != null) {
                sb.append(tempbyte);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return sb.toString();
    }

    //初始化Retrofit
    public static <T> T createAPI(Class<T> cs) {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .client(mOkHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(BASEURL)
                    .build();
            LogUtils.d("weijie", "init Retrofit success");
            LogUtils.d("weijie", "BaseUrl:" + BASEURL);
        }
        return retrofit.create(cs);
    }


    /**
     * 初始化OkHttpClient
     */
    private static void initOkHttpClient() {
        if (mOkHttpClient == null) {
            synchronized (SwapNetUtils.class) {
                if (mOkHttpClient == null) {
                    File cacheFile = new File(Environment.getDownloadCacheDirectory(), "HttpCache");
                    cache = new Cache(cacheFile, 1024 * 1024 * 20);
                    mOkHttpClient = new OkHttpClient.Builder()
                            .cache(cache)
//                            .addNetworkInterceptor(new CacheInterceptor())
                            .retryOnConnectionFailure(true)
                            .connectTimeout(30, TimeUnit.SECONDS)
                            .writeTimeout(30, TimeUnit.SECONDS)
                            .readTimeout(20, TimeUnit.SECONDS)
                            .build();
                    LogUtils.d("weijie", "initOkHttpClient成功: ");
                }
            }
        }
    }


}
