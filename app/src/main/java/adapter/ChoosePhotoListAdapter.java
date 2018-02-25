package adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.swapanytime.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import cn.finalteam.galleryfinal.model.PhotoInfo;
import cn.finalteam.toolsfinal.DeviceUtils;
import minterface.OnItemClickListener;

/**
 * Created by weijie on 2017/10/5.
 */

public class ChoosePhotoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<PhotoInfo> data = null;
    private Context context = null;
    private DisplayImageOptions options;
    private OnItemClickListener cameralistener = null;

    public ChoosePhotoListAdapter(Context context, List<PhotoInfo> data, DisplayImageOptions options) {
        this.data = data;
        this.context = context;
        this.options = options;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        GridLayoutManager gridLayoutManager= new GridLayoutManager(context,4);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    public ChoosePhotoListAdapter addCameraListener(OnItemClickListener onItemClickListener) {
        this.cameralistener = onItemClickListener;
        return this;
    }

    private static final int SHOW_TYPE_CAMERA = 1;
    private static final int SHOW_TYPE_PHOTOS = 2;

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return SHOW_TYPE_CAMERA;
        } else {
            return SHOW_TYPE_PHOTOS;
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == SHOW_TYPE_CAMERA) {
            return new CameraHolder(LayoutInflater.from(context).inflate(R.layout.view_camera, parent, false));
        } else {
            return new PhotoHolder(LayoutInflater.from(context).inflate(R.layout.adapter_photo_list_item, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof PhotoHolder) {
            ImageLoader.getInstance().displayImage("file:/" + data.get(position - 1).getPhotoPath(), ((PhotoHolder) holder).photo, options);
        }
        if (holder instanceof CameraHolder) {
            ((CameraHolder) holder).icon_camera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cameralistener.onItemClick(holder.itemView, 0);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return data.size() + 1;
    }


    public class PhotoHolder extends RecyclerView.ViewHolder {

        ImageView photo;

        public PhotoHolder(View itemView) {
            super(itemView);
            photo = (ImageView) itemView.findViewById(cn.finalteam.galleryfinal.R.id.iv_photo);
        }
    }

    public class CameraHolder extends RecyclerView.ViewHolder {

        ImageView icon_camera;
        TextView txt_add;

        public CameraHolder(View itemView) {
            super(itemView);
            icon_camera = (ImageView) itemView.findViewById(R.id.publish_ic_camera);
            txt_add = (TextView) itemView.findViewById(R.id.publish_txt);
        }
    }


}
