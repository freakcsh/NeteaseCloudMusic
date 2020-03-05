package com.freak.neteasecloudmusic.dialog;

import android.app.Dialog;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.freak.neteasecloudmusic.R;
import com.freak.neteasecloudmusic.dialog.adapter.FiltrateDialogAdapter;
import com.freak.neteasecloudmusic.modules.find.recommend.entity.HotSongListCategoryEntity;
import com.freak.neteasecloudmusic.net.log.LogUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * 显示系統提示的对话框，可以自行设置题目，内容，取消，成功
 *
 * @author freak
 * @date 2019/2/19
 */
public class FiltrateDialogFragment extends DialogFragment implements View.OnClickListener {

    private Dialog mDialog;
    private RecyclerView mRecycleViewFiltrateDialog;
    private ImageView mImgFiltrateDialogClose;
    private FiltrateDialogAdapter mFiltrateDialogAdapter;
    private List<HotSongListCategoryEntity.TagsBean> mList;
    private HotSongListCategoryEntity mHotSongListCategoryEntity;
    private RelativeLayout mRelativeLayoutItemFiltrateHead;
    private TextView mTextViewItemFiltrateHead;
    private View mHeadView;
    public static String category = "";

    @Override

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mDialog = new Dialog(getActivity(), R.style.dialog);
        mDialog.setContentView(R.layout.dialog_fragment_filtrate);
        getActivity().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        mDialog.setCancelable(true);
        mList = new ArrayList<>();
        mHotSongListCategoryEntity = (HotSongListCategoryEntity) getArguments().getSerializable("hotSongListCategoryEntity");
        category = getArguments().getString("category");
        if (mHotSongListCategoryEntity != null) {
            mList.addAll(mHotSongListCategoryEntity.getTags());
        }
        LogUtil.e("传值"+mHotSongListCategoryEntity.toString());
        initHeadView();
        mImgFiltrateDialogClose = (ImageView) mDialog.findViewById(R.id.img_filtrate_dialog_close);
        mRecycleViewFiltrateDialog = (RecyclerView) mDialog.findViewById(R.id.recycle_view_filtrate_dialog);
        mRecycleViewFiltrateDialog.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        mFiltrateDialogAdapter = new FiltrateDialogAdapter(R.layout.item_filtract_dialgo, mList);
        mFiltrateDialogAdapter.addHeaderView(mHeadView);
        mFiltrateDialogAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                LogUtil.e("点击");
                category = mList.get(position).getName();
                if (onTipsListener != null) {
                    onTipsListener.onSuccess(mDialog, category);
                }
            }
        });
        mRecycleViewFiltrateDialog.setAdapter(mFiltrateDialogAdapter);
        mImgFiltrateDialogClose.setOnClickListener(this);

        return mDialog;
    }

    private void initHeadView() {
        mHeadView = LayoutInflater.from(getActivity()).inflate(R.layout.item_filtract_dialgo_head, null);
        mRelativeLayoutItemFiltrateHead = mHeadView.findViewById(R.id.relative_layout_item_filtrate_head);
        mTextViewItemFiltrateHead = mHeadView.findViewById(R.id.text_view_item_filtrate_head);
        if ("全部".equals(category)) {
            mRelativeLayoutItemFiltrateHead.setSelected(true);
        } else {
            mRelativeLayoutItemFiltrateHead.setSelected(false);
        }
        mRelativeLayoutItemFiltrateHead.setOnClickListener(this);
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
        void onSuccess(Dialog dialog, String category);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_filtrate_dialog_close:
                mDialog.dismiss();
                if (onTipsListener != null) {
                    onTipsListener.onCancel();
                }
                break;
            case R.id.relative_layout_item_filtrate_head:
                category = "全部";
                if (onTipsListener != null) {
                    onTipsListener.onSuccess(mDialog,category);
                }
                break;
            default:
                break;
        }
    }
}

