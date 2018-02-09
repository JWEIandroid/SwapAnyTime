package entiry;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by weij on 2017/12/29.
 */

public class User implements Parcelable {


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

    private String name;
    private int id;
    private String password;
    private String tel;
    private String description;
    private String sex;
    private String headimg;
    private String token;
    private String adress;
    private String create_time;
    private String update_time;



    public User(Builder builder) {
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

    public User() {

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

    public void setCreate_time(String create_time) {
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
        private String create_time;
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

        public Builder create_time(String val) {
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.id);
        dest.writeString(this.password);
        dest.writeString(this.tel);
        dest.writeString(this.description);
        dest.writeString(this.sex);
        dest.writeString(this.headimg);
        dest.writeString(this.token);
        dest.writeString(this.adress);
        dest.writeString(this.create_time);
        dest.writeString(this.update_time);
    }

    protected User(Parcel in) {
        this.name = in.readString();
        this.id = in.readInt();
        this.password = in.readString();
        this.tel = in.readString();
        this.description = in.readString();
        this.sex = in.readString();
        this.headimg = in.readString();
        this.token = in.readString();
        this.adress = in.readString();
        this.create_time = in.readString();
        this.update_time = in.readString();
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
/**/