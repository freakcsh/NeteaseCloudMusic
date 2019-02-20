package com.freak.commonappframework.model.homepage.base;


import com.freak.commonappframework.base.BaseView;
import com.freak.commonappframework.model.homepage.base.bean.LoginBean;
import com.freak.httphelper.BasePresenter;

/**
 * @author freak
 * @date 2019/2/19
 */

public interface HomepageContract {
    interface View extends BaseView {
        void onSuccess(LoginBean loginBean);
        void onError(String msg);
    }

    interface Presenter extends BasePresenter<View> {
        void doLogin(String userName, String pwd);
    }
}
