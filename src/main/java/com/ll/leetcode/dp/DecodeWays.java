package com.ll.leetcode.dp;

/**
 * Created by liaoli
 * date: 2019/10/19
 * time: 12:52
 * https://www.lintcode.com/problem/decode-ways/description
 *
 * @author: liaoli
 */
public class DecodeWays {

    /**
     * @param s: a string,  encoded message
     * @return: an integer, the number of ways decoding
     */
    public static int numDecodings(String ss) {
        // f[i] = f[i-1]+f[i-2]|s[i-2][i-1] 是字母

        if (ss == null) {
            return 0;
        }
        char[] s = ss.toCharArray();
        int m = s.length;
        if (m == 0) {
            return 0;
        }
        int[] f = new int[m + 1];
        int i, j;
        f[0] = 1;
        for (i = 1; i <= m; i++) {
            f[i] = 0;
            if (s[i - 1] <= '9' && s[i - 1] > '0') {
                f[i] += f[i - 1];
            }
            if (i - 2 >= 0) {
                j = (s[i - 2] - '0') * 10 + (s[i - 1] - '0');
                if (s[i - 2] != '0' && j >= 10 && j <= 26) {
                    f[i] += f[i - 2];
                }
            }
        }
        return f[m];
    }

    public static void main(String[] args) {
        String s = "0";
        System.out.println(numDecodings(s));
    }
}
