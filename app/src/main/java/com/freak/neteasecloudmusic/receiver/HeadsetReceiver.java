package com.freak.neteasecloudmusic.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;



/**
 * 耳机插入拔出监听广播接收类
 */
public class HeadsetReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // 耳机插入状态 0 拔出，1 插入
        boolean state = intent.getIntExtra("state", 0) != 0;
        // 耳机类型
        String name = intent.getStringExtra("name");
        // 耳机是否带有麦克风 0 没有，1 有
        boolean mic = intent.getIntExtra("microphone", 0) != 0;
        String headsetChange = String.format("耳机插入: %b, 有麦克风: %b", state, mic);
//        Toast.makeText(context, headsetChange, Toast.LENGTH_SHORT).show();
    }
}
