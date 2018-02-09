package fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.swapanytime.MainActivity;
import com.example.swapanytime.MsgBoardActivity;
import com.example.swapanytime.R;
import com.example.swapanytime.ShowLinkManMessasgeActivity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import base.baseFragment;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import entiry.User;

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
                List<User> user_data = new ArrayList<>();
                User user = new User.Builder().headimg("").name("try").build();
                User user1 = new User.Builder().headimg("").name("catch").build();
                user_data.add(user);
                user_data.add(user1);

                Intent intent = new Intent(this.getContext(),ShowLinkManMessasgeActivity.class);
                intent.putExtra("user",new Gson().toJson(user_data));
                startActivity(intent);
//                goToActivity(ShowLinkManMessasgeActivity.class);
                break;
            case R.id.comment:
                goToActivity(MsgBoardActivity.class);
                break;
        }
    }


}
