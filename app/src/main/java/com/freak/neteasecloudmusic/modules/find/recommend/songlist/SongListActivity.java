package com.freak.neteasecloudmusic.modules.find.recommend.songlist;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.freak.httphelper.RxBus;
import com.freak.neteasecloudmusic.R;
import com.freak.neteasecloudmusic.base.BaseAbstractMvpActivity;
import com.freak.neteasecloudmusic.base.IActivityStatusBar;
import com.freak.neteasecloudmusic.event.CategoryEvent;
import com.freak.neteasecloudmusic.modules.find.recommend.entity.HotSongListEntity;
import com.freak.neteasecloudmusic.modules.find.recommend.entity.SongListCategoryEntity;
import com.freak.neteasecloudmusic.modules.find.recommend.entity.SongListEntity;
import com.freak.neteasecloudmusic.modules.find.recommend.songlist.adapter.SongListAdapter;
import com.freak.neteasecloudmusic.modules.find.recommend.songlist.all.AllSongListActivity;
import com.freak.neteasecloudmusic.modules.find.recommend.songlist.detail.DetailActivity;
import com.freak.neteasecloudmusic.modules.find.recommend.songlist.hot.HotSongListActivity;
import com.freak.neteasecloudmusic.view.custom.toolbar.SimpleToolbar;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 歌单
 *
 * @author Administrator
 */
public class SongListActivity extends BaseAbstractMvpActivity<SongListPresenter> implements SongListContract.View, IActivityStatusBar, View.OnClickListener {
    private RecyclerView mRecycleViewSongList;
    private TextView mTextViewCategory;
    private TextView mTextViewHy, mTextViewDz, mTextViewGf;
    private SongListAdapter mSongListAdapter;
    private List<SongListEntity.PlaylistsBean> mList;
    private View mHeadView;
    private SongListCategoryEntity mSongListCategoryEntity;
    private Disposable mDisposable;
    private ImageView mImgCategoryBg;
    private LinearLayout mLinearLayoutHotSongList;
    private TextView mTextViewHeadCategory;
    private TextView mTextViewHeadCategoryTip;
    private SimpleToolbar mSimpleToolbarSongList;

    @Override
    protected int getLayout() {
        return R.layout.activity_song_list;
    }

    @Override
    protected void initEventAndData() {
        mPresenter.loadSongListCategory();

        mPresenter.loadSongListCategoryList("new", "全部歌单", mPresenter.mLimit, mPresenter.mOffset);
        mPresenter.loadHotSongListCategoryList("全部歌单", 1, 0);
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

    @SuppressLint("CheckResult")
    @Override
    protected void initView() {
        mList = new ArrayList<>();
        initToolbar();
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
                mPresenter.loadSongListCategoryList("new", mTextViewCategory.getText().toString().trim(), mPresenter.mLimit, mPresenter.mOffset);
            }
        }, mRecycleViewSongList);
        mSongListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DetailActivity.startAction(SongListActivity.this,mList.get(position).getId());
            }
        });
        mDisposable = RxBus.getDefault().tObservable(CategoryEvent.class).subscribe(new Consumer<CategoryEvent>() {
            @Override
            public void accept(CategoryEvent categoryEvent) throws Exception {
                if (categoryEvent.getId() == 100) {
                    mTextViewCategory.setText(categoryEvent.getName());
                    mPresenter.mOffset = 0;
                    mList.clear();
                    mPresenter.loadSongListCategoryList("new", categoryEvent.getName(), mPresenter.mLimit, mPresenter.mOffset);
                }
            }
        });
    }

    private void initToolbar() {
        mSimpleToolbarSongList = findViewById(R.id.simple_toolbar_song_list);
        mSimpleToolbarSongList.setLeftIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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
        return getResources().getColor(R.color.color_FF3D3B35);
    }

    @Override
    public int getDrawableStatusBar() {
        return 0;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.text_view_gf:
                mTextViewCategory.setText(mTextViewGf.getText().toString().trim());
                mPresenter.mOffset = 0;
                mList.clear();
                mPresenter.loadSongListCategoryList("new", mTextViewGf.getText().toString().trim(), mPresenter.mLimit, mPresenter.mOffset);
                break;
            case R.id.text_view_dz:
                mTextViewCategory.setText(mTextViewDz.getText().toString().trim());
                mPresenter.mOffset = 0;
                mList.clear();
                mPresenter.loadSongListCategoryList("new", mTextViewDz.getText().toString().trim(), mPresenter.mLimit, mPresenter.mOffset);
                break;
            case R.id.text_view_hy:
                mTextViewCategory.setText(mTextViewHy.getText().toString().trim());
                mPresenter.mOffset = 0;
                mList.clear();
                mPresenter.loadSongListCategoryList("new", mTextViewHy.getText().toString().trim(), mPresenter.mLimit, mPresenter.mOffset);
                break;
            case R.id.text_view_category:
                Bundle bundle = new Bundle();
                bundle.putSerializable("category", mSongListCategoryEntity);
                gotoActivity(AllSongListActivity.class, false, bundle);
                break;
            //精品歌单
            case R.id.linear_layout_hot_song_list:
                gotoActivity(HotSongListActivity.class, false);
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
        if (songListEntity != null) {
            if (mPresenter.mOffset >= songListEntity.getTotal()) {
                mSongListAdapter.loadMoreEnd();
            } else {
                mSongListAdapter.loadMoreComplete();
            }
            mList.addAll(songListEntity.getPlaylists());
        } else {
            mList.clear();
        }

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

        mTextViewHeadCategoryTip = mHeadView.findViewById(R.id.text_view_head_category_tip);
        mTextViewHeadCategory = mHeadView.findViewById(R.id.text_view_head_category);
        mLinearLayoutHotSongList = mHeadView.findViewById(R.id.linear_layout_hot_song_list);
        mImgCategoryBg = mHeadView.findViewById(R.id.img_category_bg);
        mLinearLayoutHotSongList.setOnClickListener(this);
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
        mSongListCategoryEntity = songListCategoryEntity;
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


    @Override
    public void loadHotSongListCategoryListError(String msg) {

    }

    @Override
    public void loadHotSongListCategoryListSuccess(HotSongListEntity hotSongListEntity) {
        Glide.with(this).load(hotSongListEntity.getPlaylists().get(0).getCoverImgUrl()).thumbnail(0.1f).into(mImgCategoryBg);
        mTextViewHeadCategory.setText(hotSongListEntity.getPlaylists().get(0).getName());
        mTextViewHeadCategoryTip.setText(hotSongListEntity.getPlaylists().get(0).getCopywriter());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDisposable.isDisposed()) {
            mDisposable.dispose();
            mDisposable = null;
        }
    }
}
