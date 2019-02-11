package com.ll;

/**
 * Created by liaoli
 * date: 2019/2/2
 * time: 23:22
 */
public class NumDistinct {
    /**
     * @param S: A string
     * @param T: A string
     * @return: Count the number of distinct subsequences
     */
    public int numDistinct(String S, String T) {
        // write your code here
        if(S == null || S.length() == 0)
            return (T == null ? 0 :  T.length());
        char[] s = S.toCharArray();
        char[] t = T.toCharArray();
        int i, j, len1 = s.length, len2 = t.length;
        int[][] res = new int[len1 + 1][len2 + 1];
        for(i = 0; i <= len1; i++){
            for(j = 0; j <= len2; j++){
                if(i < j){
                    res[i][j] = 0;
                }else{
                    if(j == 0){
                        res[i][j] = 1;
                        continue;
                    }
                    if(s[i - 1] == t[j - 1]){
                        res[i][j] = res[i -1][j - 1] + res[i - 1][j];
                    }else{
                        res[i][j] = res[i - 1][j];
                    }
                }
            }
        }
        return res[len1][len2];
    }

    /**
     * 滚动数组优化
     * @param S: A string
     * @param T: A string
     * @return: Count the number of distinct subsequences
     */
    public int numDistinct1(String S, String T) {
        // write your code here
        if(S == null || S.length() == 0)
            return (T == null ? 0 :  T.length());
        char[] s = S.toCharArray();
        char[] t = T.toCharArray();
        int i, j, len1 = s.length, len2 = t.length;
        int[][] res = new int[2][len2 + 1];
        int old = 0, newRow = 0;
        for(i = 0; i <= len1; i++){
            old = newRow;
            newRow = 1 - newRow;
            for(j = 0; j <= len2; j++){
                if(i < j){
                    res[newRow][j] = 0;
                }else{
                    if(j == 0){
                        res[newRow][j] = 1;
                        continue;
                    }
                    if(s[i - 1] == t[j - 1]){
                        res[newRow][j] = res[old][j - 1] + res[old][j];
                    }else{
                        res[newRow][j] = res[old][j];
                    }
                }
            }
        }
        return res[newRow][len2];
    }
}
