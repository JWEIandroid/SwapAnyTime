package entiry;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by weijie on 2018/2/5.
 */

public class Comment {

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
}

