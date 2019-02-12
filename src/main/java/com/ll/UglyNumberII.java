package com.ll;

/**
 * Created by liaoli
 * date: 2019/2/12
 * time: 19:08
 */
public class UglyNumberII {

    public static void main(String[] args) {
        UglyNumberII uglyNumberII = new UglyNumberII();
        System.out.println(uglyNumberII.nthUglyNumber(10));
    }
    public int nthUglyNumber1(int n) {
        if(n == 1)
            return 1;
        int[] f = new int[n + 1];
        f[1] = 1;
        int i, j;
        for (i = 2;i <= n; i++){
            int min = Integer.MAX_VALUE;
            for (j = 1; j <= i; j++){
                if (f[j] * 2 > f[i - 1]){
                    min = Math.min(min, f[j] * 2);
                    f[i] = min;
                    break;
                }
                if (f[j] * 3 > f[i - 1]){
                    min = Math.min(min, f[j] * 3);
                }
                if (f[j] * 5 > f[i - 1]){
                    min = Math.min(min, f[j] * 5);
                }
            }
        }
        return f[n];
    }


    /**
     * DP 优化
     * (1) 1×2, 2×2, 3×2, 4×2, 5×2, …
     * (2) 1×3, 2×3, 3×3, 4×3, 5×3, …
     * (3) 1×5, 2×5, 3×5, 4×5, 5×5, …
     * @param n
     * @return
     */
    public int nthUglyNumber(int n) {
        int[] ugly = new int[n];
        ugly[0] = 1;
        int index2 = 0, index3 = 0, index5 = 0;
        int factor2 = 2, factor3 = 3, factor5 = 5;
        for(int i=1;i<n;i++){
            int min = Math.min(Math.min(factor2,factor3),factor5);
            ugly[i] = min;
            if(factor2 == min)
                factor2 = 2*ugly[++index2];
            if(factor3 == min)
                factor3 = 3*ugly[++index3];
            if(factor5 == min)
                factor5 = 5*ugly[++index5];
        }
        return ugly[n-1];
    }
}
