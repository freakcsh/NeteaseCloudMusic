package com.freak.commonappframework.view.custom.password.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * 自定义EditText
 *
 * @author Administrator
 * @date 2018/12/26
 */

@SuppressLint("AppCompatCustomView")
public class LastInputEditText extends EditText {
    public LastInputEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public LastInputEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LastInputEditText(Context context) {
        super(context);
    }

    @Override
    public void onSelectionChanged(int selStart, int selEnd) {
        //保证光标始终在最后面
        super.onSelectionChanged(selStart, selEnd);
        //防止不能多选
        if (selStart == selEnd) {
            setSelection(getText().length());
        }
    }
}
