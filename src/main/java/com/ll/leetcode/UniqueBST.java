package com.ll.leetcode;

/**
 * Created by liaoli
 * date: 2018/7/12
 * time: 13:58
 */
public class UniqueBST {


    public static void main(String[] args) {
        System.out.println(String.format("input:%s output:%s expected:%s",3,numTrees(3),5));
    }
    /**
     * Input: 3
     * Output: 5
     * Explanation:
     * Given n = 3, there are a total of 5 unique BST's:
     *
     *    1         3     3      2      1
     *     \       /     /      / \      \
     *      3     2     1      1   3      2
     *     /     /       \                 \
     *    2     1         2                 3
     * @param n
     * @return
     */
    public static int numTrees(int n){
        int[][]num=new int[n+1][n+1];
        for(int i=1;i<n+1;i++){
            num[i][i]=1;
        }
        for(int l=2;l<n+1;l++){
            for(int i=1;i+l<=n+1;i++){
                int k=0;
                for(int j=i;j<i+l;j++){
                    if(j==i){
                        k+=num[j+1][i+l-1];
                    }else if(j==i+l-1){
                        k+=num[i][j-1];
                    }else {
                        k+=num[i][j-1]*num[j+1][i+l-1];
                    }
                }
                num[i][i+l-1]=k;
            }
        }
        return num[1][n];
    }
}
