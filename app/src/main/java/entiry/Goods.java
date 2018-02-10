package entiry;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by weijie on 2017/11/21.
 */

public class Goods implements Parcelable {


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
    private float price_before;
    private float price_sale;
    private String status;
    private String description;
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
        private float price_before;
        private float price_sale;
        private String status;
        private String description;
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

        public Builder price_before(float val) {
            price_before = val;
            return this;
        }

        public Builder price_sale(float val) {
            price_sale = val;
            return this;
        }

        public Builder status(String val) {
            status = val;
            return this;
        }

        public Builder description(String val) {
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

    public float getPrice_before() {
        return price_before;
    }

    public void setPrice_before(float price_before) {
        this.price_before = price_before;
    }

    public float getPrice_sale() {
        return price_sale;
    }

    public void setPrice_sale(float price_sale) {
        this.price_sale = price_sale;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(String description) {
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.type);
        dest.writeFloat(this.price_before);
        dest.writeFloat(this.price_sale);
        dest.writeString(this.status);
        dest.writeString(this.description);
        dest.writeInt(this.express);
        dest.writeStringList(this.imgurl);
        dest.writeParcelable(this.user, flags);
    }

    protected Goods(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.type = in.readString();
        this.price_before = in.readFloat();
        this.price_sale = in.readFloat();
        this.status = in.readString();
        this.description = in.readString();
        this.express = in.readInt();
        this.imgurl = in.createStringArrayList();
        this.user = in.readParcelable(User.class.getClassLoader());
    }

    public static final Creator<Goods> CREATOR = new Creator<Goods>() {
        @Override
        public Goods createFromParcel(Parcel source) {
            return new Goods(source);
        }

        @Override
        public Goods[] newArray(int size) {
            return new Goods[size];
        }
    };
}


