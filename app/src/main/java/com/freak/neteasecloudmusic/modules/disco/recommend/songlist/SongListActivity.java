package com.freak.neteasecloudmusic.modules.disco.recommend.songlist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.freak.neteasecloudmusic.R;
import com.freak.neteasecloudmusic.base.BaseAbstractMvpActivity;
import com.freak.neteasecloudmusic.base.IActivityStatusBar;
import com.freak.neteasecloudmusic.modules.disco.recommend.entity.HotSongListCategoryEntity;
import com.freak.neteasecloudmusic.modules.disco.recommend.entity.SongListCategoryEntity;
import com.freak.neteasecloudmusic.modules.disco.recommend.entity.SongListEntity;
import com.freak.neteasecloudmusic.modules.disco.recommend.songlist.adapter.SongListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
public class SongListActivity extends BaseAbstractMvpActivity<SongListPresenter> implements SongListContract.View, IActivityStatusBar, View.OnClickListener {
    private RecyclerView mRecycleViewSongList;
    private TextView mTextViewCategory;
    private TextView mTextViewHy, mTextViewDz, mTextViewGf;
    private SongListAdapter mSongListAdapter;
    private List<SongListEntity.PlaylistsBean> mList;
    private View mHeadView;

    public static void startAction(Context context) {
        Intent intent = new Intent(context, SongListActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_song_list;
    }

    @Override
    protected void initEventAndData() {
        mPresenter.loadSongListCategory();
        mPresenter.loadSongListCategoryList("hot", "全部歌单", mPresenter.mLimit, mPresenter.mOffset);
    }

    @Override
    protected void initView() {
        mList = new ArrayList<>();
        mRecycleViewSongList = findViewById(R.id.recycle_view_song_list);
        mRecycleViewSongList.setOnClickListener(this);
        mRecycleViewSongList.setLayoutManager(new GridLayoutManager(this, 2));
        mSongListAdapter = new SongListAdapter(mList, this);
        mSongListAdapter.bindToRecyclerView(mRecycleViewSongList);
        mSongListAdapter.disableLoadMoreIfNotFullPage();
        mSongListAdapter.setEnableLoadMore(true);
        initHeadView();
        mSongListAdapter.addHeaderView(mHeadView);
        mRecycleViewSongList.setAdapter(mSongListAdapter);
        mSongListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPresenter.loadSongListCategoryList("hot", mTextViewCategory.getText().toString().trim(), mPresenter.mLimit, mPresenter.mOffset);
            }
        }, mRecycleViewSongList);
    }

    @Override
    protected SongListPresenter createPresenter() {
        return new SongListPresenter();
    }

    @Override
    public void showToast(String toast) {

    }

    @Override
    public int getStatusBarColor() {
        return getResources().getColor(R.color.color_FF919C96);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text_view_gf:
                break;
            case R.id.text_view_dz:
                break;
            case R.id.text_view_hy:
                break;
            case R.id.text_view_category:
                break;
            default:
                break;
        }
    }

    /**
     * 歌单分类类别列表
     *
     * @param songListEntity
     */
    @Override
    public void loadSongListCategoryListSuccess(SongListEntity songListEntity) {
        if (mPresenter.mOffset >= songListEntity.getTotal()) {
            mSongListAdapter.loadMoreEnd();
        } else {
            mSongListAdapter.loadMoreComplete();
        }
        mList.addAll(songListEntity.getPlaylists());
        mSongListAdapter.notifyDataSetChanged();
    }

    /**
     * 获取歌单失败
     *
     * @param msg
     */
    @Override
    public void loadSongListCategoryListError(String msg) {
        mSongListAdapter.loadMoreFail();
    }

    public void initHeadView() {
        mHeadView = LayoutInflater.from(this).inflate(R.layout.item_song_list_head_view, null);
        mTextViewCategory = mHeadView.findViewById(R.id.text_view_category);
        mTextViewHy = mHeadView.findViewById(R.id.text_view_hy);
        mTextViewDz = mHeadView.findViewById(R.id.text_view_dz);
        mTextViewGf = mHeadView.findViewById(R.id.text_view_gf);
        mTextViewGf.setOnClickListener(this);
        mTextViewDz.setOnClickListener(this);
        mTextViewHy.setOnClickListener(this);
        mTextViewCategory.setOnClickListener(this);
    }

    /**
     * 歌单分类
     *
     * @param songListCategoryEntity
     */
    @Override
    public void loadSongListCategorySuccess(SongListCategoryEntity songListCategoryEntity) {
        mTextViewCategory.setText(songListCategoryEntity.getAll().getName());
        switch (songListCategoryEntity.getSub().size()) {
            case 1:
                mTextViewGf.setText(songListCategoryEntity.getSub().get(0).getName());
                break;
            case 2:
                mTextViewDz.setText(songListCategoryEntity.getSub().get(1).getName());
                mTextViewGf.setText(songListCategoryEntity.getSub().get(0).getName());
                break;
            case 3:
                mTextViewHy.setText(songListCategoryEntity.getSub().get(2).getName());
                mTextViewDz.setText(songListCategoryEntity.getSub().get(1).getName());
                mTextViewGf.setText(songListCategoryEntity.getSub().get(0).getName());
                break;
            default:
                mTextViewHy.setText(songListCategoryEntity.getSub().get(2).getName());
                mTextViewDz.setText(songListCategoryEntity.getSub().get(1).getName());
                mTextViewGf.setText(songListCategoryEntity.getSub().get(0).getName());
                break;
        }

    }

    /**
     * 热门歌单分类
     *
     * @param hotSongListCategoryEntity
     */
    @Override
    public void HotSongListCategorySuccess(HotSongListCategoryEntity hotSongListCategoryEntity) {

    }
}
