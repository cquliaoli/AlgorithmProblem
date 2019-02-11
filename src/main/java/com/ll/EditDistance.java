package com.ll;

/**
 * Created by liaoli
 * date: 2019/2/2
 * time: 21:28
 */
public class EditDistance {
    /**
     * @param word1: A string
     * @param word2: A string
     * @return: The minimum number of steps.
     */
    public int minDistance(String word1, String word2) {
        // write your code here
        if (word1 == null || word1.length() == 0){
            return word2 == null ? 0 :word2.length();
        }
        char[] s1 = word1.toCharArray();
        char[] s2 = word2.toCharArray();
        int i, j, len1 = s1.length, len2 = s2.length;
        int[][] res = new int[len1 + 1][len2 + 1];
        //f[i][j]=if(s1[i - 1] == s2[j - 1]) f[i - 1][j - 1]
        //  else min{1 + f[i-1][j-1],1+f[i-1][j],1+f[i][j-1]}
        for(i = 0; i <= len1; i++){
            for(j = 0; j <= len2; j++){
                if(i == 0 || j == 0){
                    if(i > 0){
                        res[i][j] = i;
                    }else{
                        res[i][j] = j;
                    }
                    continue;
                }
                if(s1[i - 1] == s2[j - 1]){
                    res[i][j] = res[i - 1][j - 1];
                }else{
                    res[i][j] =1 + Math.min(Math.min(res[i - 1][j],res[i][j - 1]),res[i - 1][j - 1]);
                }
            }
        }
        return res[len1][len2];
    }
}
