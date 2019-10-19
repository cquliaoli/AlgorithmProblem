package com.ll.dp;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by liaoli
 * date: 2019/10/19
 * time: 10:47
 * https://www.lintcode.com/problem/unique-paths-iii/description
 * @author: liaoli
 */
public class UniquePathsIII {

    /**
     * @param : an array of arrays
     * @return: the sum of all unique weighted paths
     */
    public static int uniqueWeightedPaths(int[][] grid) {

        int m = grid.length, n = grid[0].length;
        if(m==0||n==0){
            return 0;
        }
        // s[i][j] 为grid[i][j]不同路径和的集合，f[i][j]=grid[i][j] + (s[i-1][j] U s[i][j-1])
        int[][] f = new int[m][n];
        HashSet[][] s = new HashSet[m][n];
        s[0][0] = new HashSet<Integer>();
        s[0][0].add(grid[0][0]);
        f[0][0] = grid[0][0];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                s[i][j] = new HashSet<Integer>();
                if (i > 0) {
                    for (Object o : s[i - 1][j]) {
                        s[i][j].add((int) o + grid[i][j]);
                    }
                }
                if (j > 0) {
                    for (Object o : s[i][j - 1]) {
                        s[i][j].add((int) o + grid[i][j]);
                    }
                }
            }
        }
        int sum = 0;
        for (Object o : s[m - 1][n - 1]) {
            sum += (int) o;
        }

        return sum;
    }

    public static void main(String[] args) {
        int[][] g = new int[][]{{1, 1, 2}, {1, 2, 3}, {3, 2, 4}};
        int i = uniqueWeightedPaths(g);
        System.out.println(i);
    }
}
