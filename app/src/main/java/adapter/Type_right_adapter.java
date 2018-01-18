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

import java.util.ArrayList;
import java.util.List;

import entiry.SortDetail;
import minterface.FragmentListener;

/**
 * Created by weijie on 2017/10/12.
 */

public class Type_right_adapter extends RecyclerView.Adapter<Type_right_adapter.Type_right_holder> {

    private Context context;
    private List<SortDetail> list = new ArrayList<>();

    public Type_right_adapter(Context context, List<SortDetail> list) {
        this.context = context;
        this.list = list;
    }

    private FragmentListener fragmentListener = null;

    public Type_right_adapter addOnItemClickListener(FragmentListener fragmentListener) {
        this.fragmentListener = fragmentListener;
        return this;
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).istitle() ? 0 : 1;
    }


    @Override
    public Type_right_holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = null;

        if (viewType == 0) {
            view = LayoutInflater.from(context).inflate(R.layout.item_type_right_title, parent, false);
        } else if (viewType == 1) {
            view = LayoutInflater.from(context).inflate(R.layout.recycleview_type_right_item, parent, false);
        }


        Type_right_holder holder = new Type_right_holder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(Type_right_holder holder, int position) {

        int itemViewType = Type_right_adapter.this.getItemViewType(position);

        if (list.get(position).istitle()) {

            holder.title.setText(list.get(position).getTitlename());

        } else {

            holder.name.setText(list.get(position).getName());
            Glide.with(context).load(list.get(position).getImageSrc()).crossFade().into(holder.imageView);
//            holder.imageView.setImageResource(list.get(position).getImageSrc());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentListener.updateUI(null);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Type_right_holder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView name;
        private ImageView imageView;

        public Type_right_holder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.right_item_title);
            imageView = (ImageView) itemView.findViewById(R.id.right_item_img);
            title = (TextView) itemView.findViewById(R.id.right_title_head);

        }

    }


}

