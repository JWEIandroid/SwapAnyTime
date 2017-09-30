package entiry;

import java.io.File;

import cn.bmob.v3.BmobObject;

/**
 * Created by weijie on 2017/9/30.
 */

public class GoodsPics extends BmobObject {


    private File pics;

    public File getPics() {
        return pics;
    }

    public void setPics(File pics) {
        this.pics = pics;
    }



}
