package com.freak.commonappframework.model.myself;

import android.view.View;

import com.freak.commonappframework.R;
import com.freak.commonappframework.base.BaseAbstractMvpFragment;


/**
 * Created by Administrator on 2019/2/19.
 */

public class MyselfFragment extends BaseAbstractMvpFragment<MyselfPresenter> implements MyselfContract.View {
    @Override
    public void showToast(String toast) {

    }

    @Override
    protected MyselfPresenter createPresenter() {
        return new MyselfPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_myself;
    }

    @Override
    protected void initEventAndData(View view) {

    }

    @Override
    protected void initLazyData() {

    }

    @Override
    protected void showLoading() {

    }
}
