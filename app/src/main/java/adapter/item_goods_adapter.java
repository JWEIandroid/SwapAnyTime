package adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bumptech.glide.Glide;
import com.example.swapanytime.R;

import java.util.ArrayList;
import java.util.List;

import cn.finalteam.galleryfinal.model.PhotoInfo;
import cn.finalteam.galleryfinal.widget.HorizontalListView;
import entiry.Goods;
import entiry.User;
import minterface.OnItemClickListener;
import ui.CircleImageView;
import utils.CBViewCreator;
import utils.LogUtils;

/**
 * Created by Administrator on 2017/11/21.
 */

public class item_goods_adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<Goods> goods;
    private List<String> mlist;
    private Context context;
    private List<Integer> bannder_imglist;

    private OnItemClickListener onItemClickListener = null;

    public item_goods_adapter setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        return this;
    }

    private final int ITEM_TYPE_HEADER = 1000;
    private final int ITEM_TYPE_NORMAL = 1001;



    public item_goods_adapter(Context context, List<Goods> goods) {
        this.context = context;
        this.goods = goods;
        bannder_imglist = new ArrayList<>();
        bannder_imglist.add(R.mipmap.banner1);
        bannder_imglist.add(R.mipmap.banner2);
        bannder_imglist.add(R.mipmap.banner1);
        bannder_imglist.add(R.mipmap.banner2);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ITEM_TYPE_HEADER;
        } else {
            return ITEM_TYPE_NORMAL;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = null;

        switch (viewType) {

            case ITEM_TYPE_HEADER:
                view = LayoutInflater.from(context).inflate(R.layout.view_rv_bannerheader, parent, false);
                HeadHolder headHolder = new HeadHolder(view);
                return headHolder;

            case ITEM_TYPE_NORMAL:
                view = LayoutInflater.from(context).inflate(R.layout.recycleview_item_goods_index, parent, false);
                NormalHolder normalHolder = new NormalHolder(view);
                return normalHolder;


            default:
                return null;
        }

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int position) {


        if (viewHolder instanceof HeadHolder) {

            ((HeadHolder) viewHolder).banner.setPages(new CBViewHolderCreator<CBViewCreator>() {
                @Override
                public CBViewCreator createHolder() {
                    return new CBViewCreator();
                }
            }, bannder_imglist)
                    .setPageIndicator(new int[]{R.mipmap.ic_page_indicator, R.mipmap.ic_page_indicator_focused})
                    //设置指示器的方向
                    .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)

                    .startTurning(5 * 1000L);


        } else if (viewHolder instanceof NormalHolder) {

            Goods good = goods.get(position);
            User user = good.getPulisher();

            Glide.with(context).load(user.getImgUrl()).asBitmap().centerCrop().into(((NormalHolder) viewHolder).head);
            ((NormalHolder) viewHolder).name.setText(good.getName());
            ((NormalHolder) viewHolder).price.setText("" + (int) good.getPrice_after());
            ((NormalHolder) viewHolder).desc.setText(good.getDescrtption());

            index_good_itemImgAdapter index_good_itemImgAdapter = new index_good_itemImgAdapter(context, good.getImgUrls());

            ((NormalHolder) viewHolder).goods_img.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            ((NormalHolder) viewHolder).goods_img.setAdapter(index_good_itemImgAdapter);


            //设置点击事件
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(viewHolder.itemView,position);
                }
            });


        }

    }

    @Override
    public int getItemCount() {
        return goods.size();
    }





    public class NormalHolder extends RecyclerView.ViewHolder {

        private CircleImageView head;
        private TextView name;
        private TextView price;
        private RecyclerView goods_img;
        private TextView desc;

        public NormalHolder(View itemView) {
            super(itemView);

            head = (CircleImageView) itemView.findViewById(R.id.head);
            name = (TextView) itemView.findViewById(R.id.name);
            price = (TextView) itemView.findViewById(R.id.price);
            desc = (TextView) itemView.findViewById(R.id.item_goods_description);
            goods_img = (RecyclerView) itemView.findViewById(R.id.item_goods_listview);

        }
    }


    public class HeadHolder extends RecyclerView.ViewHolder {

        private ConvenientBanner banner;

        public HeadHolder(View itemView) {
            super(itemView);
            banner = (ConvenientBanner) itemView.findViewById(R.id.index_banner);
        }

    }




}
