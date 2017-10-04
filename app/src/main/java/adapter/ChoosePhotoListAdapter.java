package adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import cn.finalteam.toolsfinal.DeviceUtils;

/**
 * Created by weijie on 2017/10/5.
 */

public class ChoosePhotoListAdapter extends BaseAdapter {

    private List mlist;
    private LayoutInflater layoutInflater;
    private int mScreenWidth;


    public ChoosePhotoListAdapter(Activity activity, List list) {

        this.mlist = list;
        this.layoutInflater = LayoutInflater.from(activity);
        this.mScreenWidth = DeviceUtils.getScreenPix(activity).widthPixels;
    }

    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        return null;
    }
}
