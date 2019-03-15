package com.freak.neteasecloudmusic.modules.disco.recommend;

import android.view.View;

import com.freak.neteasecloudmusic.R;
import com.freak.neteasecloudmusic.base.BaseAbstractMvpFragment;

/**
 * Created by Administrator on 2019/3/15.
 */

public class RecommendFragment extends BaseAbstractMvpFragment<RecommendPresenter> implements RecommendContract.View {
    @Override
    public void showToast(String toast) {

    }

    @Override
    protected RecommendPresenter createPresenter() {
        return new RecommendPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recommend;
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
