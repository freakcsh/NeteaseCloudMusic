package com.freak.neteasecloudmusic.modules.myself;

import android.view.View;

import com.freak.neteasecloudmusic.R;
import com.freak.neteasecloudmusic.base.BaseAbstractMvpFragment;


/**
 * @author freak
 * @date 2019/2/19
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
    protected void initEventAndData() {

    }

    @Override
    protected void initView(View view) {

    }


    @Override
    protected void initLazyData() {

    }

    @Override
    protected void showLoading() {

    }
}
