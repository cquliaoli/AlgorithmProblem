package com.ll;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liaoli
 * date: 2019/2/14
 * time: 19:30
 */
public class ArithmeticSlicesIISubsequence {

    public static void main(String[] args) {
        int[] a = new int[]{2,2,3,4};
        int[] b = new int[]{2,4,6,8,10};
        int[] c = new int[]{0,2000000000,-294967296};
        int[] s = new int[]{-2147483648,0,-2147483648};
        System.out.println(numberOfArithmeticSlices(a));
        System.out.println(numberOfArithmeticSlices(b));
        System.out.println(numberOfArithmeticSlices(c));
        System.out.println(numberOfArithmeticSlices(s));
        System.out.println(((long)-294967296-(long)2000000000));
    }

    /**
     * dp
     * 由于是序列，要将a[i] 添加到前面的序列，必然需要记录结尾数字，即记录dp[j] 为以a[j] (j 为 0-(i-1))结尾的 xxx
     * 要判断a[i]结尾的算术序列数，需要知道dp[j]中公差为 a[i] - a[j] 的数量，可以考虑用map存储
     * 故 dp[j] 为以a[j]结尾的公差为 b[k]的map ，即dp[j] = sum{map[b[k],t]} b[k] 为a[j] -a[k]
     * @param A
     * @return
     */
    public static int numberOfArithmeticSlices(int[] A) {
        int n = A.length;
        if(n < 3){
            return 0;
        }
        List<Map<Long, Integer>> f = new ArrayList<Map<Long, Integer>>(n);
        int res = 0;
        f.add(new HashMap<Long,Integer>());
        f.add(new HashMap<Long,Integer>());
        f.get(1).put((long) ((long)A[1] - (long)A[0]),1);
        int i, j;
        for (i = 2; i < n; i++){
            Map<Long,Integer> map = new HashMap<Long, Integer>();
            f.add(map);
            for (j = 0; j < i; j++){
                long d = (long)A[i] - (long)A[j];
                Map<Long,Integer> preMap = f.get(j);
                int r = map.getOrDefault(d, 0);
                Integer pre = preMap.getOrDefault(d, 0);
                res += pre;
                map.put(d, r + pre + 1);
            }
        }
        return res;
    }
}
