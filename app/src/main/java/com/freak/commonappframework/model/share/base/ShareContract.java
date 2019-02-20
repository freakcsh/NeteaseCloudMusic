package com.freak.commonappframework.model.share.base;

import com.freak.commonappframework.base.BaseView;
import com.freak.httphelper.BasePresenter;

/**
 * Created by Administrator on 2019/2/19.
 */

public interface ShareContract {
    interface View extends BaseView {

    }

    interface Presenter extends BasePresenter<View> {

    }
}
