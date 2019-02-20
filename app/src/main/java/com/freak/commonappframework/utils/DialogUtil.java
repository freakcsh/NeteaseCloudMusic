package com.freak.commonappframework.utils;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.freak.commonappframework.dialog.CommonDialogFragment;
import com.freak.commonappframework.dialog.PasswordDialogFragment;
import com.freak.commonappframework.dialog.ToastDialogFragment;


/**
 *
 * @author Administrator
 * @date 2018/12/17
 */

public class DialogUtil {
    /**
     * 通用弹窗、可自定义标题、内容、取消按钮和确定按钮
     * @param activity
     * @param title 标题
     * @param context 内容
     * @param cancel 取消按钮
     * @param commit 确定按钮
     * @param onTipsListener 回调接口
     */
    public static void showCommonDialog(AppCompatActivity activity, String title, String context, String cancel , String commit, CommonDialogFragment.OnTipsListener onTipsListener) {
        CommonDialogFragment dialogFragment = new CommonDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("context", context);
        args.putString("cancel", cancel);
        args.putString("commit", commit);
        dialogFragment.setArguments(args);
        dialogFragment.setCancelable(false);
        dialogFragment.setOnConfirmListener(onTipsListener);
        dialogFragment.show(activity.getSupportFragmentManager(), "");
    }

    /**
     * 温馨提示弹窗
     * @param activity
     */
    public static void showToastDialog(AppCompatActivity activity, String title, String context, String cancel) {
        ToastDialogFragment dialogFragment = new ToastDialogFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("context", context);
        args.putString("cancel", cancel);
        dialogFragment.setArguments(args);
        dialogFragment.setCancelable(false);
        dialogFragment.show(activity.getSupportFragmentManager(), "");
    }

    /**
     * 显示密码输入弹窗
     * @param activity
     * @param onTipsListener
     */
    public static void showPasswordDialog(AppCompatActivity activity, PasswordDialogFragment.OnTipsListener onTipsListener) {
        PasswordDialogFragment dialogFragment = new PasswordDialogFragment();
        dialogFragment.setCancelable(false);
        dialogFragment.setOnConfirmListener(onTipsListener);
        dialogFragment.show(activity.getSupportFragmentManager(), "");
    }
}
