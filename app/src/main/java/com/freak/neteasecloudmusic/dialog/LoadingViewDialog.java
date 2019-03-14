package com.freak.neteasecloudmusic.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.freak.neteasecloudmusic.R;
import com.wang.avi.AVLoadingIndicatorView;


/**
 * @author freak
 * @date 2019/2/19
 */

public class LoadingViewDialog extends DialogFragment {

    private AVLoadingIndicatorView mAVLoadingIndicatorView;
    private TextView mTextViewTitle;

    private String mTitle;
    private static LoadingViewDialog mViewDialog;

    private boolean isShow;


    public static LoadingViewDialog getInstance() {
        if (mViewDialog == null) {
            mViewDialog = new LoadingViewDialog();
        }
        return mViewDialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        if (window != null) {
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.dimAmount = 0.0f;
            window.setAttributes(layoutParams);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().setCanceledOnTouchOutside(false);


        View view = inflater.inflate(R.layout.layout_loading_view, null);
        mAVLoadingIndicatorView = view.findViewById(R.id.av_loading_view);
        mTextViewTitle = view.findViewById(R.id.text_view_loading_title);

        if (!TextUtils.isEmpty(mTitle)) {
            mTextViewTitle.setText(mTitle);
        }
        return view;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }


    public void setTitle(String title) {
        mTitle = title;
    }

    public void show(Activity activity) {
        if (mAVLoadingIndicatorView != null) {
            if (mAVLoadingIndicatorView.getVisibility() == View.VISIBLE) {
                return;
            }
            if (mAVLoadingIndicatorView.getVisibility() == View.GONE) {
                mAVLoadingIndicatorView.setVisibility(View.VISIBLE);
                mAVLoadingIndicatorView.show();
            }
        }
        if (!isVisible()) {
            isShow = true;
            if (activity != null) {
                super.show(activity.getFragmentManager(), "LoadingViewDialog");
            }

        }
    }

    @Override
    public void dismiss() {
//        if (isShow) {
//            if (isVisible()) {
//                if (mAVLoadingIndicatorView != null) {
//                    if (mAVLoadingIndicatorView.getVisibility() == View.VISIBLE) {
//                        mAVLoadingIndicatorView.setVisibility(View.GONE);
//                    }
//                    mAVLoadingIndicatorView.hide();
//                }
//                if (isShow) {
//                    isShow = false;
////                    if (mViewDialog != null && mViewDialog.getDialog() != null && mViewDialog.getDialog().isShowing()){
////                        mViewDialog.dismissAllowingStateLoss();
////                    }
//                        super.dismiss();
//                }
//
//
//            } else {
//                if (isShow) {
//                    isShow = false;
////                    if (mViewDialog != null && mViewDialog.getDialog() != null && mViewDialog.getDialog().isShowing()){
////                        mViewDialog.dismissAllowingStateLoss();
////                    }
//                    super.dismiss();
//                }
//            }
//        }
        if (mViewDialog != null && mViewDialog.getDialog() != null && mViewDialog.getDialog().isShowing()) {
            mViewDialog.dismissAllowingStateLoss();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        isShow = false;
    }

    @Override
    public void onDestroyView() {
        if (mAVLoadingIndicatorView != null) {
            mAVLoadingIndicatorView.hide();
        }
        isShow = false;
        super.onDestroyView();
    }
}
