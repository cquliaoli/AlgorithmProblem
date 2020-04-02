package com.ll.leetcode.dp;

/**
 * Created by liaoli
 * date: 2019/10/19
 * time: 22:39
 *
 * @author: liaoli
 */
public class HouseRobber {
    /**
     * @param A: An array of non-negative integers
     * @return: The maximum amount of money you can rob tonight
     */
    public long houseRobber(int[] A) {
        // f[n] = max{A[n] + f[n - 2] , f[n - 1] }

        int m = A.length;
        if( m == 0 ){
            return 0;
        }

        long[] f = new long[m + 1];
        f[0] = 0;
        for (int i = 1; i < m+1; i++){
            f[i] = Math.max(f[i-1],A[i-1] + (i - 2 >= 0? f[i -2] : 0));
        }

        return f[m];
    }
}
