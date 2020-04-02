package com.ll.leetcode.dp;

/**
 * Created by liaoli
 * date: 2019/10/19
 * time: 17:13
 *
 * @author: liaoli
 */
public class BombEnemy {

    /**
     * @param grid: Given a 2D grid, each cell is either 'W', 'E' or '0'
     * @return: an integer, the maximum enemies you can kill using one bomb
     */
    public int maxKilledEnemies(char[][] grid) {
        // 上下左右分别使用 dp
        int m = grid.length;
        if (m == 0) {
            return 0;
        }
        int n = grid[0].length;
        int[][] up = new int[m][n];
        int[][] left = new int[m][n];
        int[][] down = new int[m][n];
        int[][] right = new int[m][n];

        // up
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'W') {
                    up[i][j] = 0;
                    left[i][j] = 0;
                    continue;
                }
                up[i][j] = grid[i][j] == 'E' ? 1 : 0;
                if (i > 0) {
                    up[i][j] += up[i - 1][j];
                }
                if (j > 0) {
                    left[i][j] += left[i][j - 1];
                }
            }
        }
        // down
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (grid[i][j] == 'W') {
                    down[i][j] = 0;
                    right[i][j] = 0;
                    continue;
                }
                down[i][j] = grid[i][j] == 'E' ? 1 : 0;
                if (i < m - 1) {
                    down[i][j] += down[i + 1][j];
                }
                if (j < n - 1) {
                    right[i][j] += right[i][j + 1];
                }
            }
        }
        int max = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, up[i][j] + down[i][j] + right[i][j] + left[i][j]);
            }
        }
        return max;
    }
}
