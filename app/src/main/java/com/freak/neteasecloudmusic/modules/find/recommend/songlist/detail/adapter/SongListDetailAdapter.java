package com.freak.neteasecloudmusic.modules.find.recommend.songlist.detail.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.freak.neteasecloudmusic.R;
import com.freak.neteasecloudmusic.modules.find.recommend.entity.SongListDetailEntity;

import java.util.List;

/**
 * Created by Administrator on 2019/3/20.
 */

public class SongListDetailAdapter extends BaseQuickAdapter<SongListDetailEntity.PlaylistBean.TracksBean, BaseViewHolder> {
    public SongListDetailAdapter(int layoutResId, @Nullable List<SongListDetailEntity.PlaylistBean.TracksBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SongListDetailEntity.PlaylistBean.TracksBean item) {
        helper.setText(R.id.text_view_song_list_detail_item_song_name, item.getName());
        helper.setText(R.id.text_view_song_list_detail_item_author, item.getAr().get(0).getName() + "-" + item.getAl().getName());
    }
}
