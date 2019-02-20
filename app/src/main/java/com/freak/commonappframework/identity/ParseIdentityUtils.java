package com.freak.commonappframework.identity;

import android.graphics.Bitmap;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

/**
 * @author freak
 * @date 2019/2/20
 */

public class ParseIdentityUtils {
    private static ParseIdentityUtils parseIdentityUtils;

    public static ParseIdentityUtils getInstance() {
        if (parseIdentityUtils == null) {
            synchronized (ParseIdentityUtils.class) {
                parseIdentityUtils = new ParseIdentityUtils();
            }
        }
        return parseIdentityUtils;
    }

    /**
     * 解析身份证，获取文字信息
     */
    public void parsingIdCard(Bitmap bitmap, DealInterface<String> stringDealInterface) {
        Bitmap positionBitmap = bitmap;
        String mess = String.format("{\"inputs\":[{\"image\":{\"dataType\": 50,\"dataValue\":\"%s\"},\"configure\": {\"dataType\": 50,\"dataValue\": \"{\\\"side\\\":\\\"face\\\"}\"}}]}",
                bitmapToString(positionBitmap));
        Demo.identificationHttpTest(mess, stringDealInterface);
    }


    private String bitmapToString(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        byte[] encode = Base64.encode(bytes, Base64.DEFAULT);
        return new String(encode);
    }
}
