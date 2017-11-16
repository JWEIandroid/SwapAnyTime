package base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;

/**
 * Created by weijie on 2017/9/24.
 */

public abstract class baseFragment extends Fragment implements View.OnClickListener {


    private boolean isFragmentVisible;
    private boolean isReuseView;
    private boolean isFirstVisible;
    private View rootView;


    /**
     * setUserVisibleHint()在Fragment创建时会先被调用一次，传入isVisibleToUser = false
     * 如果当前Fragment可见，那么setUserVisibleHint()会再次被调用一次，传入isVisibleToUser = true
     * 如果Fragment从可见->不可见，那么setUserVisibleHint()也会被调用，传入isVisibleToUser = false
     *
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //setUserVisibleHint有可能在OncreaseView之前调用，需要确定view已经初始化
        if (rootView == null) {
            return;
        }
        if (isFirstVisible && isVisibleToUser) {
            onFragmentFirstVisible();
            isFirstVisible = false;
        }
        if (isVisibleToUser) {
            onFragmentVisibleChange(true);
            isVisibleToUser = false;
            return;
        }
        if (isFragmentVisible) {
            isFragmentVisible = false;
            onFragmentVisibleChange(false);
        }
    }

    protected void onFragmentFirstVisible() {

    }

    protected void onFragmentVisibleChange(boolean IsVisible) {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVariable();
    }

    private void initVariable() {
        isFirstVisible = true;
        isFragmentVisible = false;
        rootView = null;
        isReuseView = true;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = view;
            if (getUserVisibleHint()) {
                if (isFirstVisible) {
                    onFragmentFirstVisible();
                    isFirstVisible = false;
                }
                onFragmentVisibleChange(true);
                isFragmentVisible = true;
            }
        }
        super.onViewCreated(isReuseView ? rootView : view, savedInstanceState);
    }

    //设置是否开启view的复用，默认开启
    protected void reuseView(boolean isReuse) {
        isReuseView = isReuse;
    }

    protected boolean isFragmentVisible() {
        return isFragmentVisible;
    }

    protected enum ToastDuration {
        LONG, SHORT
    }

    protected void showToast(String text, ToastDuration toastDuration) {
        Context context = getContext();
        switch (toastDuration) {
            case LONG:
                Toast.makeText(context, text, Toast.LENGTH_LONG).show();
                break;
            case SHORT:
                Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    protected void showSnackBar(String msg, ToastDuration toastDuration){
        Context context = getContext();
        switch (toastDuration) {
            case LONG:
                Snackbar.make(rootView,msg,Snackbar.LENGTH_LONG).show();
                break;
            case SHORT:
                Snackbar.make(rootView,msg,Snackbar.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    protected abstract int getContentView();

    protected abstract void initView(View view);

    protected abstract void initData();

    protected abstract void initEvent();

    protected abstract void initConfig(View view);



    private static View mview = null;

    public View getmView() {
        return mview;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mview = inflater.inflate(getContentView(), container, false);
        initConfig(mview);
        initView(getmView());
        initData();
        initEvent();
        return mview;
    }

    public void goToActivity(Context context, Class<?> targetclass) {
        Intent intent = new Intent(context, targetclass);
        startActivity(intent);
    }


    protected  void  replaceFragment(int parent,Fragment fragment){

        FragmentTransaction transaction = this.getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(parent,fragment);
        transaction.commit();

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        initVariable();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}
