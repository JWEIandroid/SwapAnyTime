package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.swapanytime.R;

import java.util.List;

import entiry.Comment;
import entiry.MessageBoard;
import entiry.Msg;
import ui.CircleImageView;
import utils.LogUtils;


/**
 * Created by weijie on 2017/12/5.
 */

public class MsgBoardApapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context context;
    private List<Msg> list;

    private List<MessageBoard> messageBoardList = null;
    private List<Comment> commentList = null;
    private int SHOW_TYPE_COMMENT = 0X1000;
    private int SHOW_TYPE_MESSAGEBOARD = 0X1001;

    private static final int TYPE_LEFT = 10000;
    private static final int TYPE_RIGHT = 10001;

    public MsgBoardApapter(Context context, List<Msg> list, List<MessageBoard> messageBoardList, List<Comment> commentList) {
        this.context = context;
        this.list = list;
        this.messageBoardList = messageBoardList;
        this.commentList = commentList;
    }

    @Override
    public int getItemViewType(int position) {

        int isleft = -1;

        if (commentList != null) {
            isleft = commentList.get(position).getIsLeft();
        } else if (messageBoardList != null) {
            isleft = messageBoardList.get(position).getIsLeft();
        } else {
            isleft = list.get(position).getIsLeft();
        }
        switch (isleft) {

            case 0:
                return TYPE_LEFT;
            case 1:
                return TYPE_RIGHT;
        }
        return 0x1002;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {

            case TYPE_LEFT:
                View view = LayoutInflater.from(context).inflate(R.layout.msg_board_item_left, parent, false);
                LeftUserHolder leftUserHolder = new LeftUserHolder(view);
                return leftUserHolder;
            case TYPE_RIGHT:
                View view1 = LayoutInflater.from(context).inflate(R.layout.msg_board_item_right, parent, false);
                RightUserHolder rightUserHolder = new RightUserHolder(view1);
                return rightUserHolder;

        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof LeftUserHolder) {

            if (commentList != null) {
                Glide.with(context).load(R.mipmap.coolman).asBitmap().centerCrop().into(((LeftUserHolder) holder).leftHead);
                ((LeftUserHolder) holder).content_lf.setText(commentList.get(position).getContent());
            } else if (messageBoardList != null) {
                Glide.with(context).load(R.mipmap.coolman).asBitmap().centerCrop().into(((LeftUserHolder) holder).leftHead);
                ((LeftUserHolder) holder).content_lf.setText(messageBoardList.get(position).getContent());
            } else {
                Glide.with(context).load(R.mipmap.coolman).asBitmap().centerCrop().into(((LeftUserHolder) holder).leftHead);
                ((LeftUserHolder) holder).content_lf.setText(list.get(position).getContent());
            }

        } else if (holder instanceof RightUserHolder) {

            if (commentList != null) {
                Glide.with(context).load(R.mipmap.banner1).asBitmap().centerCrop().into(((RightUserHolder) holder).rightHead);
                ((RightUserHolder) holder).content_rh.setText(commentList.get(position).getContent());
            } else if (messageBoardList != null) {
                Glide.with(context).load(R.mipmap.banner1).asBitmap().centerCrop().into(((RightUserHolder) holder).rightHead);
                ((RightUserHolder) holder).content_rh.setText(messageBoardList.get(position).getContent());
            } else {
                Glide.with(context).load(R.mipmap.banner1).asBitmap().centerCrop().into(((RightUserHolder) holder).rightHead);
                ((RightUserHolder) holder).content_rh.setText(list.get(position).getContent());
            }
        }

    }


    @Override
    public int getItemCount() {

        if (messageBoardList != null) {
            return messageBoardList.size();
        } else if (commentList != null) {
            return commentList.size();
        } else {
            return list.size();
        }
    }

    public class LeftUserHolder extends RecyclerView.ViewHolder {

        private CircleImageView leftHead;
        private TextView content_lf;

        public LeftUserHolder(View itemView) {
            super(itemView);
            leftHead = (CircleImageView) itemView.findViewById(R.id.msg_board_user_head_left);
            content_lf = (TextView) itemView.findViewById(R.id.msg_board_content_left);
        }
    }

    public class RightUserHolder extends RecyclerView.ViewHolder {

        private CircleImageView rightHead;
        private TextView content_rh;

        public RightUserHolder(View itemView) {
            super(itemView);
            rightHead = (CircleImageView) itemView.findViewById(R.id.msg_board_user_head_right);
            content_rh = (TextView) itemView.findViewById(R.id.msg_board_content_right);
        }
    }

}
