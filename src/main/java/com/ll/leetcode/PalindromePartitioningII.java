package com.ll.leetcode;

/**
 * Created by liaoli
 * date: 2018/7/21
 * time: 22:45
 */
public class PalindromePartitioningII {


    public static void main(String[] args) {
        System.out.println(minCut3("cdd"));
        System.out.println(minCut3("aab"));
    }
    public static int minCut(String s) {
        if(s==null||s.length()==0)
            return 0;
        int len=s.length();
        int[][]min=new int[len+1][len+1];
        for(int i=0;i<len;i++){
            //单个字符一定是回文
            min[i][i+1]=0;
        }
        for(int i=2;i<len+1;i++){
            for(int j=0;j+i<=len;j++){
                if(palindrome(s.substring(j,j+i))){
                    min[j][j+i]=0;
                }
                else {
                    min[j][j+i]=Integer.MAX_VALUE;
                    for(int k=j+1;k<i+j;k++){
                        min[j][j+i]=Math.min(min[j][j+i],1+min[j][k]+min[k][i+j]);
                        if(1==min[j][j+1]){
                            break;
                        }
                    }
                }
            }
        }
        return min[0][len];
    }
    public static int minCut3(String s){
        if(s==null||s.length()==0)
            return 0;
        int len=s.length();
        boolean[][]pal=new boolean[len][len];
        int[]dp=new int[len];
        for(int i=0;i<len;i++){
            dp[i]=i;
            for(int j=i;j>=0;j--){
                if(s.charAt(i)==s.charAt(j)&&(i-j<2||pal[j+1][i-1])){
                    pal[j][i]=true;
                    if(j==0){
                        dp[i]=0;
                    }else {
                        dp[i]=Math.min(dp[i],dp[j-1]+1);
                    }

                }
            }
        }
        return dp[len-1];
    }
    //region 其他解法
    /**
     * 判断回文
     * @param s
     * @return
     */
    public static boolean palindrome(String s){
        if(s.length()==1)
            return true;
        int start=0,end=s.length()-1;
        while (start<=end&&s.charAt(start)==s.charAt(end)){
            start++;
            end--;
        }
        if(start>end)
            return true;
        else
            return false;
    }
    // dp[i] refers to min cuts for first i chars
    public int minCut1(String s) {
        int n = s.length();
        int[] dp = new int[n];
        for (int i = 0; i < dp.length; ++i) dp[i] = i;
        for (int i = 0; i < s.length(); ++i) {
            helper(s, i, i, dp);//odd
            helper(s, i, i + 1, dp);//even
        }
        return dp[n - 1];
    }
    private void helper(String s, int left, int right, int[] dp) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            if (left == 0) dp[right] = 0;
            else dp[right] = Math.min(dp[right], dp[left - 1] + 1);
            ++right;
            --left;
        }
    }
    //endregion
}
