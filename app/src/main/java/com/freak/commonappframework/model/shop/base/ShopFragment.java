package com.freak.commonappframework.model.shop.base;

import android.view.View;

import com.freak.commonappframework.R;
import com.freak.commonappframework.base.BaseAbstractMvpFragment;


/**
 * Created by Administrator on 2019/2/19.
 */

public class ShopFragment extends BaseAbstractMvpFragment<ShopPresenter> implements ShopContract.View {
    @Override
    public void showToast(String toast) {

    }


    @Override
    protected ShopPresenter createPresenter() {
        return new ShopPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_shop;
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
