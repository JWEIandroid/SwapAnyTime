package utils;

import android.util.TimeUtils;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import api.UserLogin;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by weij on 2017/12/23.
 */


public class SwapNetUtils {

    private final String TAG = "weijie";
    private Retrofit retrofit = null;
    private static SwapNetUtils instance = null;
    private Map<String,Object> map_netparams = null;

    private final String BASEURL = "http://localhost:8080/";
    //请求操作类型
    private enum REQUSET_TYPE {
        FILE, NORMAL
    }

    private final long DEFAULT_TIMEOUT = 15 * 1000L;

    private SwapNetUtils() {
        initRetrofit();
    }

    public SwapNetUtils getInstance() {
        if (instance == null) {
            instance = new SwapNetUtils();
        }
        return instance;
    }


    //初始化Retrofit
    protected Retrofit initRetrofit() {

        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);


        LogUtils.d("weijie", "init Retrofit...");
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .client(httpClientBuilder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(BASEURL)
                    .build();
            LogUtils.d("weijie", "init Retrofit success");
        }
        return  retrofit;
    }

    //在主线程中更新UI
    public static boolean requestInMainThread(Class<?> cs) {

        boolean res = true;


        return res;


    }


    protected boolean params(String key,Object data){

        if (map_netparams==null){
            return  false;
        }
        map_netparams.put(key, data);
        return  true;
    }

    protected void execute(){

        //遍历请求表
        for(map_netparams.keySet():map_netparams){

        }



    }


}
