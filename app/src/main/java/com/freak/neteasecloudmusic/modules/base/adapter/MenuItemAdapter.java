package com.freak.neteasecloudmusic.modules.base.adapter;

import androidx.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.freak.neteasecloudmusic.R;
import com.freak.neteasecloudmusic.modules.homepage.base.entity.MenuEntity;

import java.util.List;

public class MenuItemAdapter extends BaseQuickAdapter<MenuEntity, BaseViewHolder> {

    public MenuItemAdapter(int layoutResId, @Nullable List<MenuEntity> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, MenuEntity item) {
//        if (item.isShowSmallView()) {
//            helper.getView(R.id.view_item_small_header).setVisibility(View.VISIBLE);
//        } else {
//            helper.getView(R.id.view_item_small_header).setVisibility(View.GONE);
//        }
        if (item.isShowBigView()) {
            helper.getView(R.id.view_item_big).setVisibility(View.VISIBLE);
        } else {
            helper.getView(R.id.view_item_big).setVisibility(View.GONE);
        }
        helper.setText(R.id.text_view_item_name, item.getTitle());
        helper.setBackgroundRes(R.id.img_item_icon, item.getIcon());

    }
}