package com.freak.neteasecloudmusic.modules.disco.recommend.songlist.hot;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.freak.neteasecloudmusic.R;
import com.freak.neteasecloudmusic.base.BaseAbstractMvpActivity;
import com.freak.neteasecloudmusic.base.IActivityStatusBar;
import com.freak.neteasecloudmusic.modules.disco.recommend.entity.HotSongListCategoryEntity;
import com.freak.neteasecloudmusic.modules.disco.recommend.entity.HotSongListEntity;
import com.freak.neteasecloudmusic.modules.disco.recommend.songlist.hot.adapter.HotSongListAdapter;
import com.freak.neteasecloudmusic.utils.ToastUtil;
import com.freak.neteasecloudmusic.view.custom.toolbar.SimpleToolbar;

import java.util.ArrayList;
import java.util.List;

/**
 * 热门歌单
 *
 * @author Administrator
 */
public class HotSongListActivity extends BaseAbstractMvpActivity<HotSongListPresenter> implements HotSongListContract.View, IActivityStatusBar {
    private SimpleToolbar mSimpleToolbarHotSongList;
    private RecyclerView mRecycleViewHotSongList;
    private HotSongListAdapter mHotSongListAdapter;
    private List<HotSongListEntity.PlaylistsBean> mList;
    private String category = "全部";

    @Override
    protected int getLayout() {
        return R.layout.activity_hot_song_list;
    }

    @Override
    protected void initEventAndData() {
        mPresenter.loadHotSongListCategoryList("全部", mPresenter.mLimit, mPresenter.mOffset);
    }

    @Override
    protected void initView() {
        initToolbar();
        initRecycleView();
    }

    private void initRecycleView() {
        mList = new ArrayList<>();
        mRecycleViewHotSongList = findViewById(R.id.recycle_view_hot_song_list);
        mRecycleViewHotSongList.setLayoutManager(new LinearLayoutManager(this));
        mHotSongListAdapter = new HotSongListAdapter(R.layout.item_hot_song_list, mList);
        mHotSongListAdapter.bindToRecyclerView(mRecycleViewHotSongList);
        mHotSongListAdapter.disableLoadMoreIfNotFullPage();
        mHotSongListAdapter.setEnableLoadMore(true);
        mRecycleViewHotSongList.setAdapter(mHotSongListAdapter);
        mHotSongListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPresenter.mOffset = 0;
                mPresenter.loadHotSongListCategoryList("全部", mPresenter.mLimit, mPresenter.mOffset);
            }
        }, mRecycleViewHotSongList);
    }

    private void initToolbar() {
        mSimpleToolbarHotSongList = findViewById(R.id.simple_toolbar_hot_song_list);
        mSimpleToolbarHotSongList.setTitleName("精品歌单."+category);
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
        if (hotSongListEntity != null) {
            if (hotSongListEntity.getPlaylists().size() != 0) {
                mList.addAll(hotSongListEntity.getPlaylists());
                mHotSongListAdapter.notifyDataSetChanged();
            }
        }
    }
}
