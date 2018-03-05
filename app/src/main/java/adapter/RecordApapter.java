package adapter;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.swapanytime.R;

import java.util.List;

import entiry.Buyrecord;
import entiry.ForkRecord;
import entiry.Goods;
import entiry.ReportRecord;
import entiry.SaleRecord;
import minterface.ItemTouchHelperAdapter;
import minterface.OnItemClickListener;
import utils.LogUtils;
import utils.SwapNetUtils;

/**
 * Created by weijie on 2018/1/29.
 */

public class RecordApapter extends RecyclerView.Adapter<RecordApapter.RecordHolder> implements ItemTouchHelperAdapter{

    private Context context = null;
    private List<?> list = null; //记录数据表
    private OnItemClickListener onItemClickListener = null;
    private int type = -1; //加载数据类型  0：购买记录  1：卖出记录  2：发布记录 3：收藏记录


    public RecordApapter(Context context, List<?> list, int type) {
        this.context = context;
        this.list = list;
        this.type = type;
    }

    public void addItemclickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    @Override
    public RecordHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecordHolder(LayoutInflater.from(context).inflate(R.layout.horizonal_item_record, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecordHolder holder, final int position) {

        switch (type) {

            case 0:
                Buyrecord buyrecord = (Buyrecord) list.get(position);
                if (buyrecord==null){
                    break;
                }
                holder.txt_price_before.setVisibility(View.VISIBLE);
                Glide.with(context)
                        .load(SwapNetUtils.getBaseURL() + buyrecord.getGoods().getImgurl().get(0))
                        .asBitmap()
                        .centerCrop()
                        .error(R.mipmap.pic_error)
                        .placeholder(R.mipmap.pic_loading)
                        .into(holder.record_item_pic);
                holder.record_item_desc.setText(buyrecord.getGoods().getDescription() + "");
                holder.record_itemprice_before.setText(buyrecord.getGoods().getPrice_before() + "");
                holder.record_itemprice_sale.setText(buyrecord.getGoods().getPrice_sale() + "");
                holder.record_item_del_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Snackbar.make(holder.record_item_del_btn,"del",Snackbar.LENGTH_SHORT).show();
                    }
                });
                break;
            case 1:
                SaleRecord saleRecord = (SaleRecord) list.get(position);
                if (saleRecord==null){
                    break;
                }
                holder.txt_price_before.setVisibility(View.VISIBLE);
                Glide.with(context)
                        .load(SwapNetUtils.getBaseURL() + saleRecord.getGoods().getImgurl().get(0))
                        .asBitmap()
                        .centerCrop()
                        .error(R.mipmap.pic_error)
                        .placeholder(R.mipmap.pic_loading)
                        .into(holder.record_item_pic);
                holder.record_item_desc.setText(saleRecord.getGoods().getDescription() + "");
                holder.record_itemprice_before.setText(saleRecord.getGoods().getPrice_before() + "");
                holder.record_itemprice_sale.setText(saleRecord.getGoods().getPrice_sale() + "");
                holder.record_item_del_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Snackbar.make(holder.record_item_del_btn,"del",Snackbar.LENGTH_SHORT).show();
                    }
                });
                break;
            case 2:
                ReportRecord reportRecord = (ReportRecord) list.get(position);
                if (reportRecord==null){
                    break;
                }
                holder.txt_price_before.setVisibility(View.VISIBLE);
                Glide.with(context)
                        .load(SwapNetUtils.getBaseURL() + reportRecord.getGoods().getImgurl().get(0))
                        .asBitmap()
                        .centerCrop()
                        .error(R.mipmap.pic_error)
                        .placeholder(R.mipmap.pic_loading)
                        .into(holder.record_item_pic);
                holder.record_item_desc.setText(reportRecord.getGoods().getDescription() + "");
                holder.record_itemprice_before.setText(reportRecord.getGoods().getPrice_before() + "");
                holder.record_itemprice_sale.setText(reportRecord.getGoods().getPrice_sale() + "");
                holder.record_item_del_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Snackbar.make(holder.record_item_del_btn,"del",Snackbar.LENGTH_SHORT).show();
                    }
                });
                break;
            case 3:
                ForkRecord forkRecord = (ForkRecord) list.get(position);
                if (forkRecord==null){
                    break;
                }
                holder.txt_price_before.setVisibility(View.VISIBLE);
                Glide.with(context)
                        .load(SwapNetUtils.getBaseURL() + forkRecord.getGoods().getImgurl().get(0))
                        .asBitmap()
                        .centerCrop()
                        .error(R.mipmap.pic_error)
                        .placeholder(R.mipmap.pic_loading)
                        .into(holder.record_item_pic);
                holder.record_item_desc.setText(forkRecord.getGoods().getDescription() + "");
                holder.record_itemprice_before.setText(forkRecord.getGoods().getPrice_before() + "");
                holder.record_itemprice_sale.setText(forkRecord.getGoods().getPrice_sale() + "");
                holder.record_item_del_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Snackbar.make(holder.record_item_del_btn,"del",Snackbar.LENGTH_SHORT).show();
                    }
                });
                break;
            default:
                break;


        }


    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onItemDismiss(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    public class RecordHolder extends RecyclerView.ViewHolder {

        public ImageView record_item_pic;
        public TextView record_itemprice_sale;
        public TextView record_itemprice_before;
        public TextView record_item_desc;
        public TextView record_item_del_btn;
        public TextView txt_price_before;
        public FrameLayout rv;
        public ImageView img;

        public RecordHolder(View itemView) {
            super(itemView);
            record_item_pic = (ImageView) itemView.findViewById(R.id.horizonal_record_item_pic);
            record_itemprice_sale = (TextView) itemView.findViewById(R.id.horizonal_record_item_price_sale);
            record_itemprice_before = (TextView) itemView.findViewById(R.id.horizonal_record_item_price_before);
            record_item_desc = (TextView) itemView.findViewById(R.id.horizonal_record_item_desc);
            record_item_del_btn = (TextView) itemView.findViewById(R.id.horizonal_record_item_del_btn);
            rv = (FrameLayout) itemView.findViewById(R.id.horizonal_record_item_del_framelayout);
            img = (ImageView) itemView.findViewById(R.id.horizonal_record_item_del_img);
            txt_price_before = (TextView) itemView.findViewById(R.id.txt_price_before);
        }

    }
}
