package adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.swapanytime.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import minterface.OnItemClickListener;

/**
 * Created by weijie on 2017/10/12.
 */

public class Type_left_adapter extends RecyclerView.Adapter<Type_left_adapter.ViewHolder> {

    private Context context;
    private List<String> list = new ArrayList<>();
    private List<Boolean> isClick = new ArrayList<>();
    private Vector<Boolean> vector = new Vector<>();
    private static int size;
    private LayoutInflater layoutInflater;

    public Type_left_adapter setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
        return this;
    }

    private OnItemClickListener listener = null;

    public Type_left_adapter(Context context, List<String> list) {
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.list = list;
        for (int i = 0; i < list.size(); i++) {
            isClick.add(false);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.recyleview_type_left_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.title = (TextView) view.findViewById(R.id.left_type_item);
        viewHolder.line = (ImageView) view.findViewById(R.id.left_line);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        if (!list.get(position).equals("")){
            holder.title.setText(list.get(position));
            holder.title.setVisibility(View.VISIBLE);
        }

//        holder.line.setVisibility(View.GONE);

        if (isClick.get(position)) {
            holder.line.setVisibility(View.VISIBLE);
            holder.title.setTextColor(Color.BLACK);
            holder.itemView.setBackgroundColor(Color.BLACK);
            holder.itemView.getBackground().setAlpha(50);
        } else {
            holder.line.setVisibility(View.GONE);
            holder.title.setTextColor(context.getResources().getColor(R.color.not_selected));
            holder.itemView.setBackgroundColor(Color.WHITE);
        }

        if (listener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < isClick.size(); i++) {
                        isClick.set(i, false);
                    }
//                    for (boolean isclicked : isClick) {
//                        if (isclicked){
//                            listener.changeBackground(holder.line,position);
//                        }
//                    }
                    isClick.set(position, true);
                    notifyDataSetChanged();
                    listener.onItemClick(holder.itemView, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }

        public TextView getTitle() {
            return title;
        }

        public ImageView getLine() {
            return line;
        }

        TextView title;
        ImageView line;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
//        void changeBackground(View view,int position);
    }

}




