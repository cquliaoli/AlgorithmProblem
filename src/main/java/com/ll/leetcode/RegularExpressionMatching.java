package com.ll.leetcode;
//看小本本！！！！第12页
//基本相似：44. Wildcard Matching，区别; '*' 可以表示：including the empty sequence
//time complexity = O(min(s.length, p.length))? 不太确定是min还是max？
/**思路：DP
 build 2D boolean array, that boolean[i][j] means that whether i length character from s can match j characters from p。从s里去0 ~ 第i个字符，和从p中取0 ~ j个字符，是否match
 三种情况：i and j are length, NOT index
 abcd
 adfd
 1, If p.charAt(j) == s.charAt(i) :  dp[i][j] = dp[i-1][j-1];
 abcd
 adf.
 2, If p.charAt(j) == '.' : dp[i][j] = dp[i-1][j-1];
 3, If p.charAt(j) == '*':
 here are two sub conditions:
 abcd
 abcf*d
 1   if p.charAt(j-1) != s.charAt(i) : dp[i][j] = dp[i][j-2]  //in this case, a* only counts as empty
 abcd
 abcd*
 abc.*
 2   if p.charAt(j-2) == s.charAt(i-1) or p.charAt(j-2) == '.':
 dp[i][j] = dp[i-1][j]    //in this case, a* counts as multiple a
 or dp[i][j] = dp[i][j-1]   // in this case, a* counts as single a
 or dp[i][j] = dp[i][j-2]   // in this case, a* counts as empty
 Time Complexity: Let T, P be the lengths of the text and the pattern respectively. The work for every call to dp(i, j) for i=0, ... ,Ti=0,...,T; j=0, ... ,Pj=0,...,P is done once, and it is O(1) work. Hence, the time complexity is O(TP).

 Space Complexity: The only memory we use is the O(TP) boolean entries in our cache. Hence, the space complexity is O(TP).
 time = O(mn), space = O(mn), m = s.length(), n = p.length()
 */
// class Solution {
//     public boolean isMatch(String s, String p) {
//         //since * match zero or more of the preceding element, so * can't be the head of p, otherwise, index overflow
//        if (s == null || p == null) {
//         return false;
//         }

//         boolean[][] dp = new boolean[s.length()+1][p.length()+1];
//         //initial 0 string s length matches 0 string p length
//         dp[0][0] = true;  //remember to initialize dp[0][0]: "" matches "" is true !!!

//         //build dp by get 0 char from s, and get i length char from p, our later results will depend on this
//          for (int i = 1; i <= p.length(); ++i) {
//             if (p.charAt(i - 1) == '*') {  //小本本里的第一种情况
//                 dp[0][i] = dp[0][i - 2];   //不用check i-2 是否overflow，因为 * 不能是p的第一个字符
//             }
//         }

//         for (int si = 1; si <= s.length(); ++si) {
//             for (int pi = 1; pi <= p.length(); ++pi) {
//                 if (p.charAt(pi - 1) == '.' || p.charAt(pi - 1) == s.charAt(si - 1)) {   // '.'可以match任何char
//                     dp[si][pi] = dp[si - 1][pi - 1];  //depends on previous stage
//                 } else if (p.charAt(pi - 1) == '*') {
//                     //p.charAt(pi - 2)shi*之前的那个char，因为pi是长度，pi - 1是 * 的index
//                     /**
//                     s = abcd|d
//                     p = abc|d*
//                     p = abc|.*
//                     */
//                     if (p.charAt(pi - 2) == s.charAt(si - 1) || p.charAt(pi - 2) == '.') {
//                         dp[si][pi] = dp[si][pi - 2] || dp[si - 1][pi] || dp[si][pi - 1];  //两个箭头的值
//                     } else {
//                         dp[si][pi] = dp[si][pi - 2];   //如果不match，就只等于一个上面一个箭头的值：false？
//                     }
//                 }
//             }
//         }
//     return dp[s.length()][p.length()];
//     }
// }


//Method2:// dfs, O(2^n) time, O(n) space, n is length of p (each part can be matched or not matched)
// class Solution {
//     public boolean isMatch(String s, String p) {
//         if (s == null || p == null) {
//             return false;
//         }
//         if (p.length() == 0) {//if no pattern can be used to match s, check whether s is empty too
//             return s.length() == 0;
//         }
//         if (p.length() == 1) {//if p only has one char, check whether s is also one char left, and then try to match them
//             return s.length() == 1 && matchFirstChar(s, p);
//         }
//         if (p.charAt(1) != '*') {//if second char isn't '*',we have to match first char of both s&p,and try to match the rest
//             return matchFirstChar(s, p) && isMatch(s.substring(1), p.substring(1));
//         }
//         return isMatch(s, p.substring(2)) || (matchFirstChar(s, p) && isMatch(s.substring(1), p));
//     }
//     //isMatch(s,p.substring(2)):check if we can skip this 2-char pattern,or pattern is used by last 1st char of s(so skip it)
//     //if pattern shouldn't be skipped(which means it should match more char),continue to try match 1 char with this pattern

//     private boolean matchFirstChar(String s, String p) {
//         return s.length() != 0 && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');
//     }
// }


//Method3: time = O(mn), space = O(n)
/**思路
 这道题可以用递归解决，不过时间复杂度是指数级，这里介绍一个用动态规划在平方时间内解决的办法。
 解法的核心理念是：从后往前看pattern的每一位，对于pattern的每一位，我们尽可能的把待匹配串string从后往前给匹配上。我们用一个数组match[string.length() + 1]来表示string被匹配的情况，这里如果match[j]是true，而我们pattern正指向第i位，则说明string从第j位到最后都已经被pattern第i位之前的某些部分给成功匹配了，所以我们不用再操心了。match[i]为true的条件是match[i + 1]为true，且string第i个字符也能被成功匹配。

 那我们就可以从后往前开始看pattern的每一位能匹配多少string的字符了：

 如果pattern的这一位是*，那我们要用这一位，来从后往前尝试匹配string，因为string后面是已经匹配好的，前面是还没匹配好的，所以从前往后匹配星号可能会导致我们匹配了一些pattern该星号前面的星号应该匹配的部分。而从后往前匹配则不会影响pattern该星号后面星号所匹配的部分，因为已经匹配的部分我们会直接跳过。
 如果pattern这一位不是*，那我们则不能匹配多个字符，我们只能匹配一个字符，这时候要对string从前往后匹配，因为如果后面没被匹配，前面也肯定不会被匹配，所以从前向后能保证我们把pattern的这一位匹配到string当前最后面那个还没匹配的字符。这样如果那个字符能被匹配就通过了。
 我们举个例子

 match:   0 0 0 1
 string:  a a b
 pattern: a * b
 |
 这里我们先看pattern最后一位b能匹配到多少，这里因为b不是星号，所以我们从左往右尝试匹配string，第一个a不行，第二个a也不行，然后到b，这里因为match[3]是true，b也和b相同，所以匹配成功。

 match:   0 0 1 1
 string:  a a b
 pattern: a * b
 |
 然后看pattern的这个星号，我们要从后往前匹配string。因为b已经被匹配了，match[2]是true，所以直接跳过。然后到a，发现个pattern中星号前面的字符a相同，所以匹配成功，match[1]也置为true再看string的第一个a，还是可以匹配成功，这样整个string都被匹配成功了。

 这里还有几个情况，首先，无论刚才那pattern中最后一个b有没有匹配到string中任何一个字符，match[3]也要置为false。这样才能防止pattern最后字母没有匹配上，而pattern前面的部分反而把string的结尾给匹配了。还有如果pattern中是句号的话，那相当于字符相同。
 */
/**
 * Created by liaoli
 * date: 2018/6/19
 * time: 19:33
 */
public class RegularExpressionMatching {

    public static void main(String[] args) {
        //System.out.println(isMatch("aa","a"));
        System.out.println(isMatch("", ".*"));
        System.out.println(isMatch("aafdgdfg", ".*"));
        System.out.println(isMatch("aab", "c*a*b"));
    }

    public static boolean isMatch(String text, String pattern) {
        if ((text == null && pattern == null)
                || (text.length() == 0 && pattern.length() == 0)) {
            return true;
        }
        if (pattern == null || pattern.length() == 0) {
            return false;
        }
        int tLen = text.length(), pLen = pattern.length();
        int firstCharIndex = -1;
        for (int i = 0; i < pLen; i++) {
            if (pattern.charAt(i) != '*') {
                firstCharIndex = i;
                break;
            }
        }
        if (firstCharIndex > 0) {
            pattern = pattern.substring(firstCharIndex);
            pLen = pattern.length();
        }
        pattern = '#' + pattern;
        text = '#' + text;
        tLen = text.length();
        pLen = pattern.length();
        boolean[][] endIndexArr = new boolean[pLen][tLen];
        endIndexArr[0][0] = true;
        for (int i = 1; i < pLen; i++) {
            for (int j = 0; j < tLen; j++) {
                if ((pattern.charAt(i) <= 'z' && pattern.charAt(i) >= 'a')
                        || '.' == pattern.charAt(i)) {
                    if (endIndexArr[i - 1][j]
                            && j + 1 < tLen
                            && (pattern.charAt(i) == text.charAt(j + 1)
                            || '.' == pattern.charAt(i))) {
                        endIndexArr[i][j + 1] = true;
                    }
                } else if (pattern.charAt(i) == '*') {
                    if (endIndexArr[i - 2][j] || endIndexArr[i - 1][j])
                        endIndexArr[i][j] = true;
                    if (endIndexArr[i - 1][j]) {
                        int k = j;
                        while (++k < tLen) {
                            if (pattern.charAt(i - 1) == text.charAt(k)
                                    || pattern.charAt(i - 1) == '.') {
                                endIndexArr[i][k] = true;
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
        }
        return endIndexArr[pLen - 1][tLen - 1];
    }

    public boolean isMatch2(String s, String p) {
        boolean[] match = new boolean[s.length() + 1];
        match[s.length()] = true;
        for (int i = p.length() - 1; i >= 0; i--) {
            if (p.charAt(i) == '*') {
                // 如果是星号，从后往前匹配
                for (int j = s.length() - 1; j >= 0; j--) {
                    match[j] = match[j] || (match[j + 1] && (p.charAt(i - 1) == '.' || (p.charAt(i - 1) == s.charAt(j))));
                }
                // 记得把i多减一，因为星号是和其前面的字符配合使用的
                i--;
            } else {
                // 如果不是星号，从前往后匹配
                for (int j = 0; j < s.length(); j++) {
                    match[j] = match[j + 1] && (p.charAt(i) == '.' || (p.charAt(i) == s.charAt(j)));
                }
                // 只要试过了pattern中最后一个字符，就要把match[s.length()]置为false
                match[s.length()] = false;
            }
        }
        return match[0];
    }

    public static boolean isMatch1(String text, String pattern) {
        boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];
        dp[text.length()][pattern.length()] = true;
        for (int i = text.length(); i >= 0; i--) {
            for (int j = pattern.length() - 1; j >= 0; j--) {
                boolean first_match = (i < text.length() &&
                        (pattern.charAt(j) == text.charAt(i) ||
                                pattern.charAt(j) == '.'));
                if (j + 1 < pattern.length() && pattern.charAt(j + 1) == '*') {
                    dp[i][j] = dp[i][j + 2] || first_match && dp[i + 1][j];
                } else {
                    dp[i][j] = first_match && dp[i + 1][j + 1];
                }
            }
        }
        return dp[0][0];
    }
}
