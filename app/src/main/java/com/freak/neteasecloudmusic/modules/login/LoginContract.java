package com.freak.neteasecloudmusic.modules.login;

import com.freak.httphelper.BasePresenter;
import com.freak.neteasecloudmusic.base.BaseView;
import com.freak.neteasecloudmusic.modules.login.entty.LoginEntity;

/**
 * Created by Administrator on 2019/3/16.
 */

public class LoginContract {
    interface View extends BaseView {
        void onLoginSuccess(LoginEntity loginEntity);

        void onLoginError(String msg);
    }

    interface Presenter extends BasePresenter<View> {
        void doLogin(String phone,String password);
    }
}
