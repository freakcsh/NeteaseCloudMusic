package com.freak.neteasecloudmusic.modules.disco.recommend.base;

import com.freak.httphelper.ApiCallback;
import com.freak.httphelper.HttpMethods;
import com.freak.httphelper.RxPresenter;
import com.freak.httphelper.SubscriberCallBack;
import com.freak.neteasecloudmusic.app.api.ApiService;
import com.freak.neteasecloudmusic.commom.constants.Constants;
import com.freak.neteasecloudmusic.modules.disco.recommend.entity.BannerEntity;

import io.reactivex.Observable;

/**
 * Created by Administrator on 2019/3/15.
 */

public class RecommendPresenter extends RxPresenter<RecommendContract.View> implements RecommendContract.Presenter {
    ApiService mApiService = HttpMethods.getInstance().create(ApiService.class);

    @Override
    public void loadBanner() {
        Observable<BannerEntity> observable = mApiService.loadBanner();
        addSubscription(observable, new SubscriberCallBack<>(new ApiCallback<BannerEntity>() {
            @Override
            public void onSuccess(BannerEntity model) {
                if (model.getCode() == Constants.SUCCESS_CODE) {
                    mView.loadBannerSuccess(model);
                }
            }

            @Override
            public void onFailure(String msg) {

            }
        }));
    }
}
