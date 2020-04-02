package com.ll.leetcode.dp;

/**
 * Created by liaoli
 * date: 2019/10/19
 * time: 12:10
 * https://www.lintcode.com/problem/minimum-path-sum/description
 * @author: liaoli
 */
public class MinPathSum {

    /**
     * @param grid: a list of lists of integers
     * @return: An integer, minimizes the sum of all numbers along its path
     */
    public int minPathSum(int[][] grid) {
        // write your code here
        int m = grid.length;
        if(m == 0){
            return 0;
        }

        int n = grid[0].length;
        if(n == 0){
            return 0;
        }
        int[][] f = new int[m][n];

        for (int i= 0 ; i< m ;i++){
            for (int j = 0; j<n; j++){
                if(i == 0 && j == 0 ){
                    f[i][j] = grid[i][j];
                    continue;
                }

                f[i][j] = Integer.MAX_VALUE;
                if(i > 0){
                    f[i][j] = Math.min(f[i][j],f[i-1][j]+grid[i][j]);
                }

                if(j > 0){
                    f[i][j] = Math.min(f[i][j],f[i][j-1]+grid[i][j]);
                }
            }
        }

        return f[m-1][n-1];

    }
}
