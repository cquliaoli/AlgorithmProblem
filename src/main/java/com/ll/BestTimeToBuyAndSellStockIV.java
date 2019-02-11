package com.ll;

import java.io.*;
import java.util.Arrays;


/**
 * Created by liaoli
 * date: 2018/7/22
 * time: 17:59
 */
public class BestTimeToBuyAndSellStockIV {
    public static void testLong(){
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(new File("C:\\LiaoLi\\LeetCode\\src\\DP\\BestTimeToBuyAndSellStockIV.txt")));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                result.append(System.lineSeparator()+s);
            }
            br.close();
            String[]numStrs=result.toString().split(",");
            int[]nums=new int[numStrs.length];
            for (int i = 0; i < numStrs.length; i++) {
                nums[i]=Integer.parseInt(numStrs[i].trim());
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {

        int []num={1,2,3,0,2};
        int []num2={3,2,6,5,0,3};
        int []num3={1,2,4,7};
        int []num4={3,3,5,0,0,3,1,4};

        testLong();
    }

    /**
     * d[i,j]=max{ d[i,j-1],max{ max{d[i-1,t]+s[j]-s[t+1]} 0<=t<j ,d[i-1,j]}}
     * pre[i,j]=max{d[i-1,t]+s[j]-s[t+1]} 0<=t<j  pre[i,j+1]=max(pre[i,j]+s[j+1]-s[j],d[i-1,j+1]}
     *
     * d[i,j]=max{ d[i,j-1],max(pre[i,j-1]+s[j]-s[j-1],d[i-1,j]}}
     * pre[i,j]=max(pre[i,j-1]+s[j]-s[j-1],d[i-1,j])
     *
     *
     *      d[i-1,0]+s[j]-s[1]         d[i-1,0]+s[j+1]-s[1] +  s[j]-s[j]
     *      d[i-1,1]+s[j]-s[2]         d[i-1,1]+s[j+1]-s[2] +  s[j]-s[j]
     *      d[i-1,2]+s[j]-s[3]         d[i-1,2]+s[j+1]-s[3] +  s[j]-s[j]
     *      d[i-1,3]+s[j]-s[4]         d[i-1,3]+s[j+1]-s[4] +  s[j]-s[j]
     *              .
     *              .
     *              .
     *      d[i-1,j-1]+s[j]-s[j]        d[i-1,j-1]+s[j+1]-s[j] +  s[j]-s[j]
     *      d[i-1,j]                    d[i-1,j]
     *                                  d[i-1,j+1]
     *
     *  pre[i,j]=max{ max{d[i-1,t]+s[j]-s[t+1]} 0<=t<j ,d[i-1,j]}
     *  pre[i,j+1]=max(pre[i,j]+s[j+1]-s[j],d[i-1,j+1]}
     *
     * @param k
     * @param prices
     * @return
     */
    public static int maxProfit(int k, int[] prices) {
        Runtime.getRuntime().gc();
        long initm = Runtime.getRuntime().freeMemory();
        int len = prices.length;
        if (k == 0 || len < 2)
            return 0;
        if (k > len / 2) {
            int ans = 0;
            for(int i = 1; i < prices.length; i++)
                ans += Math.max(0, prices[i] - prices[i-1]);
            return ans;
        }
        int[] dp = new int[len];
        int[] pre=new int[len];
        int minPrice = prices[0];
        for (int i = 1; i < len; i++) {
            dp[i] = Math.max(dp[i-1], prices[i] - minPrice);
            pre[i]=dp[i];
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            }
        }
        int j;
        for (int i = 2; i < k + 1; i++) {
            int preMax=0;
            for ( j = 1; j < len; j++) {
                preMax=Math.max(preMax+prices[j]-prices[j-1],pre[j]);
                dp[j]=Math.max(dp[j-1],preMax);
                pre[j]=dp[j];
            }
        }
        Runtime.getRuntime().gc();
        long endm = Runtime.getRuntime().freeMemory();
        //计算空闲差：
        System.out.println("内存使用：" + (initm - endm) / 1024);
        return dp[len-1];
    }

    public int maxProfit1(int k, int[] prices) {
        if(prices.length == 0 || k == 0)
            return 0;
        if(k > prices.length/2){
            int ans = 0;
            for(int i = 1; i < prices.length; i++)
                ans += Math.max(0, prices[i] - prices[i-1]);
            return ans;
        }
        int i, j = 0, d = 0;
        int[] tmpMin = new int[k + 1];
        int[] bT = new int[k  + 1];
        for(i = 0; i <= k; i++)
            tmpMin[i] = prices[0];
        for(i = 0; i < prices.length; i++){
            for(j = 1; j <= k; j++){
                tmpMin[j] = Math.min(tmpMin[j], prices[i] - bT[j - 1]);
                bT[j] = Math.max(prices[i] - tmpMin[j], bT[j]);

            }
        }
        return bT[k];
    }

    public int rec(int k, int[] prices, int d){
        if(d == -1 || k == 0)
            return 0;
        int a = rec(k, prices, d - 1); // jump over today without selling
        int maxi = 0;
        for(int i = 0; i < d; i++){
            maxi = Math.max(prices[d] - prices[i]  + rec(k-1, prices, i), maxi);
        }
        return Math.max(maxi, a);
    }
}
