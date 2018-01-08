package entiry;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/21.
 */

public class Goods implements Serializable{


    /**
     * id : 1
     * name : goods1
     * type : w
     * price_before : 1000
     * price_sale : 500
     * status : true
     * description : null
     * express : 0
     * userid : 0
     */

    private int id;
    private String name;
    private String type;
    private int price_before;
    private int price_sale;
    private boolean status;
    private Object description;
    private int express;
    private ArrayList<String> imgurl;
    private User user;

    private Goods(Builder builder) {
        id = builder.id;
        name = builder.name;
        type = builder.type;
        price_before = builder.price_before;
        price_sale = builder.price_sale;
        status = builder.status;
        description = builder.description;
        express = builder.express;
        user = builder.user;
        imgurl = builder.imgurl;
    }


    public static final class Builder {
        private int id;
        private String name;
        private String type;
        private int price_before;
        private int price_sale;
        private boolean status;
        private Object description;
        private int express;
        private User user;
        private ArrayList<String> imgurl;

        public Builder() {
        }

        public Builder id(int val) {
            id = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder type(String val) {
            type = val;
            return this;
        }

        public Builder price_before(int val) {
            price_before = val;
            return this;
        }

        public Builder price_sale(int val) {
            price_sale = val;
            return this;
        }

        public Builder status(boolean val) {
            status = val;
            return this;
        }

        public Builder description(Object val) {
            description = val;
            return this;
        }

        public Builder express(int val) {
            express = val;
            return this;
        }

        public Builder user(User val) {
            user = val;
            return this;
        }

        public Builder imgurl(ArrayList<String> val) {
            imgurl = val;
            return this;
        }

        public Goods build() {
            return new Goods(this);
        }
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice_before() {
        return price_before;
    }

    public void setPrice_before(int price_before) {
        this.price_before = price_before;
    }

    public int getPrice_sale() {
        return price_sale;
    }

    public void setPrice_sale(int price_sale) {
        this.price_sale = price_sale;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public int getExpress() {
        return express;
    }

    public void setExpress(int express) {
        this.express = express;
    }

    public User getUser() {
        return user;
    }


    public void setUserid(User user) {
        this.user = user;
    }

    public ArrayList<String> getImgurl() {
        return imgurl;
    }

    public void setImgurl(ArrayList<String> imgurl) {
        this.imgurl = imgurl;
    }
}


