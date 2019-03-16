package com.freak.neteasecloudmusic.modules.homepage.base;

import com.freak.httphelper.HttpMethods;
import com.freak.httphelper.RxPresenter;
import com.freak.neteasecloudmusic.app.api.ApiService;


/**
 * @author freak
 * @date 2019/2/19
 */
public class HomepagePresenter extends RxPresenter<HomepageContract.View> implements HomepageContract.Presenter {
    ApiService apiServer = HttpMethods.getInstance().create(ApiService.class);

    @Override
    public void doLogin(String userName, String pwd) {
//        Observable<LoginBean> observable = apiServer.login(userName, pwd).map(new HttpResultFunc<LoginBean>());
//        addSubscription(observable, new SubscriberCallBack<>(new ApiCallback<LoginBean>() {
//            @Override
//            public void onSuccess(LoginBean model) {
//                Logger.d(model);
//            }
//
//            @Override
//            public void onFailure(String msg) {
//                Logger.d(msg);
//            }
//        }));


    }
}
