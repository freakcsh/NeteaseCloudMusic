package com.freak.neteasecloudmusic.modules.disco.recommend.base;

import com.freak.httphelper.BasePresenter;
import com.freak.neteasecloudmusic.base.BaseView;
import com.freak.neteasecloudmusic.modules.disco.recommend.entity.BannerEntity;

/**
 * Created by Administrator on 2019/3/15.
 */

public class RecommendContract {
    interface View extends BaseView {
        void loadBannerSuccess(BannerEntity bannerEntity);
    }

    interface Presenter extends BasePresenter<View> {
        void loadBanner();
    }
}
