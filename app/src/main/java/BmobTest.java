import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.swapanytime.R;

import cn.bmob.v3.Bmob;

/**
 * Created by Administrator on 2017/9/30.
 */

public class BmobTest extends Activity{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initdata();
    }

    private void initdata() {
        Bmob.initialize(this, "0126923760b21776be65e5674cdef780");
    }
}
