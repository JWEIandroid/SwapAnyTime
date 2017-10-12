package adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.swapanytime.R;

import java.util.List;

/**
 * Created by weijie on 2017/10/12.
 */

public class Type_left_adapter extends RecyclerView.Adapter<Type_left_adapter.left_holder> {

    private Context context;
    private String title[];

    public Type_left_adapter(Context context, String[] title) {
        this.context = context;
        this.title = title;
    }

    @Override
    public Type_left_adapter.left_holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recyleview_type_left_item, parent, false);

        left_holder holder = new left_holder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(Type_left_adapter.left_holder holder, int position) {

        holder.title.setText("Test"+position);
        holder.gridView.setBackgroundColor(Color.DKGRAY);
    }

    @Override
    public int getItemCount() {
        return title.length;
    }

    class left_holder extends RecyclerView.ViewHolder {

        private TextView title;
        private GridView gridView;

        public left_holder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.rigth_item_title);
            gridView = (GridView) itemView.findViewById(R.id.right_item_gridview);
        }
    }

}

