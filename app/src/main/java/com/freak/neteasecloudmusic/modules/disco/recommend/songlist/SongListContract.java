package com.freak.neteasecloudmusic.modules.disco.recommend.songlist;

import com.freak.httphelper.BasePresenter;
import com.freak.neteasecloudmusic.base.BaseView;
import com.freak.neteasecloudmusic.modules.disco.recommend.entity.HotSongListCategoryEntity;
import com.freak.neteasecloudmusic.modules.disco.recommend.entity.HotSongListEntity;
import com.freak.neteasecloudmusic.modules.disco.recommend.entity.SongListCategoryEntity;
import com.freak.neteasecloudmusic.modules.disco.recommend.entity.SongListEntity;

/**
 * @author Administrator
 */
public class SongListContract {
    interface View extends BaseView {
        void loadSongListCategoryListSuccess(SongListEntity songListEntity);

        void loadSongListCategoryListError(String msg);

        void loadSongListCategorySuccess(SongListCategoryEntity songListCategoryEntity);


        void loadHotSongListCategoryListError(String msg);

        void loadHotSongListCategoryListSuccess(HotSongListEntity hotSongListEntity);
    }

    interface Presenter extends BasePresenter<View> {
        /**
         * 获取歌曲分类列表
         *
         * @param order 可选值为 'new' 和 'hot', 分别对应最新和最热 , 默认为 'hot'
         * @param cat   比如 " 华语 "、" 古风 " 、" 欧美 "、" 流行 ", 默认为 "全部",可从歌单分类接口获取(/playlist/catlist)
         * @param limit 取出歌单数量 , 默认为 20
         */
        void loadSongListCategoryList(String order, String cat, int limit, int offset);

        /**
         * 歌单分类
         */
        void loadSongListCategory();

        /**
         * 热门歌单分类列表
         *
         * @param cat 比如 " 华语 "、" 古风 " 、" 欧美 "、" 流行 ", 默认为 "全部",可从歌单分类接口获取(/playlist/catlist)
         * @param limit 取出歌单数量 , 默认为 20
         * @param offset
         */
        void loadHotSongListCategoryList(String cat, final int limit, final int offset);
    }
}
