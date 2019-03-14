package com.freak.neteasecloudmusic.modules.share.base;

import android.view.View;

import com.freak.neteasecloudmusic.R;
import com.freak.neteasecloudmusic.base.BaseAbstractMvpFragment;


/**
 * @author freak
 * @date 2019/2/19
 */

public class ShareFragment extends BaseAbstractMvpFragment<SharePresenter> implements ShareContract.View {
    @Override
    public void showToast(String toast) {

    }

    @Override
    protected SharePresenter createPresenter() {
        return new SharePresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_share;
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
