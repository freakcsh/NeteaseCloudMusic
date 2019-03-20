package com.freak.neteasecloudmusic.modules.disco.recommend.songlist.detail;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.freak.neteasecloudmusic.R;
import com.freak.neteasecloudmusic.base.BaseAbstractMvpActivity;
import com.freak.neteasecloudmusic.modules.disco.recommend.entity.SongListDetailEntity;
import com.freak.neteasecloudmusic.modules.disco.recommend.songlist.detail.adapter.SongListDetailAdapter;
import com.freak.neteasecloudmusic.view.custom.toolbar.SimpleToolbar;

import java.util.ArrayList;
import java.util.List;

/**
 * 歌单详情
 *
 * @author Administrator
 * @date 2019/3/20
 */

public class DetailActivity extends BaseAbstractMvpActivity<DetailPresenter> implements DetailContract.View, View.OnClickListener {
    private SimpleToolbar mSimpleToolbarSongListDetail;
    private RecyclerView mRecycleViewSongListDetail;
    private SongListDetailAdapter mSongListDetailAdapter;
    private List<SongListDetailEntity> mList;
    private View mHeadView;

    @Override
    public void onClick(View v) {

    }

    @Override
    public void showToast(String toast) {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_song_list_detail;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected void initView() {
        mList = new ArrayList<>();
        initHeadView();
        mSimpleToolbarSongListDetail = findViewById(R.id.simple_toolbar_song_list_detail);
        mRecycleViewSongListDetail = findViewById(R.id.recycle_view_song_list_detail);
        mRecycleViewSongListDetail.setLayoutManager(new LinearLayoutManager(this));
        mSongListDetailAdapter = new SongListDetailAdapter(R.layout.item_song_list_detail, mList);
        mSongListDetailAdapter.addHeaderView(mHeadView);
        mRecycleViewSongListDetail.setAdapter(mSongListDetailAdapter);
        mSimpleToolbarSongListDetail.setLeftIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initHeadView() {
        mHeadView = LayoutInflater.from(this).inflate(R.layout.iten_song_list_detail_head, null);
    }

    @Override
    protected DetailPresenter createPresenter() {
        return new DetailPresenter();
    }
}
