package utils;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


public class DialogUtil {

    /**
     * 设置dialog宽度满屏
     * @param dialog
     */
    public static void setDialogFullScreenWidth(Dialog dialog){
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        //填充宽度
        dialogWindow.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialogWindow.setAttributes(lp);
    }
    /**
     * 底部弹出窗(宽度满屏)
     *
     * @param layoutId
     * @param themeResId
     * @param context
     * @param dialogInitListener 预留接口
     * @return
     */
    public static Dialog showBottomDialog(int layoutId,int themeResId, Context context, IDialogInitListener dialogInitListener) {
        Dialog dialog = new Dialog(context,themeResId);
        View dialogView = LayoutInflater.from(context).inflate(layoutId, null);
        if (dialogInitListener != null) {
            dialogInitListener.initDialogView(dialogView); //预留接口
        }
        dialog.setContentView(dialogView);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        //填充宽度
        dialogWindow.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialogWindow.setAttributes(lp);

        return dialog;
    }



    public interface IDialogInitListener {

        void initDialogView(View view);
    }
}
