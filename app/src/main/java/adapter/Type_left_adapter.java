package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.swapanytime.R;

import java.util.ArrayList;
import java.util.List;

import minterface.OnItemClickListener;

/**
 * Created by weijie on 2017/10/12.
 */

public class Type_left_adapter extends RecyclerView.Adapter<Type_left_adapter.left_holder> {

    private Context context;
    private List<String> list = new ArrayList<>();
    private List<Boolean> isClick = new ArrayList<>();

    public Type_left_adapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;

        for(int i = 0;i<list.size();i++){
            isClick.add(false);
        }
    }

    @Override
    public Type_left_adapter.left_holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recyleview_type_left_item, parent, false);

        left_holder holder = new left_holder(view);

        return holder;
    }



    //注册监听器
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public void onBindViewHolder(final Type_left_adapter.left_holder holder, final int position) {

        holder.title.setText(list.get(position)+"!");

        if (isClick.get(position)){
            holder.line.setVisibility(View.VISIBLE);
        }else {
            holder.line.setVisibility(View.GONE);
        }

        //为title设置监听器
        if (onItemClickListener!=null){
          holder.itemView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  int pos = holder.getLayoutPosition();
                  onItemClickListener.onItemClick(holder.itemView,pos);

                  for (int i =0;i<isClick.size();i++){
                      isClick.set(i,false);
                  }
                  isClick.set(position,true);
              }
          });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class left_holder extends RecyclerView.ViewHolder {

        private TextView title;
        private ImageView line;

        public left_holder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.left_type_item);
            line = (ImageView) itemView.findViewById(R.id.left_line);
        }
    }




}

