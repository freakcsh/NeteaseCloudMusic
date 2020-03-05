package com.freak.neteasecloudmusic.modules.find.recommend.songlist.hot;

import com.freak.httphelper.BasePresenter;
import com.freak.neteasecloudmusic.base.BaseView;
import com.freak.neteasecloudmusic.modules.find.recommend.entity.HotSongListCategoryEntity;
import com.freak.neteasecloudmusic.modules.find.recommend.entity.HotSongListEntity;

public class HotSongListContract {
    interface View extends BaseView {
        void HotSongListCategorySuccess(HotSongListCategoryEntity hotSongListCategoryEntity);

        void loadHotSongListCategoryListError(String msg);

        void loadHotSongListCategoryListSuccess(HotSongListEntity hotSongListEntity);
    }

    interface Presenter extends BasePresenter<View> {
        /**
         * 热门歌单分类
         */
        void loadHotSongListCategory();

        /**
         * 热门歌单分类列表
         *
         * @param cat    比如 " 华语 "、" 古风 " 、" 欧美 "、" 流行 ", 默认为 "全部",可从歌单分类接口获取(/playlist/catlist)
         * @param limit  取出歌单数量 , 默认为 20
         * @param before
         */
        void loadHotSongListCategoryList(String cat, final int limit, final long before);
    }
}
