package com.example.swapanytime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import adapter.MsgBoardApapter;
import base.baseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import entiry.Comment;
import entiry.MessageBoard;
import entiry.Msg;

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

    private List<Msg> msgdata = null;
    private Intent intent = null;
    private List<MessageBoard> messageBoardList = null;
    private List<Comment> commentList = null;
    private MsgBoardApapter msgBoardApapter = null;


    @Override
    public void initData() {

        //模拟数据
        msgdata = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                Msg msg = new Msg.Builder().type(0)
                        .content("我没有别的意思我没有别的意思我没有别的意思我没有别的意思我没有别的意思我没有别的意思我没有别的意思\n" +
                                "我没有别的意思我没有别的意思我没有别的意思我没有别的意思我没有别的意思我没有别的意思我没有别的意思\n" +
                                "我没有别的意思我没有别的意思我没有别的意思我没有别的意思我没有别的意思我没有别的意思我没有别的意思" + i)
                        .isLeft(1)
                        .build();
                msgdata.add(msg);
            } else {
                Msg msg = new Msg.Builder().type(0)
                        .content("傻逼你好啊你好啊你好啊你啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊" + i)
                        .isLeft(0)
                        .build();
                msgdata.add(msg);
            }
        }

        intent = getIntent();
        messageBoardList = intent.getParcelableArrayListExtra("messageboard");


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

        if (messageBoardList != null && messageBoardList.size() > 1) {
            msgBoardApapter = new MsgBoardApapter(MsgBoardActivity.this, msgdata, messageBoardList, null);
        } else if (commentList != null && commentList.size() > 1) {
            msgBoardApapter = new MsgBoardApapter(MsgBoardActivity.this, msgdata, messageBoardList, null);
        } else {
            msgBoardApapter = new MsgBoardApapter(MsgBoardActivity.this, msgdata, null, null);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MsgBoardActivity.this, LinearLayoutManager.VERTICAL, false);
        msgBoardRv.setLayoutManager(linearLayoutManager);
        msgBoardRv.setAdapter(msgBoardApapter);

    }

    @OnClick({R.id.ic_back, R.id.titlebar_title, R.id.msg_board_rv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ic_back:
                goActivity(MainActivity.class);
                break;
            case R.id.titlebar_title:
                break;
            case R.id.msg_board_rv:
                break;
        }
    }
}
