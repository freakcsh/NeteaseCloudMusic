package com.freak.neteasecloudmusic.modules.merchant.base;

import android.view.View;

import com.freak.neteasecloudmusic.R;
import com.freak.neteasecloudmusic.base.BaseAbstractMvpFragment;


/**
 * @author freak
 * @date 2019/2/19
 */

public class MerchantFragment extends BaseAbstractMvpFragment<MerchantPresenter> implements MerchantContract.View {
    @Override
    public void showToast(String toast) {

    }

    @Override
    protected MerchantPresenter createPresenter() {
        return new MerchantPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_merchant;
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
