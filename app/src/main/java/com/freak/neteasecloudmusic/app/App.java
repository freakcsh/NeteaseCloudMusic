package com.freak.neteasecloudmusic.app;

import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.multidex.MultiDexApplication;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.freak.httphelper.HttpMethods;
import com.freak.httphelper.log.LogLevel;
import com.freak.neteasecloudmusic.R;
import com.freak.neteasecloudmusic.base.IActivityStatusBar;
import com.freak.neteasecloudmusic.commom.constants.Constants;
import com.freak.neteasecloudmusic.net.cookie.CookieJarImpl;
import com.freak.neteasecloudmusic.receiver.AudioBroadcastReceiver;
import com.freak.neteasecloudmusic.receiver.NetworkConnectChangedReceiver;
import com.freak.neteasecloudmusic.service.AudioPlayerService;
import com.freak.neteasecloudmusic.utils.imagepick.loader.ImagePickerGlideLoader;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.view.CropImageView;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;


/**
 * 配置全局变量
 *
 * @author freak
 * @date 2019/2/19
 */

public class App extends MultiDexApplication {
    private static final int DESIGN_WIDTH = 375;
    private static App instance;
    private Set<Activity> allActivities;
//    public static BaseAbstractMvpActivity baseActivity;


    public Set<Activity> getAllActivities() {
        return allActivities;
    }

    private NetworkConnectChangedReceiver mNetworkConnectChangedReceiver;
    private static AudioBroadcastReceiver mAudioBroadcastReceiver;

    public void setAllActivities(Set<Activity> allActivities) {
        this.allActivities = allActivities;
    }

    public static App getInstance() {
        return instance;
    }

    public static void setInstance(App instance) {
        App.instance = instance;
    }

    public static AudioBroadcastReceiver getAudioBroadcastReceiverInstance() {
        if (mAudioBroadcastReceiver == null) {
            mAudioBroadcastReceiver = new AudioBroadcastReceiver();
        }
        return mAudioBroadcastReceiver;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        HttpMethods
                .getInstanceBuilder()
                .setBaseUrl(Constants.BASE_URL)//设置域名
                .setLogLevel(LogLevel.ERROR)//设置日志打印级别，使用默认的日志打印才需要设置这个
                .setLogName("NeteaseCloudMusic")//设置默认日志打印名字
                .setIsOpenLog(true)//设置是否开启框架默认的日志打印
                .setCookieJar(new CookieJarImpl())//设置自定义的cookiejar
//                .setLogger(new HttpLogger())//设置自定义logger，此设置是打印网络请求的数据（如果设置了自定义的，则框架默认的则不需要设置）
//                .setLevel(LoggerLevel.BODY)//设置日志打印级别（自定义logger可设置，框架默认的是BODY级别，如果上架需要关闭日志打印，则设置setIsOpenLog(false)即可）
                .setReadTimeOut(60)
                .setConnectTimeOut(60)
                .setWriteTimeOut(60);
//                .setInterceptor(new CommonParametersInterceptor())//设置拦截器
//                .setNetworkInterceptor(new CommonParametersInterceptor())//设置拦截器
//                .setFactory(CustomConverterFactory.create())//设置自定义解析器
//                .setInterceptors(new CommonParametersInterceptor(), new CommonParametersInterceptorHead());//设置多个拦截器
        initImagePicker();
        initReceiver();
        initService();
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {

            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                //限制竖屏
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                resetDensity(getApplicationContext(), DESIGN_WIDTH);
                resetDensity(activity, DESIGN_WIDTH);
                setImmersiveStatusBar(activity);

            }

            @Override
            public void onActivityStarted(Activity activity) {
                setToolBar(activity);
                resetDensity(getApplicationContext(), DESIGN_WIDTH);
                resetDensity(activity, DESIGN_WIDTH);
            }

            @Override
            public void onActivityResumed(Activity activity) {
                resetDensity(getApplicationContext(), DESIGN_WIDTH);
                resetDensity(activity, DESIGN_WIDTH);
            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }

    private void initService() {
        AudioPlayerService.startService(this);
    }

    /**
     * 注册广播
     */
    private void initReceiver() {
        //网络
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        filter.addAction("android.net.ic_network_off.WIFI_STATE_CHANGED");
        filter.addAction("android.net.ic_network_off.STATE_CHANGE");
        filter.setPriority(1000);
        if (mNetworkConnectChangedReceiver == null) {
            mNetworkConnectChangedReceiver = new NetworkConnectChangedReceiver();
        }
        registerReceiver(mNetworkConnectChangedReceiver, filter);

        getAudioBroadcastReceiverInstance().registerReceiver(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        if (mNetworkConnectChangedReceiver != null) {
            unregisterReceiver(mNetworkConnectChangedReceiver);
        }
        if (mAudioBroadcastReceiver != null) {
            mAudioBroadcastReceiver.unregisterReceiver(this);
        }
        AudioPlayerService.stopService(this);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        if (mNetworkConnectChangedReceiver != null) {
            unregisterReceiver(mNetworkConnectChangedReceiver);
        }
        if (mAudioBroadcastReceiver != null) {
            mAudioBroadcastReceiver.unregisterReceiver(this);
        }
        AudioPlayerService.stopService(this);
    }

    private void initImagePicker() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        //设置图片加载器
        imagePicker.setImageLoader(new ImagePickerGlideLoader());
        //显示拍照按钮
        imagePicker.setShowCamera(true);
        //允许裁剪（单选才有效）
        imagePicker.setCrop(true);
        //是否按矩形区域保存
        imagePicker.setSaveRectangle(true);
        //选中数量限制
        imagePicker.setSelectLimit(9);
        //裁剪框的形状
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);
        //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusWidth(800);
        //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);
        //保存文件的宽度。单位像素
        imagePicker.setOutPutX(1000);
        //保存文件的高度。单位像素
        imagePicker.setOutPutY(1000);
    }

    /**
     * 设置ToolBar
     *
     * @param activity
     */
    private void setToolBar(final Activity activity) {
        if (activity.findViewById(R.id.tool_bar) != null && ((AppCompatActivity) activity).getSupportActionBar() == null) {
            Toolbar toolbar = activity.findViewById(R.id.tool_bar);
            if (!TextUtils.isEmpty(activity.getTitle())) {
                toolbar.setTitle(activity.getTitle());
            } else {
                toolbar.setTitle("");
            }

            if (((IActivityStatusBar) activity).getStatusBarColor() != 0) {
                toolbar.setBackgroundColor(((IActivityStatusBar) activity).getStatusBarColor());
            } else {
                toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
            }

            ((AppCompatActivity) activity).setSupportActionBar(toolbar);
            ActionBar actionBar = ((AppCompatActivity) activity).getSupportActionBar();
            if (actionBar != null) {
                actionBar.setHomeButtonEnabled(true);
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.onBackPressed();
                }
            });
        }
    }

    /**
     * 设置状态栏
     *
     * @param activity
     */
    private void setImmersiveStatusBar(Activity activity) {
        if (activity instanceof IActivityStatusBar) {
            if (((IActivityStatusBar) activity).getStatusBarColor() != 0) {
                setTranslucentStatus(activity);
                addImmersiveStatusBar(activity, ((IActivityStatusBar) activity).getStatusBarColor());
            }
        }
    }

    /**
     * 添加自定义状态栏
     *
     * @param activity
     */
    private void addImmersiveStatusBar(Activity activity, int color) {
        ViewGroup contentFrameLayout = activity.findViewById(android.R.id.content);
        View contentView = contentFrameLayout.getChildAt(0);
        if (contentView != null && Build.VERSION.SDK_INT >= 14) {
            contentView.setFitsSystemWindows(true);
        }

        View statusBar = new View(activity);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        if (hasNotchInScreen(activity)){
//            params.height = getNotchSize(activity)[1];
//        }else {
//            params.height = getStatusBarHeight();
//        }
        params.height = getStatusBarHeight();
        statusBar.setLayoutParams(params);
        statusBar.setBackgroundColor(color);
        contentFrameLayout.addView(statusBar);
    }

    /**
     * 获取状态栏高度
     *
     * @return
     */
    private int getStatusBarHeight() {
        int statusBarHeight = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            statusBarHeight = getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }

    /**
     * 设置状态栏为透明
     *
     * @param activity
     */
    private void setTranslucentStatus(Activity activity) {
        //******** 5.0以上系统状态栏透明 ********

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    /**
     * 以pt为单位重新计算大小
     */
    public static void resetDensity(Context context, float designWidth) {
        if (context == null) {
            return;
        }
        Point size = new Point();
        ((WindowManager) context.getSystemService(WINDOW_SERVICE)).getDefaultDisplay().getSize(size);
        Resources resources = context.getResources();
        resources.getDisplayMetrics().xdpi = size.x / designWidth * 72f;
        DisplayMetrics metrics = getMetricsOnMIUI(context.getResources());
        if (metrics != null) {
            metrics.xdpi = size.x / designWidth * 72f;
        }
    }

    /**
     * 解决MIUI屏幕适配问题
     *
     * @param resources
     * @return
     */
    private static DisplayMetrics getMetricsOnMIUI(Resources resources) {
        if ("MiuiResources".equals(resources.getClass().getSimpleName()) || "XResources".equals(resources.getClass().getSimpleName())) {
            try {
                Field field = Resources.class.getDeclaredField("mTmpMetrics");
                field.setAccessible(true);
                return (DisplayMetrics) field.get(resources);
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    public void addActivity(Activity act) {
        if (allActivities == null) {
            allActivities = new HashSet<>();
        }
        allActivities.add(act);
    }

    public void removeActivity(Activity act) {
        if (allActivities != null) {
            allActivities.remove(act);
        }
    }
}
