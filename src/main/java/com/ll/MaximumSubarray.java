package com.ll;

/**
 * Created by liaoli
 * date: 2018/6/20
 * time: 12:32
 */
public class MaximumSubarray {
    public static void main(String[] args) {
        int[]nums=new int[]{-2,1,-3,4,-1,2,1,-5,4};

        System.out.println(maxSubArray(nums));
        System.out.println(divideAndConquer(0,nums.length-1,nums));
    }
    public static int maxSubArray(int[] nums) {
        int len=nums.length;
        if(len==0)
            return 0;
        int max=0;
        int[]maxArr=new int[len];
        maxArr[0]=nums[0];
        max=maxArr[0];
        for(int i=1;i<len;i++){
            maxArr[i]=Math.max(nums[i],nums[i]+maxArr[i-1]);
            if(maxArr[i]>max){
                max=maxArr[i];
            }
        }
        return max;

    }
    public static int divideAndConquer(int i,int j,int[]nums){
        if(i<0||j>nums.length-1||i>j)
            return 0;
        if(i==j)
            return nums[i];
        int mid=(i+j)/2;
        int pre=divideAndConquer(i,mid,nums);
        int suc=divideAndConquer(mid+1,j,nums);
        int max=nums[mid]+nums[mid+1];
        int sum=max;
        for(int k=mid-1;k>=i;k--){
            sum+=nums[k];
            if(sum>max)
                max=sum;
        }
        sum=max;
        for(int k=mid+2;k<=j;k++){
            sum+=nums[k];
            if(sum>max)
                max=sum;
        }
        return Math.max(max,Math.max(pre,suc));
    }
}
