package com.ll;


/**
 * Created by liaoli
 * date: 2018/7/26
 * time: 23:47
 */
public class BestTimeBuyAndSellStockWithCooldown {


    public static void main(String[] args) {

        int[]shares3=new int[]{1,2,3,0,2};
    }
    public static int maxProfit(int[] prices) {
        if(prices.length<2)
            return 0;
        if(prices.length==2){
            return Math.max(0,prices[1]-prices[0]);
        }
        int[]dp=new int[prices.length+2];
        dp[0]=dp[1]=0;
        int max=0;
        for(int i=3;i<prices.length+2;i++){
            for(int j=1;j<i;j++){
                max=Math.max(max,prices[i-2]-prices[j+1-2]+dp[j-1]);
            }
            dp[i]=Math.max(max,dp[i-1]);
        }
        return dp[prices.length+1];
    }

    public static int maxProfit1(int[] prices) {
        if(prices.length<2)
            return 0;
        if(prices.length==2){
            return Math.max(0,prices[1]-prices[0]);
        }
        int[]dp=new int[prices.length+2];
        dp[0]=dp[1]=0;
        int max=0;
        for(int i=3;i<prices.length+2;i++){
            max=Math.max(max+prices[i-2]-prices[i-3],prices[i-2]-prices[i-3]+dp[i-3]);
            dp[i]=Math.max(max,dp[i-1]);
        }
        return dp[prices.length+1];
    }
}
