package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.swapanytime.R;

import java.util.List;

import entiry.Goods;
import minterface.OnItemClickListener;
import utils.LogUtils;
import utils.SwapNetUtils;

/**
 * Created by weij on 2018/1/21.
 */

public class SearchGoodsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Goods> list;
    private Context context;
    private int type;
    private OnItemClickListener onItemClickListener;

    public static final int LAYOUT_TYPE = 1000;
    public static final int LAYOUT_SEARCH = 1001;


    public SearchGoodsAdapter(List<Goods> list, Context context, int type) {
        this.list = list;
        this.context = context;
        this.type = type;
    }

    public SearchGoodsAdapter addInitItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        return this;
    }


    @Override
    public int getItemViewType(int position) {
        if (type == LAYOUT_TYPE) {
            return LAYOUT_TYPE;
        }
        return LAYOUT_SEARCH;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        if (type == LAYOUT_TYPE) {
            View view = LayoutInflater.from(context).inflate(R.layout.adapterview_searchgoods, parent, false);
            SearchHolder2View searchHolder2View = new SearchHolder2View(view);
            return searchHolder2View;
        } else if (type == LAYOUT_SEARCH) {
            View view = LayoutInflater.from(context).inflate(R.layout.adapterview_searchgoods, parent, false);
            SearchHolder2View searchHolder2View = new SearchHolder2View(view);
            return searchHolder2View;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof SearchHolder1View) {


        } else if (holder instanceof SearchHolder2View) {
            LogUtils.d("weijie", SwapNetUtils.getBaseURL() + list.get(position).getImgurl().get(0) + "---====");
            Glide.with(context).load(SwapNetUtils.getBaseURL() + list.get(position).getImgurl().get(0)).asBitmap().centerCrop().into(((SearchHolder2View) holder).pic);
            ((SearchHolder2View) holder).txt_good_name.setText(list.get(position).getName());
            ((SearchHolder2View) holder).txt_good_price.setText("￥ " + list.get(position).getPrice_sale() + "");

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(holder.itemView,position);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SearchHolder1View extends RecyclerView.ViewHolder {

        public SearchHolder1View(View itemView) {
            super(itemView);
        }
    }

    public class SearchHolder2View extends RecyclerView.ViewHolder {

        ImageView pic;
        TextView txt_good_name;
        TextView txt_good_price;

        public SearchHolder2View(View itemView) {
            super(itemView);
            pic = (ImageView) itemView.findViewById(R.id.searchgoods_goods_imgs);
            txt_good_name = (TextView) itemView.findViewById(R.id.searchgoods_goods_name);
            txt_good_price = (TextView) itemView.findViewById(R.id.searchgoods_goods_pricesale);
        }
    }

}
