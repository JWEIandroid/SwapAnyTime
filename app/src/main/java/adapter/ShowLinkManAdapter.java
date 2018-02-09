package adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.swapanytime.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import entiry.User;

/**
 * Created by weij on 2018/2/9.
 */


public class ShowLinkManAdapter extends RecyclerView.Adapter<ShowLinkManAdapter.LinkManHolder> {

    private Context context = null;
    private List<User> list = null;

    public ShowLinkManAdapter(Context context, List<User> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);

    }

    @Override
    public LinkManHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LinkManHolder(LayoutInflater.from(context).inflate(R.layout.recycleview_item_linkman, parent, false));
    }

    @Override
    public void onBindViewHolder(LinkManHolder holder, int position) {

        if (holder instanceof  LinkManHolder){
            holder.linkman_name.setText(list.get(position).getName());
            Glide.with(context).load(list.get(position).getHeadimg())
                    .asBitmap()
                    .centerCrop()
                    .error(R.mipmap.pic_error)
                    .placeholder(R.mipmap.pic_loading)
                    .into(holder.linkman_headimg);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class LinkManHolder extends RecyclerView.ViewHolder {

        CircleImageView linkman_headimg;
        TextView linkman_name;

        public LinkManHolder(View itemView) {
            super(itemView);
            linkman_headimg = (CircleImageView) itemView.findViewById(R.id.linkman_headimg);
            linkman_name = (TextView) itemView.findViewById(R.id.linkman_name);
        }
    }

}
