package com.freak.neteasecloudmusic.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;


import java.lang.reflect.Method;

/**
 * 打开或关闭软键盘
 *
 * @author zhy
 */
public class KeyBoardUtils {


    /**
     * 打卡软键盘
     *
     * @param mEditText 输入框
     * @param mContext  上下文
     */
    public static void openKeybord(EditText mEditText, Context mContext) {
        InputMethodManager imm = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(mEditText, InputMethodManager.RESULT_SHOWN);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,
                InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    /**
     * 关闭软键盘
     *
     * @param mEditText 输入框
     * @param mContext  上下文
     */
    public static void closeKeybord(EditText mEditText, Context mContext) {
        InputMethodManager imm = (InputMethodManager) mContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
    }

    /**
     * 屏蔽软键盘
     *
     * @param ed
     */
    public static void hideSoftInputMethod(Activity act, EditText ed) {
        if (android.os.Build.VERSION.SDK_INT <= 10) {
            ed.setInputType(InputType.TYPE_NULL);
        } else {
            act.getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            try {
                Class<EditText> cls = EditText.class;
                Method setShowSoftInputOnFocus;
                setShowSoftInputOnFocus = cls.getMethod(
                        "setShowSoftInputOnFocus", boolean.class);
                setShowSoftInputOnFocus.setAccessible(true);
                setShowSoftInputOnFocus.invoke(ed, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 屏蔽软键盘
     *
     * @param ed
     */
    public static void hideSoftInputMethod(Dialog dialog, EditText ed) {
        if (android.os.Build.VERSION.SDK_INT <= 10) {
            ed.setInputType(InputType.TYPE_NULL);
        } else {
            dialog.getWindow().setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            try {
                Class<EditText> cls = EditText.class;
                Method setShowSoftInputOnFocus;
                setShowSoftInputOnFocus = cls.getMethod(
                        "setShowSoftInputOnFocus", boolean.class);
                setShowSoftInputOnFocus.setAccessible(true);
                setShowSoftInputOnFocus.invoke(ed, false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 显示系统软键盘
     */
    @SuppressLint("ClickableViewAccessibility")
    public static void showSystemKeyBord(final Context context, final EditText ed) {
        ed.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                openKeybord(ed, context);
                return false;
            }
        });
    }
}
