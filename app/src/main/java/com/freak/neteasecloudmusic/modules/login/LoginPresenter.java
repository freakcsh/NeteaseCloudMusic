package com.freak.neteasecloudmusic.modules.login;

import com.freak.httphelper.ApiCallback;
import com.freak.httphelper.HttpMethods;
import com.freak.httphelper.RxPresenter;
import com.freak.httphelper.SubscriberCallBack;
import com.freak.neteasecloudmusic.app.api.ApiService;
import com.freak.neteasecloudmusic.commom.constants.Constants;
import com.freak.neteasecloudmusic.modules.login.entty.LoginEntity;
import com.freak.neteasecloudmusic.utils.ToastUtil;

import io.reactivex.Observable;

/**
 * @author Administrator
 * @date 2019/3/16
 */

public class LoginPresenter extends RxPresenter<LoginContract.View> implements LoginContract.Presenter {
    ApiService mApiService = HttpMethods.getInstance().create(ApiService.class);

    @Override
    public void doLogin(String phone, String password) {
        Observable<LoginEntity> observable = mApiService.login(phone, password);
        addSubscription(observable, new SubscriberCallBack<>(new ApiCallback<LoginEntity>() {
            @Override
            public void onSuccess(LoginEntity model) {
                if (model.getCode() == Constants.SUCCESS_CODE) {
                    mView.onLoginSuccess(model);
                } else {
                    //失败
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
