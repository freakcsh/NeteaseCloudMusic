package com.freak.neteasecloudmusic.modules.find.recommend.songlist.hot;

import com.freak.httphelper.ApiCallback;
import com.freak.httphelper.HttpMethods;
import com.freak.httphelper.RxPresenter;
import com.freak.httphelper.SubscriberCallBack;
import com.freak.neteasecloudmusic.app.api.ApiService;
import com.freak.neteasecloudmusic.commom.constants.Constants;
import com.freak.neteasecloudmusic.modules.find.recommend.entity.HotSongListCategoryEntity;
import com.freak.neteasecloudmusic.modules.find.recommend.entity.HotSongListEntity;
import com.freak.neteasecloudmusic.net.log.LogUtil;

import io.reactivex.Observable;

/**
 * 热门歌单
 *
 * @author Administrator
 */
public class HotSongListPresenter extends RxPresenter<HotSongListContract.View> implements HotSongListContract.Presenter {
    ApiService mApiService = HttpMethods.getInstance().create(ApiService.class);
    public long mBefore = 0;
    public final int mLimit = 20;
    public int count = 0;

    /**
     * 热门歌单分类
     */
    @Override
    public void loadHotSongListCategory() {
        Observable<HotSongListCategoryEntity> observable = mApiService.loadSongListCategoryHot();
        addSubscription(observable, new SubscriberCallBack<>(new ApiCallback<HotSongListCategoryEntity>() {
            @Override
            public void onSuccess(HotSongListCategoryEntity model) {
                if (model.getCode() == Constants.SUCCESS_CODE) {
                    mView.HotSongListCategorySuccess(model);
                } else {
                    mView.loadHotSongListCategoryListError(model.getMsg());
                }
            }

            @Override
            public void onFailure(String msg) {
                LogUtil.e(msg);
            }
        }));
    }

    @Override
    public void loadHotSongListCategoryList(String cat, final int limit, final long before) {
        Observable<HotSongListEntity> observable = mApiService.loadQualitySongList(cat, limit, before);
        addSubscription(observable, new SubscriberCallBack<>(new ApiCallback<HotSongListEntity>() {
            @Override
            public void onSuccess(HotSongListEntity model) {
                if (model.getCode() == Constants.SUCCESS_CODE) {
                    if (model.isMore()) {
                        count += mLimit;
                    }
                    mBefore = model.getPlaylists().get(model.getPlaylists().size() - 1).getUpdateTime();
                    mView.loadHotSongListCategoryListSuccess(model);
                } else {
                    mView.loadHotSongListCategoryListError(model.getMsg());
                }
            }

            @Override
            public void onFailure(String msg) {
                LogUtil.e(msg);
                mView.loadHotSongListCategoryListError(msg);
            }
        }));
    }
}
