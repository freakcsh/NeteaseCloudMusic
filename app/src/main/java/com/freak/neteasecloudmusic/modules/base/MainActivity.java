package com.freak.neteasecloudmusic.modules.base;

import android.Manifest;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.flyco.tablayout.SlidingTabLayout;
import com.freak.httphelper.ApiCallback;
import com.freak.httphelper.SubscriberCallBack;
import com.freak.neteasecloudmusic.R;
import com.freak.neteasecloudmusic.base.BaseAbstractMvpActivity;
import com.freak.neteasecloudmusic.commom.constants.Constants;
import com.freak.neteasecloudmusic.modules.base.adapter.MainTabAdapter;
import com.freak.neteasecloudmusic.modules.base.adapter.MenuItemAdapter;
import com.freak.neteasecloudmusic.modules.base.entity.LoginStatusEntity;
import com.freak.neteasecloudmusic.modules.find.recommend.base.RecommendFragment;
import com.freak.neteasecloudmusic.modules.homepage.base.entity.MenuEntity;
import com.freak.neteasecloudmusic.modules.login.LoginActivity;
import com.freak.neteasecloudmusic.modules.mine.MineFragment;
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

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @author freak
 * @date 2019/02/19
 */
@SuppressWarnings("ALL")
public class MainActivity extends BaseAbstractMvpActivity<MainPresenter> implements MainContract.View, View.OnClickListener {
    @BindView(R.id.img_menu)
    ImageView imgMenu;
    @BindView(R.id.img_search)
    ImageView imgSearch;
    @BindView(R.id.toolbar)
    ConstraintLayout toolbar;
    @BindView(R.id.main_viewpager)
    CustomViewPager mainViewpager;
    @BindView(R.id.bottom_container)
    FrameLayout bottomContainer;
    @BindView(R.id.recycle_view_menu)
    RecyclerView recycleViewMenu;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.slidingTabLayoutMain)
    SlidingTabLayout slidingTabLayoutMain;
    private List<String> titleList;
    private long time = 0;
    private List<MenuEntity> mMenuEntityList;
    private MenuItemAdapter mMenuItemAdapter;
    private View mHeadVew;
    private TextView textViewMenuLogin;
    private ImageView imgBgLogin;
    private TextView textViewUsername;
    private String mBackgroundUrl;
    private ImageView imgBg;
    private ConfigInfo mConfigInfo;
    private MainTabAdapter mainTabAdapter;
    private List<Fragment> fragmentList;
    private MineFragment mineFragment;
    private MusicFragment musicFragment;
    private RecommendFragment recommendFragment;
    private VideoFragment videoFragment;

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
    protected void onCreateLoadData() {

    }

    @Override
    protected void onDestroyRelease() {
        releaseData();
        destroyService();
        destroyReceiver();
    }

    @Override
    protected void onResumeLoadData() {

    }


    @Override
    protected void initView() {

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
        titleList = new ArrayList<>();
        fragmentList = new ArrayList<>();
        titleList.add("我的");
        titleList.add("发现");
        titleList.add("云村");
        titleList.add("视频");
        mineFragment = new MineFragment();
        recommendFragment = new RecommendFragment();
        musicFragment = new MusicFragment();
        videoFragment = new VideoFragment();

        fragmentList.add(mineFragment);
        fragmentList.add(recommendFragment);
        fragmentList.add(musicFragment);
        fragmentList.add(videoFragment);
        mainTabAdapter = new MainTabAdapter(getSupportFragmentManager(), fragmentList, titleList);
        mainViewpager.setAdapter(mainTabAdapter);
        mainViewpager.setScanScroll(true);
        slidingTabLayoutMain.setViewPager(mainViewpager, titleList.toArray(new String[0]), mActivity, (ArrayList<Fragment>) fragmentList);
        mainViewpager.setCurrentItem(0);

    }

    private void setUpDrawer() {
        mMenuEntityList = new ArrayList<>();
        mMenuEntityList = getItemList(this);
        recycleViewMenu.setLayoutManager(new LinearLayoutManager(this));
        mMenuItemAdapter = new MenuItemAdapter(R.layout.item_menu, mMenuEntityList);
        mPresenter.loadLoginStatusEntity();
        mMenuItemAdapter.bindToRecyclerView(recycleViewMenu);
        recycleViewMenu.setAdapter(mMenuItemAdapter);
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
                imgBgLogin = mHeadVew.findViewById(R.id.img_bg_login);
                imgBg = mHeadVew.findViewById(R.id.img_bg);
                textViewUsername = mHeadVew.findViewById(R.id.text_view_username);
                Glide.with(this).load(mBackgroundUrl).thumbnail(0.1f).into(imgBgLogin);
                Glide.with(this).load(loginStatusEntity.getProfile().getAvatarUrl()).thumbnail(0.1f).into(imgBg);
                textViewUsername.setText(loginStatusEntity.getProfile().getNickname());
                mMenuItemAdapter.addHeaderView(mHeadVew);
                break;
            case 2:
                mHeadVew = LayoutInflater.from(this).inflate(R.layout.item_menu_head, null);
                textViewMenuLogin = mHeadVew.findViewById(R.id.text_view_menu_login);
                textViewMenuLogin.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //登陆
            case R.id.text_view_menu_login:
                gotoActivity(LoginActivity.class, true);
                break;
            default:
                break;
        }
    }

    @Override
    public void showToast(String toast) {
        ToastUtil.shortShow(toast);
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

    @OnClick({R.id.img_menu, R.id.img_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_menu:
                drawerLayout.openDrawer(Gravity.LEFT);
                break;
            case R.id.img_search:
                break;
        }
    }


}
