package com.freak.neteasecloudmusic.modules.disco.recommend.songlist.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.freak.neteasecloudmusic.R;
import com.freak.neteasecloudmusic.glide.GlideApp;
import com.freak.neteasecloudmusic.modules.disco.recommend.entity.SongListEntity;

import java.util.List;

import static com.freak.neteasecloudmusic.modules.disco.recommend.entity.MultipleItem.NEW_SONG_LIST;
import static com.freak.neteasecloudmusic.modules.disco.recommend.entity.MultipleItem.RECOMMEND_SONG_LIST;

public class SongListAdapter extends BaseMultiItemQuickAdapter<SongListEntity.PlaylistsBean , BaseViewHolder> {
    private Context mContext;

    public SongListAdapter(@Nullable List<SongListEntity.PlaylistsBean> data, Context context) {
        super(data);
        this.mContext = context;
        addItemType(RECOMMEND_SONG_LIST, R.layout.item_song_list);
        addItemType(NEW_SONG_LIST, R.layout.item_new_song);
    }


    @Override
    protected void convert(BaseViewHolder helper, SongListEntity.PlaylistsBean item) {
        switch (item.getItemType()) {
            //推荐歌单
            case RECOMMEND_SONG_LIST:
                GlideApp.with(mContext).load(item.getCoverImgUrl()).thumbnail(0.1f).into((ImageView) helper.getView(R.id.img_bg_song_list));
                helper.setText(R.id.text_view_song_list_tip, item.getName());
                helper.setText(R.id.text_view_author, item.getCreator().getNickname());
                helper.setText(R.id.text_view_bfl, item.getPlayCount()+"");
                break;
            //最新音乐
            case NEW_SONG_LIST:
                break;
            default:
                break;
        }
    }
}
