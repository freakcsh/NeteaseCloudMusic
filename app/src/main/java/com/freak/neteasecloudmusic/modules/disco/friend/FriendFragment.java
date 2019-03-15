package com.freak.neteasecloudmusic.modules.disco.friend;

import android.view.View;

import com.freak.neteasecloudmusic.R;
import com.freak.neteasecloudmusic.base.BaseAbstractMvpFragment;

/**
 * Created by Administrator on 2019/3/15.
 */

public class FriendFragment extends BaseAbstractMvpFragment<FriendPresenter> implements FriendContract.View {
    @Override
    public void showToast(String toast) {

    }

    @Override
    protected FriendPresenter createPresenter() {
        return new FriendPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_friend;
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
