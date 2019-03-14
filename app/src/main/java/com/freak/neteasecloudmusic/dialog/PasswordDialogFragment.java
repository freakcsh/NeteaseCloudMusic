package com.freak.neteasecloudmusic.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.LinearLayout;

import com.freak.neteasecloudmusic.R;
import com.freak.neteasecloudmusic.view.custom.password.common.PasswordView;


/**
 * 显示密码输入框
 *
 * @author freak
 * @date 2019/2/19
 */
public class PasswordDialogFragment extends DialogFragment implements View.OnClickListener {

    private Dialog mDialog;
    private LinearLayout mLinearLayoutCancel;
    private PasswordView mPasswordView;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mDialog = new Dialog(getActivity(), R.style.password_dialog);
        mDialog.setContentView(R.layout.fragment_dialog_password);
        mDialog.setCancelable(true);

        mLinearLayoutCancel = mDialog.findViewById(R.id.linear_layout_cancel);
        mPasswordView = mDialog.findViewById(R.id.edit_text_password);
        mLinearLayoutCancel.setOnClickListener(this);
        mPasswordView.setOnInputEndListener(new PasswordView.OnInputEndListener() {
            @Override
            public void onInputEnd(String password) {
                if (onTipsListener != null) {
                    onTipsListener.onSuccess(mDialog, password);
                }
            }
        });
        return mDialog;
    }

    private OnTipsListener onTipsListener;

    public void setOnConfirmListener(OnTipsListener onTipsListener) {
        this.onTipsListener = onTipsListener;
    }

    public interface OnTipsListener {
        /**
         * 输入密码完成
         *
         * @param dialog
         * @param password
         */
        void onSuccess(Dialog dialog, String password);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_layout_cancel:
                mDialog.dismiss();
                break;
            default:
                break;
        }
    }
}

