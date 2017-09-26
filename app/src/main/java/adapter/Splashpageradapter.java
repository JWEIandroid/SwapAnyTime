package adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by weijie on 2017/9/24.
 */

public class Splashpageradapter extends FragmentPagerAdapter {


    public Splashpageradapter(FragmentManager fragmentManager, List list) {
        super(fragmentManager);
        fragments = list;
    }

    private List<Fragment> fragments = new ArrayList<Fragment>();

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    //去掉super(),不销毁view
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }

    private int titles[] = {1, 2, 3};

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position]+" ";
    }
}
