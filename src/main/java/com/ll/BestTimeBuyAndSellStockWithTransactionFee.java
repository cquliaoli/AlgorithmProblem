package com.ll;


/**
 * Created by liaoli
 * date: 2018/7/26
 * time: 21:03
 */
public class BestTimeBuyAndSellStockWithTransactionFee {


    public static void main(String[] args) {
        int[]shares=new int[]{1, 3, 2, 8, 4, 9};
        int[]shares1=new int[]{1,3,7,5,10,3};
        int[]shares2=new int[]{1,3,2,8,4,9};
        int[]shares3=new int[]{4,5,2,4,3,3,1,2,5,4};

    }

    /**
     *
     * @param prices
     * @param fee
     * @return
     */
    @Deprecated
    public static int maxProfit(int[] prices, int fee) {
        if(prices.length<2)
            return 0;
        if(prices.length==2){
            return Math.max(0,prices[1]-prices[0]-fee);
        }
        int min=prices[0],preMax=-1;
        int ans=0;
        for(int i=1;i<prices.length;i++){
            if(i<prices.length-1){
                if(prices[i]-min-fee>0
                        &&prices[i]>=prices[i+1]){
                  if(preMax!=-1&&min-preMax+fee>0){
                      ans+=prices[i]-preMax;
                  }else {
                      ans+=prices[i]-min-fee;
                      min=prices[i+1];
                  }
                  preMax=prices[i];
                }else {
                    min=Math.min(min,prices[i]);
                }
            }else {
                if(prices[i]-min-fee>0){
                    if(preMax!=-1&&min-preMax+fee>0){
                        ans+=prices[i]-preMax;
                    }else {
                        ans+=prices[i]-min-fee;
                    }

                }
            }
        }
        return ans;
    }

    /**
     *
     * @param prices
     * @param fee
     * @return
     */
    public static int maxProfit1(int[] prices, int fee){
        if(prices.length<2)
            return 0;
        if(prices.length==2){
            return Math.max(0,prices[1]-prices[0]-fee);
        }
        int []dp=new int[prices.length];
        dp[0]=0;
        int max=0;
        for(int i=1;i<prices.length;i++){
            for(int j=0;j<i;j++){
                max=Math.max(max,dp[j]+prices[i]-prices[j]-fee);
                dp[i]=Math.max(dp[i-1],max);
            }
        }
        return dp[prices.length-1];
    }

    /**
     * 改进时间复杂度
     * @param prices
     * @param fee
     * @return
     */
    public static int maxProfit2(int[] prices, int fee){
        if(prices.length<2)
            return 0;
        if(prices.length==2){
            return Math.max(0,prices[1]-prices[0]-fee);
        }
        int []dp=new int[prices.length];
        dp[0]=0;
        int max=-fee;//注意初始情况
        for(int i=1;i<prices.length;i++){
            //第二部分prices[i]-prices[i-1]-fee+dp[i-1] 取dp[i-1]的原因不影响结果，方便处理
            max=Math.max(max+prices[i]-prices[i-1],prices[i]-prices[i-1]-fee+dp[i-1]);
            dp[i]=Math.max(dp[i-1],max);
        }
        return dp[prices.length-1];
    }
}
