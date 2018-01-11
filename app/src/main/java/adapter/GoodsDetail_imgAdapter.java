package adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.swapanytime.R;

import java.util.List;

import cn.finalteam.galleryfinal.model.PhotoInfo;
import ui.CircleImageView;
import utils.LogUtils;

/**
 * Created by weijie on 2017/11/29.
 */

public class GoodsDetail_imgAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<String> list;
    private Context context;

    //开始显示三行格式图片的位置
    private int middle = 0;


    private static final int TYPE_GOOD_TITLE = 1000;
    private static final int TYPE_ONELINE = 1001;
    private static final int TYPE_THREE = 1002;
    private static final int TYPE_USE = 1003;
    private static final int TYPE_COMMENT = 1004;


    public GoodsDetail_imgAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
        middle = list.size() / 2;
    }


    @Override
    public int getItemViewType(int position) {

        if (position == 0) {
            return TYPE_GOOD_TITLE;
        } else if (position == list.size()) {
            return TYPE_USE;
        } else if (position == list.size()+1) {
            return TYPE_COMMENT;
        } else if (position <= middle) {
            return TYPE_ONELINE;
        }
        return TYPE_THREE;
    }

    @Override
    public int getItemCount() {
        return list.size()+2;
    }


    @Override
    public void onAttachedToRecyclerView(final RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                switch (getItemViewType(position)) {

                    case TYPE_USE:
                    case TYPE_ONELINE:
                    case TYPE_COMMENT:
                        return 2;
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

            Glide.with(context).load(list.get(position-2)).fitCenter().into(((NormalImgHolder) holder).imageView);

        } else if (holder instanceof GoodsDetail_imgAdapter.threeLineImgHolder) {

            Glide.with(context).load(list.get(position-2)).fitCenter().into(((threeLineImgHolder) holder).imageView);
        }


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

        public UserMsgHolder(View itemView) {
            super(itemView);
            head = (CircleImageView) itemView.findViewById(R.id.user_head);
        }
    }

    public class CommentHolder extends RecyclerView.ViewHolder {

        private RecyclerView comment_rv;

        public CommentHolder(View itemView) {
            super(itemView);
            comment_rv = (RecyclerView) itemView.findViewById(R.id.goodsdetail_comment);
        }
    }


}
