package com.ll.leetcode;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * from:lintCode
 * url:https://www.lintcode.com/problem/dices-sum/description
 * Created by liaoli
 * date: 2019/2/11
 * time: 9:38
 */
public class DicesSum {

    /**
     * @param n an integer
     * @return a list of Map.Entry<sum, probability>
     */
    public List<Map.Entry<Integer, Double>> dicesSum(int n) {
        // Ps. new AbstractMap.SimpleEntry<Integer, Double>(sum, pro)
        // to create the pair
        // n~6n  f[i][j]=f[i-1][j-k]|1<=k<=6  前i个骰子摇出j的方式数
        // f[0][0]=1 f[0][1~6n]=0 f[1~n][0]=0 f[1][1]=f[0][0]=1 f[1][2]=f[0][1]+f[0][0]
        List<Map.Entry<Integer,Double>> res=new ArrayList<Map.Entry<Integer,Double>>();
        long[][] f = new long[n + 1][6*n + 1];
        int i, j, k;
        f[0][0] = 1;
        for (i =1; i <= 6*n; i++){
            f[0][i] = 0;
            if(i <= n)
                f[i][0] = 0;
        }
        for (i = 1;i <= n; i++){
            for (j = 1;j <= 6 * n ;j++){
                if(j < i){
                    f[i][j] = 0;
                    continue;
                }
                for (k = 1; k <= 6; k++){
                    if (j-k >= 0){
                        f[i][j] += f[i - 1][j - k];
                    }
                }
            }
        }
        for(i = n; i <= 6*n; i++){
            //new AbstractMap.SimpleEntry<Integer, Double>(i,f[n][i]*Math.pow(1/6, n));
            res.add(new AbstractMap.SimpleEntry<Integer, Double>(i, f[n][i] * Math.pow(1.0/6, n)));
            //System.out.print(f[n][i]*Math.pow(1.0/6,n));
            // System.out.print(",");
        }
        return res;
    }
}
