package com.freak.neteasecloudmusic.modules.base;

import com.freak.httphelper.ApiCallback;
import com.freak.httphelper.HttpMethods;
import com.freak.httphelper.RxPresenter;
import com.freak.httphelper.SubscriberCallBack;
import com.freak.neteasecloudmusic.app.api.ApiService;
import com.freak.neteasecloudmusic.commom.constants.Constants;
import com.freak.neteasecloudmusic.modules.base.entity.LoginStatusEntity;
import com.freak.neteasecloudmusic.net.log.LogUtil;

import io.reactivex.Observable;

/**
 * @author Administrator
 * @date 2019/3/16
 */

public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter {
    ApiService mApiService = HttpMethods.getInstance().create(ApiService.class);

    @Override
    public void loadLoginStatusEntity() {
        Observable<LoginStatusEntity> observable = mApiService.loadLoginStatus();
        addSubscription(observable, new SubscriberCallBack<>(new ApiCallback<LoginStatusEntity>() {
            @Override
            public void onSuccess(LoginStatusEntity model) {
                if (model.getCode() == Constants.SUCCESS_CODE) {
                    mView.getLoginStatusSuccess(model);
                } else {

                }
            }

            @Override
            public void onFailure(String msg) {
                LogUtil.e(msg);
                mView.getLoginStatusError();
            }
        }));
    }
}
