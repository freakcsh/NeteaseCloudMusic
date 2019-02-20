package com.freak.commonappframework.view.custom.password.common;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import com.freak.commonappframework.R;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * 方格6位支付密码，不带输入键盘
 *
 * @author Administrator
 * @date 2018/12/26
 */

public class PasswordView extends LinearLayout {
    private LastInputEditText mEditTextOne;
    private LastInputEditText mEditTextTwo;
    private LastInputEditText mEditTextThree;
    private LastInputEditText mEditTextFour;
    private LastInputEditText mEditTextFive;
    private LastInputEditText mEditTextSix;
    private Context mContext;
    private View mView;
    private StringBuilder stringBuilder;
    private InputMethodManager imm;

    public PasswordView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        imm = (InputMethodManager) mContext.getSystemService(INPUT_METHOD_SERVICE);
        initView();
    }

    private void initView() {
        mView = LayoutInflater.from(mContext).inflate(R.layout.view_layout_password, this, false);
        mEditTextOne = mView.findViewById(R.id.edit_text_one);
        mEditTextTwo = mView.findViewById(R.id.edit_text_two);
        mEditTextThree = mView.findViewById(R.id.edit_text_three);
        mEditTextFour = mView.findViewById(R.id.edit_text_four);
        mEditTextFive = mView.findViewById(R.id.edit_text_five);
        mEditTextSix = mView.findViewById(R.id.edit_text_six);

        mEditTextOne.addTextChangedListener(textWatcher);
        mEditTextTwo.addTextChangedListener(textWatcher);
        mEditTextThree.addTextChangedListener(textWatcher);
        mEditTextFour.addTextChangedListener(textWatcher);
        mEditTextFive.addTextChangedListener(textWatcher);
        mEditTextSix.addTextChangedListener(textWatcher);


        mEditTextOne.setOnKeyListener(onKeyListener);
        mEditTextTwo.setOnKeyListener(onKeyListener);
        mEditTextThree.setOnKeyListener(onKeyListener);
        mEditTextFour.setOnKeyListener(onKeyListener);
        mEditTextFive.setOnKeyListener(onKeyListener);
        mEditTextSix.setOnKeyListener(onKeyListener);

        mEditTextOne.setTransformationMethod(passwordTransformationMethod);
        mEditTextTwo.setTransformationMethod(passwordTransformationMethod);
        mEditTextThree.setTransformationMethod(passwordTransformationMethod);
        mEditTextFour.setTransformationMethod(passwordTransformationMethod);
        mEditTextFive.setTransformationMethod(passwordTransformationMethod);
        mEditTextSix.setTransformationMethod(passwordTransformationMethod);

        mView.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
        this.addView(mView);
    }

    private TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            if (s.toString().length() == 1) {
                if (mEditTextOne.isFocused()) {
                    mEditTextTwo.requestFocus();
                } else if (mEditTextTwo.isFocused()) {
                    mEditTextThree.requestFocus();
                } else if (mEditTextThree.isFocused()) {
                    mEditTextFour.requestFocus();
                } else if (mEditTextFour.isFocused()) {
                    mEditTextFive.requestFocus();
                } else if (mEditTextFive.isFocused()) {
                    mEditTextSix.requestFocus();
                } else if (mEditTextSix.isFocused()) {
                    if (getValue().toString().length() == 6) {
                        if (mOnInputEndListener != null) {
                            mOnInputEndListener.onInputEnd(getValue().toString());
                        }
                    }
                }
            }
        }
    };


    private PasswordTransformationMethod passwordTransformationMethod = new PasswordTransformationMethod() {
        @Override
        public CharSequence getTransformation(CharSequence source, View view) {
            return new PasswordCharSequence(source);
        }

    };

    private OnKeyListener onKeyListener = new OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_DEL && event.getAction() == KeyEvent.ACTION_DOWN) {
                if (mEditTextSix.isFocused() && mEditTextSix.getText().length() == 0) {
                    mEditTextFive.requestFocus();
                    mEditTextFive.setText("");
                } else if (mEditTextFive.isFocused() && mEditTextFive.getText().length() == 0) {
                    mEditTextFour.requestFocus();
                    mEditTextFour.setText("");
                } else if (mEditTextFour.isFocused() && mEditTextFour.getText().length() == 0) {
                    mEditTextThree.requestFocus();
                    mEditTextThree.setText("");
                } else if (mEditTextThree.isFocused() && mEditTextThree.getText().length() == 0) {
                    mEditTextTwo.requestFocus();
                    mEditTextTwo.setText("");
                } else if (mEditTextTwo.isFocused() && mEditTextTwo.getText().length() == 0) {
                    mEditTextOne.requestFocus();
                    mEditTextOne.setText("");
                }
            }
            return false;
        }
    };

    class PasswordCharSequence implements CharSequence {
        private CharSequence mSource;

        public PasswordCharSequence(CharSequence source) {
            mSource = source;
        }

        @Override
        public int length() {
            return mSource.length();
        }

        @Override
        public char charAt(int index) {
            return '●';
        }

        @Override
        public CharSequence subSequence(int start, int end) {
            return mSource.subSequence(start, end);
        }
    }

    private OnInputEndListener mOnInputEndListener;

    public OnInputEndListener getOnInputEndListener() {
        return mOnInputEndListener;
    }

    public void setOnInputEndListener(OnInputEndListener onInputEndListener) {
        mOnInputEndListener = onInputEndListener;
    }

    public interface OnInputEndListener {
        /**
         * 输入完成
         * @param password
         */
        void onInputEnd(String password);
    }

    private StringBuilder getValue() {
        stringBuilder = null;
        stringBuilder = new StringBuilder();
        return stringBuilder.append(mEditTextOne.getText().toString())
                .append(mEditTextTwo.getText().toString())
                .append(mEditTextThree.getText().toString())
                .append(mEditTextFour.getText().toString())
                .append(mEditTextFive.getText().toString())
                .append(mEditTextSix.getText().toString());
    }

}
