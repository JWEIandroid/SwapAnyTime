package FunctionTest;

import com.example.swapanytime.R;

import base.baseActivity;
import entiry.Manager;
import utils.LogUtils;

/**
 * Created by weijie on 2017/9/30.
 */

public class BmobTest extends baseActivity {

    @Override
    public void initData() {


        Manager manager = new Manager();
        manager.setHeadImg(null);
        manager.setName("test");
        manager.setNumber("000000");
        manager.setPassword("123");
        manager.setUsername("dadada");

//        File file = new File()


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
