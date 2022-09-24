package pers.zylai.algorithm.pac02_sort.quick;

import pers.zylai.algorithm.utils.LogarithmicDetectorArr;

import java.util.Arrays;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/08/09/18:03
 * @Description: 荷兰国旗问题，学习快排之前要先会这个
 * 一个数组，给定一个值mid，要求把小于mid的值放在左边，大于mid的值放在右边，等于mid的值放在中间。
 */
public class NetherlandsFlag {

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void netherlandsFlag(int[] arr, int mid) {
        if (arr == null || arr.length < 2) {
            return;
        }
        //初始小于和大于的边界内都没有数
        int s = -1; //  < 区域的右边界
        int l = arr.length; // > 区域的左边界
        for (int i = 0; i < l; i++) {
            if (arr[i] < mid) {
                //交换[i]和小于边界的右一个值，并且i++，下一次循环在上面++了
                swap(arr, i, ++s);
            } else if (arr[i] > mid) {
                //交换[i]和大于边界的左一个值，并且i保持不变，因为新换过来一个数需要判断
                swap(arr, i, --l);
                i--;
            }
            //如果[i]==mid ,i++,不需要额外操作
        }

    }

    /**
     * 指定边界的荷兰国旗，用于快排
     * 没啥变化，就是有了边界，划分值为数组最后一个元素
     *
     * @param arr 数组
     * @param L   左边界（索引）
     * @param R   右边界（索引）
     *            划分值为arr[R]
     */
    public static void netherlandsFlag(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        int s = L - 1;//小于区域的边界
        int l = R+1; //大于区域的边界
        int mid = arr[R];//划分值
        for(int i = L; i < l; i++){
            if(arr[i] < mid){
                //交换[i]和小于边界的右一个值，并且i++，下一次循环在上面++了
                swap(arr,i,++s);
            }else if(arr[i] > mid){
                //交换[i]和大于边界的左一个值，并且i保持不变，因为新换过来一个数需要判断
                swap(arr,i,--l);
                i--;
            }
            //如果[i]==mid ,i++,不需要额外操作
        }
    }


    public static void main(String[] args) {
        int[] arr = LogarithmicDetectorArr.generateRandomArray(100, 10);
//        int[] arr = {7,5,2,8,6,0,5,1,5};
        System.out.println("划分值为："+arr[arr.length-1]);
        netherlandsFlag(arr, 0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

}
