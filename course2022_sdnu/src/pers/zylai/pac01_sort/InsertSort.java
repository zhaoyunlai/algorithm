package pers.zylai.pac01_sort;

import java.util.Random;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/09/24/17:09
 * @Description: 插入排序
 */
public class InsertSort {
    
    public static void insertSort(int[] arr){
        //如果数组为空，或者数组长度为1，直接返回
        if(arr == null || arr.length < 2){
            return ;
        }

        for (int i = 1; i < arr.length; i++) {
            //进行条件判断，如果后面的数大于前面的数就进行交换
            for(int j = i-1; j >= 0 && arr[j+1] < arr[j]; j--){
                swap(arr,j,j+1);
            }
        }
    }

    public static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        //实例化Random类
        Random random = new Random();
        //设置随机种子
        random.setSeed(71212L);
        int size  = 660000;
        //根据种子获取一个长度为10000的数组
        int[] arr = random.ints(size).toArray();

        //开始插入排序
        //记录开始时间戳
        long start = System.currentTimeMillis();
        insertSort(arr);
        //排序完成，记录结束时间戳
        long end = System.currentTimeMillis();

        System.out.println("排序长度为"+size+"的数组，所用时间为："+(end-start)+"毫秒");

    }
    
}
