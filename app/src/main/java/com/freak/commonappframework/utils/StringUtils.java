package com.freak.commonappframework.utils;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author freak
 * @date 2019/2/19
 */

public class StringUtils {

    static DecimalFormat df = new DecimalFormat("#.##");
    static DecimalFormat df2 = new DecimalFormat("0.00");

    /**
     * 比较大小
     *
     * @param v1
     * @param v2
     * @return -1：v1<v2 0：v1=v2 1:v1>v2
     */
    public static int compareTo(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.compareTo(b2);

    }

    /**
     * 验证手机号码
     *
     * @param phone
     * @return
     */
    public static boolean verifyPhone(String phone) {
        if (phone != null && phone.length() == 11) {
            Pattern pattern = Pattern.compile("^1[3|4|5|6|7|8|9][0-9]\\d{8}$");
            Matcher matcher = pattern.matcher(phone);
            return matcher.matches();
        }
        return false;
    }

    /**
     * 读取本地JSON文件
     *
     * @param context
     * @param fileName
     * @return
     */
    public static String getJsonForLocation(Context context, String fileName) {
        StringBuffer stringBuffer = new StringBuffer();
        AssetManager assetManager = context.getAssets();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(assetManager.open(fileName), "UTF-8"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }

    /**
     * 银行卡中间转为*
     *
     * @param number
     * @return
     */
    public static String turnToBankCardStar(String number) {
        if (number.length() > 8) {
            return number.substring(0, 4) +
                    " **** **** " +
                    number.substring(number.length() - 4, number.length());
        }
        return number;
    }

    public static String tailFour(String number) {
        if (number.length() > 4) {
            return number.substring(number.length() - 4, number.length());
        } else {
            return number;
        }

    }

    /**
     * 保留2未小数，小数为0时不显示
     *
     * @param number
     * @return
     */
    public static String toDecimalFormat(double number) {
        return df.format(number);
    }

    /**
     * 保留2未小数
     *
     * @param number
     * @return
     */
    public static String toDecimalFormat2Decimal(double number) {
        return df2.format(number);
    }

    /**
     * 金钱加法
     *
     * @param v1
     * @param v2
     * @return
     */
    public static String add(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);

        return b1.add(b2).toString();
    }


}
