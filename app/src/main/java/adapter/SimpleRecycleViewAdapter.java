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
 * Created by weijie on 2018/1/12.
 */

public class SimpleRecycleViewAdapter extends RecyclerView.Adapter<SimpleRecycleViewAdapter.mHolder>{

    private List<String> data = new ArrayList<>();
    private Context context;
    private View viewGroup;

    public SimpleRecycleViewAdapter(Context context,List<String> data,View viewGroup) {
        this.context = context;
        this.data = data;
        this.viewGroup = viewGroup;
    }

    @Override
    public mHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.text,parent,false);
        mHolder mHolder = new mHolder(view);
        return mHolder;
    }

    @Override
    public void onBindViewHolder(mHolder holder, int position) {

        if (holder instanceof mHolder){
            holder.textview.setText(data.get(position));
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
