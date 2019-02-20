package com.freak.commonappframework.model.homepage.base;

import android.view.View;

import com.freak.commonappframework.R;
import com.freak.commonappframework.base.BaseAbstractMvpFragment;


/**
 * Created by Administrator on 2019/2/19.
 */

public class HomePageFragment extends BaseAbstractMvpFragment<HomepagePresenter> implements HomepageContract.View {
    @Override
    public void showToast(String toast) {

    }

    @Override
    protected HomepagePresenter createPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_homepage;
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
