package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.swapanytime.R;

import java.util.List;

import cn.finalteam.galleryfinal.model.PhotoInfo;

/**
 * Created by Administrator on 2017/11/29.
 */

public class GoodsDetail_imgAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<String> list;
    private Context context;

    private static final int TYPE_ONELINE = 1000;
    private static final int TYPE_THREE = 1001;


    public GoodsDetail_imgAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @Override
    public int getItemViewType(int position) {

        if (position <= 2) {
            return TYPE_ONELINE;
        }
        return TYPE_THREE;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view = null;

        switch (viewType){

            case TYPE_ONELINE:
                 view = LayoutInflater.from(context).inflate(R.layout.view_gooddetailimg_item, parent, false);
                NormalImgHolder normalImgHolder = new NormalImgHolder(view);
                return normalImgHolder;
            case TYPE_THREE:
                 view = LayoutInflater.from(context).inflate(R.layout.adapter_photo_list_item, parent, false);
                threeLineImgHolder threeLineImgHolder = new threeLineImgHolder(view);
                return threeLineImgHolder;
            default:
                return  null;
        }


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof GoodsDetail_imgAdapter.NormalImgHolder){

            Glide.with(context).load(list.get(position)).centerCrop().into(((NormalImgHolder) holder).imageView);

        }else if (holder instanceof GoodsDetail_imgAdapter.threeLineImgHolder){

            Glide.with(context).load(list.get(position)).centerCrop().into(((threeLineImgHolder) holder).imageView);
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class NormalImgHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;

        public NormalImgHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.goods_detailimg_item);
        }
    }


    public class threeLineImgHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;

        public threeLineImgHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(cn.finalteam.galleryfinal.R.id.iv_photo);
        }
    }


}
