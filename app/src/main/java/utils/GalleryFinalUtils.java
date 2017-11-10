package utils;

import android.content.Context;

import cn.finalteam.galleryfinal.CoreConfig;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.ImageLoader;
import cn.finalteam.galleryfinal.ThemeConfig;

/**
 * Created by Administrator on 2017/11/10.
 */

public class GalleryFinalUtils {


    private GalleryFinal galleryFinal;

    private GalleryFinalUtils() {
    }

    public GalleryFinal getInstance() {
        if (galleryFinal == null) {
            galleryFinal = new GalleryFinal();
        }
        return galleryFinal;
    }

    //初始化


    public void init(Context context, ImageLoader imageLoader, ThemeConfig themeConfig) {

       CoreConfig config = new CoreConfig.Builder(context,imageLoader,themeConfig)
               .setFunctionConfig(null)
               .setPauseOnScrollListener(null)
               .setNoAnimcation(false)
               .build();
       galleryFinal.init(config);


    }



}
