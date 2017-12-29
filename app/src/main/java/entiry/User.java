package entiry;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/12/29.
 */

public class User {


    /**
     * id : 19
     * name : hello
     * tel : 18814129361
     * password : 7
     * token : hello71514539624825
     * adress : null
     * description : nu;;
     * sex : 男
     * create_time : null
     * update_time : 1514539624825
     */

    @SerializedName("name")
    private String name;
    @SerializedName("id")
    private int id;
    @SerializedName("password")
    private String password;
    @SerializedName("tel")
    private String tel;
    @SerializedName("description")
    private String description;
    @SerializedName("sex")
    private String sex;
    @SerializedName("headimg")
    private String headimg;
    @SerializedName("token")
    private String token;
    @SerializedName("adress")
    private String adress;
    @SerializedName("create_time")
    private Object create_time;
    @SerializedName("updaie_time")
    private String update_time;



    private User(Builder builder) {
        setName(builder.name);
        setId(builder.id);
        setPassword(builder.password);
        setTel(builder.tel);
        setDescription(builder.description);
        setSex(builder.sex);
        headimg = builder.headimg;
        setToken(builder.token);
        setAdress(builder.adress);
        setCreate_time(builder.create_time);
        setUpdate_time(builder.update_time);
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

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Object getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Object create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }


    public User setHeadimg(String headimg) {
        this.headimg = headimg;
        return this;
    }

    public String getHeadimg() {
        return headimg;
    }

    public static final class Builder {
        private String name;
        private int id;
        private String password;
        private String tel;
        private String description;
        private String sex;
        private String headimg;
        private String token;
        private String adress;
        private Object create_time;
        private String update_time;

        public Builder() {
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder id(int val) {
            id = val;
            return this;
        }

        public Builder password(String val) {
            password = val;
            return this;
        }

        public Builder tel(String val) {
            tel = val;
            return this;
        }

        public Builder description(String val) {
            description = val;
            return this;
        }

        public Builder sex(String val) {
            sex = val;
            return this;
        }

        public Builder headimg(String val) {
            headimg = val;
            return this;
        }

        public Builder token(String val) {
            token = val;
            return this;
        }

        public Builder adress(String val) {
            adress = val;
            return this;
        }

        public Builder create_time(Object val) {
            create_time = val;
            return this;
        }

        public Builder update_time(String val) {
            update_time = val;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }


}
/**/