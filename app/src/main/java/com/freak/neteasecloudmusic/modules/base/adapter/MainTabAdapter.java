package com.freak.neteasecloudmusic.modules.base.adapter;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 *
 * @author pc1994
 * @date 2018/3/22
 */

public class MainTabAdapter extends FragmentPagerAdapter {

    public List<Fragment> mFragments;
    private List<String> titles;

    public MainTabAdapter(FragmentManager fm, List<Fragment> fragments, List<String> titles) {
        super(fm);
        this.mFragments = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

}
