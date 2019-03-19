package com.freak.neteasecloudmusic.modules.disco.recommend.songlist.hot.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.freak.neteasecloudmusic.R;
import com.freak.neteasecloudmusic.app.App;
import com.freak.neteasecloudmusic.glide.GlideApp;
import com.freak.neteasecloudmusic.modules.disco.recommend.entity.HotSongListEntity;

import java.util.List;

/**
 * @author Administrator
 * @date 2019/3/19
 */

public class HotSongListAdapter extends BaseQuickAdapter<HotSongListEntity.PlaylistsBean, BaseViewHolder> {
    public HotSongListAdapter(int layoutResId, @Nullable List<HotSongListEntity.PlaylistsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HotSongListEntity.PlaylistsBean item) {
        helper.setText(R.id.hot_song_list_bfl, item.getPlayCount() + "");
        helper.setText(R.id.text_view_hot_ms, item.getName());
        helper.setText(R.id.text_view_hot_author, "by " + item.getCreator().getNickname());
        helper.setText(R.id.text_view_hot_type, item.getTag());
        helper.setText(R.id.text_view_hot_type_tip, item.getCopywriter());
        GlideApp.with(App.getInstance().getApplicationContext()).load(item.getCoverImgUrl()).into((ImageView) helper.getView(R.id.img_item_hot_bg));
    }
}
