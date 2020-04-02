package com.ll.leetcode;

import java.util.*;

/**
 * Created by liaoli
 * date: 2018/7/8
 * time: 18:05
 */
public class ScrambleString {

    public static void main(String[] args) {
        String s="abcd";
        System.out.println(isScramble("rgtae","great"));
        System.out.println(isScrambleDp("rgtae","great"));
    }
    /**
     * Input: s1 = "great", s2 = "rgeat"
     * Output: true
     * @param s1
     * @param s2
     * @return
     */
    public static boolean isScramble(String s1, String s2) {

        if(Objects.equals(s1,s2))
            return true;
        int len=s1.length();
        Map<Character,Integer>map=new HashMap<>();
        for (char c : s1.toCharArray()) {
            Integer count=map.putIfAbsent(c,1);
            if(count!=null)
                map.put(c,count+1);
        }
        for (char c : s2.toCharArray()) {
            if(!map.containsKey(c)){
                return false;
            }
            map.put(c,map.get(c)-1);
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if(entry.getValue()!=0){
                return false;
            }
        }
        for(int i=1;i<len;i++){
            if(isScramble(s1.substring(0,i),s2.substring(0,i))&&isScramble(s1.substring(i,len),s2.substring(i,len))){
                return true;
            }
            if(isScramble(s1.substring(0,i),s2.substring(len-i,len))&&
                    isScramble(s1.substring(i,len),s2.substring(0,len-i))){
                return true;
            }
        }
        return false;
    }
    public static boolean isScrambleDp(String s1, String s2) {
        if(Objects.equals(s1,s2))
            return true;
        int len=s1.length();
        Map<Character,Integer>map=new HashMap<>();
        for (char c : s1.toCharArray()) {
            Integer count=map.putIfAbsent(c,1);
            if(count!=null)
                map.put(c,count+1);
        }
        for (char c : s2.toCharArray()) {
            if(!map.containsKey(c)){
                return false;
            }
            map.put(c,map.get(c)-1);
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if(entry.getValue()!=0){
                return false;
            }
        }
        boolean[][][]match=new boolean[len][len][len];
        for(int i=0;i<len;i++){
            for(int j=0;j<len;j++){
                if(s1.charAt(i)==s2.charAt(j)){
                    match[1][i][j]=true;
                }
            }
        }
        for(int l=2;l<len;l++){
            for(int i=0;i+l<len+1;i++){
                for(int j=0;j+l<len+1;j++){
                    for(int k=1;k<l;k++){
                        if(match(match,k,i,j,l)){
                            match[l][i][j]=true;
                            break;
                        }
                    }
                }
            }
        }
        for(int l=1;l<len;l++){
            if(match(match,l,0,0,len))
                return true;
        }
        return false;
    }

    /**
     *
     *
     *
     * @param match
     * @param k 前半部分长度
     * @param i s1 i-----i+len-1
     * @param j s2 j-----j+len-1
     * @param len 当前判断的长度
     * @return
     */
    public static boolean match(boolean[][][]match,int k,int i,int j,int len){
        if(match[k][i][j]&&match[len-k][i+k][j+k]){
            return true;
        }
        if(match[k][i][j+len-k]&&match[len-k][i+k][j]){
            return true;
        }
        return false;
    }
}
