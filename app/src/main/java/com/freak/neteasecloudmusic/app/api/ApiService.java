package com.freak.neteasecloudmusic.app.api;

import com.freak.neteasecloudmusic.modules.homepage.base.entity.LoginBean;
import com.freak.neteasecloudmusic.net.resonse.HttpResult;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 *
 * @author freak
 * @date 2019/2/19
 */
public interface ApiService {
    /**
     * 用户登陆
     *
     * @return
     */
    @POST("/login")
    Observable<HttpResult<LoginBean>> login(@Query("userName") String userName,
                                            @Query("pwd") String pwd
    );
}
