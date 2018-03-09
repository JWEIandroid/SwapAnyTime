package com.example.swapanytime;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import adapter.ShowLinkManAdapter;
import api.MessageApi;
import base.MyApplication;
import base.baseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import entiry.HttpDefault;
import entiry.MessageBoard;
import entiry.User;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import minterface.OnItemClickListener;
import utils.LogUtils;
import utils.SwapNetUtils;

/**
 * Created by weij on 2018/2/9.
 */

public class ShowLinkManMessasgeActivity extends baseActivity {


    @Bind(R.id.ic_back)
    ImageView icBack;
    @Bind(R.id.titlebar_title)
    TextView titlebarTitle;
    @Bind(R.id.rv_showlinkman)
    RecyclerView rvShowlinkman;

    private ShowLinkManAdapter showLinkManAdapter = null;
    private Intent intent = null;
    private List<User> user_data = null; //给当前用户留言或者评论的全部用户信息
    private int userid_login = -1; //当前用户ID

    @Override
    public void initData() {

        userid_login = MyApplication.getInstance().getLoginUserid(ShowLinkManMessasgeActivity.this);
        intent = getIntent();
        user_data = intent.getParcelableArrayListExtra("user");
        if (user_data == null || user_data.size() < 1) {
            return;
//            user_data = new ArrayList<>();
//            User user = new User.Builder().headimg("").name("try").build();
//            User user1 = new User.Builder().headimg("").name("catch").build();
//            user_data.add(user);
//            user_data.add(user1);
        }
        showLinkManAdapter = new ShowLinkManAdapter(ShowLinkManMessasgeActivity.this, user_data);
        showLinkManAdapter.addOnItemOnclickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                LogUtils.d("weijie", "click name" + user_data.get(position).getName());
                if (userid_login == -1) {
                    return;
                }
                RequestMessage(userid_login,user_data.get(position).getId());
            }
        });
        rvShowlinkman.setAdapter(showLinkManAdapter);

    }

    @Override
    public void initView() {
        titlebarTitle.setText("留言板");


    }

    private void RequestMessage(final int userid, final int receiverid) {

        Observable<HttpDefault<List<MessageBoard>>> observable = SwapNetUtils.createAPI(MessageApi.class).SelectAllMessage(userid, receiverid);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpDefault<List<MessageBoard>>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull HttpDefault<List<MessageBoard>> messageBoards) {

                        for (MessageBoard messageBoard : messageBoards.getData()) {
                            if (messageBoard.getUserid() == userid) {
                                messageBoard.setIsLeft(1);
                            } else {
                                messageBoard.setIsLeft(0);
                            }
                        }

                        Intent intent = new Intent(ShowLinkManMessasgeActivity.this, MsgBoardActivity.class);
                        intent.putExtra("receiverid",receiverid);
                        intent.putExtra("userid",userid);
                        intent.putParcelableArrayListExtra("messageboard", (ArrayList<? extends Parcelable>) messageBoards.getData());
                        startActivity(intent);


                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    @Override
    public Object getContentView() {
        return R.layout.activity_showlinkmanmsg;
    }

    @Override
    public void initEvent() {

    }


    @OnClick(R.id.ic_back)
    public void onViewClicked() {

        goActivity(MainActivity.class);


    }
}
