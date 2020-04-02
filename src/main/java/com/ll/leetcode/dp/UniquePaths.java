package com.ll.leetcode.dp;

/**
 * Created by liaoli
 * date: 2019/10/19
 * time: 10:03
 * https://www.lintcode.com/problem/unique-paths/description
 * @author: liaoli
 */
public class UniquePaths {

    /**
     * @param m: positive integer (1 <= m <= 100)
     * @param n: positive integer (1 <= n <= 100)
     * @return: An integer
     */
    public int uniquePaths(int m, int n) {
        // write your code here
        if (m == 1 || n == 1){
            return 1;
        }
        int[][] f = new int[m][n];
        for(int i=0;i< m;i++){
            f[i][0] = 1;
        }
        for(int i = 0; i<n;i++){
            f[0][i] = 1;
        }
        for (int i = 1;i < m; i++){
            for (int j = 1;j < n; j++){
                f[i][j] = f[i-1][j] + f[i][j-1];
            }
        }

        return f[m-1][n-1];
    }
}
