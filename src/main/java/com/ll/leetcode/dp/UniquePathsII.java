package com.ll.leetcode.dp;

/**
 * Created by liaoli
 * date: 2019/10/19
 * time: 10:04
 * https://www.lintcode.com/problem/unique-paths-ii/description
 * @author: liaoli
 */
public class UniquePathsII {

    /**
     * @param obstacleGrid: A list of lists of integers
     * @return: An integer
     */
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // write your code here

        int m = obstacleGrid.length, n = obstacleGrid[0].length;
        int[][] f = new int[m][n];
        f[0][0] = obstacleGrid[0][0] == 1 ? 0 : 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    obstacleGrid[i][j] = 0;
                    continue;
                }
                if (i > 0) {
                    f[i][j] += f[i - 1][j];
                }
                if (j > 0) {
                    f[i][j] += f[i][j - 1];
                }
            }
        }
        return f[m - 1][n - 1];
    }

    public static void main(String[] args) {
        int[][] f = new int[][]{{0, 0}, {0, 0}, {0, 0}, {1, 0}, {0, 0}};
        int i = uniquePathsWithObstacles(f);
        System.out.println(i);
    }
}
