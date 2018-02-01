package entiry;

/**
 * Created by weijie on 2018/1/29.
 */

public class Buyrecord {


    /**
     * id : 29
     * userid : 71
     * goodsid : 74
     * shouhuomsg : 67
     * date : 1516960965056
     */

    private int id;
    private int userid;
    private int goodsid;
    private int shouhuomsgid;
    private String date;
    private User user_shop;
    private Goods goods;
    private Shouhuomsg shouhuomsg;

    public int getShouhuomsgid() {
        return shouhuomsgid;
    }

    public Buyrecord setShouhuomsgid(int shouhuomsgid) {
        this.shouhuomsgid = shouhuomsgid;
        return this;
    }

    public User getUser_shop() {
        return user_shop;
    }

    public Buyrecord setUser_shop(User user_shop) {
        this.user_shop = user_shop;
        return this;
    }

    public Goods getGoods() {
        return goods;
    }

    public Buyrecord setGoods(Goods goods) {
        this.goods = goods;
        return this;
    }

    public Buyrecord setShouhuomsg(Shouhuomsg shouhuomsg) {
        this.shouhuomsg = shouhuomsg;
        return this;
    }

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

    public int getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(int goodsid) {
        this.goodsid = goodsid;
    }

    public Shouhuomsg getShouhuomsg() {
        return shouhuomsg;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
