package entiry;

/**
 * Created by weijie on 2017/11/21.
 */

public class User {

    private String name;
    private String userId;
    private String password;
    private String imgUrl;
    private String description;
    private String imgurl;

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public User setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public User setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public User setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImgurl() {
        return imgurl;
    }

    public User setImgurl(String imgurl) {
        this.imgurl = imgurl;
        return this;
    }



    private User(Builder builder) {
        name = builder.name;
        userId = builder.userId;
        password = builder.password;
        imgUrl = builder.imgUrl;
        description = builder.description;
        imgurl = builder.imgurl;
    }


    public static final class Builder {
        private String name;
        private String userId;
        private String password;
        private String imgUrl;
        private String description;
        private String imgurl;

        public Builder() {
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder userId(String val) {
            userId = val;
            return this;
        }

        public Builder password(String val) {
            password = val;
            return this;
        }

        public Builder imgUrl(String val) {
            imgUrl = val;
            return this;
        }

        public Builder description(String val) {
            description = val;
            return this;
        }

        public Builder imgurl(String val) {
            imgurl = val;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
