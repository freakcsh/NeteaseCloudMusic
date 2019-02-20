//
//  Created by  fred on 2016/10/26.
//  Copyright © 2016年 Alibaba. All rights reserved.
//

package com.freak.commonappframework.identity;

import android.util.Log;

import com.alibaba.cloudapi.sdk.constant.SdkConstant;
import com.alibaba.cloudapi.sdk.model.ApiCallback;
import com.alibaba.cloudapi.sdk.model.ApiRequest;
import com.alibaba.cloudapi.sdk.model.ApiResponse;
import com.alibaba.cloudapi.sdk.model.HttpClientBuilderParams;
import com.freak.commonappframework.utils.LogUtils;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * @author freak
 * @date 2019/2/19
 */
public class Demo {
    private static final String AppKey = "24683350";
    private static final String AppSecret = "fc21a25461d82dbb90174800cd212b3f";
    private static DealInterface dealInterface;


    static {
        //HTTP Client init
        HttpClientBuilderParams httpParam = new HttpClientBuilderParams();
        httpParam.setAppKey(AppKey);
        httpParam.setAppSecret(AppSecret);
        HttpApiClient.getInstance().init(httpParam);


        //HTTPS Client init
        HttpClientBuilderParams httpsParam = new HttpClientBuilderParams();
        httpsParam.setAppKey(AppKey);
        httpsParam.setAppSecret(AppSecret);

        /**
         * 以HTTPS方式提交请求
         * 本DEMO采取忽略证书的模式,目的是方便大家的调试
         * 为了安全起见,建议采取证书校验方式
         */
        X509TrustManager xtm = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                X509Certificate[] x509Certificates = new X509Certificate[0];
                return x509Certificates;
            }
        };

        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, new TrustManager[]{xtm}, new SecureRandom());

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (KeyManagementException e) {
            throw new RuntimeException(e);
        }
        HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };

        httpsParam.setSslSocketFactory(sslContext.getSocketFactory());
        httpsParam.setX509TrustManager(xtm);
        httpsParam.setHostnameVerifier(DO_NOT_VERIFY);
        HttpsApiClient.getInstance().init(httpsParam);
    }

    public static void identificationHttpTest(String body, DealInterface dealInterface) {
        Demo.dealInterface = dealInterface;
        HttpApiClient.getInstance().identification(body.getBytes(SdkConstant.CLOUDAPI_ENCODING), new ApiCallback() {
            @Override
            public void onFailure(ApiRequest request, Exception e) {
                LogUtils.e("身份直接失败 ");
                Demo.dealInterface.failure(e.toString());
                e.printStackTrace();
            }

            @Override
            public void onResponse(ApiRequest request, ApiResponse response) {
                try {
                    LogUtils.e("身份回到 ");
                    System.out.println(getResultString(response));
                    LogUtils.e("身份20-》" + getResultString(response));
                } catch (Exception ex) {
                    LogUtils.e("身份回到异常");
                    Demo.dealInterface.failure(ex.toString());
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void identificationHttpsTest(String body) {
        HttpsApiClient.getInstance().identification(body.getBytes(SdkConstant.CLOUDAPI_ENCODING), new ApiCallback() {
            @Override
            public void onFailure(ApiRequest request, Exception e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(ApiRequest request, ApiResponse response) {
                try {
                    Log.d(Demo.class.getSimpleName(), "onResponse: " + response.getMessage());
                    System.out.println(getResultString(response));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }


    private static String getResultString(ApiResponse response) throws IOException {
        StringBuilder result = new StringBuilder();
        result.append("【服务器返回结果为】").append(SdkConstant.CLOUDAPI_LF).append(SdkConstant.CLOUDAPI_LF);
        result.append("ResultCode:").append(SdkConstant.CLOUDAPI_LF).append(response.getCode()).append(SdkConstant.CLOUDAPI_LF).append(SdkConstant.CLOUDAPI_LF);
        if (response.getCode() != 200) {
            Demo.dealInterface.failure("");
            result.append("错误原因：").append(response.getHeaders().get("X-Ca-Error-Message")).append(SdkConstant.CLOUDAPI_LF).append(SdkConstant.CLOUDAPI_LF);
        } else {
            Demo.dealInterface.success(new String(response.getBody(), SdkConstant.CLOUDAPI_ENCODING));
        }

        result.append("ResultBody:").append(SdkConstant.CLOUDAPI_LF).append(new String(response.getBody(), SdkConstant.CLOUDAPI_ENCODING));

        return result.toString();
    }
}
