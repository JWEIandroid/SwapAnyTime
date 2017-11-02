package com.example.swapanytime;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import adapter.Type_left_adapter;
import adapter.Type_right_adapter;
import base.baseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import entiry.SortDetail;

/**
 * Created by weijie on 2017/9/29.
 */

public class ShowTypeActivity extends baseActivity {

    @Bind(R.id.titlebar_title)
    TextView titlebarTitle;
    @Bind(R.id.left_)
    RecyclerView left;
    @Bind(R.id.right_)
    RecyclerView right;
    @Bind(R.id.ic_back)
    ImageView icBack;

    private String TAG = getTAG();
    List<String> list = new ArrayList<>();
    List<SortDetail> right_data;
    static List<String> left_data;
    static List<String> left_data_after;


    @Override
    public void initData() {

        right_data = new ArrayList<>();
        left_data = new ArrayList<>();
        left_data_after = new ArrayList<>();


        //初始化左边数据
//        for (int i = 1; i < 31; i++) {
//            list.add("种类 " + i);
//        }

        String[] array_type = this.getResources().getStringArray(R.array.left);
        String[] type_detail_before = this.getResources().getStringArray(R.array.right);
        String[][] array_type_detail = getTwoDimensionalArray(type_detail_before);


//
        for (int i = 0; i < array_type.length; i++) {
            left_data.add(array_type[i]);
        }


//        初始化右边数据
        for (int i = 0; i < left_data.size(); i++) {

            SortDetail head = new SortDetail();
            head.setIstitle(true);
            head.setTitlename(left_data.get(i));
            head.setTag(i);
            right_data.add(head);
            Log.d(getTAG(), "initData: " + head.getTitlename());

            left_data_after.add(left_data.get(i));

            for (int j = 0; j < array_type_detail.length - 1; j++) {
                if (array_type_detail[i][j] != null) {
                    SortDetail sortDetail = new SortDetail();
                    sortDetail.setIstitle(false);
                    sortDetail.setName(array_type_detail[i][j]);
                    sortDetail.setTag(i);
                    right_data.add(sortDetail);
                    Log.d(getTAG(), "initData2: " + sortDetail.getName());

                    left_data_after.add("");
                }
            }

        }


        Log.d(getTAG(), "right: " + right_data.size());
        Log.d(TAG, "left: " + left_data_after.size());


    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
    }

    @Override
    public Object getContentView() {
        return R.layout.activity_showtype;
    }

    private final LinearLayoutManager leftmanager = new LinearLayoutManager(this);
    private LinearLayoutManager rightmanager = new LinearLayoutManager(this);
    private GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);

    private boolean move;
    private int mIndex;

    /**
     * 按设定规则解析一维数组为二维数组
     *
     * @param array
     * @return
     */
    private String[][] getTwoDimensionalArray(String[] array) {
        String[][] twoDimensionalArray = null;
        for (int i = 0; i < array.length; i++) {
            String[] tempArray = array[i].split(",");
            if (twoDimensionalArray == null) {
                twoDimensionalArray = new String[array.length][8];
            }
            for (int j = 0; j < tempArray.length; j++) {
                twoDimensionalArray[i][j] = tempArray[j];
            }
        }
        return twoDimensionalArray;
    }


    @Override
    public void initEvent() {

        final Type_left_adapter left_adapter = new Type_left_adapter(ShowTypeActivity.this, left_data_after);
        Type_right_adapter right_adapter = new Type_right_adapter(ShowTypeActivity.this, right_data);

        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return right_data.get(position).istitle() ? 3 : 1;
            }
        });

        left.setLayoutManager(leftmanager);
        right.setLayoutManager(gridLayoutManager);

        left_adapter.setOnItemClickListener(new Type_left_adapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                mIndex = position;
//                Log.d(getTAG(), "onItemClick: " + position);
                smoothMoveToPosition(right, position);
//                right.scrollToPosition(position);
            }
        });
        left.setAdapter(left_adapter);
        right.setAdapter(right_adapter);
        right.addOnScrollListener(new RecyclerViewListener());

        icBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goActivity(MainActivity.class);
            }
        });


    }

    private void smoothMoveToPosition(RecyclerView rv, int n) {
        int firstItem = gridLayoutManager.findFirstVisibleItemPosition();
        int lastItem = gridLayoutManager.findLastVisibleItemPosition();
        if (n <= firstItem) {
//            rv.scrollToPosition(n);
            rv.smoothScrollToPosition(n);
        } else if (n <= lastItem) {
            int top = rv.getChildAt(n - firstItem).getTop();
            rv.smoothScrollBy(0, top);
        } else {
            rv.scrollToPosition(n);
            move = true;
        }
    }

    class RecyclerViewListener extends RecyclerView.OnScrollListener {


        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (move && newState == RecyclerView.SCROLL_STATE_IDLE) {
                move = false;
                int n = mIndex - gridLayoutManager.findFirstVisibleItemPosition();
                if (0 <= n && n < recyclerView.getChildCount()) {
                    int top = recyclerView.getChildAt(n).getTop();
                    recyclerView.smoothScrollBy(0, top);
                }

            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (move) {
                move = false;
                int n = mIndex - gridLayoutManager.findFirstVisibleItemPosition();
                if (0 <= n && n < recyclerView.getChildCount()) {
                    int top = recyclerView.getChildAt(n).getTop();
                    recyclerView.smoothScrollBy(0, top);
                }
            }

        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, TAG+" onCreate time is: "+System.currentTimeMillis());
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, TAG+"onDestroy time is "+System.currentTimeMillis());


    }
}
