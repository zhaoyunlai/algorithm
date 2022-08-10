package pers.zylai.algorithm.sort.basesort;

import pers.zylai.algorithm.sort.utils.SortTestUtil;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/08/07/10:52
 * @Description:
 */
public class Code01_SelectSort {

    public static void selectSort(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            /**
             * 0 ~ N-1  找到最小值，在哪，放到0位置上
               1 ~ n-1  找到最小值，在哪，放到1 位置上
               2 ~ n-1  找到最小值，在哪，放到2 位置上
             **/
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[minIndex] < arr[j] ? minIndex :j;
            }
            swap(arr,minIndex,i);
        }
    }

    public static void swap(int[] arr,int i ,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        SortTestUtil.run(500000,new Code01_SelectSort(),"selectSort");

        System.out.println(System.currentTimeMillis()-start);

    }


}
