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

/**
 * Created by weijie on 2018/1/29.
 */

public class RecordApapter extends RecyclerView.Adapter<RecordApapter.RecordHolder> {

    private Context context = null;
    private List<Goods> list = null;
    private OnItemClickListener onItemClickListener = null;


    public RecordApapter(Context context, List<Goods> list) {
        this.context = context;
        this.list = list;
    }

    public void addItemclickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    @Override
    public RecordHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecordHolder(LayoutInflater.from(context).inflate(R.layout.adapter_record_btn, parent, false));
    }

    @Override
    public void onBindViewHolder(RecordHolder holder, final int position) {

        if (holder instanceof RecordHolder) {

            Glide.with(context)
                    .load(list.get(position).getImgurl().get(0))
                    .asBitmap()
                    .centerCrop()
                    .error(R.mipmap.pic_error)
                    .placeholder(R.mipmap.pic_loading);
            holder.record_item_desc.setText(list.get(position).getDescription()+"");
            holder.record_itemprice_before.setText(list.get(position).getPrice_before()+"");
            holder.record_itemprice_sale.setText(list.get(position).getPrice_sale()+"");
            holder.record_item_del_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(v, position);
                }
            });


        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RecordHolder extends RecyclerView.ViewHolder {

        ImageView record_item_pic;
        TextView record_itemprice_sale;
        TextView record_itemprice_before;
        TextView record_item_desc;
        TextView record_item_del_btn;

        public RecordHolder(View itemView) {
            super(itemView);
            record_item_pic = (ImageView) itemView.findViewById(R.id.horizonal_record_item_pic);
            record_itemprice_sale = (TextView) itemView.findViewById(R.id.horizonal_record_item_price_sale);
            record_itemprice_before = (TextView) itemView.findViewById(R.id.horizonal_record_item_price_before);
            record_item_desc = (TextView) itemView.findViewById(R.id.horizonal_record_item_desc);
            record_item_del_btn = (TextView) itemView.findViewById(R.id.horizonal_record_item_del_btn);
        }
    }

}
