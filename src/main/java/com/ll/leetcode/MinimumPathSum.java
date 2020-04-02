package com.ll.leetcode;

/**
 * Created by liaoli
 * date: 2018/6/20
 * time: 13:28
 */
public class MinimumPathSum {

    public static void main(String[] args) {
        int[][]grid=new int[][]{
                {1,3,1},
                {1,5,1},
                {4,2,1}
        };
        int[][]grid1=new int[][]{
                {0,2},
                {1,0}
        };
        System.out.println(minPathSum(grid));
        System.out.println(minPathSum(grid1));
    }
    public static int minPathSum(int[][] grid) {
        if(grid.length==0)
            return 0;
        int row=grid.length,col=grid[0].length;
        if(row<=0||col<=0){
            return 0;
        }
        int[][]path=new int[row][col];
        path[0][0]=grid[0][0];
        for(int i=1;i<row;i++){
            path[i][0]=path[i-1][0]+grid[i][0];
        }
        for(int i=1;i<col;i++){
            path[0][i]=path[0][i-1]+grid[0][i];
        }
        for(int i=1;i<row;i++){
            for(int j=1;j<col;j++){
                path[i][j]=Math.min(path[i-1][j]+grid[i][j],path[i][j-1]+grid[i][j]);

            }
        }
        return path[row-1][col-1];
    }
}
