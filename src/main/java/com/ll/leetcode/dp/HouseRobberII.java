package com.ll.leetcode.dp;

/**
 * Created by liaoli
 * date: 2019/10/19
 * time: 22:57
 *
 * @author: liaoli
 */
public class HouseRobberII {
    public static int houseRobber2(int[] nums) {
        int n = nums.length;
        if( n == 0){
            return 0;
        }
        if( n == 1){
            return nums[0];
        }
        int[] A = new int[n-1];
        int res = Integer.MIN_VALUE;
        for(int i=0;i<n-1;i++){
            A[i] = nums[i];
        }
        res = Math.max(res,houseRobber(A));
        for(int i=1;i<n;i++){
            A[i-1] = nums[i];
        }
        res = Math.max(res,houseRobber(A));
        return res;
    }

    public static int houseRobber(int[] A) {
        // f[n] = max{A[n] + f[n - 2] , f[n - 1] }
        int m = A.length;
        if( m == 0 ){
            return 0;
        }

        int[] f = new int[m + 1];
        f[0] = 0;
        for (int i = 1; i < m+1; i++){
            f[i] = Math.max(f[i-1],A[i-1] + (i - 2 >= 0? f[i -2] : 0));
        }
        return f[m];
    }

    public static void main(String[] args) {
        int[] a = new int[]{2};
        System.out.println(houseRobber2(a));
    }
}
