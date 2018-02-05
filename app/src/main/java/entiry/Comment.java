package entiry;

import java.util.List;

/**
 * Created by weijie on 2018/2/5.
 */

public class Comment {

    private int id;
    private int userid;
    private int receiverid;
    private int goodsid;
    private int type;
    private String content;
    private String date;
    private User user;
    private Goods goods;
    private User receiver;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getReceiverid() {
        return receiverid;
    }

    public void setReceiverid(int receiverid) {
        this.receiverid = receiverid;
    }

    public int getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(int goodsid) {
        this.goodsid = goodsid;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
}

