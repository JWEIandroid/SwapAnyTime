package com.example.swapanytime;


import android.os.Bundle;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.List;

import adapter.AlbumAdapter;
import base.baseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;

/**
 * Created by weijie on 2017/10/4.
 */

public class TakePhotoTest extends baseActivity {

    private final int REQUEST_CODE_CAMERA = 1000;
    private final int REQUEST_CODE_GALLERY = 1001;
    private final int REQUEST_CODE_CROP = 1002;
    private final int REQUEST_CODE_EDIT = 1003;

    @BindView(R.id.albumviewpager)
    ViewPager albumviewpager;

    private List<String> imageUrls;


    // TODO: 2017/10/6   功能：弹出Actionsheet,选择拍照或者打开相册
    // TODO: 2017/10/6   拍完照或者选完照片可以裁剪，旋转

    @Override
    public void initData() {
        imageUrls = new ArrayList<>();
        if (imageUrls.size() > 0) {
            AlbumAdapter albumAdapter = new AlbumAdapter(TakePhotoTest.this, imageUrls);
            albumviewpager.setAdapter(albumAdapter);
        }

    }

    @Override
    public void initView() {


    }

    @Override
    public Object getContentView() {
        return R.layout.activity_album;
    }

    @Override
    public void initEvent() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }


    private void openGallery() {
        GalleryFinal.openGallerySingle(REQUEST_CODE_GALLERY, new GalleryFinal.OnHanlderResultCallback() {
            @Override
            public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {


            }

            @Override
            public void onHanlderFailure(int requestCode, String errorMsg) {

            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (imageUrls != null) {
            imageUrls.clear();
        }
    }
}
