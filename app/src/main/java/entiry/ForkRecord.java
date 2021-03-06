package entiry;

/**
 * Created by Administrator on 2018/1/29.
 */

public class ForkRecord {

    /**
     * id : 10
     * userid : 71
     * goodsid : 94
     * date : 1517196328054
     * status : fork/nofork
     */

    private int id;
    private int userid;
    private int goodsid;
    private String date;
    private String status;
    private User user ;
    private Goods goods;

    public User getUser() {
        return user;
    }

    public ForkRecord setUser(User user) {
        this.user = user;
        return this;
    }

    public Goods getGoods() {
        return goods;
    }

    public ForkRecord setGoods(Goods goods) {
        this.goods = goods;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
