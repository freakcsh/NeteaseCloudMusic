package com.freak.neteasecloudmusic.modules.find.recommend.songlist.all;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.freak.httphelper.RxBus;
import com.freak.neteasecloudmusic.R;
import com.freak.neteasecloudmusic.base.BaseAbstractSimpleActivity;
import com.freak.neteasecloudmusic.base.IActivityStatusBar;
import com.freak.neteasecloudmusic.event.CategoryEvent;
import com.freak.neteasecloudmusic.modules.find.recommend.entity.SongListCategoryEntity;

/**
 * 所有歌单分类
 *
 * @author Administrator
 */
public class AllSongListActivity extends BaseAbstractSimpleActivity implements IActivityStatusBar {
    private RecyclerView mRecycleViewAllSongList;
    private SongListCategoryEntity mSongListCategoryEntity;
    private AllSongListAdapter mAllSongListAdapter;
    private TextView mTextViewItemHeadAllSongList;
    private View mHeadView;


    @Override
    protected int getLayout() {
        return R.layout.activity_all_song_list;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected void onDestroyRelease() {

    }

    @SuppressLint("CheckResult")
    @Override
    protected void initView() {
        setTitle("选择分类");
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mSongListCategoryEntity = (SongListCategoryEntity) bundle.getSerializable("category");
        }
        mRecycleViewAllSongList = findViewById(R.id.recycle_view_all_song_list);
        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        mRecycleViewAllSongList.setLayoutManager(layoutManager);
        mAllSongListAdapter = new AllSongListAdapter(R.layout.item_all_song_list, mSongListCategoryEntity.getSub());
        initHeadView();
        mAllSongListAdapter.addHeaderView(mHeadView);
        mRecycleViewAllSongList.setAdapter(mAllSongListAdapter);
        mAllSongListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                RxBus.getDefault().post(new CategoryEvent(100, mSongListCategoryEntity.getSub().get(position).getName()));
                finish();
            }
        });
    }

    private void initHeadView() {
        mHeadView = LayoutInflater.from(this).inflate(R.layout.item_all_song_list_head, null);
        mTextViewItemHeadAllSongList = mHeadView.findViewById(R.id.text_view_item_head_all_song_list);
        mTextViewItemHeadAllSongList.setText(mSongListCategoryEntity.getAll().getName());
    }

    @Override
    public int getStatusBarColor() {
        return 0;
    }

    @Override
    public int getDrawableStatusBar() {
        return 0;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
