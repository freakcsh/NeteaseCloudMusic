package com.freak.neteasecloudmusic.modules.find.recommend.songlist.hot;

import android.app.Dialog;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.freak.neteasecloudmusic.R;
import com.freak.neteasecloudmusic.base.BaseAbstractMvpActivity;
import com.freak.neteasecloudmusic.base.IActivityStatusBar;
import com.freak.neteasecloudmusic.dialog.FiltrateDialogFragment;
import com.freak.neteasecloudmusic.modules.find.recommend.entity.HotSongListCategoryEntity;
import com.freak.neteasecloudmusic.modules.find.recommend.entity.HotSongListEntity;
import com.freak.neteasecloudmusic.modules.find.recommend.songlist.detail.DetailActivity;
import com.freak.neteasecloudmusic.modules.find.recommend.songlist.hot.adapter.HotSongListAdapter;
import com.freak.neteasecloudmusic.net.log.LogUtil;
import com.freak.neteasecloudmusic.utils.DialogUtil;
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
    private String mCategory = "全部";
    private HotSongListCategoryEntity mHotSongListCategoryEntity;

    @Override
    protected int getLayout() {
        return R.layout.activity_hot_song_list;
    }

    @Override
    protected void initEventAndData() {
        mPresenter.loadHotSongListCategory();
        mPresenter.loadHotSongListCategoryList("全部", mPresenter.mLimit, mPresenter.mBefore);
    }

    @Override
    protected void onCreateLoadData() {

    }

    @Override
    protected void onDestroyRelease() {

    }

    @Override
    protected void onResumeLoadData() {

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
                mPresenter.loadHotSongListCategoryList("全部", mPresenter.mLimit, mPresenter.mBefore);
            }
        }, mRecycleViewHotSongList);
        mHotSongListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DetailActivity.startAction(HotSongListActivity.this,mList.get(position).getId());
            }
        });
    }

    private void initToolbar() {
        mSimpleToolbarHotSongList = findViewById(R.id.simple_toolbar_hot_song_list);
        mSimpleToolbarHotSongList.setTitleName("精品歌单." + mCategory);
        mSimpleToolbarHotSongList.setLeftIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        }).setRightTextClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUtil.showFiltrateDialog(HotSongListActivity.this, mCategory, mHotSongListCategoryEntity, new FiltrateDialogFragment.OnTipsListener() {
                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onSuccess(Dialog dialog, String category) {
                        LogUtil.e("回调");
                        dialog.dismiss();
                        mPresenter.mBefore = 0;
                        mList.clear();
                        mCategory = category;
                        mPresenter.loadHotSongListCategoryList(category, mPresenter.mLimit, mPresenter.mBefore);
                        mSimpleToolbarHotSongList.setTitleName("精品歌单." + mCategory);
                    }
                });
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

    @Override
    public int getDrawableStatusBar() {
        return 0;
    }

    /**
     * 热门歌单分类
     *
     * @param hotSongListCategoryEntity
     */
    @Override
    public void HotSongListCategorySuccess(HotSongListCategoryEntity hotSongListCategoryEntity) {
        mHotSongListCategoryEntity = hotSongListCategoryEntity;
    }

    @Override
    public void loadHotSongListCategoryListError(String msg) {

    }

    @Override
    public void loadHotSongListCategoryListSuccess(HotSongListEntity hotSongListEntity) {
        if (hotSongListEntity != null) {
            if (hotSongListEntity.getPlaylists().size() != 0) {
                if (mPresenter.count >= hotSongListEntity.getTotal()) {
                    mHotSongListAdapter.loadMoreEnd();
                } else {
                    mHotSongListAdapter.loadMoreComplete();
                }
                mList.addAll(hotSongListEntity.getPlaylists());

            } else {
                mList.clear();
            }
        } else {
            mList.clear();
        }
        mHotSongListAdapter.notifyDataSetChanged();
    }
}
