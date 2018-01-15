package adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.swapanytime.R;

import java.util.ArrayList;
import java.util.List;

import minterface.OnItemClickListener;

/**
 * Created by weijie on 2018/1/12.
 */

public class SimpleRecycleViewAdapter extends RecyclerView.Adapter<SimpleRecycleViewAdapter.mHolder>{

    private List<String> data = new ArrayList<>();
    private Context context =null;
    private View viewGroup = null;
    private Dialog dialog = null;

    private OnItemClickListener onItemClickListener;

    public SimpleRecycleViewAdapter(Context context,List<String> data) {
        this.context = context;
        this.data = data;
    }

    public void addTextClickListener(OnItemClickListener onItemClickListener){
        if (this.onItemClickListener==null){
            this.onItemClickListener = onItemClickListener;
        }
    }


    @Override
    public mHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.text,parent,false);
        mHolder mHolder = new mHolder(view);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(final mHolder holder, final int position) {

        if (holder instanceof mHolder){

            if (TextUtils.isEmpty(data.get(position))){
                holder.textview.setVisibility(View.GONE);
            }
            holder.textview.setText(data.get(position));
            holder.textview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(v,position);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class mHolder extends RecyclerView.ViewHolder {

        public TextView textview = null;


        public mHolder(View itemView) {
            super(itemView);
            textview = (TextView) itemView.findViewById(R.id.txt);
        }
    }

}
