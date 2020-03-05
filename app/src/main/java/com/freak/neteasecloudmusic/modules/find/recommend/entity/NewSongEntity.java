package com.freak.neteasecloudmusic.modules.find.recommend.entity;

/**
 * Created by Administrator on 2019/3/18.
 */

public class NewSongEntity extends MultipleItem {
    public NewSongEntity(int itemType, int spanSize, MultipleItem multiItemEntity) {
        super(itemType, spanSize, multiItemEntity);
    }

    @Override
    public int getItemType() {
        return MultipleItem.NEW_SONG_LIST;
    }
}
