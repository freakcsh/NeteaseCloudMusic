package com.freak.neteasecloudmusic.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.freak.neteasecloudmusic.R;
import com.freak.neteasecloudmusic.base.BaseAbstractMvpActivity;

import java.lang.ref.WeakReference;

public class PlaybackStatusReceiver extends BroadcastReceiver {
    private final WeakReference<BaseAbstractMvpActivity> mReference;

    public PlaybackStatusReceiver(BaseAbstractMvpActivity activity) {
        mReference = new WeakReference<>(activity);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        final String action = intent.getAction();
        BaseAbstractMvpActivity baseActivity = mReference.get();
        if (baseActivity != null) {
//            if (action.equals(MediaService.META_CHANGED)) {
//                baseActivity.updateTrackInfo();
//
//            } else if (action.equals(MediaService.PLAYSTATE_CHANGED)) {
//
//            } else if (action.equals(MediaService.TRACK_PREPARED)) {
//                baseActivity.updateTime();
//            } else if (action.equals(MediaService.BUFFER_UP)) {
//                baseActivity.updateBuffer(intent.getIntExtra("progress", 0));
//            } else if (action.equals(MediaService.MUSIC_LODING)) {
//                baseActivity.loading(intent.getBooleanExtra("isloading",false));
//            } else if (action.equals(MediaService.REFRESH)) {
//
//            } else if (action.equals(IConstants.MUSIC_COUNT_CHANGED)) {
//                baseActivity.refreshUI();
//            } else if (action.equals(IConstants.PLAYLIST_COUNT_CHANGED)) {
//                baseActivity.refreshUI();
//            } else if (action.equals(MediaService.QUEUE_CHANGED)) {
//                baseActivity.updateQueue();
//            } else if (action.equals(MediaService.TRACK_ERROR)) {
//                final String errorMsg = context.getString(R.string.exit,
//                        intent.getStringExtra(MediaService.TrackErrorExtra.TRACK_NAME));
//                Toast.makeText(baseActivity, errorMsg, Toast.LENGTH_SHORT).show();
//            } else if (action.equals(MediaService.MUSIC_CHANGED)) {
//                baseActivity.updateTrack();
//            } else if (action.equals(MediaService.LRC_UPDATED)) {
//                baseActivity.updateLrc();
//            }

        }
    }
}
