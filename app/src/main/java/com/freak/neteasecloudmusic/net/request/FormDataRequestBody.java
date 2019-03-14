package com.freak.neteasecloudmusic.net.request;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * form_data 方式请求数据转换
 * aip接口需要使用 @Multipart注解 参数使用@PartMap或者@Part注解  使用@Part注解需要使用RequestBody格式的参数
 *
 * @author Administrator
 * @date 2019/3/13
 */

public class FormDataRequestBody {
    public static Map<String, RequestBody> generateRequestBody(Map<String, String> requestDataMap) {
        Map<String, RequestBody> requestBodyMap = new HashMap<>();
        for (String key : requestDataMap.keySet()) {
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),
                    requestDataMap.get(key) == null ? "" : requestDataMap.get(key));
            requestBodyMap.put(key, requestBody);
        }
        return requestBodyMap;
    }
}
