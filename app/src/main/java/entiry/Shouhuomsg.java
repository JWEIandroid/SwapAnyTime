package entiry;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by weijie on 2018/1/26.
 */

public class Shouhuomsg implements Serializable {


    /**
     * id : 0
     * userid : 46
     * goodsid : 5
     * receiver : 魔王达
     * tel : 1382816212
     * adress : jy
     */

    @SerializedName("id")
    private int id;
    @SerializedName("userid")
    private int userid;
    @SerializedName("goodsid")
    private int goodsid;
    @SerializedName("receiver")
    private String receiver;
    @SerializedName("tel")
    private String tel;
    @SerializedName("adress")
    private String adress;

    private Shouhuomsg(Builder builder) {
        setId(builder.id);
        setUserid(builder.userid);
        setGoodsid(builder.goodsid);
        setReceiver(builder.receiver);
        setTel(builder.tel);
        setAdress(builder.adress);
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

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }


    public static final class Builder {
        private int id;
        private int userid;
        private int goodsid;
        private String receiver;
        private String tel;
        private String adress;

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

        public Builder goodsid(int val) {
            goodsid = val;
            return this;
        }

        public Builder receiver(String val) {
            receiver = val;
            return this;
        }

        public Builder tel(String val) {
            tel = val;
            return this;
        }

        public Builder adress(String val) {
            adress = val;
            return this;
        }

        public Shouhuomsg build() {
            return new Shouhuomsg(this);
        }
    }
}/**/
