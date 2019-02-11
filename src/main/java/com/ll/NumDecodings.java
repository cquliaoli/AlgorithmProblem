package com.ll;

/**
 * Created by liaoli
 * date: 2018/7/10
 * time: 19:15
 */
public class NumDecodings {
    public static void main(String[] args) {
        System.out.println(numDecodings("101"));
        System.out.println(numDecodings("12"));
        System.out.println(numDecodings("0"));
        System.out.println(numDecodings("226"));
        System.out.println(numDecodings("2226"));
        System.out.println(numDecodings("301"));
    }

    /**
     * 2 2 2 6
     * 22 2 6
     * 2 22 6
     * 2 2 26
     * 22 26
     * 'A' -> 1
     * 'B' -> 2
     * ...
     * 'Z' -> 26
     * @param s
     * @return
     */
    public static int numDecodings(String s) {
        if(s==null||s.length()==0||s.startsWith("0"))
            return 0;

        int len=s.length();
        int []num=new int[len];
        num[0]=1;
        if(len>1){
            if(s.charAt(1)=='0'){
                if(Integer.parseInt(s.substring(0,2))<=26)
                    num[1]=1;
                else
                    return 0;
            }else {
                if(Integer.parseInt(s.substring(0,2))<=26)
                    num[1]=2;
                else
                    num[1]=1;
            }
        }else {
            return num[0];
        }
        for (int i = 2; i < s.toCharArray().length; i++) {
            //      xxxxxxx 10
            if(s.charAt(i)=='0'){
                if(s.charAt(i-1)=='1'||s.charAt(i-1)=='2'){
                    num[i]=num[i-2];
                }else {
                    return 0;
                }
            }else {
                //        xxxxxx b
                num[i]=num[i-1];
                //              x xxx(1|2)b
                if(s.charAt(i-1)!='0'&&Integer.parseInt(s.substring(i-1,i+1))<=26){
                    num[i]=num[i]+num[i-2];
                }
            }

        }
        return num[len-1];
    }
}
