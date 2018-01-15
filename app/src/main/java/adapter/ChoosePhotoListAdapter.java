package adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.swapanytime.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import cn.finalteam.galleryfinal.model.PhotoInfo;
import cn.finalteam.toolsfinal.DeviceUtils;

/**
 * Created by weijie on 2017/10/5.
 */

public class ChoosePhotoListAdapter extends RecyclerView.Adapter<ChoosePhotoListAdapter.PhotoHolder> {


    private List<PhotoInfo> data = null;
    private Context context = null;
    private DisplayImageOptions options;

    public ChoosePhotoListAdapter(Context context, List<PhotoInfo> data, DisplayImageOptions options) {
        this.data = data;
        this.context = context;
        this.options = options;
    }


    @Override
    public PhotoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PhotoHolder(LayoutInflater.from(context).inflate(R.layout.adapter_photo_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(PhotoHolder holder, int position) {

        if (holder instanceof PhotoHolder) {
            ImageLoader.getInstance().displayImage("file:/" + data.get(position).getPhotoPath(), holder.photo, options);
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class PhotoHolder extends RecyclerView.ViewHolder {

        ImageView photo;

        public PhotoHolder(View itemView) {
            super(itemView);
            photo = (ImageView) itemView.findViewById(cn.finalteam.galleryfinal.R.id.iv_photo);
        }
    }


}
