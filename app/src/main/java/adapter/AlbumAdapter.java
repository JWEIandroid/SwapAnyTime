package adapter;

import android.app.Activity;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.finalteam.galleryfinal.widget.zoonview.PhotoView;
import cn.finalteam.galleryfinal.widget.zoonview.PhotoViewAttacher;

import static cn.bmob.v3.Bmob.getApplicationContext;

/**
 * Created by weijie on 2017/10/7.
 */


public class AlbumAdapter extends PagerAdapter {

    public AlbumAdapter(Activity activity, List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    private  List<String> imageUrls;
    private  Activity activity;


    @Override
    public int getCount() {
        return imageUrls.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public View instantiateItem(ViewGroup container, int position) {
        PhotoView photoView = new PhotoView(container.getContext());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            photoView.setTransitionName("image");
        }
        container.addView(photoView, ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.MATCH_PARENT);
        Glide.with(getApplicationContext()).load(imageUrls.get(position)).into(photoView);
        photoView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float x, float y) {
                activity.finish();
            }
        });
        return photoView;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
