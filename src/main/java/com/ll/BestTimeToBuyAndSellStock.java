package com.ll;

/**
 * Created by liaoli
 * date: 2018/7/22
 * time: 13:06
 */
public class BestTimeToBuyAndSellStock {
    public static void main(String[] args) {
        int []num={7,1,5,3,6,4};
        int []num1={7,6,4,3,1};
        System.out.println(maxProfit(num));
        System.out.println(maxProfit(num1));
    }
    public static int maxProfit(int[] prices) {

        int len=prices.length;
        if(len<2)
            return 0;
        int[]max=new int[len];
        int maxNum=prices[len-1];
        max[len-1]=maxNum;
        for(int i=len-2;i>=0;i--){
            if(prices[i]>maxNum){
                maxNum=prices[i];
            }
            max[i]=maxNum;
        }
        int maxProfit=0;
        for(int i=0;i<len-1;i++){
            maxProfit=Math.max(maxProfit,max[i+1]-prices[i]);
        }
        return maxProfit;
    }
}
