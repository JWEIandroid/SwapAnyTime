package minterface;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import adapter.RecordApapter;
import minterface.ItemTouchHelperAdapter;

/**
 * Created by weij on 2018/2/1.
 */

public class mcallback extends ItemTouchHelper.Callback {

    private ItemTouchHelperAdapter mAdapter;
    //限制ImageView长度所能增加的最大值
    private double ICON_MAX_SIZE = 20;
    //ImageView的初始长宽
    private int fixWidth = 50;


    public mcallback(ItemTouchHelperAdapter mAdapter) {
        this.mAdapter = mAdapter;
    }

    //用于返回可以滑动的方向
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.LEFT;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        mAdapter.onItemDismiss(viewHolder.getAdapterPosition());
    }


    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        viewHolder.itemView.setScrollX(0);
        ((RecordApapter.RecordHolder) viewHolder).record_item_del_btn.setText("左滑删除");
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) ((RecordApapter.RecordHolder) viewHolder).img.getLayoutParams();
        params.width = 50;
        params.height = 50;
        ((RecordApapter.RecordHolder) viewHolder).img.setLayoutParams(params);
        ((RecordApapter.RecordHolder) viewHolder).img.setVisibility(View.INVISIBLE);
    }


    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        //仅对侧滑状态下的效果做出改变
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            //如果dX小于等于删除方块的宽度，那么我们把该方块滑出来
            if (Math.abs(dX) <= getSlideLimitation(viewHolder)) {
                viewHolder.itemView.scrollTo(-(int) dX, 0);
            }
            //如果dX还未达到能删除的距离，此时慢慢增加“眼睛”的大小，增加的最大值为ICON_MAX_SIZE
            else if (Math.abs(dX) <= recyclerView.getWidth() / 2) {
                double distance = (recyclerView.getWidth() / 2 - getSlideLimitation(viewHolder));
                double factor = ICON_MAX_SIZE / distance;
                double diff = (Math.abs(dX) - getSlideLimitation(viewHolder)) * factor;
                if (diff >= ICON_MAX_SIZE)
                    diff = ICON_MAX_SIZE;
                ((RecordApapter.RecordHolder) viewHolder).record_item_del_btn.setText("");   //把文字去掉
                ((RecordApapter.RecordHolder) viewHolder).img.setVisibility(View.VISIBLE);  //显示眼睛
                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) ((RecordApapter.RecordHolder) viewHolder).img.getLayoutParams();
                params.width = (int) (fixWidth + diff);
                params.height = (int) (fixWidth + diff);
                ((RecordApapter.RecordHolder) viewHolder).img.setLayoutParams(params);
            }
        } else {
            //拖拽状态下不做改变，需要调用父类的方法
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    }

    /**
     * 获取删除方块的宽度
     */
    public int getSlideLimitation(RecyclerView.ViewHolder viewHolder) {
        ViewGroup viewGroup = (ViewGroup) viewHolder.itemView;
        return viewGroup.getChildAt(1).getLayoutParams().width;
    }
}
