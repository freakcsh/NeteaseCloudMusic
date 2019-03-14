package com.freak.neteasecloudmusic.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by hboxs001 on 2016/9/19.
 * MD5 密码加密
 */
public class Md5 {

    public static String getMD5Str(String str) {
        MessageDigest messageDigest = null;

        try {
            messageDigest = MessageDigest.getInstance("MD5");

            /**
             * 重新设置摘要以供进一步使用
             */
            messageDigest.reset();
            /**
             * 使用指定的字节组更新摘要
             */
            messageDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("NoSuchAlgorithmException caught!");
            System.exit(-1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        /**
         *   将二进制的byte数组进行加密
         */

        byte[] byteArray = messageDigest.digest();

        StringBuffer md5StrBuff = new StringBuffer();

        for (int i = 0; i < byteArray.length; i++) {
            /**
             * 0xFF & byteArray[i]  进行加密 & int值的 255   000...00010000001
             *
             * toHexString   将int值转换为16进制
             *
             * String hexString = Integer.toHexString(result) + 1 ;//这里加1可以进行2次加密
             */
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            } else {
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
            }
        }
        //16位加密，从第9位到25位
//        return md5StrBuff.substring(8, 24).toString().toUpperCase();
        return md5StrBuff.substring(0, 32).toString().toLowerCase();
    }
}
