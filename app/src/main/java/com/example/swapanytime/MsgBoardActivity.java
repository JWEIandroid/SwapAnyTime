package com.example.swapanytime;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import adapter.MsgBoardApapter;
import api.MessageApi;
import base.baseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import entiry.Comment;
import entiry.HttpDefault;
import entiry.MessageBoard;
import entiry.Msg;
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
 * Created by weijie on 2017/12/5.
 */

public class MsgBoardActivity extends baseActivity {


    @Bind(R.id.ic_back)
    ImageView icBack;
    @Bind(R.id.titlebar_title)
    TextView titlebarTitle;
    @Bind(R.id.msg_board_rv)
    RecyclerView msgBoardRv;
    @Bind(R.id.msg_et)
    EditText msgEt;
    @Bind(R.id.btn_sendmsg)
    TextView btnSendmsg;

    private List<Msg> msgdata = new ArrayList<>();
    private Intent intent = null;
    private List<MessageBoard> messageBoardList = null;
    private List<Comment> commentList = null;
    private MsgBoardApapter msgBoardApapter = null;
    private User user = null;
    private User receiver = null;
    private int userid = 0;
    private int receiverid = 0;
    private int goodsid = 0;


    @Override
    public void initData() {

        intent = getIntent();
        receiverid = intent.getIntExtra("receiverid", -1);
        userid = intent.getIntExtra("userid", -1);
        messageBoardList = intent.getParcelableArrayListExtra("messageboard");

        if (intent!=null){
            user = intent.getParcelableExtra("user");
            receiver = intent.getParcelableExtra("shopper");
        }// TODO: 2018/3/7 处理空指针问题

        if (messageBoardList.size() > 0) {
            user = messageBoardList.get(0).getUser();
            receiver = messageBoardList.get(0).getReceiver();
        }

        if (receiverid == -1 || userid == -1 || messageBoardList == null || messageBoardList.size() <= 0) {
            //模拟数据
            msgdata = new ArrayList<>();
            messageBoardList = new ArrayList<>();
        }

    }

    @Override
    public void initView() {

        titlebarTitle.setText("消息");

    }

    @Override
    public Object getContentView() {
        return R.layout.activity_messageboard;
    }

    @Override
    public void initEvent() {

        if (messageBoardList != null && messageBoardList.size() >= 0) {
            msgBoardApapter = new MsgBoardApapter(MsgBoardActivity.this, msgdata, messageBoardList, null);
        } else if (commentList != null && commentList.size() >= 0) {
            msgBoardApapter = new MsgBoardApapter(MsgBoardActivity.this, msgdata, null, commentList);
        } else {
            msgBoardApapter = new MsgBoardApapter(MsgBoardActivity.this, msgdata, null, null);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MsgBoardActivity.this, LinearLayoutManager.VERTICAL, false);
        msgBoardRv.setLayoutManager(linearLayoutManager);
        msgBoardRv.setAdapter(msgBoardApapter);

    }

    @OnClick({R.id.ic_back, R.id.titlebar_title, R.id.msg_board_rv, R.id.btn_sendmsg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ic_back:
                goActivity(MainActivity.class);
                break;
            case R.id.titlebar_title:
                break;
            case R.id.msg_board_rv:
                break;
            case R.id.btn_sendmsg:

                if (TextUtils.isEmpty(msgEt.getText().toString())) {
                    showSnackBar("内容不能为空", ToastDuration.SHORT, btnSendmsg);
                } else {
                    sendMsg(msgEt.getText().toString());
                }
                break;
        }
    }

    //   发送信息
    private void sendMsg(String content) {


        if (messageBoardList != null && messageBoardList.size() >= 0) {

            MessageBoard messageboard = new MessageBoard.Builder()
                    .content(content)
                    .date(System.currentTimeMillis() + "")
                    .isLeft(1)
                    .userid(userid)
                    .receiverid(receiverid)
                    .user(user)
                    .Receiver(receiver)
                    .build();
            int positionstart = messageBoardList.size();
            messageBoardList.add(messageboard);
            msgBoardApapter.notifyItemInserted(positionstart);
            reportMsg(1, null, messageboard);
        } else if (commentList != null && commentList.size() >= 0) {

            Comment commment = new Comment.Builder()
                    .content(msgEt.getText().toString())
                    .date(System.currentTimeMillis() + "")
                    .isLeft(1)
                    .like(0)
                    .goodsid(goodsid)
                    .user(user)
                    .receiver(receiver)
                    .build();
            int positionstart_comment = commentList.size();
            commentList.add(commment);
            msgBoardApapter.notifyItemInserted(positionstart_comment);
            reportMsg(0, commment, null);

        } else {
            msgBoardApapter = new MsgBoardApapter(MsgBoardActivity.this, msgdata, null, null);
        }


    }


    private Observable<HttpDefault<String>> observable = null;

    //更新数据库
    private void reportMsg(int type, Comment comment, MessageBoard messageboard) {

        if (type == 0) {
            observable = SwapNetUtils.createAPI(MessageApi.class).insertOneCommment(
                    comment.getUserid(),
                    comment.getReceiverid(),
                    comment.getGoodsid(),
                    comment.getContent());
        } else if (type == 1) {
            observable = SwapNetUtils.createAPI(MessageApi.class).insertOneMessage(
                    messageboard.getUserid(),
                    messageboard.getReceiverid(),
                    0,
                    messageboard.getContent());
        }
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpDefault<String>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull HttpDefault<String> stringHttpDefault) {
                        showSnackBar(stringHttpDefault.getMessage(), ToastDuration.SHORT, btnSendmsg);
                        msgBoardRv.scrollToPosition(messageBoardList.size() - 1);
                        msgEt.setText("");

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        LogUtils.d("weijie", "更新评论/留言板数据库Error:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
