package com.ll.leetcode.dp;

/**
 * Created by liaoli
 * date: 2019/10/19
 * time: 12:12
 * https://www.lintcode.com/problem/paint-house-ii/description
 * @author: liaoli
 */
public class PaintHouseII {
    /**
     * @param costs: n x k cost matrix
     * @return: an integer, the minimum cost to paint all houses
     */
    public int minCostII(int[][] costs) {
        // f[i][j] 前 i -1 栋房子染成颜色 j 的最小花费；
        int m = costs.length;
        if(m == 0){
            return 0;
        }
        int n = costs[0].length;
        if(n ==0){
            return 0;
        }
        int[][] f = new int[m+1][n];
        for (int i = 0;i < n; i++){
            f[0][i] = 0;
        }
        for (int i=1 ;i < m+1; i++){
            for (int j=0;j<n;j++){
                f[i][j] = Integer.MAX_VALUE;
                for (int k=0;k<n;k++){
                    if(k!=j){
                        f[i][j] = Math.min(f[i-1][k]+costs[i-1][j],f[i][j]);
                    }
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (int i=0;i<n;i++){
            min = Math.min(f[m][i],min);
        }
        return min;
    }
}
