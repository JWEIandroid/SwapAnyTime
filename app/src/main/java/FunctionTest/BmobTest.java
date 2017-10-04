package FunctionTest;

import com.example.swapanytime.R;

import base.baseActivity;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import entiry.Manager;
import utils.LogUtils;

/**
 * Created by weijie on 2017/9/30.
 */

public class BmobTest extends baseActivity {

    @Override
    public void initData() {

        Bmob.initialize(this, "0126923760b21776be65e5674cdef780");

        Manager manager = new Manager();
        manager.setHeadImg(null);
        manager.setName("test");
        manager.setNumber("000000");
        manager.setPassword("123");
        manager.setUsername("Test");

//        File file = new File()


        manager.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e==null){
                    LogUtils.d("TAG","Bmob SUCCESS"+s);
                }else{
                    LogUtils.d("TAG",e.getMessage());
                }
            }
        });

    }

    @Override
    public void initView() {

    }

    @Override
    public Object getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void initEvent() {

    }



}