package pers.zylai.algorithm.sort.basesort;

import pers.zylai.algorithm.sort.utils.SortTestUtil;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/08/07/14:18
 * @Description:
 * 插入排序
 */
public class Code03_InsertionSort {

    public static void insertSort(int[] arr){
        if(arr == null || arr.length < 2){
            return ;
        }
        for (int i = 1; i < arr.length; i++) {
            /**
             * 使 [0,i]上有序
             * 每次j=i，向前插入排序，从而保证0 ~ i上有序
             */
            for(int j=i; j >= 1 && arr[j] < arr[j-1]; j--){
                swap(arr,j,j-1);
            }
        }
    }

    public static void swap(int[] arr,int i ,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        SortTestUtil.run(500000,new Code03_InsertionSort(),"insertSort");
        System.out.println(System.currentTimeMillis()-start);
    }
}
