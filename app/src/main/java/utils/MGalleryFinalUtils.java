package utils;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.widget.Toast;

import java.util.List;

import base.MyApplication;
import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.FunctionConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.ThemeConfig;
import cn.finalteam.galleryfinal.model.PhotoInfo;

/**
 * Created by Administrator on 2017/11/10.
 */

public class MGalleryFinalUtils {


    private static MGalleryFinalUtils galleryFinalUtils;
    private static ThemeConfig theme;
//    private static ImageLoader imageloader;
    private static  UILImageLoader imageloader;
    private static CoreConfig coreConfig;
    private static FunctionConfig functionConfig;
    private static Context context;


    private static final int REQUEST_CODE_OPENCAMERA = 1000;
    private static final int REQUEST_CODE_GALLERY = 1001;
    private static final int REQUEST_CODE_CROP = 1002;
    private static final int REQUEST_CODE_EDIT = 1003;


    private MGalleryFinalUtils() {}

    public static MGalleryFinalUtils getInstance() {
        if (galleryFinalUtils == null) {
            galleryFinalUtils = new MGalleryFinalUtils();
        }
        return galleryFinalUtils;
    }

    //初始化

    private void initImageLoader(){

        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context);

    }


    /***************************************
     * 初始化galleryfinal
     */
    public static void initGalleryFinal(Context mcontext) {

        //配置主题
        theme = new ThemeConfig.Builder()
                .build();
        //配置功能
        functionConfig = new FunctionConfig.Builder()
                .setEnableCamera(true)
                .setEnableEdit(true)
                .setEnableCrop(true)
                .setEnableRotate(true)
                .setCropSquare(true)
                .setEnablePreview(true)
                .build();

        //配置imageloader
        imageloader = new UILImageLoader();
        //设置核心配置信息
        coreConfig = new CoreConfig.Builder(mcontext, imageloader, theme)
                .setFunctionConfig(functionConfig)
                .build();
        GalleryFinal.init(coreConfig);

    }


    /*************************************
     * 打开相机
     */
    public static void openCamera() {
        GalleryFinal.openCamera(REQUEST_CODE_OPENCAMERA, functionConfig, mHandlerResultCallback);
    }

    /*************************************
     * 打开相册
     */
    public  static  void openAlbum(){
        GalleryFinal.openGalleryMuti(REQUEST_CODE_GALLERY,functionConfig,mHandlerResultCallback);
    }



    private static GalleryFinal.OnHanlderResultCallback mHandlerResultCallback = new GalleryFinal.OnHanlderResultCallback() {
        @Override
        public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {

            switch (reqeustCode) {
                case REQUEST_CODE_OPENCAMERA:


                    break;
            }

        }

        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {
            Toast.makeText(MyApplication.getContext(), errorMsg, Toast.LENGTH_SHORT).show();
        }

    };


    private void initImageLoader(Context context){

        

    }


}
