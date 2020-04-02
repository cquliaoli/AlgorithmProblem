package com.ll.leetcode.dp;

/**
 * Created by liaoli
 * date: 2019/10/19
 * time: 8:54
 * https://www.lintcode.com/problem/coin-change/description
 * @author: liaoli
 */
public class CoinChange {

    /**
     *  dp : f[i] = min{f[i-j]+1} ,j in coins
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        // write your code here

        int[] min = new int[amount+1];
        for(int i = 1; i < min.length; i++){
            min[i] = Integer.MAX_VALUE;
        }
        min[0] = 0;
        for(int i = 1; i <= amount; i++){
            for (int coin : coins) {
                if (i >= coin && min[i - coin] != Integer.MAX_VALUE) {
                    min[i] = Math.min(min[i - coin] + 1, min[i]);
                }
            }
        }
        return min[amount] == Integer.MAX_VALUE?-1:min[amount];
    }
}
