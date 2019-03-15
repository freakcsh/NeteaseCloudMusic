package com.freak.neteasecloudmusic.modules.music.adapter;

import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.freak.neteasecloudmusic.R;
import com.freak.neteasecloudmusic.modules.music.entity.MusicHeadEntity;
import com.freak.neteasecloudmusic.modules.music.entity.MusicItemEntity;

import java.util.List;

/**
 * Created by Administrator on 2019/3/15.
 */

public class MusicAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    public static final int LEVEL_HEADER = 0;
    public static final int LEVEL_ITEM = 1;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MusicAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(LEVEL_HEADER, R.layout.item_music_head);
        addItemType(LEVEL_ITEM, R.layout.item_music_item);
    }

    @Override
    protected void convert(final BaseViewHolder helper, MultiItemEntity item) {
        switch (item.getItemType()) {
            case LEVEL_HEADER:
                final MusicHeadEntity musicHeadEntity = (MusicHeadEntity) item;
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = helper.getAdapterPosition();
                        if (musicHeadEntity.isExpanded()) {
                            collapse(position);
                        } else {
                            expand(position);
                        }
                    }
                });
                break;
            case LEVEL_ITEM:
                MusicItemEntity musicItemEntity = (MusicItemEntity) item;
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                break;
            default:
                break;
        }
    }
}
