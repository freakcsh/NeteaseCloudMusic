package com.freak.commonappframework.model.myself;


import com.freak.commonappframework.base.BaseView;
import com.freak.httphelper.BasePresenter;

/**
 * @author freak
 * @date 2019/2/19
 */

public interface MyselfContract {
    interface View extends BaseView {

    }

    interface Presenter extends BasePresenter<View> {

    }
}
