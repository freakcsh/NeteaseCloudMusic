package com.freak.neteasecloudmusic.modules.disco.recommend.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.freak.neteasecloudmusic.app.App;
import com.freak.neteasecloudmusic.modules.disco.recommend.entity.BannerEntity;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;

import java.util.List;


public class RollPagerViewAdapter extends StaticPagerAdapter {
    private Context context;
    private List<BannerEntity.BannersBean> dataList;
    private ImageView mImageView;

    public RollPagerViewAdapter(Context context, List<BannerEntity.BannersBean> list) {
        this.context = context;
        this.dataList = list;
    }

    @Override
    public View getView(ViewGroup container, final int position) {

        mImageView = new ImageView(App.getInstance().getApplicationContext());
        Glide.with(App.getInstance().getApplicationContext()).load(dataList.get(position).getPicUrl()).into(mImageView);
        mImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mImageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams
                .MATCH_PARENT));
        return mImageView;
    }



    @Override
    public int getCount() {
        return dataList.size();
    }

    public void setData(List<BannerEntity.BannersBean> dataList) {
        this.dataList = dataList;
    }
}
