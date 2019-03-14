package com.freak.neteasecloudmusic.utils;

import java.text.DecimalFormat;

/**
 *
 * @author freak
 * @date 2019/3/14
 */

public class FormatUtil {

    public static final DecimalFormat DECIMAL_FORMAL = new DecimalFormat("#0.00");

    public static String changeToDecimal(Double value){

        return DECIMAL_FORMAL.format(value);
    }
}
