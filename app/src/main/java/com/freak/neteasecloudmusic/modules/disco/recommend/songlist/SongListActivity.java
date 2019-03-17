package com.freak.neteasecloudmusic.modules.disco.recommend.songlist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.freak.neteasecloudmusic.R;
import com.freak.neteasecloudmusic.base.BaseAbstractMvpActivity;
import com.freak.neteasecloudmusic.base.IActivityStatusBar;
import com.freak.neteasecloudmusic.modules.disco.recommend.entity.SongListEntity;
import com.freak.neteasecloudmusic.modules.disco.recommend.songlist.adapter.SongListAdapter;

import java.util.ArrayList;
import java.util.List;

public class SongListActivity extends BaseAbstractMvpActivity<SongListPresenter> implements SongListContract.View, IActivityStatusBar, View.OnClickListener {
    private RecyclerView mRecycleViewSongList;
    private TextView mTextViewCategory;
    private TextView mTextViewHy, mTextViewDz, mTextViewGf;
    private SongListAdapter mSongListAdapter;
    private List<SongListEntity> mList;

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

    }

    @Override
    protected void initView() {
        mList = new ArrayList<>();
        mRecycleViewSongList = findViewById(R.id.recycle_view_song_list);
        mTextViewCategory = findViewById(R.id.text_view_category);
        mTextViewHy = findViewById(R.id.text_view_hy);
        mTextViewDz = findViewById(R.id.text_view_dz);
        mTextViewGf = findViewById(R.id.text_view_gf);
        mTextViewGf.setOnClickListener(this);
        mTextViewDz.setOnClickListener(this);
        mTextViewHy.setOnClickListener(this);
        mTextViewCategory.setOnClickListener(this);
        mRecycleViewSongList.setOnClickListener(this);
        mRecycleViewSongList.setLayoutManager(new GridLayoutManager(this, 2));
        mSongListAdapter = new SongListAdapter(R.layout.item_song_list, mList);
        mSongListAdapter.bindToRecyclerView(mRecycleViewSongList);
        mRecycleViewSongList.setAdapter(mSongListAdapter);
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
}
