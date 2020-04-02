package com.ll.leetcode;



import com.ll.util.printUtil;

import java.util.*;

/**
 * Created by liaoli
 * date: 2019/2/11
 * time: 18:56
 */
public class WordBreakII {
    public static void main(String[] args) {
        WordBreakII wordBreakII = new WordBreakII();
        printUtil.printList(wordBreakII.wordBreak("lintcode",new HashSet<String>(){
            {
                add("de");//["de","ding","co","code","lint"]
                add("ding");
                add("co");
                add("code");
                add("lint");
            }
        }));

        printUtil.printList(wordBreakII.wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaba" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaa",new HashSet<String>(){
            {
                add("a");//["de","ding","co","code","lint"]
                add("aa");
                add("aaa");
                add("aaaa");
                add("aaaaa");
                add("aaaaaa");
                add("aaaaaaa");
                add("aaaaaaaa");
                add("aaaaaaaaa");
                add("aaaaaaaaaa");
            }
        }));
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
    }

    /**
     * 记忆化 dp
     * @param s
     * @param wordDict
     * @param map
     * @return
     */
    public List<String> dfs(String s, Set<String> wordDict, Map<String,List<String>>map){
        List<String> list = new ArrayList();
        int i, n = s.length();
        if(map.containsKey(s)){
            return map.get(s);
        }
        // 字典中包含该字符串
        if (wordDict.contains(s)){
            list.add(s);
        }

        // 状态 f[i][j] 为i到j字符串组成的句子
        // f[i][j]=s[i][k] + f[i+1][j] 其中s[i][k] 表示该字符串在字典中
        for (i = 1; i < n; i++){
            String sub = s.substring(0, i);
            if(wordDict.contains(sub) && i<n){
                List<String> remain = dfs(s.substring(i), wordDict, map);;
                if(remain !=null && remain.size() > 0){
                    for (String s1 : remain) {
                        list.add(sub + " "+ s1);
                    }
                }
            }
        }
        map.put(s, list);
        return list;
    }
    /*
     * @param s: A string
     * @param wordDict: A set of words.
     * @return: All possible sentences.
     */
    public List<String> wordBreak(String s, Set<String> wordDict) {
        // 构造字母树
        /*TrieNode root = new TrieNode();
        for (String str : wordDict){
            root.Insert(root,str);
        }*/
        // dp 状态 f[i][j] 为i到j字符串组成的句子，f[i][j]=s[i][k] + f[i+1][j] 其中s[i][k] 表示该字符串在字典中
        return dfs(s, wordDict, new HashMap<String, List<String>>());
    }
}
