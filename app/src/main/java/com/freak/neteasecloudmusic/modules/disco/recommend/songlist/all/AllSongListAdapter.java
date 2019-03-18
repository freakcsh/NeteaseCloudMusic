package com.freak.neteasecloudmusic.modules.disco.recommend.songlist.all;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.freak.neteasecloudmusic.R;
import com.freak.neteasecloudmusic.modules.disco.recommend.entity.SongListCategoryEntity;

import java.util.List;

public class AllSongListAdapter extends BaseQuickAdapter<SongListCategoryEntity.SubBean, BaseViewHolder> {
    public AllSongListAdapter(int layoutResId, @Nullable List<SongListCategoryEntity.SubBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SongListCategoryEntity.SubBean item) {
        helper.setText(R.id.text_view_item_all_song_list, item.getName());
    }
}
