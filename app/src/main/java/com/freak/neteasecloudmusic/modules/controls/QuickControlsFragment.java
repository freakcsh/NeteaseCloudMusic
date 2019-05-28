package com.freak.neteasecloudmusic.modules.controls;

import android.view.View;

import com.freak.neteasecloudmusic.R;
import com.freak.neteasecloudmusic.base.BaseAbstractMvpFragment;
import com.freak.neteasecloudmusic.service.AudioPlayerService;

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
        initService();
    }

    /**
     * 初始服务
     */
    private void initService() {
        AudioPlayerService.startService(getActivity());
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
