package com.berlski.tool.custom.manager;

import android.app.Activity;

import com.berlski.tool.custom.dialog.LoadingDialog;

public class DialogManager {

    public static Activity getActivity() {
        return MyActivityManager.getInstance().getCurrentActivity();
    }

    public static void showLoadingDialog() {
        LoadingDialog.builder(getActivity()).show();
    }

    public static void dismissLoadingDialog() {
        LoadingDialog.builder(getActivity()).dismiss();
    }
}
