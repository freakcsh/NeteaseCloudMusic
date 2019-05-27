package com.freak.neteasecloudmusic.splash;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.freak.neteasecloudmusic.R;
import com.freak.neteasecloudmusic.base.IActivityStatusBar;
import com.freak.neteasecloudmusic.modules.base.MainActivity;

import java.lang.ref.WeakReference;

/**
 * 启动页
 *
 * @author Administrator
 */
public class SplashActivity extends AppCompatActivity implements IActivityStatusBar ,View.OnClickListener{
    private TextView text_view_time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_splash);
        super.onCreate(savedInstanceState);
//        new InnerThread(this).start();
        text_view_time = findViewById(R.id.text_view_time);
        text_view_time.setOnClickListener(this);
        timer.start();
    }
    CountDownTimer timer = new CountDownTimer(8000, 100) {
        @SuppressLint("SetTextI18n")
        @Override
        public void onTick(long sin) {
            text_view_time.setText("跳过 " + sin / 1000);
        }

        @Override
        public void onFinish() {
            startLogin();
        }
    };
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_view_time:
                timer.cancel();
                startLogin();
                break;
            default:
                break;
        }
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
