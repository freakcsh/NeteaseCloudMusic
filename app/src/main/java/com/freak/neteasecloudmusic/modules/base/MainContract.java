package com.freak.neteasecloudmusic.modules.base;

import com.freak.httphelper.BasePresenter;
import com.freak.neteasecloudmusic.base.BaseView;
import com.freak.neteasecloudmusic.modules.base.entity.LoginStatusEntity;

/**
 * Created by Administrator on 2019/3/16.
 */

public class MainContract {
    interface View extends BaseView {
        void getLoginStatusSuccess(LoginStatusEntity loginStatusEntity);
        void getLoginStatusError();
    }

    interface Presenter extends BasePresenter<View> {
        void loadLoginStatusEntity();
    }
}
