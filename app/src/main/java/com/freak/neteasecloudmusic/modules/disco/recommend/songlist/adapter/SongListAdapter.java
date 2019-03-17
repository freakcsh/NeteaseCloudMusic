package com.freak.neteasecloudmusic.modules.disco.recommend.songlist.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.freak.neteasecloudmusic.modules.disco.recommend.entity.SongListEntity;

import java.util.List;

public class SongListAdapter extends BaseQuickAdapter<SongListEntity,BaseViewHolder> {
    public SongListAdapter(int layoutResId, @Nullable List<SongListEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SongListEntity item) {

    }
}
