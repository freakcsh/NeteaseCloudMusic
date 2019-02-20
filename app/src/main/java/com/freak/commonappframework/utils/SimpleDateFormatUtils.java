package com.freak.commonappframework.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Administrator
 * @date 2018/4/17
 */

public class SimpleDateFormatUtils {


    public static String formatYMDHS(long time){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(new Date(time));
    }

    public static String formatYMD(long time){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        return format.format(new Date(time));
    }

    public static String formatYM(long time){
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM");
        return format.format(new Date(time));
    }

}
