package com.freak.neteasecloudmusic.net.resonse;

import android.support.annotation.NonNull;

import com.freak.neteasecloudmusic.commom.constants.Constants;


/**
 * 放回结果过滤
 *
 * @author Administrator
 * @date 2019/3/7
 */

public class ResponseFiltrate {
    public static void filtrateSuccessOrFailure(HttpResult httpResult, @NonNull Filtrate filtrate) {
        if (Constants.SUCCESS_CODE.equals(httpResult.getCode())) {
            filtrate.onSuccess(httpResult);
        } else {
            filtrate.onFailure(httpResult.getMsg());
        }
    }

    public interface Filtrate {
        /**
         * 过滤成功
         *
         * @param data
         */
        void onSuccess(HttpResult data);

        /**
         * 过滤失败
         *
         * @param msg
         */
        void onFailure(String msg);

        void onOther(HttpResult httpResult);
    }
}
