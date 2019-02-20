package com.freak.commonappframework.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.freak.commonappframework.R;
import com.freak.commonappframework.app.App;
import com.freak.commonappframework.commom.constants.Constants;
import com.freak.commonappframework.event.UserEvent;
import com.freak.commonappframework.net.status.NetStateChangeObserver;
import com.freak.commonappframework.net.status.NetStateChangeReceiver;
import com.freak.commonappframework.net.status.NetworkType;
import com.freak.commonappframework.utils.SharedPreferencesUtils;
import com.freak.commonappframework.utils.ToastUtil;
import com.freak.httphelper.BasePresenter;
import com.freak.httphelper.RxBaseView;
import com.freak.httphelper.RxBus;


/**
 * @author freak
 * @date 2019/2/19
 * MVP activity基类
 */

public abstract class BaseAbstractMvpActivity<T extends BasePresenter> extends AppCompatActivity implements RxBaseView,NetStateChangeObserver {
    protected T mPresenter;
    protected Activity mActivity;
    private View netErrorView;

    /**
     * 绑定布局
     *
     * @return
     */
    protected abstract int getLayout();

    /**
     * 初始化事件和数据
     */
    protected abstract void initEventAndData();

    /**
     * 控件初始化
     */
    protected abstract void initView();

    /**
     * 创建Presenter
     *
     * @return
     */
    protected abstract T createPresenter();

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        /**
         * 创建presenter对象
         */
        mPresenter = createPresenter();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mActivity = this;
        //活动控制器
        App.getInstance().addActivity(this);
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        initView();
        initEventAndData();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        /**
         * presenter 解除view订阅
         */
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        App.getInstance().removeActivity(this);
        if (needRegisterNetworkChangeObserver()) {
            NetStateChangeReceiver.unregisterObserver(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (needRegisterNetworkChangeObserver()) {
            NetStateChangeReceiver.registerObserver(this);
        }
    }

    /**
     * 是否需要注册网络变化的Observer,如果不需要监听网络变化,则返回false;否则返回true
     */
    protected boolean needRegisterNetworkChangeObserver() {
        return true;
    }

    /**
     * 网络断开时执行的操作
     */
    @Override
    public void onNetDisconnected() {
        showDisConnectedView();
    }

    /**
     * 网络重连时执行的操作
     *
     * @param networkType
     */
    @Override
    public void onNetConnected(NetworkType networkType) {
        ToastUtil.showLong(mActivity, "当前连接的是" + networkType.toString() + "网络");
        hideDisConnectedView();
    }

    /**
     * 显示无网络状态
     */
    public void showDisConnectedView() {
        ToastUtil.shortShow("网络连接错误，请检测您的网络设置");
//        netErrorView = findViewById(R.id.rl_net_error);
//        netErrorView.setVisibility(View.VISIBLE);
    }

    /**
     * 隐藏无网络状态
     */
    public void hideDisConnectedView() {
//        netErrorView = findViewById(R.id.rl_net_error);
//        netErrorView.setVisibility(View.GONE);
    }


    /**
     * 打开一个Activity 默认 不关闭当前activity
     *
     * @param className
     */
    public void gotoActivity(Class<?> className) {
        gotoActivity(className, false, null);
    }

    /**
     * 打开一个Activity，并控制是否finish
     *
     * @param className              className
     * @param isCloseCurrentActivity 是否关闭
     */
    public void gotoActivity(Class<?> className, boolean isCloseCurrentActivity) {
        gotoActivity(className, isCloseCurrentActivity, null);
    }

    /**
     * 打开一个activity，并传递数据
     *
     * @param className              className
     * @param isCloseCurrentActivity 是否关闭
     * @param bundle                 bundle数据
     */
    public void gotoActivity(Class<?> className, boolean isCloseCurrentActivity, Bundle bundle) {
        Intent intent = new Intent(this, className);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        if (isCloseCurrentActivity) {
            finish();
        }
    }
    /**
     * 自定义返回监听
     *
     */
    public void setBackPress() {
        try {
            View backView = findViewById(R.id.tool_bar);
            backView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        } catch (Exception e) {

        }
    }
    /**
     * 系统返回键监听
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();//返回
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    /**
     * 未登录状态提示
     *
     * @param text 未登陆提示语
     */
    public void toastShow(String text) {
        if (text.equals(Constants.UO_LOGIN)) {
            SharedPreferencesUtils.put(getApplicationContext(), Constants.TOKEN_ABLE, false);
            RxBus.getDefault().post(new UserEvent(1, Constants.RENOVATE));
        }
        ToastUtil.shortShow(text);
    }
}
