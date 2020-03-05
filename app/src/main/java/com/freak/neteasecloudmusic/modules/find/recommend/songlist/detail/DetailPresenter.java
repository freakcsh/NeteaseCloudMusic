package com.freak.neteasecloudmusic.modules.find.recommend.songlist.detail;

import com.freak.httphelper.ApiCallback;
import com.freak.httphelper.HttpMethods;
import com.freak.httphelper.RxPresenter;
import com.freak.httphelper.SubscriberCallBack;
import com.freak.neteasecloudmusic.app.api.ApiService;
import com.freak.neteasecloudmusic.commom.constants.Constants;
import com.freak.neteasecloudmusic.modules.find.recommend.entity.SongListDetailEntity;
import com.freak.neteasecloudmusic.modules.find.recommend.entity.SongUrlEntity;
import com.freak.neteasecloudmusic.utils.ToastUtil;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2019/3/20.
 */

public class DetailPresenter extends RxPresenter<DetailContract.View> implements DetailContract.Presenter {
    ApiService mApiService = HttpMethods.getInstance().create(ApiService.class);

    @Override
    public void getSongListDetail(String id) {
        Observable<SongListDetailEntity> observable=mApiService.loadSongListCategoryDetail(id,"");
        addSubscription(observable,new SubscriberCallBack<SongListDetailEntity>(new ApiCallback<SongListDetailEntity>() {
            @Override
            public void onSuccess(SongListDetailEntity model) {
                if (model.getCode()==Constants.SUCCESS_CODE){
                    mView.getSongListDetailSuccess(model);
                }else {
                    ToastUtil.shortShow(model.getMsg());
                }
            }

            @Override
            public void onFailure(String msg) {
                ToastUtil.shortShow(msg);
            }
        }));
    }

    @Override
    public void loadSongUrl(String id) {
        Observable<SongUrlEntity> observable=mApiService.loadSongUrl(id);
        addSubscription(observable,new SubscriberCallBack<SongUrlEntity>(new ApiCallback<SongUrlEntity>() {
            @Override
            public void onSuccess(SongUrlEntity model) {
                if (model.getCode()==Constants.SUCCESS_CODE){
                    mView.loadSongUrlSuccess(model);
                }else {
                    ToastUtil.shortShow(model.getMsg());
                }
            }

            @Override
            public void onFailure(String msg) {
                ToastUtil.shortShow(msg);
            }
        }));
    }
}
