package com.freak.neteasecloudmusic.app.api;

import com.freak.neteasecloudmusic.modules.base.entity.LoginStatusEntity;
import com.freak.neteasecloudmusic.modules.disco.recommend.entity.BannerEntity;
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
     * 歌单分类列表
     *
     * @param order
     * @param cat
     * @param limit
     * @return
     */
    @POST("top/playlist")
    Observable loadSongListCategoryList(@Query("order") String order,
                                        @Query("cat") String cat,
                                        @Query("limit") int limit);

    /**
     * 歌单分类
     *
     * @return
     */
    @POST("playlist/catlist")
    Observable loadSongListCategory();

    /**
     * 热门歌单分类
     *
     * @return
     */
    @POST("playlist/hot")
    Observable loadSongListCategoryHot();

    /**
     * 获取精品歌单
     *
     * @param cat    比如 " 华语 "、" 古风 " 、" 欧美 "、" 流行 ", 默认为 "全部",可从歌单分类接口获取
     * @param limit  取出歌单数量 , 默认为 20
     * @param before 分页参数,取上一页最后一个歌单的 updateTime 获取下一页数据
     * @return
     */
    @POST("top/playlist/highquality")
    Observable loadQualitySongList(@Query("cat") String cat,
                                   @Query("limit") String limit,
                                   @Query("before") int before);

    /**
     * 歌单 ( 网友精选碟 )
     * @param cat 比如 " 华语 "、" 古风 " 、" 欧美 "、" 流行 ", 默认为 "全部",可从歌单分类接口获取
     * @param limit 取出歌单数量 , 默认为 20
     * @param before 分页参数,取上一页最后一个歌单的 updateTime 获取下一页数据
     * @return
     */
    @POST("top/playlist/highquality")
    Observable loadQualitySongListWy(@Query("cat") String cat,
                                   @Query("limit") String limit,
                                   @Query("before") int before);
    /**
     * 获取歌单详情
     *
     * @param id 歌单 id 必选参数
     * @param s  歌单最近的 s 个收藏者 可选参数
     * @return
     */
    @POST("playlist/detail")
    Observable loadSongListCategoryDetail(@Query("id") String id,
                                          @Query("s") String s);
}
