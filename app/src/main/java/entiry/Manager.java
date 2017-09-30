package entiry;

import java.io.File;

import cn.bmob.v3.BmobObject;

/**
 * Created by weijie on 2017/9/30.
 */

public class Manager extends BmobObject{

    private String username;
    private String name;
    private String Number;
    private String password;
    private File headImg;

    public String getUsername() {
        return username;
    }

    public Manager setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getName() {
        return name;
    }

    public Manager setName(String name) {
        this.name = name;
        return this;
    }

    public String getNumber() {
        return Number;
    }

    public Manager setNumber(String number) {
        Number = number;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Manager setPassword(String password) {
        this.password = password;
        return this;
    }

    public File getHeadImg() {
        return headImg;
    }

    public Manager setHeadImg(File headImg) {
        this.headImg = headImg;
        return this;
    }


}
