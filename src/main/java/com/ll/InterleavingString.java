package com.ll;

/**
 * Created by liaoli
 * date: 2018/7/12
 * time: 23:54
 */
public class InterleavingString {

    public static void main(String[] args) {
        System.out.println(isInterleave("aabcc","dbbca","aadbbcbcac"));
        //Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
        //Output: false
        System.out.println(isInterleave("aabcc","dbbca","aadbbbaccc"));
    }
    /**
     * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
     * Output: true
     * s[i,j]=if s1[i]
     *
     * 0 1 2
     * 0 1 2 3
     * @param s1
     * @param s2
     * @param s3
     * @return
     */
    public static boolean isInterleave(String s1, String s2, String s3) {
        int len1=s1.length(),len2=s2.length(),len3=s3.length();
        if(len1+len2!=len3)
            return false;
        boolean [][]s=new boolean[len1+1][len2+1];
        s[0][0]=true;
        for(int row=1;row<len1+1;row++){
            s[row][0]=s[row-1][0]&&(s1.charAt(row-1)==s3.charAt(row-1));
        }
        for(int col=1;col<len2+1;col++){
            s[0][col]=s[0][col-1]&&(s2.charAt(col-1)==s3.charAt(col-1));
        }
        for(int row=1;row<len1+1;row++){
            for(int col=1;col<len2+1;col++){
                if(s1.charAt(row-1)==s3.charAt(row+col-1)){
                    s[row][col]=s[row-1][col];
                }
                if(!s[row][col]&&s2.charAt(col-1)==s3.charAt(row+col-1)){
                    s[row][col]|=s[row][col-1];
                }
            }
        }
        return s[len1][len2];
    }
}
