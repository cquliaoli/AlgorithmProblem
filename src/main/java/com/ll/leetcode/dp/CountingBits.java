package com.ll.leetcode.dp;

/**
 * Created by liaoli
 * date: 2019/10/19
 * time: 22:19
 * https://www.lintcode.com/problem/counting-bits/description
 * @author: liaoli
 */
public class CountingBits {

    /**
     * @param num: a non negative integer number
     * @return: an array represent the number of 1's in their binary
     */
    public int[] countBits(int num) {
        // write your code here
        int[] f = new int[num + 1];
        f[0] = 0;
        for (int i = 1; i < num + 1; i++){
            f[i] = (i & 1) + f[i >> 1];
        }
        return f;
    }
}
