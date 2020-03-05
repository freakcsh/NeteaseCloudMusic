package com.freak.neteasecloudmusic.dialog.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.freak.neteasecloudmusic.R;
import com.freak.neteasecloudmusic.dialog.FiltrateDialogFragment;
import com.freak.neteasecloudmusic.modules.find.recommend.entity.HotSongListCategoryEntity;

import java.util.List;

public class FiltrateDialogAdapter extends BaseQuickAdapter<HotSongListCategoryEntity.TagsBean, BaseViewHolder> {
    public FiltrateDialogAdapter(int layoutResId, @Nullable List<HotSongListCategoryEntity.TagsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HotSongListCategoryEntity.TagsBean item) {
        if (FiltrateDialogFragment.category.equals(item.getName())) {
            helper.getView(R.id.relative_layout_bg_filtrate).setSelected(true);
        } else {
            helper.getView(R.id.relative_layout_bg_filtrate).setSelected(false);
        }
        helper.setText(R.id.text_view_category_filtrate, item.getName());
    }
}
