package com.freak.neteasecloudmusic.modules.disco.recommend.songlist;

import com.freak.httphelper.ApiCallback;
import com.freak.httphelper.HttpMethods;
import com.freak.httphelper.RxPresenter;
import com.freak.httphelper.SubscriberCallBack;
import com.freak.neteasecloudmusic.app.api.ApiService;
import com.freak.neteasecloudmusic.commom.constants.Constants;
import com.freak.neteasecloudmusic.modules.disco.recommend.entity.HotSongListEntity;
import com.freak.neteasecloudmusic.modules.disco.recommend.entity.SongListCategoryEntity;
import com.freak.neteasecloudmusic.modules.disco.recommend.entity.SongListEntity;
import com.freak.neteasecloudmusic.net.log.LogUtil;
import com.freak.neteasecloudmusic.utils.ToastUtil;

import io.reactivex.Observable;

/**
 * @author Administrator
 */
public class SongListPresenter extends RxPresenter<SongListContract.View> implements SongListContract.Presenter {
    ApiService mApiService = HttpMethods.getInstance().create(ApiService.class);
    public int mOffset = 0;
    public final int mLimit = 20;

    /**
     * 获取歌曲分类列表
     *
     * @param order 可选值为 'new' 和 'hot', 分别对应最新和最热 , 默认为 'hot'
     * @param cat   比如 " 华语 "、" 古风 " 、" 欧美 "、" 流行 ", 默认为 "全部",可从歌单分类接口获取(/playlist/catlist)
     * @param limit 取出歌单数量 , 默认为 20
     */
    @Override
    public void loadSongListCategoryList(String order, String cat, final int limit, final int offset) {
        Observable<SongListEntity> observable = mApiService.loadSongListCategoryList(order, cat, limit, offset);
        addSubscription(observable, new SubscriberCallBack<>(new ApiCallback<SongListEntity>() {
            @Override
            public void onSuccess(SongListEntity model) {
                if (model.getCode() == Constants.SUCCESS_CODE) {
                    if (model.isMore()) {
                        mOffset += mLimit;
                    }
                    mView.loadSongListCategoryListSuccess(model);
                } else {
                    mView.loadSongListCategoryListError(model.getMsg());
                }
            }

            @Override
            public void onFailure(String msg) {
                LogUtil.e(msg);
                mView.loadSongListCategoryListError(msg);
            }
        }));
    }

    /**
     * 获取歌曲分类
     */
    @Override
    public void loadSongListCategory() {
        Observable<SongListCategoryEntity> observable = mApiService.loadSongListCategory();
        addSubscription(observable, new SubscriberCallBack<>(new ApiCallback<SongListCategoryEntity>() {
            @Override
            public void onSuccess(SongListCategoryEntity model) {
                if (model.getCode() == Constants.SUCCESS_CODE) {
                    mView.loadSongListCategorySuccess(model);
                } else {
                    ToastUtil.shortShow(model.getMsg() + "");
                }
            }

            @Override
            public void onFailure(String msg) {
                LogUtil.e(msg);
            }
        }));
    }


    @Override
    public void loadHotSongListCategoryList(String cat, final int limit, final int offset) {
        Observable<HotSongListEntity> observable = mApiService.loadQualitySongList(cat, limit, offset);
        addSubscription(observable, new SubscriberCallBack<>(new ApiCallback<HotSongListEntity>() {
            @Override
            public void onSuccess(HotSongListEntity model) {
                if (model.getCode() == Constants.SUCCESS_CODE) {
                    if (model.isMore()) {
                        mOffset += mLimit;
                    }
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
