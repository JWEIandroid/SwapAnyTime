package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.swapanytime.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weijie on 2017/10/12.
 */

public class Type_right_adapter extends RecyclerView.Adapter<Type_right_adapter.Type_right_holder> {

    private Context context;
    private List<String> list = new ArrayList<>();

    public Type_right_adapter(Context context,List<String> list) {
        this.context = context;
        this.list = list;
    }



    @Override
    public Type_right_holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recycleview_type_right_item,parent,false);

        Type_right_holder holder  = new Type_right_holder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(Type_right_holder holder, int position) {

        holder.title.setText(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Type_right_holder extends RecyclerView.ViewHolder{

        private TextView title;

        public Type_right_holder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.rigth_item_title);
        }
    }


}

