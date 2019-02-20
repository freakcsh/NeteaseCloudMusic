package com.freak.commonappframework.model.homepage.base;

import com.freak.commonappframework.app.api.ApiService;
import com.freak.commonappframework.model.homepage.base.bean.LoginBean;
import com.freak.httphelper.ApiCallback;
import com.freak.httphelper.HttpMethods;
import com.freak.httphelper.HttpResultFunc;
import com.freak.httphelper.RxPresenter;
import com.freak.httphelper.SubscriberCallBack;
import com.orhanobut.logger.Logger;

import io.reactivex.Observable;


/**
 * @author freak
 * @date 2019/2/19
 */
public class HomepagePresenter extends RxPresenter<HomepageContract.View> implements HomepageContract.Presenter {
    ApiService apiServer = HttpMethods.getInstance().create(ApiService.class);

    @Override
    public void doLogin(String userName, String pwd) {
        Observable<LoginBean> observable = apiServer.login(userName, pwd).map(new HttpResultFunc<LoginBean>());
        addSubscription(observable, new SubscriberCallBack<>(new ApiCallback<LoginBean>() {
            @Override
            public void onSuccess(LoginBean model) {
                Logger.d(model);
            }

            @Override
            public void onFailure(String msg) {
                Logger.d(msg);
            }
        }));


    }
}
