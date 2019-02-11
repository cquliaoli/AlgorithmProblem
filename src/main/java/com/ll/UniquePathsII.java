package com.ll;

import java.util.Arrays;

/**
 * Created by liaoli
 * date: 2018/6/20
 * time: 13:05
 */
public class UniquePathsII {
    public static void main(String[] args) {
        int[][]obstacleGrid=new int[][]{
                {1,0,}
                };
        System.out.println(uniquePathsWithObstacles(obstacleGrid));
    }

    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid.length==0)
            return 0;
        int row=obstacleGrid.length,col=obstacleGrid[0].length;
        if(row<=0||col<=0){
            return 0;
        }
        int[][]path=new int[row][col];
        path[0][0]=obstacleGrid[0][0]==1?0:1;
        for(int i=1;i<row;i++){
            if(obstacleGrid[i][0]==0&&path[i-1][0]==1){
                path[i][0]=1;
            }else {
                path[i][0]=0;
            }

        }
        for(int i=1;i<col;i++){
            if(obstacleGrid[0][i]==0&&path[0][i-1]==1){
                path[0][i]=1;
            }else {
                path[0][i]=0;
            }

        }
        for(int i=1;i<row;i++){
            for(int j=1;j<col;j++){
                if(obstacleGrid[i][j]==1){
                    path[i][j]=0;
                }else {
                    path[i][j]=path[i-1][j]+path[i][j-1];
                }

            }
        }
        return path[row-1][col-1];
    }
}
