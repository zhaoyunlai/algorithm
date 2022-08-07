package pers.zylai.algorithm.sort.p02;

import pers.zylai.algorithm.sort.utils.SortTestUtil;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/08/07/12:30
 * @Description:
 */
public class Code02_BubbleSort {

    public static void bubbleSort(int[] arr){
        if(arr == null || arr.length < 2){
            return ;
        }
        for(int i = arr.length -1;i > 0;i--){
            /**
             * 0 ~ N-1 挨个交换，把最大值移到N-1位置上
             * 0 ~ N-2 挨个交换，把最大值移到N-2位置上
             * 0 ~ N-3 挨个交换，把最大值移到N-3位置上
             */
            for(int j = 0; j < i;j++){
                if(arr[j]>arr[j+1]){
                    swap(arr,j,j+1);
                }
            }
        }
    }

    public static void swap(int[] arr,int i ,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {

        SortTestUtil.run(500000,new Code02_BubbleSort(),"bubbleSort");
    }
}
