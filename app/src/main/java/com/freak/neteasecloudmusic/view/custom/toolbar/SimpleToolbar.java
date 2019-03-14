package com.freak.neteasecloudmusic.view.custom.toolbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.freak.neteasecloudmusic.R;


/**
 * @author freak
 * @date 2019/2/19
 */
public class SimpleToolbar extends LinearLayout {

    private TextView mTitle;
    private TextView mTextViewLeftText;
    private TextView mTextViewRightText;
    private ImageView mRightIcon;
    private TextView mTextViewAngle;
    private RelativeLayout mRelativeLayoutRoot;
    private ImageView mLeftIcon;

    public SimpleToolbar(Context context) {
        this(context, null);
    }

    public SimpleToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.layout_simple_toolbar, this);
        mTitle = findViewById(R.id.tv_toolbar_title);
        mLeftIcon = findViewById(R.id.iv_toolbar_left_icon);
        mRightIcon = findViewById(R.id.iv_toolbar_right_icon);
        mRelativeLayoutRoot = findViewById(R.id.root);
        mTextViewAngle = findViewById(R.id.text_view_angle);
        mTextViewLeftText = findViewById(R.id.text_view_left_text);
        mTextViewRightText = findViewById(R.id.text_view_right_text);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.SimpleToolbar);
        if (ta.hasValue(R.styleable.SimpleToolbar_tool_title)) {
            mTitle.setText(ta.getString(R.styleable.SimpleToolbar_tool_title));
        }
        if (ta.hasValue(R.styleable.SimpleToolbar_tool_left_text)) {
            mTextViewLeftText.setVisibility(VISIBLE);
            mTextViewLeftText.setText(ta.getString(R.styleable.SimpleToolbar_tool_left_text));
        }
        if (ta.hasValue(R.styleable.SimpleToolbar_tool_right_text)) {
            mTextViewRightText.setVisibility(VISIBLE);
            mTextViewRightText.setText(ta.getString(R.styleable.SimpleToolbar_tool_right_text));
        }
        if (ta.hasValue(R.styleable.SimpleToolbar_tool_left_icon)) {
            mLeftIcon.setImageResource(ta.getResourceId(R.styleable.SimpleToolbar_tool_left_icon, 0));
        }
        if (ta.hasValue(R.styleable.SimpleToolbar_tool_right_icon)) {
            mRightIcon.setImageResource(ta.getResourceId(R.styleable.SimpleToolbar_tool_right_icon, 0));
        }
        ta.recycle();
    }


    public SimpleToolbar setTitleName(String titleName) {
        mTitle.setText(titleName);
        return this;
    }

    public RelativeLayout getRoot() {
        return mRelativeLayoutRoot;
    }

    public SimpleToolbar setRightIcon(int imgRes) {
        mRightIcon.setImageResource(imgRes);
        return this;
    }

    public void setAngle(int number) {
        if (number > 0) {
            mTextViewAngle.setText(number + "");
            mTextViewAngle.setVisibility(VISIBLE);
        } else {
            mTextViewAngle.setVisibility(GONE);
        }
    }

    public SimpleToolbar setRightIconClickListener(OnClickListener listener) {
        mRightIcon.setOnClickListener(listener);
        return this;
    }

    public SimpleToolbar setRightTextClickListener(OnClickListener listener) {
        mTextViewRightText.setOnClickListener(listener);
        return this;
    }

    public SimpleToolbar setLeftTextClickListener(OnClickListener listener) {
        mTextViewLeftText.setOnClickListener(listener);
        return this;
    }

    public SimpleToolbar setLeftIconClickListener(OnClickListener listener) {
        mLeftIcon.setOnClickListener(listener);
        return this;
    }
}
