package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.swapanytime.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import entiry.Comment;
import utils.ContentUtils;
import utils.LogUtils;
import utils.SwapNetUtils;

/**
 * Created by weijie on 2017/11/29.
 */

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentHolder> {


    private Context context;
    private List<Comment> comments;

    public CommentAdapter(Context context, List<Comment> comments) {
        this.context = context;
        this.comments = comments;
    }


    @Override
    public CommentHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.adapter_comment_item, parent, false);
        CommentHolder commentHolder = new CommentHolder(view);
        return commentHolder;

    }

    @Override
    public void onBindViewHolder(CommentHolder holder, int position) {

        if (holder instanceof CommentHolder) {
            if (comments == null || comments.size() < 1) {
                return;
            }
            holder.comment_erea.setVisibility(View.VISIBLE);
            holder.comment_no_erea.setVisibility(View.GONE);
            Glide.with(context).load(SwapNetUtils.getBaseURL() + comments.get(position).getUser().getHeadimg())
                    .asBitmap().centerCrop().error(R.mipmap.pic_error).placeholder(R.mipmap.pic_loading).into(((CommentHolder) holder).comment_user_img);
            ((CommentHolder) holder).comment_user_name.setText(comments.get(position).getUser().getName());
            ((CommentHolder) holder).comment_content.setText(comments.get(position).getContent());

//                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                String date = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date(comments.get(position).getDate()));
            Long time_data = Long.parseLong(comments.get(position).getDate());
            String date = getDayFromDate(time_data);
//                String date = getDayFromDate(comments.get(position).getDate());
            ((CommentHolder) holder).comment_date.setText(date);
            ((CommentHolder) holder).comment_num.setText(comments.get(position).getLike() + "");
        }

    }


    // 获取激活日
    public String getDayFromDate(long date_product) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String d = format.format(date_product);
        Date date_get = null;
        int year = 0;
        int month = 0;
        int day_get = 0;
        int hour = 0;
        int min = 0;

        try {
            date_get = format.parse(d);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date_get);
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH)+1;
            day_get = calendar.get(Calendar.DAY_OF_MONTH);
            hour = calendar.get(Calendar.HOUR_OF_DAY);
            min = calendar.get(Calendar.MINUTE);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (hour<10){
            if (min<10){
                return year + "/" + month+ "/" + day_get+"  0"+hour+":0"+min;
            }
            return year + "/" + month+ "/" + day_get+"  0"+hour+":"+min;
        }

        if (min<10){
            return year + "/" + month+ "/" + day_get+"  "+hour+":0"+min;
        }
        return year + "/" + month+ "/" + day_get+"  "+hour+":"+min;
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class CommentHolder extends RecyclerView.ViewHolder {

        CircleImageView comment_user_img;
        TextView comment_user_name;
        TextView comment_num;
        TextView comment_content;
        TextView comment_date;
        RelativeLayout comment_erea;
        LinearLayout comment_no_erea;


        public CommentHolder(View itemView) {
            super(itemView);
            comment_content = (TextView) itemView.findViewById(R.id.comment_content);
            comment_user_name = (TextView) itemView.findViewById(R.id.comment_user_name);
            comment_user_img = (CircleImageView) itemView.findViewById(R.id.comment_user_head);
            comment_date = (TextView) itemView.findViewById(R.id.comment_date);
            comment_num = (TextView) itemView.findViewById(R.id.comment_like_num);
            comment_erea = (RelativeLayout) itemView.findViewById(R.id.comment_erea);
            comment_no_erea = (LinearLayout) itemView.findViewById(R.id.comment_no_erea);
        }
    }

}
