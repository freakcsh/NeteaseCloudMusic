package com.freak.commonappframework.model.share.base;

import android.view.View;

import com.freak.commonappframework.R;
import com.freak.commonappframework.base.BaseAbstractMvpFragment;


/**
 * Created by Administrator on 2019/2/19.
 */

public class ShareFragment extends BaseAbstractMvpFragment<SharePresenter> implements ShareContract.View{
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
    protected void initEventAndData(View view) {

    }

    @Override
    protected void initLazyData() {

    }

    @Override
    protected void showLoading() {

    }
}
