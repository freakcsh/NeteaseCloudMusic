package com.freak.neteasecloudmusic.modules.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.freak.neteasecloudmusic.R;
import com.freak.neteasecloudmusic.modules.base.adapter.MenuItemAdapter;
import com.freak.neteasecloudmusic.modules.disco.base.DiscoFragment;
import com.freak.neteasecloudmusic.modules.homepage.base.entity.MenuEntity;
import com.freak.neteasecloudmusic.modules.music.MusicFragment;
import com.freak.neteasecloudmusic.modules.video.base.VideoFragment;
import com.freak.neteasecloudmusic.utils.StringUtils;
import com.freak.neteasecloudmusic.utils.ToastUtil;
import com.freak.neteasecloudmusic.view.custom.viewpager.CustomViewPager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * @author freak
 * @date 2019/02/19
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView mImgDisco, mImgMusic, mImgVideo, mImgSearch;
    private List<ImageView> tabs;
    private DrawerLayout drawerLayout;
    private RecyclerView mRecycleViewMenu;
    private long time = 0;
    private List<MenuEntity> mMenuEntityList;
    private MenuItemAdapter mMenuItemAdapter;
    private ImageView mImgMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
//        mSplashScreen = new SplashScreen(this);
//        mSplashScreen.show(R.mipmap.bg_splash, SplashScreen.SLIDE_LEFT);
        super.onCreate(savedInstanceState);
        initView();
    }


    private void initView() {

        mImgDisco = (ImageView) findViewById(R.id.img_disco);
        mImgMusic = (ImageView) findViewById(R.id.img_music);
        mImgVideo = (ImageView) findViewById(R.id.img_video);
        mImgSearch = (ImageView) findViewById(R.id.img_search);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mRecycleViewMenu = (RecyclerView) findViewById(R.id.recycle_view_menu);
        mImgMenu = (ImageView) findViewById(R.id.img_menu);
        mImgMenu.setOnClickListener(this);
        mImgSearch.setOnClickListener(this);
        setViewPager();
        setUpDrawer();
//        HandlerUtil.getInstance(this).postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mSplashScreen.removeSplashScreen();
//            }
//        }, 3000);


    }


    private void setViewPager() {
        tabs = new ArrayList<>();
        mMenuEntityList = new ArrayList<>();
        tabs.add(mImgDisco);
        tabs.add(mImgMusic);
        tabs.add(mImgVideo);
        mMenuEntityList = getItemList(this);
        final CustomViewPager customViewPager = (CustomViewPager) findViewById(R.id.main_viewpager);
        final MusicFragment musicFragment = new MusicFragment();
        final DiscoFragment discoFragment = new DiscoFragment();
        final VideoFragment videoFragment = new VideoFragment();
        CustomViewPagerAdapter customViewPagerAdapter = new CustomViewPagerAdapter(getSupportFragmentManager());
        customViewPagerAdapter.addFragment(musicFragment);
        customViewPagerAdapter.addFragment(discoFragment);
        customViewPagerAdapter.addFragment(videoFragment);
        customViewPager.setAdapter(customViewPagerAdapter);
        customViewPager.setCurrentItem(1);
        mImgMusic.setSelected(true);
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

        mImgDisco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customViewPager.setCurrentItem(0);
            }
        });
        mImgMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customViewPager.setCurrentItem(1);
            }
        });
        mImgVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customViewPager.setCurrentItem(2);
            }
        });
    }

    private void setUpDrawer() {
        LayoutInflater inflater = LayoutInflater.from(this);
        mRecycleViewMenu.setLayoutManager(new LinearLayoutManager(this));
        mMenuItemAdapter = new MenuItemAdapter(R.layout.item_menu, mMenuEntityList);
        mMenuItemAdapter.addHeaderView(inflater.inflate(R.layout.item_menu_head, mRecycleViewMenu, false));
        mMenuItemAdapter.bindToRecyclerView(mRecycleViewMenu);
        mRecycleViewMenu.setAdapter(mMenuItemAdapter);
        mMenuItemAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                menuItemOnClick(position);
            }
        });
    }

    public List<MenuEntity> getItemList(Context context) {
        List<MenuEntity> mineItems = new ArrayList<>();
        String json = StringUtils.getJsonForLocation(context, "menu.json");
        if (!TextUtils.isEmpty(json)) {
            try {
                JSONArray array = new JSONArray(json);
                for (int i = 0; i < array.length(); i++) {
                    JSONArray arrayItem = array.getJSONArray(i);
                    for (int k = 0; k < arrayItem.length(); k++) {
                        JSONObject object = arrayItem.getJSONObject(k);
                        MenuEntity mineItem = new MenuEntity();
                        mineItem.setId(object.optInt("id"));
                        mineItem.setTitle(object.optString("title"));
                        mineItem.setIcon(context.getResources().getIdentifier(object.optString("icon"), "mipmap", context.getPackageName()));
                        mineItem.setShowArrows(object.optBoolean("isShowArrows"));
                        mineItem.setShowBigView(object.optBoolean("isShowBigView"));
                        mineItem.setShowSmallView(object.optBoolean("isShowSmallView"));
                        mineItems.add(mineItem);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mineItems;
    }

    public void menuItemOnClick(int position) {
        switch (mMenuEntityList.get(position).getId()) {
            case 10:
                ToastUtil.shortShow(mMenuEntityList.get(position).getTitle());
                drawerLayout.closeDrawers();
                break;
            case 11:
                ToastUtil.shortShow(mMenuEntityList.get(position).getTitle());
                drawerLayout.closeDrawers();
                break;
            case 12:
                ToastUtil.shortShow(mMenuEntityList.get(position).getTitle());
                drawerLayout.closeDrawers();
                break;
            case 13:
                ToastUtil.shortShow(mMenuEntityList.get(position).getTitle());
                drawerLayout.closeDrawers();
                break;
            case 20:
                ToastUtil.shortShow(mMenuEntityList.get(position).getTitle());
                drawerLayout.closeDrawers();
                break;
            case 21:
                ToastUtil.shortShow(mMenuEntityList.get(position).getTitle());
                drawerLayout.closeDrawers();
                break;
            case 22:
                ToastUtil.shortShow(mMenuEntityList.get(position).getTitle());
                drawerLayout.closeDrawers();
                break;
            case 30:
                ToastUtil.shortShow(mMenuEntityList.get(position).getTitle());
                drawerLayout.closeDrawers();
                break;

            default:
                break;
        }
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
            //侧滑菜单
            case R.id.img_menu:
                drawerLayout.openDrawer(Gravity.LEFT);
                break;
            //搜索
            case R.id.img_search:
                break;
            default:
                break;
        }
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
