package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.swapanytime.R;

import java.util.List;

import cn.finalteam.galleryfinal.widget.HorizontalListView;
import entiry.Goods;
import entiry.User;
import ui.CircleImageView;

/**
 * Created by Administrator on 2017/11/21.
 */

public class item_goods_adapter extends RecyclerView.Adapter<item_goods_adapter.holder>{


    private List<Goods> goods ;
    private Context context;

    public item_goods_adapter(Context context, List<Goods> goods) {
        this.context = context;
        this.goods = goods;
    }

    @Override
    public holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = null;
        view = LayoutInflater.from(context).inflate(R.layout.recycleview_item_goods_index,parent,false);

        holder holder = new holder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(holder holder, int position) {

        User user = goods.get(position).getPulisher();
        Goods g1 = goods.get(position);
//        holder.head.setImageResource(0);

//        holder.price.setText(g1.getPrice_after());
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
        private HorizontalListView goods_img;
        private TextView desc;


        public holder(View itemView) {
            super(itemView);

            head = (CircleImageView) itemView.findViewById(R.id.head);
            name = (TextView) itemView.findViewById(R.id.name);
            price = (TextView) itemView.findViewById(R.id.price);
            desc = (TextView) itemView.findViewById(R.id.item_goods_description);
            goods_img = (HorizontalListView) itemView.findViewById(R.id.item_goods_listview);

        }





    }

}
