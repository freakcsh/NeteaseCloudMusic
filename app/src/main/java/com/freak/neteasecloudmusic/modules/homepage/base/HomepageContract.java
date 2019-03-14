package com.freak.neteasecloudmusic.modules.homepage.base;


import com.freak.neteasecloudmusic.base.BaseView;
import com.freak.neteasecloudmusic.modules.homepage.base.entity.LoginBean;
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
