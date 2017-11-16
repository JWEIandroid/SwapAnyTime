package fragment;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baoyz.actionsheet.ActionSheet;
import com.example.swapanytime.MainActivity;
import com.example.swapanytime.R;

import base.baseFragment;
import butterknife.Bind;
import butterknife.ButterKnife;
import ui.CircleImageView;
import utils.MGalleryFinalUtils;

/**
 * Created by weijei on 2017/10/8.
 */

public class Main_mine extends baseFragment implements ActionSheet.ActionSheetListener{


    @Bind(R.id.ic_forward)
    ImageView icForward;
    @Bind(R.id.mine_head)
    CircleImageView mineHead;
    @Bind(R.id.mine_name)
    TextView mineName;
    @Bind(R.id.mine_desc)
    TextView mineDesc;
    @Bind(R.id.mine_titlebar)
    RelativeLayout mineTitlebar;
    @Bind(R.id.title_name)
    TextView titleName;
    @Bind(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @Bind(R.id.app_bar)
    AppBarLayout appBar;


    private static MGalleryFinalUtils instance = null;

    @Override
    protected int getContentView() {
        return R.layout.fragment_main_mine;
    }

    @Override
    protected void initConfig(View view) {
        ButterKnife.bind(this,view);
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initData() {

    }


    private BAR_STATUS bar_status;


    // 标题栏状态 : 展开,折叠,中间
    private enum BAR_STATUS {
        EXPANDED, COLLAPSED, INTERNEDIATE
    }

    @Override
    protected void initEvent() {


        //监听标题栏
        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (verticalOffset == 0) {
                    if (bar_status != BAR_STATUS.EXPANDED) {
                        bar_status = BAR_STATUS.EXPANDED;
                        mineTitlebar.setVisibility(View.VISIBLE);
                        titleName.setVisibility(View.GONE);
                    }
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    if (bar_status != BAR_STATUS.COLLAPSED) {
                        bar_status = BAR_STATUS.COLLAPSED;
                        mineTitlebar.setVisibility(View.GONE);
                        titleName.setVisibility(View.VISIBLE);
                    } else {
                        if (bar_status != BAR_STATUS.INTERNEDIATE) {
                            bar_status = BAR_STATUS.INTERNEDIATE;
                            mineTitlebar.setVisibility(View.GONE);
                            titleName.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
        });

        mineHead.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.mine_head:
                showActionsheet();
                break;

        }

    }



    private void showActionsheet() {

        ActionSheet.createBuilder(getActivity(), getActivity().getSupportFragmentManager())
                .setCancelButtonTitle("取消")
                .setOtherButtonTitles("拍照", "打开相册")
                .setCancelableOnTouchOutside(true)
                .setListener(this)
                .show();
    }

    @Override
    public void onDismiss(ActionSheet actionSheet, boolean isCancel) {

    }

    @Override
    public void onOtherButtonClick(ActionSheet actionSheet, int index) {

        if (instance==null){
            instance = MGalleryFinalUtils.getInstance(getActivity());
        }

        switch (index) {
            case 0:
                instance.initGalleryFinal();
                instance.openCamera();
                break;
            case 1:
                instance.initGalleryFinal();
                instance.openAlbum();
                break;
            default:
                break;
        }
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
