package adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.swapanytime.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import cn.finalteam.galleryfinal.model.PhotoInfo;
import cn.finalteam.toolsfinal.DeviceUtils;
import utils.LogUtils;
import utils.SwapNetUtils;

/**
 * Created by weijie on 2017/10/5.
 */

public class index_good_itemImgAdapter extends RecyclerView.Adapter<index_good_itemImgAdapter.Holder> {

    private List<String> mlist = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private Context context;


    public index_good_itemImgAdapter(Context context, List<String> mlist) {
        this.mlist = mlist;
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.adapter_photo_list_item, parent, false);
        Holder holder = new Holder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Glide.with(context).load(SwapNetUtils.getBaseURL()+mlist.get(position)).fitCenter().into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }


    public class Holder extends RecyclerView.ViewHolder {


        public ImageView imageView;

        public Holder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv_photo);
        }

    }


}
