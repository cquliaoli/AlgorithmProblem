package com.ll;

import util.printUtil;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * https://leetcode.com/problems/create-maximum-number/discuss/77310/Strictly-O(NK)-C++-Solution-with-detailed-explanation?page=1
 * Created by liaoli
 * date: 2018/7/16
 * time: 14:28
 */
public class CreateMaximumNumber {

    public static void main(String[] args) {
        CreateMaximumNumber maximumNumber=new CreateMaximumNumber();
        maximumNumber.testMaxNum();
        maximumNumber.testMerge();
        maximumNumber.test();


    }
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[]max=new int[k];
        int[]temp;
        int len1=nums1.length,len2=nums2.length;
        for(int i=0;i<=k;i++){
            if(i<=len1&&(k-i)<=len2){
                temp=mergeArr(maxNum(nums1,i),maxNum(nums2,k-i));
                if(greater(temp,0,k,max,0,k))
                    max=temp;
            }
        }

        return max;
    }

    public int[]mergeArr(int[]nums1,int[]nums2){
        int len1=nums1.length,len2=nums2.length;
        int len=len1+len2;
        int []ans=new int[len];
        //注意如果某两个元素相等要一直比较到结束
        if(len1==0)
            return nums2;
        if(len2==0)
            return nums1;
        int i=0,j=0,k=0;
        while (i<len1&&j<len2){
            if(nums1[i]<nums2[j]){
                ans[k++]=nums2[j++];
            }else if(nums1[i]>nums2[j]){
                ans[k++]=nums1[i++];
            }else {
                if(greater(nums1,i,len1,nums2,j,len2)){
                    ans[k++]=nums1[i++];
                }else {
                    ans[k++]=nums2[j++];
                }
            }
        }
        while (i<len1){
            ans[k++]=nums1[i++];
        }
        while (j<len2){
            ans[k++]=nums2[j++];
        }
        return ans;
    }

    /**
     * 比较nums1还是nums2大
     * 1.nums1中某个数大于nums2
     * 2.nums1比nums2长
     * @param nums1
     * @param s1
     * @param len1
     * @param nums2
     * @param s2
     * @param len2
     * @return
     */
    public boolean greater(int[]nums1,int s1,int len1,
                           int[]nums2,int s2,int len2){
        while (s1<len1&&s2<len2){
            if(nums1[s1]>nums2[s2])
                return true;
            else if(nums1[s1]<nums2[s2])
                return false;
            s1++;
            s2++;
        }
        //如果s1没结束 即s2先结束，则nums1大 否则num2大
        return s1<len1;
    }
    /**
     * 求数组k个数组成的最大数
     * @param nums1
     * @param k
     * @return
     */
    public int[] maxNum(int[] nums1,int k){
        int[]ans=new int[k];
        int len=nums1.length;
        int j=0;
        for(int i=0;i<len;i++){
            //j>0 且栈中元素加剩余元素大于k且当前元素大于栈中元素
            while (j>0&&nums1[i]>ans[j-1]&&(len-i+j)>k)
                j--;
            if(j<k)
                ans[j++]=nums1[i];
        }
        return ans;
    }

    public void testMaxNum(){
        System.out.println("testMaxNum:");

        int[] nums1 = {3, 4, 6, 5};
        int[] nums3 = {8, 7, 6, 5};
        int[]nums2 ={9, 1, 2, 5, 8, 3};
        printUtil.printArray(maxNum(nums2, 4));
        printUtil.printArray(maxNum(nums2, 3));
        printUtil.printArray(maxNum(nums1, 2));
        printUtil.printArray(maxNum(nums3, 2));
    }
    public void testMerge(){
        System.out.println("testMerge:");
        int[] nums1 = {3, 4, 6, 5};
        int[] nums2 ={9, 1, 2, 5, 8, 3};
        int []ans=mergeArr(maxNum(nums1,2),maxNum(nums2,3));

        int[] nums3 = {3, 8, 6, 9};
        int[] nums4 ={3, 8, 6, 9};
        int []ans1=mergeArr(maxNum(nums3,3),maxNum(nums4,3));

        printUtil.printArray(ans);
        printUtil.printArray(ans1);
    }

    public  void test(){
        System.out.println("test:");
        int[] nums1 = {3, 4, 6, 5};
        int[]nums2 ={9, 1, 2, 5, 8, 3};
        printUtil.printArray(maxNumber(nums1,nums2,5));

        int[] nums11 = {6, 7};
        int[]nums21 ={6, 0, 4};
        printUtil.printArray(maxNumber(nums11,nums21,5));

        int[] nums12= {3, 9};
        int[]nums22 ={8, 9};
        printUtil.printArray(maxNumber(nums12,nums22,3));
    }

}
