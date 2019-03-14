package com.freak.neteasecloudmusic.modules.base.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.freak.neteasecloudmusic.modules.homepage.base.entity.MenuEntity;

import java.util.List;

public class MenuItemAdapter extends BaseQuickAdapter<MenuEntity, BaseViewHolder> {

    private LayoutInflater mInflater;
    private Context mContext;




    public MenuItemAdapter(int layoutResId, @Nullable List<MenuEntity> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, MenuEntity item) {

    }
}