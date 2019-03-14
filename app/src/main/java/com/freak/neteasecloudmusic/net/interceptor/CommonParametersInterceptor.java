package com.freak.neteasecloudmusic.net.interceptor;


import com.freak.neteasecloudmusic.app.App;
import com.freak.neteasecloudmusic.commom.constants.Constants;
import com.freak.neteasecloudmusic.utils.SPUtils;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 添加公共参数
 *
 * @author Administrator
 * @date 2019/3/11
 */

public class CommonParametersInterceptor implements Interceptor {
    private String TAG = "CommonParametersInterceptor";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request oldRequest = chain.request();
        Response response = null;
        // 新的请求,添加参数
        Request newRequest = addParam(oldRequest);
        response = chain.proceed(newRequest);
        return response;
    }

    /**
     * 添加公共参数
     *
     * @param oldRequest
     * @return
     */
    private Request addParam(Request oldRequest) {

        HttpUrl.Builder builder = oldRequest.url()
                .newBuilder()
                .setEncodedQueryParameter("access_token", (String) SPUtils.get(App.getInstance().getApplicationContext(), Constants.ACCESS_TOKEN,""));
        Request newRequest = oldRequest.newBuilder()
                .method(oldRequest.method(), oldRequest.body())
                .url(builder.build())
                .build();
        return newRequest;
    }


}
