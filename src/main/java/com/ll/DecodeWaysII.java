package com.ll;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by liaoli
 * date: 2018/7/12
 * time: 8:27
 */
public class DecodeWaysII {
    public static void main(String[] args) {
        /*System.out.println(numDecodings("*"));
        System.out.println(numDecodings("1*"));
        System.out.println(numDecodings("*1"));
        System.out.println(numDecodings("*1*1"));
        System.out.println(numDecodings("*1*1*0"));
        System.out.println(numDecodings("104"));
        System.out.println(numDecodings("**"));
        System.out.println(numDecodings("********************"));*/

    }
    /**
     * Input: "*"
     * Output: 9
     * Explanation: The encoded message can be decoded to the string: "A", "B", "C", "D", "E", "F", "G", "H", "I".
     * Input: "1*"
     * Output: 9 + 9 = 18
     *
     *                xx0
     *                xx*
     *                  b
     * @param s
     * @return
     */
    public static int numDecodings(String s) {
        if(s==null||s.length()==0||s.startsWith("0"))
            return 0;
        String s1="$"+s;
        int len=s1.length();
        long[]num=new long[len];
        num[0]=1;
        if('*'==s1.charAt(1)){
            num[1]=9;
        }else {
            num[1]=1;
        }
        for(int i=2;i<len;i++){
            if(s1.charAt(i)=='0'){
                if(s1.charAt(i-1)=='0'){
                    return 0;
                }else if(s1.charAt(i-1)=='*'){
                    num[i]=2*num[i-2];//10  20
                }else if(s1.charAt(i-1)=='1'||s1.charAt(i-1)=='2') {
                    num[i]=num[i-2];
                }else {
                    return 0;
                }
            }else if(s1.charAt(i)=='*'){
                num[i]=num[i-1]*9;
                if(s1.charAt(i-1)=='1'){
                    num[i]+=num[i-2]*9;
                }else if(s1.charAt(i-1)=='2'){
                    num[i]+=num[i-2]*6;
                }else if(s1.charAt(i-1)=='*'){
                    num[i]+=num[i-2]*15;
                }
            }else {//1-9
                num[i]=num[i-1];

                if(s1.charAt(i-1)=='*'){
                    if(s1.charAt(i)<'7'){
                        num[i]+=num[i-2]*2;
                    }else {
                        num[i]+=num[i-2];
                    }
                }else {
                    int lastTwo=Integer.parseInt(s1.substring(i-1,i+1));
                    if(s1.charAt(i-1)!='0'&&lastTwo>0&&lastTwo<27){
                        num[i]+=num[i-2];
                    }
                }
            }
            num[i]%=1000000007;
        }
        System.out.println(num[len-1]%(Math.pow(10,9))+7);
        return (int) (num[len-1]%(Math.pow(10,9) + 7));
    }
}
