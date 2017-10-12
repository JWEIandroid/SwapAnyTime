package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.swapanytime.R;

/**
 * Created by weijie on 2017/10/12.
 */

public class Type_right_adapter extends RecyclerView.Adapter<Type_right_adapter.Type_right_holder> {

    private String[] type_title;
    private Context context;

    public Type_right_adapter(Context context,String[] type_title) {
        this.type_title = type_title;
        this.context = context;
    }



    @Override
    public Type_right_holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recyleview_type_left_item,parent,false);

        Type_right_holder holder  = new Type_right_holder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(Type_right_holder holder, int position) {

        holder.title.setText(type_title[position]);

    }

    @Override
    public int getItemCount() {
        return type_title.length;
    }

    class Type_right_holder extends RecyclerView.ViewHolder{

        private TextView title;

        public Type_right_holder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.left_type_item);
        }
    }


}

