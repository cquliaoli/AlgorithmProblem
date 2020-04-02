package com.ll.leetcode;

/**
 * from:lintCode
 * url:https://www.lintcode.com/problem/maximum-subarray-iii/description
 * Created by liaoli
 * date: 2019/2/11
 * time: 9:34
 */
public class MaximumSubarrayIII {

    /**
     * DP
     * @param nums: A list of integers
     * @param k: An integer denote to find k non-overlapping subarrays
     * @return: An integer denote the sum of max k non-overlapping subarrays
     */
    public int maxSubArray_1(int[] nums, int k) {
        // write your code here
        // f[i][k]:以a[i] 结尾的k个不重叠子数组最大和
        // f[i][k]=max{f[i-1][k]+a[i],max{f[m][k-1]+a[i] | 0<=m<i} }
        int n = nums.length;
        if(k > n){
            k = n;
        }
        int i, j, m, res = Integer.MIN_VALUE;
        int[][] f = new int[n][k+1];
        for (i = 0; i < n;i++){
            f[i][0] = 0;
        }
        f[0][1] = nums[0];
        for (i = 2; i < k+1;i++){
            f[0][i] = Integer.MIN_VALUE;
        }
        for (i = 1; i < n; i++){
            for (j = 1; j <= k; j++){
                if (j > i + 1){
                    f[i][j] = Integer.MIN_VALUE;
                }else{
                    f[i][j] = Integer.MIN_VALUE;
                    if(f[i-1][j] != Integer.MIN_VALUE){
                        f[i][j] = f[i-1][j] + nums[i];
                    }
                    for (m = 0; m < i; m++){
                        if(f[m][j - 1] != Integer.MIN_VALUE){
                            f[i][j] = Math.max(f[i][j],f[m][j - 1] + nums[i]);
                        }
                    }
                }
            }
        }
        for (i = 0; i < n; i++){
            res = Math.max(f[i][k],res);
        }
        return res;
    }

    /**
     * DP 优化
     * @param nums: A list of integers
     * @param k: An integer denote to find k non-overlapping subarrays
     * @return: An integer denote the sum of max k non-overlapping subarrays
     */
    public int maxSubArray(int[] nums, int k) {
        // write your code here
        // f[i][k]:以a[i] 结尾的k个不重叠子数组最大和
        // f[i][k]=max{f[i-1][k]+a[i],max{f[m][k-1]+a[i] | 0<=m<i} }
        int n = nums.length;
        if(k > n){
            k = n;
        }
        int i, j, m, res = Integer.MIN_VALUE, old = 0, newRow = 0;
        // 滚动数组
        int[][] f = new int[2][k+1];
        // 第 i 列当前最大值
        int[] maxA =new int[k + 1];
        maxA[0] = f[old][0] = 0;
        maxA[1] = f[old][1] = nums[0];
        for (i = 2; i < k+1;i++){
            maxA[i] =f[old][i] = Integer.MIN_VALUE;
        }
        for (i = 1; i < n; i++){
            old = newRow;
            newRow = 1 - newRow;
            for (j = k; j >= 1; j--){
                if (j > i + 1){
                    f[newRow][j] = Integer.MIN_VALUE;
                }else{
                    f[newRow][j] = Integer.MIN_VALUE;
                    if(f[old][j] != Integer.MIN_VALUE){
                        f[newRow][j] = f[old][j] + nums[i];
                    }
                    f[newRow][j] = Math.max(f[newRow][j],maxA[j - 1] + nums[i]) ;
                    maxA[j] = Math.max(maxA[j],f[newRow][j]);
                }
            }
        }
        return maxA[k];
    }
}
