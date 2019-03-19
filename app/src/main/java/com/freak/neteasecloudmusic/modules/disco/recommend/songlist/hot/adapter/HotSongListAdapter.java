package com.freak.neteasecloudmusic.modules.disco.recommend.songlist.hot.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.freak.neteasecloudmusic.modules.disco.recommend.entity.HotSongListEntity;

import java.util.List;

/**
 *
 * @author Administrator
 * @date 2019/3/19
 */

public class HotSongListAdapter extends BaseQuickAdapter<HotSongListEntity.PlaylistsBean,BaseViewHolder> {
    public HotSongListAdapter(int layoutResId, @Nullable List<HotSongListEntity.PlaylistsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HotSongListEntity.PlaylistsBean item) {

    }
}
