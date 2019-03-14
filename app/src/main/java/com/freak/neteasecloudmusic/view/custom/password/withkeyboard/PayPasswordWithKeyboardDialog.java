package com.freak.neteasecloudmusic.view.custom.password.withkeyboard;

import android.app.AlertDialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import com.freak.neteasecloudmusic.R;


/**
 * 使用弹框作为容器
 * 使用介绍：
 * 方式一：直接new PayPassDialog(this)，将使用默认配置
 * 方式二:new PayPassDialog(this,自定义样式);
 * setAlertDialog
 * setWindowSize
 * 等等方式进行自主配置
 *
 * @author freak
 * @date 2019/2/19
 */

public class PayPasswordWithKeyboardDialog {
    /**
     * 弹框
     */
    private AlertDialog mDialog;
    /**
     * 窗口
     */
    private Window window;
    private Context mContext;
    /**
     * 主题
     */
    private int mThemeResId;
    private View mDialogLayout;


    /**
     * 默认样式
     *
     * @param context
     */
    public PayPasswordWithKeyboardDialog(Context context) {

        this.mContext = context;
        this.mThemeResId = R.style.dialog_pay_theme;
        this.mDialogLayout = LayoutInflater.from(mContext).inflate(R.layout.dialog_pay_password_with_keyboard, null);
        mDialog = new AlertDialog.Builder(mContext, mThemeResId).create();
        mDialog.setCancelable(true);
        mDialog.show();
        /**
         * 设置透明度0.4
         */
        mDialog.getWindow().setDimAmount(0.4f);
        window = mDialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        /**
         * 设置弹框布局
         */
        window.setContentView(mDialogLayout);
        mDialog.setCanceledOnTouchOutside(false);
        /**
         * 添加动画
         */
        window.setWindowAnimations(R.style.dialogOpenAnimation);
        window.setGravity(Gravity.BOTTOM);

    }

    /**
     * 得到PayPassView控件
     *
     * @return
     */
    public PayPasswordWithKeyboardView getPayViewPass() {
        return mDialogLayout.findViewById(R.id.pay_View);

    }

    /**
     * 自定义
     *
     * @param context
     * @param themeResId 主题样式
     */
    public PayPasswordWithKeyboardDialog(Context context, int themeResId) {
        this.mContext = context;
        this.mThemeResId = themeResId;
        this.mDialogLayout = LayoutInflater.from(mContext).inflate(R.layout.dialog_pay_password_with_keyboard, null);
    }

    /**
     * 初始化Dialog
     */
    public PayPasswordWithKeyboardDialog setAlertDialog() {
        mDialog = new AlertDialog.Builder(mContext, mThemeResId).create();
        //按返回键退出
        mDialog.setCancelable(true);
        mDialog.show();
        return this;
    }

    public PayPasswordWithKeyboardDialog setAlertDialog(boolean isBack) {
        mDialog = new AlertDialog.Builder(mContext, mThemeResId).create();
        //按返回键退出
        mDialog.setCancelable(isBack);
        mDialog.show();
        return this;
    }

    /**
     * 设置弹框大小 透明度
     */
    public PayPasswordWithKeyboardDialog setWindowSize(int width, int height, float amount) {
        //设置透明度
        mDialog.getWindow().setDimAmount(amount);
        window = mDialog.getWindow();
        window.setLayout(width, height);
        //设置弹框布局
        window.setContentView(mDialogLayout);
        return this;
    }

    /**
     * 设置弹框宽高   透明度
     * custom=2      自适应高度
     * custom=其他   指定高度
     */
    public PayPasswordWithKeyboardDialog setWindowSize(int width, int height, int custom, float amount) {
        if (custom == 2) {
            //设置透明度
            mDialog.getWindow().setDimAmount(amount);
            window = mDialog.getWindow();
            window.setLayout(width, LinearLayout.LayoutParams.WRAP_CONTENT);
            //设置弹框布局
            window.setContentView(mDialogLayout);
            return this;
        } else {
            //设置透明度
            mDialog.getWindow().setDimAmount(amount);
            window = mDialog.getWindow();
            window.setLayout(width, height);
            //设置弹框布局
            window.setContentView(mDialogLayout);
            return this;
        }

    }

    /**
     * 点击外部消失
     */
    public PayPasswordWithKeyboardDialog setOutColse(boolean isOut) {
        if (isOut) {
            mDialog.setCanceledOnTouchOutside(true);
        } else {
            mDialog.setCanceledOnTouchOutside(false);
        }
        return this;
    }

    /**
     * 方式 与位置
     */
    public PayPasswordWithKeyboardDialog setGravity(int animation, int gravity) {
        //添加动画
        window.setWindowAnimations(animation);
        window.setGravity(gravity);
        return this;
    }

    /**
     * 关闭
     */

    public void dismiss() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
            mDialog = null;
            window = null;
        }
    }

}
