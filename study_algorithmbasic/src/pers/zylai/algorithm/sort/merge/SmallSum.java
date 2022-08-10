package pers.zylai.algorithm.sort.merge;

import pers.zylai.algorithm.utils.LogarithmicDetectorArr;

import java.util.Arrays;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/08/10/9:08
 * @Description:
 * 小和问题
 */
public class SmallSum {
    public static int smallSum(int[] arr){
        if(arr == null || arr.length < 2){
            return 0;
        }
        return  process(arr,0,arr.length-1);
    }
    public static int process(int[] arr,int L, int R){
        if(L >= R){
            return 0;
        }
        int mid = L +((R-L)>>1);
        return    process(arr,L,mid)
                + process(arr,mid+1,R)
                + merge(arr,L,mid,R);
    }

    /**
     * merge过程中计算右侧比左侧当前元素大的数，
     * 因为两侧都已经排好序了，当知道当前左侧元素比右侧一个元素小，那么该元素会比右侧的元素后面的所有数都小
     */
    public static int merge(int[] arr,int L,int M,int R){
        int[] help = new int[R-L+1];
        int p1 = L;
        int p2 = M+1;
        int i = 0;
        int res = 0;
        while(p1 <= M && p2 <= R){
//            //注意要乘以 R-p2+1，表明后面的数都大于arr[p1]
//            res += arr[p1] < arr[p2] ? arr[p1]*(R-p2+1) : 0;
//            //只有左侧小于了才移动，等于移动右侧
//            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];

            //上面的判断有一次是多余的，这里用ifelse代替改进了
            if(arr[p1] < arr[p2]){
                //注意要乘以 R-p2+1，表明后面的数都大于arr[p1]
                res += arr[p1] * (R-p2+1);
                //只有左侧小于了才移动，等于移动右侧
                help[i++] = arr[p1++];
            }else{
                help[i++] = arr[p2++];
            }
        }
        //将剩下的拷贝
        while(p1<=M){
            help[i++] = arr[p1++];
        }
        while(p2<=R){
            help[i++] = arr[p2++];
        }
        //把排序结果放到原数组中
        for (i = 0;i < help.length; i++){
            arr[L+i] = help[i];
        }
        return res;
    }

    //用于测试的比较方法，暴力解决
    public static int comparator(int[] arr){
        if(arr == null || arr.length < 2){
            return 0;
        }
        int res = 0;
        for (int i = 1; i < arr.length; i++) {
            for(int j = 0;j<i;j++){
                if(arr[j]<arr[i]){
                    res+=arr[j];
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {

        int times = 500000;
        int[] arr = null;
        int[] arr1 = null;
        boolean flag = true;

        int res1 = 0;
        int res2 = 0;

        for (int i = 0; i < times; i++) {
            //无参方法，maxSize和maxValue都为100
             arr = LogarithmicDetectorArr.generateRandomArray();
             arr1 = LogarithmicDetectorArr.copyArray(arr);

             res1 = smallSum(arr1);
             res2 = comparator(arr);
             if(res1 != res2){
                 flag = false;
                 System.out.println("error in "+ (i+1)+" time\n res1 = "+res1+",res2 = "+res2);
                 break;
             }
        }
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr));
        System.out.println(flag?"successful res1 = "+res1+",res2 = "+res2:"error");
    }
}
