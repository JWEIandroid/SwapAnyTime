package utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import java.util.ArrayList;
import java.util.List;

import base.MyApplication;
import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.finalteam.galleryfinal.model.PhotoInfo;

/**
 * Created by Administrator on 2017/11/10.
 */

public class MGalleryFinalUtils {


    private static  String TAG = "weijie";

    private static MGalleryFinalUtils galleryFinalUtils;
    private static ThemeConfig theme;
//    private static ImageLoader imageloader;
    private static GildeImageLoader imageloader;
    private static CoreConfig coreConfig;
    private static FunctionConfig functionConfig;
    private static FunctionConfig.Builder builder;
    private static Context context;
    private static List<PhotoInfo> mphotoList;


    private static final int REQUEST_CODE_OPENCAMERA = 1000;
    private static final int REQUEST_CODE_GALLERY = 1001;
    private static final int REQUEST_CODE_CROP = 1002;
    private static final int REQUEST_CODE_EDIT = 1003;


    private MGalleryFinalUtils() {}

    public static MGalleryFinalUtils getInstance(Context context) {

        MGalleryFinalUtils.context = context;
        mphotoList = new ArrayList<>();

        if (galleryFinalUtils == null) {
            galleryFinalUtils = new MGalleryFinalUtils();
        }
        return galleryFinalUtils;
    }




    /***************************************
     * 初始化galleryfinal
     */
    public static void initGalleryFinal() {

        //配置主题
        theme = new ThemeConfig.Builder()
                .build();
        //配置功能
        builder = new FunctionConfig.Builder();
        functionConfig = builder.setEnableCamera(true)
                .setEnableEdit(true)
                .setEnableCrop(true)
                .setEnableRotate(true)
                .setCropSquare(true)
                .setEnableCamera(true)
                .setEnablePreview(true)
                .setSelected(mphotoList)
                .setMutiSelectMaxSize(2)
                .build();


        //配置imageloader
        imageloader = new GildeImageLoader();
        //设置核心配置信息
        coreConfig = new CoreConfig.Builder(context, imageloader, theme)
                .setFunctionConfig(functionConfig)
                .build();
        GalleryFinal.init(coreConfig);


        initImageloader(context);

    }


    private static  void initImageloader(Context context){

        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.writeDebugLogs(); // Remove for release app

        // Initialize ImageLoader with configuration.
        com.nostra13.universalimageloader.core.ImageLoader.getInstance().init(config.build());

    }

    /*************************************
     * 打开相机
     */
    public static void openCamera() {
        GalleryFinal.openCamera(REQUEST_CODE_OPENCAMERA, functionConfig, mOnHanlderResultCallback);
    }

    /*************************************
     * 打开相册
     */
    public  static  void openAlbum(){
        GalleryFinal.openGalleryMuti(REQUEST_CODE_GALLERY,functionConfig,mOnHanlderResultCallback);
    }



    private static GalleryFinal.OnHanlderResultCallback mOnHanlderResultCallback = new GalleryFinal.OnHanlderResultCallback() {
        @Override
        public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
            if (resultList != null) {

            }

        }

        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {
            Toast.makeText(context, errorMsg, Toast.LENGTH_SHORT).show();
        }
    };




       public static  void clearCache(){
           GalleryFinal.cleanCacheFile();
           Toast.makeText(context,"清理成功",Toast.LENGTH_SHORT).show();
       }


}
