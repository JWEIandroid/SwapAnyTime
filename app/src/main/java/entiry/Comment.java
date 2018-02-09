package entiry;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by weijie on 2018/2/5.
 */

public class Comment implements Parcelable {

    @SerializedName("id")
    private int id;
    private int userid;
    private int receiverid;
    private int goodsid;
    private int type;
    private String content;
    private String date;
    private int like;
    private User user;
    private Goods goods;
    private User receiver;
    //显示位置,0表示左边，1表示右边
    private int isLeft;

    public int getIsLeft() {
        return isLeft;
    }

    public Comment setIsLeft(int isLeft) {
        this.isLeft = isLeft;
        return this;
    }

    private Comment(Builder builder) {
        setId(builder.id);
        setUserid(builder.userid);
        setReceiverid(builder.receiverid);
        setGoodsid(builder.goodsid);
        setType(builder.type);
        setContent(builder.content);
        setDate(builder.date);
        setLike(builder.like);
        setUser(builder.user);
        setGoods(builder.goods);
        setReceiver(builder.receiver);
    }


    public int getId() {
        return id;
    }

    public Comment setId(int id) {
        this.id = id;
        return this;
    }

    public int getUserid() {
        return userid;
    }

    public Comment setUserid(int userid) {
        this.userid = userid;
        return this;
    }

    public int getReceiverid() {
        return receiverid;
    }

    public Comment setReceiverid(int receiverid) {
        this.receiverid = receiverid;
        return this;
    }

    public int getGoodsid() {
        return goodsid;
    }

    public Comment setGoodsid(int goodsid) {
        this.goodsid = goodsid;
        return this;
    }

    public int getType() {
        return type;
    }

    public Comment setType(int type) {
        this.type = type;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Comment setContent(String content) {
        this.content = content;
        return this;
    }

    public String getDate() {
        return date;
    }

    public Comment setDate(String date) {
        this.date = date;
        return this;
    }

    public int getLike() {
        return like;
    }

    public Comment setLike(int like) {
        this.like = like;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Comment setUser(User user) {
        this.user = user;
        return this;
    }

    public Goods getGoods() {
        return goods;
    }

    public Comment setGoods(Goods goods) {
        this.goods = goods;
        return this;
    }

    public User getReceiver() {
        return receiver;
    }

    public Comment setReceiver(User receiver) {
        this.receiver = receiver;
        return this;
    }

    public static final class Builder {
        private int id;
        private int userid;
        private int receiverid;
        private int goodsid;
        private int type;
        private String content;
        private String date;
        private int like;
        private User user;
        private Goods goods;
        private User receiver;

        public Builder() {
        }

        public Builder id(int val) {
            id = val;
            return this;
        }

        public Builder userid(int val) {
            userid = val;
            return this;
        }

        public Builder receiverid(int val) {
            receiverid = val;
            return this;
        }

        public Builder goodsid(int val) {
            goodsid = val;
            return this;
        }

        public Builder type(int val) {
            type = val;
            return this;
        }

        public Builder content(String val) {
            content = val;
            return this;
        }

        public Builder date(String val) {
            date = val;
            return this;
        }

        public Builder like(int val) {
            like = val;
            return this;
        }

        public Builder user(User val) {
            user = val;
            return this;
        }

        public Builder goods(Goods val) {
            goods = val;
            return this;
        }

        public Builder receiver(User val) {
            receiver = val;
            return this;
        }

        public Comment build() {
            return new Comment(this);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeInt(this.userid);
        dest.writeInt(this.receiverid);
        dest.writeInt(this.goodsid);
        dest.writeInt(this.type);
        dest.writeString(this.content);
        dest.writeString(this.date);
        dest.writeInt(this.like);
        dest.writeParcelable(this.user, flags);
        dest.writeSerializable(this.goods);
        dest.writeParcelable(this.receiver, flags);
        dest.writeInt(this.isLeft);
    }

    protected Comment(Parcel in) {
        this.id = in.readInt();
        this.userid = in.readInt();
        this.receiverid = in.readInt();
        this.goodsid = in.readInt();
        this.type = in.readInt();
        this.content = in.readString();
        this.date = in.readString();
        this.like = in.readInt();
        this.user = in.readParcelable(User.class.getClassLoader());
        this.goods = (Goods) in.readSerializable();
        this.receiver = in.readParcelable(User.class.getClassLoader());
        this.isLeft = in.readInt();
    }

    public static final Parcelable.Creator<Comment> CREATOR = new Parcelable.Creator<Comment>() {
        @Override
        public Comment createFromParcel(Parcel source) {
            return new Comment(source);
        }

        @Override
        public Comment[] newArray(int size) {
            return new Comment[size];
        }
    };
}

