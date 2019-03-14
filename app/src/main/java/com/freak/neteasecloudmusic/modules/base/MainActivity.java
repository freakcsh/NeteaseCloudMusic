package com.freak.neteasecloudmusic.modules.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.freak.neteasecloudmusic.R;
import com.freak.neteasecloudmusic.modules.base.adapter.MenuItemAdapter;
import com.freak.neteasecloudmusic.modules.homepage.base.HomePageFragment;
import com.freak.neteasecloudmusic.modules.homepage.base.entity.MenuEntity;
import com.freak.neteasecloudmusic.modules.myself.MyselfFragment;
import com.freak.neteasecloudmusic.splash.SplashScreen;
import com.freak.neteasecloudmusic.utils.HandlerUtil;
import com.freak.neteasecloudmusic.utils.LogUtils;
import com.freak.neteasecloudmusic.view.custom.viewpager.CustomViewPager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * @author freak
 * @date 2019/02/19
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ActionBar mActionBar;
    private SplashScreen mSplashScreen;
    private ImageView barnet, barmusic, barfriends, search;
    private ArrayList<ImageView> tabs = new ArrayList<>();
    private DrawerLayout drawerLayout;
    private RecyclerView recycle_view_menu;
    private long time = 0;
    private List<MenuEntity> mItems = new ArrayList<MenuEntity>(
            Arrays.asList(
                    new MenuEntity("夜间模式"),
                    new MenuEntity("主题换肤"),
                    new MenuEntity("定时关闭音乐"),
                    new MenuEntity("下载歌曲品质"),
                    new MenuEntity("退出")

            ));
    private MenuItemAdapter mMenuItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mSplashScreen = new SplashScreen(this);
        mSplashScreen.show(R.mipmap.bg_splash, SplashScreen.SLIDE_LEFT);
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);

        initView();
    }


    private void initView() {
        getWindow().setBackgroundDrawableResource(R.color.color_fafafa);

        barnet = (ImageView) findViewById(R.id.bar_net);
        barmusic = (ImageView) findViewById(R.id.bar_music);
        barfriends = (ImageView) findViewById(R.id.bar_friends);
        search = (ImageView) findViewById(R.id.bar_search);
        barmusic = (ImageView) findViewById(R.id.bar_music);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        recycle_view_menu = (RecyclerView) findViewById(R.id.recycle_view_menu);

        setToolBar();
        setViewPager();
        setUpDrawer();
        HandlerUtil.getInstance(this).postDelayed(new Runnable() {
            @Override
            public void run() {
                mSplashScreen.removeSplashScreen();
            }
        }, 3000);


    }

    private void setToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
        mActionBar.setHomeAsUpIndicator(R.mipmap.ic_menu);
        mActionBar.setTitle("");
    }

    private void setViewPager() {
        tabs.add(barnet);
        tabs.add(barmusic);
        final CustomViewPager customViewPager = (CustomViewPager) findViewById(R.id.main_viewpager);
        final HomePageFragment mainFragment = new HomePageFragment();
        final MyselfFragment tabNetPagerFragment = new MyselfFragment();
        CustomViewPagerAdapter customViewPagerAdapter = new CustomViewPagerAdapter(getSupportFragmentManager());
        customViewPagerAdapter.addFragment(tabNetPagerFragment);
        customViewPagerAdapter.addFragment(mainFragment);
        customViewPager.setAdapter(customViewPagerAdapter);
        customViewPager.setCurrentItem(1);
        barmusic.setSelected(true);
        customViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switchTabs(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        barnet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customViewPager.setCurrentItem(0);
            }
        });
        barmusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customViewPager.setCurrentItem(1);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                final Intent intent = new Intent(MainActivity.this, NetSearchWordsActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                MainActivity.this.startActivity(intent);
            }
        });
    }

    private void setUpDrawer() {
        LayoutInflater inflater = LayoutInflater.from(this);
        recycle_view_menu.setLayoutManager(new LinearLayoutManager(this));
        mMenuItemAdapter = new MenuItemAdapter(R.layout.nav_header_main,mItems);
        mMenuItemAdapter.addHeaderView(inflater.inflate(R.layout.nav_header_main, recycle_view_menu, false));
        recycle_view_menu.setAdapter(mMenuItemAdapter);
        mMenuItemAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
    }

    private void switchTabs(int position) {
        for (int i = 0; i < tabs.size(); i++) {
            if (position == i) {
                tabs.get(i).setSelected(true);
            } else {
                tabs.get(i).setSelected(false);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtils.e("接收数据");
    }

    static class CustomViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();

        public CustomViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        public void addFragment(Fragment fragment) {
            mFragments.add(fragment);
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
}
