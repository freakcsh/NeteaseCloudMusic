package com.freak.neteasecloudmusic.app.api;

import com.freak.neteasecloudmusic.modules.base.entity.LoginStatusEntity;
import com.freak.neteasecloudmusic.modules.find.recommend.entity.BannerEntity;
import com.freak.neteasecloudmusic.modules.find.recommend.entity.HotSongListEntity;
import com.freak.neteasecloudmusic.modules.find.recommend.entity.HotSongListCategoryEntity;
import com.freak.neteasecloudmusic.modules.find.recommend.entity.SongListCategoryEntity;
import com.freak.neteasecloudmusic.modules.find.recommend.entity.SongListDetailEntity;
import com.freak.neteasecloudmusic.modules.find.recommend.entity.SongListEntity;
import com.freak.neteasecloudmusic.modules.find.recommend.entity.SongUrlEntity;
import com.freak.neteasecloudmusic.modules.login.entty.LoginEntity;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author freak
 * @date 2019/2/19
 */
public interface ApiService {
    /**
     * 用户登陆
     *
     * @return
     */
    @POST("login/cellphone")
    Observable<LoginEntity> login(@Query("phone") String phone,
                                  @Query("password") String password
    );

    /**
     * 检查登录状态
     *
     * @return
     */
    @POST("login/status")
    Observable<LoginStatusEntity> loadLoginStatus();

    /**
     * 加载轮播图
     *
     * @return
     */
    @POST("banner")
    Observable<BannerEntity> loadBanner();

    /**
     * 歌单分类类别列表
     *
     * @param order  可选值为 'new' 和 'hot', 分别对应最新和最热 , 默认为 'hot'
     * @param cat    比如 " 华语 "、" 古风 " 、" 欧美 "、" 流行 ", 默认为 "全部",可从歌单分类接口获取(/playlist/catlist)
     * @param limit  取出歌单数量 , 默认为 20
     * @param offset 分页起始位置 从0开始
     * @return
     */
    @POST("top/playlist")
    Observable<SongListEntity> loadSongListCategoryList(@Query("order") String order,
                                                        @Query("cat") String cat,
                                                        @Query("limit") int limit,
                                                        @Query("offset") int offset);

    /**
     * 歌单分类类别
     *
     * @return
     */
    @POST("playlist/catlist")
    Observable<SongListCategoryEntity> loadSongListCategory();

    /**
     * 热门歌单分类类别
     *
     * @return
     */
    @POST("playlist/hot")
    Observable<HotSongListCategoryEntity> loadSongListCategoryHot();

    /**
     * 获取精品歌单类别列表
     *
     * @param cat    比如 " 华语 "、" 古风 " 、" 欧美 "、" 流行 ", 默认为 "全部",可从歌单分类接口获取
     * @param limit  取出歌单数量 , 默认为 20
     * @param before 分页参数,取上一页最后一个歌单的 updateTime 获取下一页数据
     * @return
     */
    @POST("top/playlist/highquality")
    Observable<HotSongListEntity> loadQualitySongList(@Query("cat") String cat,
                                                      @Query("limit") int limit,
                                                      @Query("before") long before);

    /**
     * 歌单 ( 网友精选碟 )
     *
     * @param cat    比如 " 华语 "、" 古风 " 、" 欧美 "、" 流行 ", 默认为 "全部",可从歌单分类接口获取
     * @param limit  取出歌单数量 , 默认为 20
     * @param before 分页参数,取上一页最后一个歌单的 updateTime 获取下一页数据
     * @return
     */
    @POST("top/playlist/highquality")
    Observable loadQualitySongListWy(@Query("cat") String cat,
                                     @Query("limit") int limit,
                                     @Query("before") String before);

    /**
     * 获取歌单详情
     *
     * @param id 歌单 id 必选参数
     * @param s  歌单最近的 s 个收藏者 可选参数
     * @return
     */
    @POST("playlist/detail")
    Observable<SongListDetailEntity> loadSongListCategoryDetail(@Query("id") String id,
                                                                @Query("s") String s);

    /**
     * 获取音乐播放地址
     *
     * @param id
     * @return
     */
    @POST("song/url")
    Observable<SongUrlEntity> loadSongUrl(@Query("id") String id);

    /***
     * 检查音乐是否可以使用
     * 调用此接口,传入歌曲 id, 可获取音乐是否可用,返回 { success: true, message: 'ok' } 或者 { success: false, message: '亲爱的,暂无版权' }
     * @param id 歌曲 id
     * @param br 码率,默认设置了 999000 即最大码率,如果要 320k 则可设置为 320000,其他类推
     * @return
     */
    @POST("check/music")
    Observable checkSongUrl(@Query("id") String id,
                            @Query("br") String br);
}
