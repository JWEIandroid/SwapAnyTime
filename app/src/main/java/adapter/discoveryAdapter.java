package adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
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
import utils.SwapNetUtils;

/**
 * Created by weij on 2018/2/24.
 */

public class discoveryAdapter extends RecyclerView.Adapter<discoveryAdapter.DiscoveryHolder> {


    private Context context = null;
    private List<Goods> goodsList = null;
    private OnItemClickListener onItemClickListener = null;

    public discoveryAdapter(Context context, List<Goods> goodsList) {
        this.context = context;
        this.goodsList = goodsList;
    }

    public discoveryAdapter addInitItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        return this;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public DiscoveryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DiscoveryHolder(LayoutInflater.from(context).inflate(R.layout.recycleview_discovery_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final DiscoveryHolder holder, final int position) {
        holder.discovery_item_name.setText(goodsList.get(position).getName());
        Glide.with(context).load(SwapNetUtils.getBaseURL() + goodsList.get(position).getImgurl().get(0))
                .asBitmap()
                .centerCrop()
                .error(R.mipmap.pic_error)
                .placeholder(R.mipmap.pic_loading)
                .into(holder.discovery_item_img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(holder.itemView,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return goodsList.size();
    }


    public class DiscoveryHolder extends ViewHolder {

        private ImageView discovery_item_img;
        private TextView discovery_item_name;

        public DiscoveryHolder(View itemView) {
            super(itemView);
            discovery_item_img = (ImageView) itemView.findViewById(R.id.discovery_goods_img);
            discovery_item_name = (TextView) itemView.findViewById(R.id.discovery_goods_name);
        }


    }
}
