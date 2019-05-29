package com.freak.neteasecloudmusic.modules.controls;

import android.view.View;

import com.freak.neteasecloudmusic.R;
import com.freak.neteasecloudmusic.base.BaseAbstractMvpFragment;

@SuppressWarnings("ALL")
public class QuickControlsFragment extends BaseAbstractMvpFragment<QuickControlsPresenter> implements QuickControlsContract.View, View.OnClickListener {


    public static QuickControlsFragment getInstance() {
        return new QuickControlsFragment();
    }

    @Override
    protected QuickControlsPresenter createPresenter() {
        return new QuickControlsPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_quick_controls;
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

    @Override
    public void onClick(View view) {

    }
}
