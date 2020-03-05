package com.freak.neteasecloudmusic.modules.music;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.freak.neteasecloudmusic.R;
import com.freak.neteasecloudmusic.base.BaseAbstractMvpFragment;
import com.freak.neteasecloudmusic.modules.music.adapter.MusicAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * @author freak
 * @date 2019/2/19
 */

public class MusicFragment extends BaseAbstractMvpFragment<MusicPresenter> implements MusicContract.View, View.OnClickListener {
    @BindView(R.id.recycle_view_music)
    RecyclerView recycleViewMusic;
    private MusicAdapter mMusicAdapter;
    private List<MultiItemEntity> mMultiItemEntityList;
    private LinearLayout linear_layout_local_music, linear_layout_recent_play, linear_layout_download_manager, linear_layout_broadcasting_station, linear_layout_collect;
    private TextView text_view_music_local_music, text_view_music_recent_play, text_view_music_download_manager, text_view_music_broadcasting_station, text_view_music_collect;

    @Override
    public void showToast(String toast) {

    }

    @Override
    protected MusicPresenter createPresenter() {
        return new MusicPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_myself;
    }

    @Override
    protected void initEventAndData() {
        getMyLove();
    }

    @Override
    protected void initView(View view) {
        mMultiItemEntityList = new ArrayList<>();
        recycleViewMusic.setLayoutManager(new LinearLayoutManager(getActivity()));
        mMusicAdapter = new MusicAdapter(mMultiItemEntityList);
        initHeaderView();
        recycleViewMusic.setAdapter(mMusicAdapter);
    }

    private void initHeaderView() {
        View headerView = LayoutInflater.from(getActivity()).inflate(R.layout.item_music_head_view, null);
        linear_layout_local_music = headerView.findViewById(R.id.linear_layout_local_music);
        linear_layout_recent_play = headerView.findViewById(R.id.linear_layout_recent_play);
        linear_layout_download_manager = headerView.findViewById(R.id.linear_layout_download_manager);
        linear_layout_broadcasting_station = headerView.findViewById(R.id.linear_layout_broadcasting_station);
        linear_layout_collect = headerView.findViewById(R.id.linear_layout_collect);
        text_view_music_local_music = headerView.findViewById(R.id.text_view_music_local_music);
        text_view_music_recent_play = headerView.findViewById(R.id.text_view_music_recent_play);
        text_view_music_download_manager = headerView.findViewById(R.id.text_view_music_download_manager);
        text_view_music_broadcasting_station = headerView.findViewById(R.id.text_view_music_broadcasting_station);
        text_view_music_collect = headerView.findViewById(R.id.text_view_music_collect);
        linear_layout_local_music.setOnClickListener(this);
        linear_layout_recent_play.setOnClickListener(this);
        linear_layout_download_manager.setOnClickListener(this);
        linear_layout_broadcasting_station.setOnClickListener(this);
        linear_layout_collect.setOnClickListener(this);
        mMusicAdapter.addHeaderView(headerView);
    }

    public void getMyLove() {
//        mMultiItemEntityList.addAll(null);
        mMusicAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initLazyData() {

    }

    @Override
    protected void showLoading() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_layout_local_music:
                break;
            case R.id.linear_layout_recent_play:
                break;
            case R.id.linear_layout_download_manager:
                break;
            case R.id.linear_layout_broadcasting_station:
                break;
            case R.id.linear_layout_collect:
                break;

            default:
                break;
        }
    }
}
