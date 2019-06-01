package com.freak.neteasecloudmusic.player.manager.util;

import java.util.Random;

/**
 * 处理随机数
 */
public class RandomUtil {
    /**
     *
     */
    private static int[] mNums;
    /**
     * 最近一次所在的位置
     */
    private static int mLastNum = -1;
    /**
     * 随机个数
     */
    private static int mSize = -1;

    /**
     * 设置随机数
     *
     * @param size
     */
    public static void setNums(int size) {
        mNums = new int[size];
        mSize = size;
        for (int i = 0; i < size; i++) {
            mNums[i] = i;
        }
        mLastNum = mSize - 1;
    }

    /**
     * 生成随机数
     * @return
     */
    public static int createRandomNum() {
        if (mLastNum == -1) {
            setNums(mSize);
        }

        int index = new Random().nextInt(mLastNum + 1);

        int num = mNums[index];
        int temp = mNums[mLastNum];
        mNums[mLastNum] = num;
        mNums[index] = temp;
        mLastNum--;

        return num;
    }
}
