package com.ll;

/**
 * Created by liaoli
 * date: 2018/6/20
 * time: 12:08
 */
public class UniquePaths {
    public static void main(String[] args) {

        System.out.println(uniquePaths(7,3));
    }
    public static int uniquePaths(int m, int n) {

        if(m<=0||n<=0){
            return 0;
        }
        int[][]path=new int[n][m];
        for(int i=0;i<n;i++){
            path[i][0]=1;
        }
        for(int i=0;i<m;i++){
            path[0][i]=1;
        }
        for(int i=1;i<n;i++){
            for(int j=1;j<m;j++){
                path[i][j]=path[i-1][j]+path[i][j-1];
            }
        }
        return path[n-1][m-1];
    }
}
