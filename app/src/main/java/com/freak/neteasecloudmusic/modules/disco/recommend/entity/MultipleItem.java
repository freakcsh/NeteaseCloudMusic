package com.freak.neteasecloudmusic.modules.disco.recommend.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @author Administrator
 * @date 2019/3/18
 */

public class MultipleItem<T> implements MultiItemEntity {
    public static final int RECOMMEND_SONG_LIST = 0;
    public static final int NEW_SONG_LIST = 1;
    public static final int RECOMMEND_SONG_LIST_SIZE = 3;
    public static final int NEW_SONG_LIST_SIZE = 3;
    private int itemType;
    private int spanSize;
    private T mMultiItemEntity;

    public MultipleItem(int itemType, int spanSize, T multiItemEntity) {
        this.itemType = itemType;
        this.spanSize = spanSize;
        this.mMultiItemEntity = multiItemEntity;
    }


    @Override
    public int getItemType() {
        return itemType;
    }

    public int getSpanSize() {
        return spanSize;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }
}
