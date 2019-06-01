package com.freak.neteasecloudmusic.modules.base;

import android.Manifest;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.freak.httphelper.ApiCallback;
import com.freak.httphelper.SubscriberCallBack;
import com.freak.neteasecloudmusic.R;
import com.freak.neteasecloudmusic.base.BaseAbstractMvpActivity;
import com.freak.neteasecloudmusic.commom.constants.Constants;
import com.freak.neteasecloudmusic.glide.GlideApp;
import com.freak.neteasecloudmusic.modules.base.adapter.MenuItemAdapter;
import com.freak.neteasecloudmusic.modules.base.entity.LoginStatusEntity;
import com.freak.neteasecloudmusic.modules.disco.base.DiscoFragment;
import com.freak.neteasecloudmusic.modules.homepage.base.entity.MenuEntity;
import com.freak.neteasecloudmusic.modules.login.LoginActivity;
import com.freak.neteasecloudmusic.modules.music.MusicFragment;
import com.freak.neteasecloudmusic.modules.video.base.VideoFragment;
import com.freak.neteasecloudmusic.player.manager.AudioPlayerManager;
import com.freak.neteasecloudmusic.player.manager.ConfigInfo;
import com.freak.neteasecloudmusic.utils.SPUtils;
import com.freak.neteasecloudmusic.utils.StringUtils;
import com.freak.neteasecloudmusic.utils.ToastUtil;
import com.freak.neteasecloudmusic.view.custom.viewpager.CustomViewPager;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * @author freak
 * @date 2019/02/19
 */
@SuppressWarnings("ALL")
public class MainActivity extends BaseAbstractMvpActivity<MainPresenter> implements MainContract.View, View.OnClickListener {
    private ImageView mImgDisco, mImgMusic, mImgVideo, mImgSearch;
    private List<ImageView> tabs;
    private DrawerLayout drawerLayout;
    private RecyclerView mRecycleViewMenu;
    private long time = 0;
    private List<MenuEntity> mMenuEntityList;
    private MenuItemAdapter mMenuItemAdapter;
    private ImageView mImgMenu;
    private View mHeadVew;
    private TextView text_view_menu_login;
    private ImageView img_bg_login;
    private TextView text_view_username;
    private String mBackgroundUrl;
    private ImageView img_bg;
    private ConfigInfo mConfigInfo;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initEventAndData() {
        mConfigInfo = ConfigInfo.obtain();
        AudioPlayerManager.getInstance(mActivity).init();
    }

    @Override
    protected void onDestroy() {
        releaseData();
        destroyService();
        destroyReceiver();
        super.onDestroy();
    }



    @Override
    protected void initView() {
        mImgDisco = (ImageView) findViewById(R.id.img_disco);
        mImgMusic = (ImageView) findViewById(R.id.img_music);
        mImgVideo = (ImageView) findViewById(R.id.img_video);
        mImgSearch = (ImageView) findViewById(R.id.img_search);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mRecycleViewMenu = (RecyclerView) findViewById(R.id.recycle_view_menu);
        mImgMenu = (ImageView) findViewById(R.id.img_menu);
        mImgMenu.setOnClickListener(this);
        mImgSearch.setOnClickListener(this);
        mBackgroundUrl = (String) SPUtils.get(this, Constants.LOGIN_URL, "");
        setViewPager();
        setUpDrawer();
        new RxPermissions(this)
                .requestEach(Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.CALL_PHONE,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                .subscribe(new SubscriberCallBack<>(new ApiCallback<Permission>() {
                    @Override
                    public void onSuccess(Permission model) {
                        if (model.granted) {
                            mConfigInfo = ConfigInfo.obtain();
                            AudioPlayerManager.getInstance(mActivity).init();
                        } else {
//                            ToastUtil.shortShow("没有开启权限，部分功能将无法使用");
//                            finish();
                        }
                    }

                    @Override
                    public void onFailure(String msg) {

                    }
                }));
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter();
    }

    private void setViewPager() {
        tabs = new ArrayList<>();
        mMenuEntityList = new ArrayList<>();
        tabs.add(mImgMusic);
        tabs.add(mImgDisco);
        tabs.add(mImgVideo);
        mMenuEntityList = getItemList(this);
        final CustomViewPager customViewPager = (CustomViewPager) findViewById(R.id.main_viewpager);
        final MusicFragment musicFragment = new MusicFragment();
        final DiscoFragment discoFragment = new DiscoFragment();
        final VideoFragment videoFragment = new VideoFragment();
        CustomViewPagerAdapter customViewPagerAdapter = new CustomViewPagerAdapter(getSupportFragmentManager());
        customViewPagerAdapter.addFragment(discoFragment);
        customViewPagerAdapter.addFragment(musicFragment);
        customViewPagerAdapter.addFragment(videoFragment);
        customViewPager.setAdapter(customViewPagerAdapter);
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
        customViewPager.setCurrentItem(1);
        switchTabs(1);
        mImgMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customViewPager.setCurrentItem(0);
            }
        });
        mImgDisco.setOnClickListener(new View.OnClickListener() {
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
        mRecycleViewMenu.setLayoutManager(new LinearLayoutManager(this));
        mMenuItemAdapter = new MenuItemAdapter(R.layout.item_menu, mMenuEntityList);
        mPresenter.loadLoginStatusEntity();
        mMenuItemAdapter.bindToRecyclerView(mRecycleViewMenu);
        mRecycleViewMenu.setAdapter(mMenuItemAdapter);
        mMenuItemAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                menuItemOnClick(position);
            }
        });
    }

    private void initHeadView(int type, LoginStatusEntity loginStatusEntity) {
        switch (type) {
            case 1:
                mHeadVew = LayoutInflater.from(this).inflate(R.layout.view_menu_view_head_login, null);
                img_bg_login = mHeadVew.findViewById(R.id.img_bg_login);
                img_bg = mHeadVew.findViewById(R.id.img_bg);
                text_view_username = mHeadVew.findViewById(R.id.text_view_username);
                GlideApp.with(this).load(mBackgroundUrl).thumbnail(0.1f).into(img_bg_login);
                GlideApp.with(this).load(loginStatusEntity.getProfile().getAvatarUrl()).thumbnail(0.1f).into(img_bg);
                text_view_username.setText(loginStatusEntity.getProfile().getNickname());
                mMenuItemAdapter.addHeaderView(mHeadVew);
                break;
            case 2:
                mHeadVew = LayoutInflater.from(this).inflate(R.layout.item_menu_head, null);
                text_view_menu_login = mHeadVew.findViewById(R.id.text_view_menu_login);
                text_view_menu_login.setOnClickListener(this);
                mMenuItemAdapter.addHeaderView(mHeadVew);
                break;
            default:
                break;
        }

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
            //登陆
            case R.id.text_view_menu_login:
                LoginActivity.startAction(this);
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void showToast(String toast) {

    }

    @Override
    public void getLoginStatusSuccess(LoginStatusEntity loginStatusEntity) {
        if (!TextUtils.isEmpty(loginStatusEntity.getProfile().getNickname())) {
            initHeadView(1, loginStatusEntity);
        } else {
            initHeadView(2, loginStatusEntity);
        }
    }

    @Override
    public void getLoginStatusError() {
        initHeadView(2, null);
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
