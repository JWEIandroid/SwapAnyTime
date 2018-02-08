package adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.swapanytime.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import api.MessageApi;
import base.MyApplication;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import entiry.Comment;
import entiry.Goods;
import entiry.HttpDefault;
import entiry.User;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import minterface.LoginListener;
import ui.CircleImageView;
import utils.DialogUtil;
import utils.LogUtils;
import utils.SwapNetUtils;

/**
 * Created by weijie on 2017/11/29.
 */

public class GoodsDetail_imgAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<String> list;
    private Context context;
    private List<Comment> comments;
    private CommentAdapter commentAdapter = null;

    //开始显示三行格式图片的位置
    private int middle = 0;

    private Goods goods;
    private Dialog dialog = null; //底部评论Dialog


    private static final int TYPE_GOOD_TITLE = 1000;
    private static final int TYPE_ONELINE = 1001;
    private static final int TYPE_THREE = 1002;
    private static final int TYPE_USE = 1003;
    private static final int TYPE_COMMENT = 1004;


    public GoodsDetail_imgAdapter(Goods goods, List<String> list, List<Comment> comments, Context context) {
        this.list = list;
        this.context = context;
        middle = list.size() / 2;
        this.goods = goods;
        this.comments = comments;

    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_GOOD_TITLE;
        } else if (position == list.size() + 1) {
            return TYPE_USE;
        } else if (position == list.size() + 2) {
            return TYPE_COMMENT;
        } else if (position <= middle + 1) {
            return TYPE_ONELINE;
        }
        return TYPE_THREE;
    }

    @Override
    public int getItemCount() {
        return list.size() + 3;
    }


    @Override
    public void onAttachedToRecyclerView(final RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 4);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (getItemViewType(position)) {

                    case TYPE_USE:
                    case TYPE_ONELINE:
                    case TYPE_COMMENT:
                        return 4;
                    case TYPE_THREE:
                        return 1;
                }
                return 2;
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = null;

        switch (viewType) {

            case TYPE_GOOD_TITLE:
                view = LayoutInflater.from(context).inflate(R.layout.gooddetailadapter_item_title, parent, false);
                GoodMsgHolder goodMsgHolder = new GoodMsgHolder(view);
                return goodMsgHolder;
            case TYPE_ONELINE:
                view = LayoutInflater.from(context).inflate(R.layout.view_gooddetailimg_item, parent, false);
                NormalImgHolder normalImgHolder = new NormalImgHolder(view);
                return normalImgHolder;
            case TYPE_THREE:
                view = LayoutInflater.from(context).inflate(R.layout.adapter_photo_list_item, parent, false);
                threeLineImgHolder threeLineImgHolder = new threeLineImgHolder(view);
                return threeLineImgHolder;
            case TYPE_USE:
                view = LayoutInflater.from(context).inflate(R.layout.gooddetailadapter_item_user, parent, false);
                UserMsgHolder userMsgHolder = new UserMsgHolder(view);
                return userMsgHolder;
            case TYPE_COMMENT:
                view = LayoutInflater.from(context).inflate(R.layout.gooddetailadapter_comment, parent, false);
                CommentHolder commentHolder = new CommentHolder(view);
                return commentHolder;
            default:
                return null;
        }


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        if (holder instanceof GoodsDetail_imgAdapter.NormalImgHolder) {

            Glide.with(context).load(SwapNetUtils.getBaseURL() + list.get(position - 1)).fitCenter().into(((NormalImgHolder) holder).imageView);

        } else if (holder instanceof GoodsDetail_imgAdapter.threeLineImgHolder) {

            Glide.with(context).load(SwapNetUtils.getBaseURL() + list.get(position - 1)).fitCenter().into(((threeLineImgHolder) holder).imageView);
        } else if (holder instanceof GoodsDetail_imgAdapter.GoodMsgHolder) {

            ((GoodMsgHolder) holder).goodsdetail_desc.setText((String) goods.getDescription());

        } else if (holder instanceof GoodsDetail_imgAdapter.UserMsgHolder) {

            Glide.with(context).load(SwapNetUtils.getBaseURL() + goods.getUser().getHeadimg()).asBitmap().fitCenter().into(((UserMsgHolder) holder).head);
            ((UserMsgHolder) holder).name.setText(goods.getUser().getName());

        } else if (holder instanceof GoodsDetail_imgAdapter.CommentHolder) {

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            commentAdapter = new CommentAdapter(context, comments);
            ((CommentHolder) holder).comment_rv.setLayoutManager(linearLayoutManager);
            ((CommentHolder) holder).comment_rv.setAdapter(commentAdapter);

//            //打开评论界面
            ((CommentHolder) holder).btn_comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //打开底部评论框
                    ShowBottomCommentDialog();
                }
            });

        }


    }


    //打开底部评论框
    private void ShowBottomCommentDialog() {

        this.dialog = DialogUtil.showBottomDialog(R.layout.layout_comment_erea, R.style.ActionButtomDialogStyle, context, new DialogUtil.IDialogInitListener() {
            @Override
            public void initDialogView(final View view) {
                TextView btn_send = (TextView) view.findViewById(R.id.btn_sendcomment);
                final EditText comment_et = (EditText) view.findViewById(R.id.comment_et);
                btn_send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (TextUtils.isEmpty(comment_et.getText().toString())) {
                            Snackbar.make(view, "评论不能为空", Snackbar.LENGTH_SHORT).show();
                            return;
                        } else {

//                            User user = new User.Builder()
//                                    .name("草尼玛")
//                                    .headimg("https://www.baidu.com/s?rsv_idx=1&wd=github&ie=utf-8&rsv_cq=recycleview+%E5%B5%8C%E5%A5%97recycleview&rsv_dl=0_right_recommends_merge_28335&euri=3366456")
//                                    .build();
//                            Comment comment = new Comment.Builder()
//                                    .goodsid(116)
//                                    .receiverid(46)
//                                    .userid(46)
//                                    .user(user)
//                                    .date(System.currentTimeMillis() + "")
//                                    .content(comment_et.getText().toString())
//                                    .like(100)
//                                    .build();
                            Comment comment = new Comment.Builder()
                                    .userid(goods.getUser().getId())
                                    .goodsid(goods.getId())
                                    .receiverid(goods.getUser().getId())
                                    .content(comment_et.getText().toString())
                                    .date(System.currentTimeMillis()+"")
                                    .user(goods.getUser())
                                    .like(0)
                                    .build();
                            insertComment(comment);
                            //如果更新数据库成功，插入新数据更新UI,去掉第一条伪数据
//                            int positionstart = comments.size();
//                            comments.add(comment);
//                            commentAdapter.notifyItemInserted(positionstart);
//                            if (comments.get(0).getUser() == null) {
//                                comments.remove(0);
//                                commentAdapter.notifyItemRemoved(0);
//                            }
                        }
                        dialog.dismiss();
                    }
                });
            }
        });
        dialog.show();
    }


    /**
     * 插入一条评论
     *
     * @param comment
     */
    private void insertComment(final Comment comment) {
        Observable<HttpDefault<String>> observable = SwapNetUtils.createAPI(MessageApi.class).insertOneCommment(
                comment.getUserid(),
                comment.getReceiverid(),
                comment.getGoodsid(),
                comment.getContent());
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpDefault<String>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull HttpDefault<String> stringHttpDefault) {

                        if (stringHttpDefault.getError_code() == 0) {
                            int positionstart = comments.size();
                            comments.add(comment);
                            commentAdapter.notifyItemInserted(positionstart);
                            if (comments.get(0).getUser() == null) {
                                comments.remove(0);
                                commentAdapter.notifyItemRemoved(0);
                            }

                        } else if (stringHttpDefault.getError_code() == -1) {
                            LogUtils.d("weijie","插入出错："+stringHttpDefault.getMessage());
                        }

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                        LogUtils.d("weijie","插入评论失败："+e.getMessage());

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    public class GoodMsgHolder extends RecyclerView.ViewHolder {

        private TextView goodsdetail_desc;

        public GoodMsgHolder(View itemView) {
            super(itemView);
            goodsdetail_desc = (TextView) itemView.findViewById(R.id.goodsdetail_desc);
        }
    }


    public class NormalImgHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;

        public NormalImgHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.goods_detailimg_item);
        }
    }

    public class threeLineImgHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;

        public threeLineImgHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(cn.finalteam.galleryfinal.R.id.iv_photo);
        }
    }

    public class UserMsgHolder extends RecyclerView.ViewHolder {

        private CircleImageView head;
        private TextView name;

        public UserMsgHolder(View itemView) {
            super(itemView);
            head = (CircleImageView) itemView.findViewById(R.id.user_head);
            name = (TextView) itemView.findViewById(R.id.goodsdetail_username);
        }
    }

    public class CommentHolder extends RecyclerView.ViewHolder {

        private RecyclerView comment_rv;
        private TextView btn_comment;

        public CommentHolder(View itemView) {
            super(itemView);
            comment_rv = (RecyclerView) itemView.findViewById(R.id.goodsdetail_comment);
            btn_comment = (TextView) itemView.findViewById(R.id.btn_comment);
        }
    }


}
