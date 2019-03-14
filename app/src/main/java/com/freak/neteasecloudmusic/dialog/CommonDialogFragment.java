package com.freak.neteasecloudmusic.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.TextView;

import com.freak.neteasecloudmusic.R;


/**
 * 显示系統提示的对话框，可以自行设置题目，内容，取消，成功
 *
 * @author freak
 * @date 2019/2/19
 */
public class CommonDialogFragment extends DialogFragment implements View.OnClickListener {

    private Dialog mDialog;
    private TextView mTextViewCommonTitle;
    private TextView mTextViewCommonContext;
    private TextView mTextViewCancel;
    private TextView mTextViewCommit;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mDialog = new Dialog(getActivity(), R.style.dialog);
        mDialog.setContentView(R.layout.fragment_dialog_common);
        mDialog.setCancelable(true);

        mTextViewCommonTitle = (TextView) mDialog.findViewById(R.id.text_view_common_title);
        mTextViewCommonContext = (TextView) mDialog.findViewById(R.id.text_view_common_context);
        mTextViewCancel = (TextView) mDialog.findViewById(R.id.text_view_cancel);
        mTextViewCommit = (TextView) mDialog.findViewById(R.id.text_view_commit);


        mTextViewCommonTitle.setText(getArguments().getString("title"));
        mTextViewCommonContext.setText(getArguments().getString("context"));
        mTextViewCancel.setText(getArguments().getString("cancel"));
        mTextViewCommit.setText(getArguments().getString("commit"));
        mTextViewCancel.setOnClickListener(this);
        mTextViewCommit.setOnClickListener(this);

        return mDialog;
    }

    private OnTipsListener onTipsListener;

    public void setOnConfirmListener(OnTipsListener onTipsListener) {
        this.onTipsListener = onTipsListener;
    }

    public interface OnTipsListener {
        /**
         * 返回
         */
        void onCancel();

        /**
         * 确定
         *
         * @param dialog
         */
        void onSuccess(Dialog dialog);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_view_cancel:
                mDialog.dismiss();
                if (onTipsListener != null) {
                    onTipsListener.onCancel();
                }
                break;
            case R.id.text_view_commit:
                if (onTipsListener != null) {
                    onTipsListener.onSuccess(mDialog);
                }
                break;
            default:
                break;
        }
    }
}

