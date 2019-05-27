package com.freak.neteasecloudmusic.modules.disco.broadcasting;

import android.view.View;

import com.freak.neteasecloudmusic.R;
import com.freak.neteasecloudmusic.base.BaseAbstractMvpFragment;

/**
 *
 * @author Administrator
 * @date 2019/3/15
 */

public class BroadcastingFragment extends BaseAbstractMvpFragment<BroadcastingPresenter> implements BroadcastingContract.View {
    @Override
    public void showToast(String toast) {

    }

    @Override
    protected BroadcastingPresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_broadcasting;
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
