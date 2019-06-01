package com.freak.neteasecloudmusic.player.manager.util;

import android.text.TextUtils;

import com.freak.neteasecloudmusic.modules.disco.recommend.entity.SongListDetailEntity;
import com.freak.neteasecloudmusic.modules.disco.recommend.entity.SongUrlEntity;
import com.freak.neteasecloudmusic.player.manager.entity.AudioInfo;

/**
 * 音频信息装换
 *
 * @author Freak
 * @date 2019/6/1.
 */

public class AudioInfoTransitionUtil {
    private static AudioInfo audioInfo;

    public static AudioInfo audioInfoTransition(SongUrlEntity.DataBean songInfo, SongListDetailEntity.PlaylistBean.TracksBean songEntity) {
        if (audioInfo == null) {
            audioInfo = new AudioInfo();
        }
        audioInfo.setDownloadUrl(songInfo.getUrl());//设置播放网络音乐地址
        audioInfo.setType(AudioInfo.TYPE_NET);//设置播放类型
        audioInfo.setHash(songInfo.getMd5());//设置MD5
        audioInfo.setSingerId(String.valueOf(songInfo.getId()));//设置歌曲id
        audioInfo.setFileExt(songInfo.getEncodeType());//设置歌曲格式
        audioInfo.setFileSize(songInfo.getSize());
        audioInfo.setFileSizeText(songInfo.getSize() + "");
        audioInfo.setSingerName(TextUtils.isEmpty(songEntity.getName()) ? "" : songEntity.getName());
        audioInfo.setSongName(TextUtils.isEmpty(songEntity.getAr().get(0).getName()) ? "" : songEntity.getAr().get(0).getName());
        return audioInfo;
    }
}
