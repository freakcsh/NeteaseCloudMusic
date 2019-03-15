package com.freak.neteasecloudmusic.splash;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.freak.neteasecloudmusic.R;
import com.freak.neteasecloudmusic.base.IActivityStatusBar;
import com.freak.neteasecloudmusic.modules.base.MainActivity;

import java.lang.ref.WeakReference;

/**
 * 启动页
 *
 * @author Administrator
 */
public class SplashActivity extends AppCompatActivity implements IActivityStatusBar {
    private LinearLayout linear_layout_splash;
    private ObjectAnimator animator1;
    private ObjectAnimator animator2;
    private ObjectAnimator animator3;
    private ObjectAnimator animator4;
    private AnimatorSet animSet;
    private int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_splash);
        super.onCreate(savedInstanceState);
        linear_layout_splash = findViewById(R.id.linear_layout_splash);
        height = linear_layout_splash.getHeight();
        initAnimator();
        animatorStart();
        new InnerThread(this).start();
    }

    private void animatorStart() {
        animSet = new AnimatorSet();
        animSet.play(animator4).with(animator3).with(animator2).after(animator1);
        animSet.setDuration(5000L);
        animSet.start();
    }

    /**
     * 初始化动画
     */
    public void initAnimator() {
        animator1 = ObjectAnimator.ofFloat(linear_layout_splash, "translationY", height / 8, -100, height / 2);
        animator2 = ObjectAnimator.ofFloat(linear_layout_splash, "translationY", height / 8, -100, height / 2);
        animator3 = ObjectAnimator.ofFloat(linear_layout_splash, "translationY", height / 8, -100, height / 2);
        animator4 = ObjectAnimator.ofFloat(linear_layout_splash, "translationY", height / 8, -100, height / 2);
    }

    @Override
    public int getStatusBarColor() {
        return 0;
    }

    private static class InnerThread extends Thread {

        WeakReference<SplashActivity> mReference;

        InnerThread(SplashActivity activity) {
            mReference = new WeakReference<>(activity);
        }

        @Override
        public void run() {
            SplashActivity activity = mReference.get();
            if (activity != null) {
                activity.sendMsg();
            }
        }
    }

    public void sendMsg() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Looper.prepare();
        new InnerHandler(SplashActivity.this).obtainMessage().sendToTarget();
        Looper.loop();
    }

    public void startLogin() {
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }

    private static class InnerHandler extends Handler {

        WeakReference<SplashActivity> mReference;

        InnerHandler(SplashActivity activity) {
            mReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            SplashActivity activity = mReference.get();
            if (activity != null) {
                activity.startLogin();
            }
        }
    }

}
