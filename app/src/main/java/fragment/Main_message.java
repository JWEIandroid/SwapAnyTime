package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.swapanytime.MainActivity;
import com.example.swapanytime.MsgBoardActivity;
import com.example.swapanytime.R;
import com.example.swapanytime.ShowLinkManMessasgeActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import api.MessageApi;
import base.MyApplication;
import base.baseFragment;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import entiry.Comment;
import entiry.HttpDefault;
import entiry.User;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import utils.LogUtils;
import utils.SwapNetUtils;

/**
 * Created by dell on 2017/10/8.
 */

public class Main_message extends baseFragment {


    @Bind(R.id.ic_back)
    ImageView icBack;
    @Bind(R.id.titlebar_title)
    TextView titlebarTitle;
    @Bind(R.id.liuyanban)
    FrameLayout liuyanban;
    @Bind(R.id.comment)
    FrameLayout comment;
    @Bind(R.id.item_head)
    ImageView itemHead;
    @Bind(R.id.item_comment)
    ImageView itemComment;

    //已经登录的用户ID
    private int userid_login = -1;
    //请求到的用户信息
    private List<User> userList = null;

    @Override
    protected int getContentView() {
        return R.layout.fragment_main_message;
    }

    @Override
    protected void initConfig(View view) {

    }

    @Override
    protected void initView(View view) {

        icBack.setVisibility(View.GONE);
        titlebarTitle.setText("消息");

        Glide.with(this.getContext())
                .load(R.mipmap.messageboard)
                .asBitmap()
                .error(R.mipmap.pic_error)
                .centerCrop()
                .placeholder(R.mipmap.pic_loading)
                .into(itemHead);

        Glide.with(this.getContext())
                .load(R.mipmap.ic_msg_fragment)
                .asBitmap()
                .error(R.mipmap.pic_error)
                .fitCenter()
                .placeholder(R.mipmap.pic_loading)
                .into(itemComment);

    }

    @Override
    protected void initData() {

        //从配置文件读取登录过的用户信息
        userid_login = MyApplication.getInstance().getLoginUserid(this.getContext());
    }

    @Override
    protected void initEvent() {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.liuyanban, R.id.comment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.liuyanban:

                //测试数据
//                List<User> user_data = new ArrayList<>();
//                User user = new User.Builder().headimg("").name("try").build();
//                User user1 = new User.Builder().headimg("").name("catch").build();
//                user_data.add(user);
//                user_data.add(user1);
//                Intent intent = new Intent(this.getContext(), ShowLinkManMessasgeActivity.class);
//                intent.putParcelableArrayListExtra("user", (ArrayList<? extends Parcelable>) user_data);
//                startActivity(intent);
//                goToActivity(ShowLinkManMessasgeActivity.class);
                RequestMessage(userid_login);
                break;
            case R.id.comment:
                RequestComment(userid_login);
//                goToActivity(MsgBoardActivity.class);
                break;
        }
    }


    private void RequestMessage(int receiverid) {

        Observable<HttpDefault<List<User>>> observable = SwapNetUtils.createAPI(MessageApi.class).getUsersFromReceiverid(receiverid);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpDefault<List<User>>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull HttpDefault<List<User>> listHttpDefault) {

                        userList = listHttpDefault.getData();

                        if (listHttpDefault.getError_code() == 0 && listHttpDefault.getData().size() >= 1) {
                            Intent intent = new Intent(getContext(), ShowLinkManMessasgeActivity.class);
                            intent.putParcelableArrayListExtra("user", (ArrayList<? extends Parcelable>) userList);
                            startActivity(intent);
                        }
                        Toast.makeText(getContext(), listHttpDefault.getMessage(), Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        LogUtils.d("weijie", "留言板请求用户信息错误:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    private void RequestComment(int receiverid) {

        Observable<HttpDefault<List<Integer>>> observable = SwapNetUtils.createAPI(MessageApi.class).SelectGoodsIdByReceiverId(receiverid);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpDefault<List<Integer>>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull HttpDefault<List<Integer>> listHttpDefault) {

                        if (listHttpDefault.getData().size() > 0) {

                            for (int goodsid : listHttpDefault.getData()) {
                                requestCommentData(goodsid);
                            }

                        }

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        showSnackBar(e.getMessage(), ToastDuration.SHORT.SHORT);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void requestCommentData(int goodsid) {

        Observable<HttpDefault<List<Comment>>> observable = SwapNetUtils.createAPI(MessageApi.class).SelectAllCommment(goodsid);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpDefault<List<Comment>>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull HttpDefault<List<Comment>> listHttpDefault) {



                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        showSnackBar(e.getMessage(),ToastDuration.SHORT);
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

}
