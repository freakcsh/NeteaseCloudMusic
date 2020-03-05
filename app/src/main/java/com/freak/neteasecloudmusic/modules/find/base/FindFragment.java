package com.freak.neteasecloudmusic.modules.find.base;

import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.freak.neteasecloudmusic.R;
import com.freak.neteasecloudmusic.base.BaseAbstractMvpFragment;
import com.freak.neteasecloudmusic.modules.find.broadcasting.BroadcastingFragment;
import com.freak.neteasecloudmusic.modules.find.friend.FriendFragment;
import com.freak.neteasecloudmusic.modules.find.recommend.base.RecommendFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * @author freak
 * @date 2019/2/19
 */

public class FindFragment extends BaseAbstractMvpFragment<FindPresenter> implements FindContract.View {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;


    @Override
    public void showToast(String toast) {

    }

    @Override
    protected FindPresenter createPresenter() {
        return new FindPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_merchant;
    }

    @Override
    protected void initEventAndData() {

    }

    @Override
    protected void initView(View view) {
        mTabLayout = view.findViewById(R.id.tab_layout);
        mViewPager = view.findViewById(R.id.viewpager);
        mTabLayout.setupWithViewPager(mViewPager);
        if (mViewPager != null) {
            setupViewPager(mViewPager);
            mViewPager.setOffscreenPageLimit(1);
        }
    }

    @Override
    protected void initLazyData() {

    }

    @Override
    protected void showLoading() {

    }

    public void setupViewPager(ViewPager upViewPager) {
        TabAdapter tabAdapter = new TabAdapter(getActivity().getSupportFragmentManager());
        tabAdapter.addFragment(new RecommendFragment(),"推荐");
        tabAdapter.addFragment(new FriendFragment(),"朋友");
        tabAdapter.addFragment(new BroadcastingFragment(),"电台");
        upViewPager.setAdapter(tabAdapter);
    }

    private class TabAdapter extends FragmentStatePagerAdapter {
        List<Fragment> mFragments = new ArrayList<>();
        List<String> mFragmentTitles = new ArrayList<>();

        public TabAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int i) {
            return mFragments.get(i);
        }

        @Override
        public int getCount() {
            return mFragmentTitles.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}
