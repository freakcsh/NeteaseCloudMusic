package com.freak.neteasecloudmusic.modules.disco.recommend.base;

import android.view.View;
import android.widget.LinearLayout;

import com.freak.neteasecloudmusic.R;
import com.freak.neteasecloudmusic.base.BaseAbstractMvpFragment;
import com.freak.neteasecloudmusic.modules.disco.recommend.adapter.RollPagerViewAdapter;
import com.freak.neteasecloudmusic.modules.disco.recommend.entity.BannerEntity;
import com.freak.neteasecloudmusic.modules.disco.recommend.songlist.SongListActivity;
import com.freak.neteasecloudmusic.net.log.LogUtil;
import com.jude.rollviewpager.OnItemClickListener;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;

/**
 * Created by Administrator on 2019/3/15.
 */

public class RecommendFragment extends BaseAbstractMvpFragment<RecommendPresenter> implements RecommendContract.View, View.OnClickListener {
    private RollPagerView mRollPagerView;
    private LinearLayout mLinearLayoutRecommendSongList;

    @Override
    public void showToast(String toast) {

    }

    @Override
    protected RecommendPresenter createPresenter() {
        return new RecommendPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recommend;
    }

    @Override
    protected void initEventAndData() {
        loadBanner();
    }

    @Override
    protected void initView(View view) {
        initRollPagerView(view);
        mLinearLayoutRecommendSongList = view.findViewById(R.id.linear_layout_recommend_song_list);
        mLinearLayoutRecommendSongList.setOnClickListener(this);
    }

    private void initRollPagerView(View view) {
        mRollPagerView = view.findViewById(R.id.roll_pagerView);
        //设置播放时间间隔
        mRollPagerView.setPlayDelay(3000);
        //设置透明度
        mRollPagerView.setAnimationDurtion(500);
        mRollPagerView.setHintView(new ColorPointHintView(getActivity(), 0xffff6d1d, 0xffeeeeee));
        mRollPagerView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                LogUtil.e("点击--》" + position);
            }
        });
    }

    @Override
    protected void initLazyData() {

    }

    public void loadBanner() {
        mPresenter.loadBanner();
    }

    @Override
    protected void showLoading() {

    }

    @Override
    public void loadBannerSuccess(BannerEntity bannerEntity) {
        RollPagerViewAdapter adapter = new RollPagerViewAdapter(getActivity(), bannerEntity.getBanners());
        mRollPagerView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear_layout_recommend_song_list:
                SongListActivity.startAction(getActivity());
                break;
            default:
                break;
        }
    }
}
