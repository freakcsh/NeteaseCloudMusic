package com.freak.neteasecloudmusic.net.resonse;


import com.freak.httphelper.ApiException;

import io.reactivex.functions.Function;

/**
 * 此方法是接口返回数据的解析
 *
 * @param <T>
 * @author freak
 * @date 2019/01/25
 */
public class HttpResultFunc<T> implements Function<HttpResult<T>, T> {


    @Override
    public T apply(HttpResult<T> tHttpResult) throws Exception {
        if (!"000000".equals(tHttpResult.getCode())) {
            throw new ApiException(tHttpResult.getMsg());
        }
        return tHttpResult.getData();
    }
}