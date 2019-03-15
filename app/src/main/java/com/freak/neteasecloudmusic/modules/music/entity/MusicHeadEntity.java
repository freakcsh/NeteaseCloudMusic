package com.freak.neteasecloudmusic.modules.music.entity;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.freak.neteasecloudmusic.modules.music.adapter.MusicAdapter;

/**
 *
 * @author Administrator
 * @date 2019/3/15
 */

public class MusicHeadEntity extends AbstractExpandableItem<MusicItemEntity> implements MultiItemEntity {
    @Override
    public int getItemType() {
        return MusicAdapter.LEVEL_HEADER;
    }

    @Override
    public int getLevel() {
        return 0;
    }
}
