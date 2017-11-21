package entiry;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/11/21.
 */

public class Goods {

    private String name;
    private List<String> imgUrls;
    private String descrtption;
    private Timestamp date_pulish;
    private User pulisher;
    private float price_before;
    private float price_after;
    private String adress;

    public String getName() {
        return name;
    }

    public List<String> getImgUrls() {
        return imgUrls;
    }

    public String getDescrtption() {
        return descrtption;
    }

    public Timestamp getDate_pulish() {
        return date_pulish;
    }

    public User getPulisher() {
        return pulisher;
    }

    public String getAdress() {
        return adress;
    }

    public float getPrice_before() {
        return price_before;
    }

    public float getPrice_after() {
        return price_after;
    }

    private Goods(Builder builder) {
        name = builder.name;
        imgUrls = builder.imgUrls;
        descrtption = builder.descrtption;
        date_pulish = builder.date_pulish;
        pulisher = builder.pulisher;
        adress = builder.adress;
        price_before = builder.price_before;
        price_after = builder.price_after;
    }


    public static final class Builder {
        private String name;
        private List<String> imgUrls;
        private String descrtption;
        private Timestamp date_pulish;
        private User pulisher;
        private String adress;
        private float price_before;
        private float price_after;

        public Builder() {
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder imgUrls(List<String> val) {
            imgUrls = val;
            return this;
        }

        public Builder descrtption(String val) {
            descrtption = val;
            return this;
        }

        public Builder date_pulish(Timestamp val) {
            date_pulish = val;
            return this;
        }

        public Builder pulisher(User val) {
            pulisher = val;
            return this;
        }

        public Builder adress(String val) {
            adress = val;
            return this;
        }

        public Builder price_before(float val) {
            price_before = val;
            return this;
        }

        public Builder price_after(float val) {
            price_after = val;
            return this;
        }

        public Goods build() {
            return new Goods(this);
        }
    }
}
