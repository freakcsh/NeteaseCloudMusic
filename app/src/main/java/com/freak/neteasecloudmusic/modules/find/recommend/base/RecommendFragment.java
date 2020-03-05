package com.freak.neteasecloudmusic.modules.find.recommend.base;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.freak.guidepage.GuideBanner;
import com.freak.neteasecloudmusic.R;
import com.freak.neteasecloudmusic.base.BaseAbstractMvpFragment;
import com.freak.neteasecloudmusic.dialog.LoadingViewDialog;
import com.freak.neteasecloudmusic.modules.find.recommend.entity.BannerEntity;
import com.freak.neteasecloudmusic.modules.find.recommend.songlist.SongListActivity;
import com.freak.neteasecloudmusic.net.log.LogUtil;
import com.freak.neteasecloudmusic.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2019/3/15.
 */

public class RecommendFragment extends BaseAbstractMvpFragment<RecommendPresenter> implements RecommendContract.View, GuideBanner.Adapter<ImageView, BannerEntity.BannersBean> {

    @BindView(R.id.guideBannerFind)
    GuideBanner guideBannerFind;
    @BindView(R.id.linearLayoutFindEveryDayRecommend)
    LinearLayout linearLayoutFindEveryDayRecommend;
    @BindView(R.id.linearLayoutFindSongList)
    LinearLayout linearLayoutFindSongList;
    @BindView(R.id.linearLayoutFindRand)
    LinearLayout linearLayoutFindRand;
    @BindView(R.id.linearLayoutFindRadioStation)
    LinearLayout linearLayoutFindRadioStation;
    @BindView(R.id.linearLayoutFindLive)
    LinearLayout linearLayoutFindLive;
    @BindView(R.id.linearLayoutFind)
    LinearLayout linearLayoutFind;
    @BindView(R.id.recyclerViewFind)
    RecyclerView recyclerViewFind;

    @Override
    public void showToast(String toast) {
        LoadingViewDialog.getInstance().dismiss();
        ToastUtil.shortShow(toast);
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

    }

    @Override
    protected void initLazyData() {

    }

    public void loadBanner() {
        mPresenter.loadBanner();
        LoadingViewDialog.getInstance().show(mActivity);
    }

    @Override
    protected void showLoading() {

    }

    @Override
    public void loadBannerSuccess(BannerEntity bannerEntity) {
        LoadingViewDialog.getInstance().dismiss();
        LogUtil.e("bannerEntity "+bannerEntity.toString() );
        if (bannerEntity.getBanners() != null && bannerEntity.getBanners().size() > 0) {
            /**
             * 设置是否开启自动轮播，需要在 setData 方法之前调用，并且调了该方法后必须再调用一次 setData 方法
             * 例如根据图片当图片数量大于 1 时开启自动轮播，等于 1 时不开启自动轮播
             */
            guideBannerFind.setAutoPlayAble(bannerEntity.getBanners().size() > 1);

            guideBannerFind.setAdapter(this);

            guideBannerFind.setData(bannerEntity.getBanners(), null);
        }

    }


    @OnClick({R.id.linearLayoutFindEveryDayRecommend, R.id.linearLayoutFindSongList, R.id.linearLayoutFindRand, R.id.linearLayoutFindRadioStation, R.id.linearLayoutFindLive})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.linearLayoutFindEveryDayRecommend:
                break;
            case R.id.linearLayoutFindSongList:
                gotoActivity(SongListActivity.class);
                break;
            case R.id.linearLayoutFindRand:
                break;
            case R.id.linearLayoutFindRadioStation:
                break;
            case R.id.linearLayoutFindLive:
                break;
        }
    }

    @Override
    public void fillBannerItem(GuideBanner banner, ImageView itemView, @Nullable BannerEntity.BannersBean model, int position) {
        Glide.with(itemView.getContext())
                .load(model.getImageUrl())
                .apply(new RequestOptions().placeholder(R.mipmap.holder).error(R.mipmap.holder).dontAnimate().centerCrop())
                .into(itemView);
    }
}
