package com.freak.neteasecloudmusic.modules.disco.recommend.songlist.detail;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.freak.httphelper.log.LogUtil;
import com.freak.neteasecloudmusic.R;
import com.freak.neteasecloudmusic.base.BaseAbstractMvpActivity;
import com.freak.neteasecloudmusic.base.IActivityStatusBar;
import com.freak.neteasecloudmusic.glide.GlideApp;
import com.freak.neteasecloudmusic.modules.disco.recommend.entity.SongListDetailEntity;
import com.freak.neteasecloudmusic.modules.disco.recommend.entity.SongUrlEntity;
import com.freak.neteasecloudmusic.modules.disco.recommend.songlist.detail.adapter.SongListDetailAdapter;
import com.freak.neteasecloudmusic.player.manager.AudioPlayerManager;
import com.freak.neteasecloudmusic.player.manager.entity.AudioInfo;
import com.freak.neteasecloudmusic.player.manager.util.AudioInfoTransitionUtil;
import com.freak.neteasecloudmusic.view.custom.toolbar.SimpleToolbar;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 歌单详情
 *
 * @author Administrator
 * @date 2019/3/20
 */

public class DetailActivity extends BaseAbstractMvpActivity<DetailPresenter> implements DetailContract.View, View.OnClickListener, IActivityStatusBar {
    private SimpleToolbar mSimpleToolbarSongListDetail;
    private RecyclerView mRecycleViewSongListDetail;
    private SongListDetailAdapter mSongListDetailAdapter;
    private List<SongListDetailEntity.PlaylistBean.TracksBean> mList;
    private View mHeadView;
    private ImageView img_song_list_detail_logo, img_song_list_detail_start_all;
    private TextView text_view_song_list_detail_bfl, text_view_song_list_detail_tip, text_view_song_list_detail_author, text_view_song_list_detail_comment_count,
            text_view_song_list_detail_share_count, text_view_song_list_detail_count, text_view_song_list_detail_collect;
    private CircleImageView img_song_list_detail_author_photo;
    private LinearLayout linear_layout_song_list_detail_comment, linear_layout_song_list_detail_share, linear_layout_song_list_detail_download, linear_layout_song_list_detail_more_selector;
    private String id;
    private SongListDetailEntity.PlaylistBean.TracksBean songInfo = null;

    public static void startAction(Context context, String songId) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("songId", songId);
        context.startActivity(intent);
    }

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
        mPresenter.getSongListDetail(id);
    }

    @Override
    protected void initView() {
        mList = new ArrayList<>();
        initHeadView();
        id = getIntent().getStringExtra("songId");
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
        mSongListDetailAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                songInfo = mList.get(position);
                mPresenter.loadSongUrl(songInfo.getId());
            }
        });
    }

    private void initHeadView() {
        mHeadView = LayoutInflater.from(this).inflate(R.layout.iten_song_list_detail_head, null);
        img_song_list_detail_logo = mHeadView.findViewById(R.id.img_song_list_detail_logo);
        text_view_song_list_detail_bfl = mHeadView.findViewById(R.id.text_view_song_list_detail_bfl);
        text_view_song_list_detail_tip = mHeadView.findViewById(R.id.text_view_song_list_detail_tip);
        text_view_song_list_detail_author = mHeadView.findViewById(R.id.text_view_song_list_detail_author);
        text_view_song_list_detail_comment_count = mHeadView.findViewById(R.id.text_view_song_list_detail_comment_count);
        text_view_song_list_detail_share_count = mHeadView.findViewById(R.id.text_view_song_list_detail_share_count);
        text_view_song_list_detail_count = mHeadView.findViewById(R.id.text_view_song_list_detail_count);
        text_view_song_list_detail_collect = mHeadView.findViewById(R.id.text_view_song_list_detail_collect);
        img_song_list_detail_author_photo = mHeadView.findViewById(R.id.img_song_list_detail_author_photo);
        img_song_list_detail_start_all = mHeadView.findViewById(R.id.img_song_list_detail_start_all);
        linear_layout_song_list_detail_comment = mHeadView.findViewById(R.id.linear_layout_song_list_detail_comment);
        linear_layout_song_list_detail_share = mHeadView.findViewById(R.id.linear_layout_song_list_detail_share);
        linear_layout_song_list_detail_download = mHeadView.findViewById(R.id.linear_layout_song_list_detail_download);
        linear_layout_song_list_detail_more_selector = mHeadView.findViewById(R.id.linear_layout_song_list_detail_more_selector);
    }

    @Override
    protected DetailPresenter createPresenter() {
        return new DetailPresenter();
    }

    @Override
    public void getSongListDetailSuccess(SongListDetailEntity songListDetailEntity) {
        if (songListDetailEntity != null) {
            GlideApp.with(this).load(songListDetailEntity.getPlaylist().getCoverImgUrl()).thumbnail(0.f).into(img_song_list_detail_logo);
            GlideApp.with(this).load(songListDetailEntity.getPlaylist().getCreator().getAvatarUrl()).thumbnail(0.f).into(img_song_list_detail_author_photo);
            text_view_song_list_detail_bfl.setText(songListDetailEntity.getPlaylist().getPlayCount() + "");
            text_view_song_list_detail_tip.setText(songListDetailEntity.getPlaylist().getName());
            text_view_song_list_detail_author.setText(songListDetailEntity.getPlaylist().getCreator().getNickname());
            text_view_song_list_detail_comment_count.setText(songListDetailEntity.getPlaylist().getCommentCount() + "");
            text_view_song_list_detail_share_count.setText(songListDetailEntity.getPlaylist().getShareCount() + "");
            text_view_song_list_detail_collect.setText("+ 收藏(" + songListDetailEntity.getPlaylist().getSubscribedCount() + ")首");
            text_view_song_list_detail_count.setText("(共" + songListDetailEntity.getPlaylist().getTrackCount() + "首)");
            if (songListDetailEntity.getPlaylist().getTracks().size() != 0) {
                mList.addAll(songListDetailEntity.getPlaylist().getTracks());
                mSongListDetailAdapter.notifyDataSetChanged();
            }
        }

    }

    @Override
    public void loadSongUrlSuccess(SongUrlEntity songUrlEntity) {
        LogUtil.e("播放歌曲信息 --》" + songUrlEntity.toString());
        AudioInfo audioInfo = AudioInfoTransitionUtil.audioInfoTransition(songUrlEntity.getData().get(0), songInfo);
//        AudioInfo audioInfo = new AudioInfo();
//        audioInfo.setDownloadUrl(songUrlEntity.getData().get(0).getUrl());
//        audioInfo.setType(AudioInfo.TYPE_LOCAL);
//        audioInfo.setHash(songUrlEntity.getData().get(0).getMd5());
        AudioPlayerManager.getInstance(this).playSongAndAdd(audioInfo);
    }

    @Override
    public int getStatusBarColor() {
        return getResources().getColor(R.color.color_FF3F3334);
    }
}
