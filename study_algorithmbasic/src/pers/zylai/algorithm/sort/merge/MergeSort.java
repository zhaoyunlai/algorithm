package pers.zylai.algorithm.sort.merge;

import pers.zylai.algorithm.sort.utils.SortTestUtil;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/08/09/21:37
 * @Description: 归并排序
 */
public class MergeSort {

    public static void mergeSort(int[] arr){
        if(arr == null || arr.length < 2){
            return ;
        }
        process(arr,0,arr.length-1);
    }

    //递归部分，将arr的[L,R]范围的数据有序
    public static void process(int[] arr,int L,int R){
        if(L >= R){
            return ;
        }
        int mid = L + ((R-L)>>1);
        //左侧有序
        process(arr,L,mid);
        //右侧有序
        process(arr,mid+1,R);
        //合并左侧和右侧
        merge(arr,L,mid,R);

    }

    //merge
    public static void merge(int[] arr,int L,int M,int R){
        int[] help = new int[R-L+1];
        int p1 = L;
        int p2 = M+1;
        int i = 0;
        //在两个指针都不越界的情况下，挨个元素比较大小拷贝到辅助数组中
        while(p1 <= M && p2 <= R){
            //这里使用<=保证稳定性
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        //下面要么p1到头了要么p2到头了，需要把剩下的拷贝进去
        while(p1 <= M){
            help[i++] = arr[p1++];
        }
        while(p2 <= R){
            help[i++] = arr[p2++];
        }

        //将排序结果放回原数组中
        for (i = 0; i < help.length; i++) {
            arr[L+i] = help[i];
        }
    }

    public static void main(String[] args) {
        SortTestUtil.run(500000,new MergeSort(),"mergeSort");
    }
}


