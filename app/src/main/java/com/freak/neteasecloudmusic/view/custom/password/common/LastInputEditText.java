package com.freak.neteasecloudmusic.view.custom.password.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;


/**
 * 自定义EditText
 *
 * @author freak
 * @date 2019/2/19
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
