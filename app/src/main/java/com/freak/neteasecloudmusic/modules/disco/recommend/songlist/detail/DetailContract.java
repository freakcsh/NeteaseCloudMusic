package com.freak.neteasecloudmusic.modules.disco.recommend.songlist.detail;

import com.freak.httphelper.BasePresenter;
import com.freak.neteasecloudmusic.base.BaseView;
import com.freak.neteasecloudmusic.modules.disco.recommend.entity.SongListDetailEntity;
import com.freak.neteasecloudmusic.modules.disco.recommend.entity.SongUrlEntity;

/**
 * Created by Administrator on 2019/3/20.
 */

public class DetailContract {
    interface View extends BaseView {
        void getSongListDetailSuccess(SongListDetailEntity songListDetailEntity);

        void loadSongUrlSuccess(SongUrlEntity songUrlEntity);
    }

    interface Presenter extends BasePresenter<View> {
        void getSongListDetail(String id);

        void loadSongUrl(String id);
    }
}
