package com.ll;

import util.printUtil;

import java.util.*;

/**
 * Created by liaoli
 * date: 2019/2/11
 * time: 20:53
 */
public class WordBreak {

    public static void main(String[] args) {
        WordBreak wordBreak = new WordBreak();
        System.out.println(wordBreak.wordBreak("lintcode",new HashSet<String>(){
            {
                add("de");//["de","ding","co","code","lint"]
                add("ding");
                add("co");
                add("code");
                add("lint");
            }
        }));
        System.out.println(wordBreak.wordBreak("aaab",new HashSet<String>(){
            {
                add("b");//["de","ding","co","code","lint"]
                add("aa");
            }
        }));
    }
    enum Result {
        NotMatch,
        NotWord,
        Word;

    }
    class TrieNode{
        TrieNode[] sons;
        boolean hasWord;
        String word;

        public TrieNode(){
            sons = new TrieNode[26];
            hasWord = false;
            word = null;
        }
        public  void Insert(TrieNode root, String s){
            char[] a = s.toCharArray();
            TrieNode p = root;
            for(int i = 0; i < a.length; i++){
                int k = a[i] - 'a';
                if(p.sons[k] == null){
                    p.sons[k] = new TrieNode();
                }
                p = p.sons[k];
            }
            p.hasWord = true;
            p.word = s;
        }

        /**
         * chars[begin-end] 是否在字母树中
         * @param chars
         * @param begin
         * @param end
         * @param root
         * @return
         */
        public Result hasWord(char[] chars, int begin, int end, TrieNode root){
            TrieNode p=root;
            for (int i = begin; i <= end; i++){
                int index = chars[i] - 'a';
                if(p.sons[index] == null){
                    return Result.NotMatch;
                }
                p=p.sons[index];
            }
            return p.hasWord?Result.Word:Result.NotWord;
        }
    }
    char[] chars;
    Map<Integer,Boolean> map = new HashMap<>();
    int end, maxLen;
    TrieNode root;
    /**
     * 记忆化 dp 字符串太长 栈溢出
     * @param begin
     * @return
     */
    @Deprecated
    public boolean dfs(int begin){
        int i;
        if(map.containsKey(begin)){
            return map.get(begin);
        }

        // 状态 f[i][j] 为i到j字符串组成的句子
        // f[i][j]=s[i][k] + f[i+1][j] 其中s[i][k] 表示该字符串在字典中
        for (i = begin; i <= end; i++){
            //超过最长字典字符串长度不用考虑
            if(i - begin +1 > maxLen)
                break;
            Result result = root.hasWord(chars, begin, i, root);
            //不匹配 不用考虑后续字符
            if(result == Result.NotMatch){
                break;
            }
            if(result == Result.Word){
                if(i == end){//整个子窜匹配 直接返回
                    map.put(begin, true);
                    return true;
                }
                boolean remain = dfs(i+1);
                if(remain){
                    map.put(begin, true);
                    return true;
                }
            }
        }
        map.put(begin, false);
        return false;
    }
    /*
     * @param s: A string
     * @param wordDict: A set of words.
     * @return: All possible sentences.
     */
    public boolean wordBreak(String s, Set<String> wordDict) {
        // 构造字母树
        root = new TrieNode();
        for (String str : wordDict){
            root.Insert(root,str);
        }
        if(s == null || s.length() == 0){
            return wordDict.size() == 0;
        }
        int minLen = 0;
        for (String str:wordDict){
            maxLen = Math.max(maxLen, str.length());
            minLen = Math.min(minLen, str.length());
        }

        chars = s.toCharArray();
        int n = chars.length;
        end = n - 1;
        if(n < minLen){
            return  false;
        }
        // dp 状态 f[i][j] 为i到j字符串组成的句子，f[i][j]=s[i][k] + f[i+1][j] 其中s[i][k] 表示该字符串在字典中
        boolean[] f = new boolean[n + 1];// 可以用滚动数组 将其优化为 maxLen
        f[n] = true;
        for (int i = end; i >= 0; i--){
            for (int j = 0; j < maxLen; j++){
                if(i + j <= end){
                    Result result = root.hasWord(chars, i, i + j, root);
                    if(Result.NotMatch == result){
                        f[i] = false;
                        break;
                    }else if(Result.Word == result && f[i + j + 1]){
                        f[i] = true;
                        break;
                    }
                }
            }
        }
        return f[0];
    }

    /**
     * 记忆化 dp
     * @param s
     * @param wordDict
     * @param maxLen
     * @param map
     * @return
     */
    public boolean dfs2(String s, Set<String> wordDict, int maxLen, Map<String,Boolean> map){
        int i, n = s.length();
        if(map.containsKey(s)){
            return map.get(s);
        }
        // 字典中包含该字符串
        if (wordDict.contains(s)){
            map.put(s, true);
            return true;
        }

        // 状态 f[i][j] 为i到j字符串组成的句子
        // f[i][j]=s[i][k] + f[i+1][j] 其中s[i][k] 表示该字符串在字典中
        for (i = 1; i < n; i++){
            if(i > maxLen){
                break;
            }
            String sub = s.substring(0, i);
            if(wordDict.contains(sub) && i<n){
                boolean remain = dfs2(s.substring(i), wordDict, maxLen, map);;
                if(remain){
                    map.put(s, true);
                    return true;
                }
            }
        }
        map.put(s, false);
        return false;
    }


    public boolean wordBreak1(String s, Set<String> wordDict) {
        // 构造字母树
        TrieNode root = new TrieNode();
        for (String str : wordDict){
            root.Insert(root,str);
        }
        if(s == null || s.length() == 0){
            return wordDict.size() == 0;
        }
        int maxLen = 0;
        for (String str:wordDict){
            maxLen = Math.max(maxLen, str.length());
        }
        // dp 状态 f[i][j] 为i到j字符串组成的句子，f[i][j]=s[i][k] + f[i+1][j] 其中s[i][k] 表示该字符串在字典中
        return dfs2(s, wordDict, maxLen, new HashMap<String, Boolean>());
    }
}
