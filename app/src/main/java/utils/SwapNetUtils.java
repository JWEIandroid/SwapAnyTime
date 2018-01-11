package utils;

import android.os.Environment;

import java.io.File;
import java.io.IOException;
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

    private static final String IP = "172.168.5.72";
//    private static final String IP = "192.168.218.79";
    private static final String BASEURL = "http://" + IP + ":8080/";
    private static OkHttpClient mOkHttpClient;
    private static Cache cache;


    static {
        initOkHttpClient();
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
                    cache = new Cache(cacheFile, 1024 * 1024 * 10);
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
