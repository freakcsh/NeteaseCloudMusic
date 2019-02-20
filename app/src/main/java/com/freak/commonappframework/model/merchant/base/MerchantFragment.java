package com.freak.commonappframework.model.merchant.base;

import android.view.View;

import com.freak.commonappframework.R;
import com.freak.commonappframework.base.BaseAbstractMvpFragment;


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
