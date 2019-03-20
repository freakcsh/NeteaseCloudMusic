package com.freak.neteasecloudmusic.modules.disco.recommend.songlist.detail.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.freak.neteasecloudmusic.modules.disco.recommend.entity.SongListDetailEntity;

import java.util.List;

/**
 * Created by Administrator on 2019/3/20.
 */

public class SongListDetailAdapter extends BaseQuickAdapter<SongListDetailEntity, BaseViewHolder> {
    public SongListDetailAdapter(int layoutResId, @Nullable List<SongListDetailEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SongListDetailEntity item) {

    }
}
