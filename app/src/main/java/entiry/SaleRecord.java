package entiry;

/**
 * Created by Administrator on 2018/1/29.
 */

public class SaleRecord {


    /**
     * id : 20
     * userid : 71
     * goodsid : 92
     * date : 1517189991019
     * user_sale_id : 46
     * shouhuomsg : 68
     */

    private int id;    //记录id
    private int userid;  //卖家Id
    private int goodsid;  //商品Id
    private String date;  //订单日期
    private User user; //卖家
    private User user_sale; //买家
    private Shouhuomsg shouhuomsg; //收货信息
    private int user_sale_id;  //买家Id
    private int shouhuomsgid;   //对应的收货信息Id
    private Goods goods;       //商品信息

    public Shouhuomsg getShouhuomsg() {
        return shouhuomsg;
    }

    public SaleRecord setShouhuomsg(Shouhuomsg shouhuomsg) {
        this.shouhuomsg = shouhuomsg;
        return this;
    }

    public Goods getGoods() {
        return goods;
    }

    public SaleRecord setGoods(Goods goods) {
        this.goods = goods;
        return this;
    }

    public User getUser() {
        return user;
    }

    public SaleRecord setUser(User user) {
        this.user = user;
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

    public int getUser_sale_id() {
        return user_sale_id;
    }

    public void setUser_sale_id(int user_sale_id) {
        this.user_sale_id = user_sale_id;
    }

    public User getUser_sale() {
        return user_sale;
    }

    public SaleRecord setUser_sale(User user_sale) {
        this.user_sale = user_sale;
        return this;
    }

    public Shouhuomsg getShuohuomsg() {
        return shouhuomsg;
    }

    public SaleRecord setShuohuomsg(Shouhuomsg shuohuomsg) {
        this.shouhuomsg = shuohuomsg;
        return this;
    }

    public int getShouhuomsgid() {
        return shouhuomsgid;
    }

    public SaleRecord setShouhuomsgid(int shouhuomsgid) {
        this.shouhuomsgid = shouhuomsgid;
        return this;
    }
}
