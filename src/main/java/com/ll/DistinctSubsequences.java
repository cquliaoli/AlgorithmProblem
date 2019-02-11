package com.ll;

/**
 * Created by liaoli
 * date: 2018/7/20
 * time: 12:54
 */
public class DistinctSubsequences {

    public static void main(String[] args) {

        System.out.println(numDistinct("rabbbit","rabbit"));
        System.out.println(numDistinct("babgbag","bag"));
    }

    /**
     * Input: S = "rabbbit", T = "rabbit"
     * Output: 3
     * Explanation:
     *
     * As shown below, there are 3 ways you can generate "rabbit" from S.
     * (The caret symbol ^ means the chosen letters)
     *
     * rabbbit
     * ^^^^ ^^
     * rabbbit
     * ^^ ^^^^
     * rabbbit
     * ^^^ ^^^
     * @param s
     * @param t
     * @return
     */
    public static int numDistinct(String s, String t) {
        if(s==null||t==null)
            return 0;
        if(s.length()<t.length())
            return 0;
        int len1=s.length(),len2=t.length();
        int [][]num=new int[len1+1][len2+1];
        for(int row=0;row<len1+1;row++){
            num[row][0]=1;
        }
        for(int col=1;col<len2+1;col++){
            for(int row=1;row<len1+1;row++){
                if(s.charAt(len1-row)==t.charAt(len2-col)){
                    num[row][col]=num[row-1][col-1]+num[row-1][col];
                }else {
                    num[row][col]=num[row-1][col];
                }
            }
        }
        return num[len1][len2];

    }
}
