package fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.swapanytime.R;

import java.util.ArrayList;
import java.util.List;

import adapter.SimpleRecycleViewAdapter;

/**
 * Created by weijie on 2018/1/12.
 */

public class mBottomFragment extends BottomSheetDialogFragment {

    private Context context = null;
    private ViewGroup viewGroup = null;
    private List<String> data_left = new ArrayList<>();
    private List<String> data_right = new ArrayList<>();

    public ViewGroup getViewGroup() {
        return viewGroup;
    }

    public mBottomFragment setParams(ViewGroup viewGroup, List<String> data_left, List<String> data_right) {
        this.data_left = data_left;
        this.data_right = data_right;
        this.viewGroup = viewGroup;
        return this;
    }

    public mBottomFragment() {

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(this.getContext()).inflate(R.layout.bottomdialog_type, container, false);

//        ListView left = (ListView) view.findViewById(R.id.bottomdialog_type_left);
//        ListView right = (ListView) view.findViewById(R.id.bottomdialog_type_right);
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_list_item_1,data_left);
//        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_list_item_1, data_right);
        RecyclerView left = (RecyclerView) view.findViewById(R.id.bottomdialog_type_left);
        RecyclerView right = (RecyclerView) view.findViewById(R.id.bottomdialog_type_right);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext());
        SimpleRecycleViewAdapter simpleRecycleViewAdapter1 = new SimpleRecycleViewAdapter(this.getContext(), data_left, viewGroup);
        SimpleRecycleViewAdapter simpleRecycleViewAdapter2 = new SimpleRecycleViewAdapter(this.getContext(), data_right, viewGroup);

        left.setLayoutManager(linearLayoutManager);
        right.setLayoutManager(linearLayoutManager1);
        left.setAdapter(simpleRecycleViewAdapter1);
        right.setAdapter(simpleRecycleViewAdapter2);

        return view;
    }


}
