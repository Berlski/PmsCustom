package com.berlski.tool.custom.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.berlski.tool.custom.R;
import com.berlski.tool.custom.manager.MyActivityManager;
import com.wang.avi.AVLoadingIndicatorView;

/**
 * 加载中Dialog
 *
 * @author hzb
 */
public class LoadingDialog extends Dialog {

    private static LoadingDialog loadingDialog;
    //private static Dialog dialog;

    public static LoadingDialog builder(Context context) {
        /*if (loadingDialog != null) {
            loadingDialog.dismiss();
        }*/

        if (loadingDialog == null || !loadingDialog.isShowing()) {
            loadingDialog = new LoadingDialog(context); //设置AlertDialog背景透明
        }

        return loadingDialog;
    }

    /*public static LoadingDialog getInstance(Context context) {
        //context = MyActivityManager.getInstance().getCurrentActivity();
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(context);
        }
        return loadingDialog;
    }*/

    public LoadingDialog(Context context) {
        super(context, R.style.ShareDialog);
        //super(context,themeResId);

        // 定义Dialog布局和参数
        //dialog = new Dialog(context, R.style.ActionSheetDialogStyle);
        setContentView(R.layout.dialog_loading);
        Window dialogWindow = getWindow();
        dialogWindow.setGravity(Gravity.CENTER);

        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialogWindow.setAttributes(lp);

        // 设置Dialog最小宽度为屏幕宽度
        //contenView.setMinimumWidth(Constants.SCREEN_WIDTH);

        final AVLoadingIndicatorView avi = (AVLoadingIndicatorView) findViewById(R.id.avi);

        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                //dialogInterfaceCallBack.dismiss();
                avi.hide();
            }
        });

        setOnShowListener(new OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                avi.show();
            }
        });

        setCancelable(false);
        setCanceledOnTouchOutside(false);
    }

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dialog_loading);
        avi = (AVLoadingIndicatorView)this.findViewById(R.id.avi);
    }*/

    //@Override
    public void show() {
        try {
            super.show();
        } catch (WindowManager.BadTokenException exception) {
            loadingDialog = new LoadingDialog(MyActivityManager.getInstance().getCurrentActivity());
            loadingDialog.show();
        }
    }

    //@Override
    public void dismiss() {
        try {
            if (loadingDialog != null && loadingDialog.isShowing()) {
                super.dismiss();
            }
        } catch (WindowManager.BadTokenException exception) {

        }
    }
}