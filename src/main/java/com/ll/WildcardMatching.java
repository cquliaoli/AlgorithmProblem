package com.ll;

/**
 * Created by liaoli
 * date: 2018/6/20
 * time: 11:47
 */
public class WildcardMatching {
    public static void main(String[] args) {
        System.out.println(isMatch("aa","a"));
        System.out.println(isMatch("aa","aa"));
        System.out.println(isMatch("aa","*"));
        System.out.println(isMatch("cb","?a"));
        System.out.println(isMatch("adceb","*a*b"));
        System.out.println(isMatch("acdcb","a*c?b"));
    }
    public static boolean isMatch(String s, String p) {

        if(s == null || s.length() == 0){
            return p == null || p.length() == 0;
        }
        s="#"+s;
        p="#"+p;
        int slen=s.length(),plen=p.length();
        boolean[][]match=new boolean[plen][slen];
        match[0][0]=true;
        for(int i=1;i<plen;i++){
            for(int j=0;j<slen;j++){
                if((p.charAt(i)<='z'&&p.charAt(i)>='a')
                        ||'?'==p.charAt(i)){
                    if(match[i-1][j]
                            &&j+1<slen
                            &&(p.charAt(i)==s.charAt(j+1)
                            ||'?'==p.charAt(i))){
                        match[i][j+1]=true;
                    }
                }else if(p.charAt(i)=='*'){
                    if(match[i-1][j])
                        match[i][j]=true;
                    if(match[i-1][j]){
                        int k=j;
                        while (++k<slen){
                            match[i][k]=true;
                        }
                    }
                }
            }
        }
        return match[plen-1][slen-1];
    }
}
