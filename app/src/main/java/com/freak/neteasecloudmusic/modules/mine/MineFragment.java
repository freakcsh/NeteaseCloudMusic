package com.freak.neteasecloudmusic.modules.mine;

import android.view.View;

import com.freak.neteasecloudmusic.R;
import com.freak.neteasecloudmusic.base.BaseAbstractMvpFragment;

public class MineFragment extends BaseAbstractMvpFragment<MinePresenter> implements MineContract.View {
    @Override
    protected MinePresenter createPresenter() {
        return new MinePresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
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

    @Override
    public void showToast(String toast) {

    }
}
