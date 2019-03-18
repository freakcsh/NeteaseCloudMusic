package com.freak.neteasecloudmusic.modules.disco.recommend.songlist.hot;

import android.view.View;

import com.freak.neteasecloudmusic.R;
import com.freak.neteasecloudmusic.base.BaseAbstractMvpActivity;
import com.freak.neteasecloudmusic.base.IActivityStatusBar;
import com.freak.neteasecloudmusic.modules.disco.recommend.entity.HotSongListCategoryEntity;
import com.freak.neteasecloudmusic.modules.disco.recommend.entity.HotSongListEntity;
import com.freak.neteasecloudmusic.utils.ToastUtil;
import com.freak.neteasecloudmusic.view.custom.toolbar.SimpleToolbar;

public class HotSongListActivity extends BaseAbstractMvpActivity<HotSongListPresenter> implements HotSongListContract.View, IActivityStatusBar {
    private SimpleToolbar mSimpleToolbarHotSongList;

    @Override
    protected int getLayout() {
        return R.layout.activity_hot_song_list;
    }

    @Override
    protected void initEventAndData() {
        mPresenter.loadHotSongListCategoryList("全部歌单", mPresenter.mLimit, mPresenter.mOffset);
    }

    @Override
    protected void initView() {
        initToolbar();
    }

    private void initToolbar() {
        mSimpleToolbarHotSongList = findViewById(R.id.simple_toolbar_hot_song_list);
        mSimpleToolbarHotSongList.setTitleName("精品歌单.全部");
        mSimpleToolbarHotSongList.setLeftIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        }).setRightTextClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.shortShow("筛选");
            }
        });
    }

    @Override
    protected HotSongListPresenter createPresenter() {
        return new HotSongListPresenter();
    }

    @Override
    public void showToast(String toast) {

    }

    @Override
    public int getStatusBarColor() {
        return 0;
    }

    /**
     * 热门歌单分类
     *
     * @param hotSongListCategoryEntity
     */
    @Override
    public void HotSongListCategorySuccess(HotSongListCategoryEntity hotSongListCategoryEntity) {

    }

    @Override
    public void loadHotSongListCategoryListError(String msg) {

    }

    @Override
    public void loadHotSongListCategoryListSuccess(HotSongListEntity hotSongListEntity) {

    }
}
