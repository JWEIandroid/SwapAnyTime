package minterface;

import java.util.List;

import cn.finalteam.galleryfinal.model.PhotoInfo;

/**
 * Created by Administrator on 2017/11/17.
 */

public interface GalleryfinalActionListener {

     public void success(List<PhotoInfo> list);
     public void failed(String msg);


}
