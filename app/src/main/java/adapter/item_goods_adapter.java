package adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.swapanytime.R;

import java.util.List;

import cn.finalteam.galleryfinal.model.PhotoInfo;
import cn.finalteam.galleryfinal.widget.HorizontalListView;
import entiry.Goods;
import entiry.User;
import ui.CircleImageView;
import utils.LogUtils;

/**
 * Created by Administrator on 2017/11/21.
 */

public class item_goods_adapter extends RecyclerView.Adapter<item_goods_adapter.holder>{


    private List<Goods> goods ;
    private List<String> mlist;
    private Context context;

    public item_goods_adapter(Context context, List<Goods> goods) {
        this.context = context;
        this.goods = goods;
    }

    @Override
    public holder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = LayoutInflater.from(context).inflate(R.layout.recycleview_item_goods_index,parent,false);

        holder holder = new holder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(holder holder, int position) {

        Goods good = goods.get(position);
        User user = good.getPulisher();

        Glide.with(context).load(user.getImgUrl()).asBitmap().centerCrop().into(holder.head);
        holder.name.setText(good.getName());
        holder.price.setText(""+(int) good.getPrice_after());
        holder.desc.setText(good.getDescrtption());

        index_good_itemImgAdapter index_good_itemImgAdapter = new index_good_itemImgAdapter(context,good.getImgUrls());

        holder.goods_img.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        holder.goods_img.setAdapter(index_good_itemImgAdapter);

//        holder.name.setText();
    }

    @Override
    public int getItemCount() {
        return goods.size();
    }

    public  class holder extends RecyclerView.ViewHolder{

        private CircleImageView head;
        private TextView name;
        private TextView price;
        private RecyclerView goods_img;
        private TextView desc;


        public holder(View itemView) {
            super(itemView);

            head = (CircleImageView) itemView.findViewById(R.id.head);
            name = (TextView) itemView.findViewById(R.id.name);
            price = (TextView) itemView.findViewById(R.id.price);
            desc = (TextView) itemView.findViewById(R.id.item_goods_description);
            goods_img = (RecyclerView) itemView.findViewById(R.id.item_goods_listview);

        }





    }

}
