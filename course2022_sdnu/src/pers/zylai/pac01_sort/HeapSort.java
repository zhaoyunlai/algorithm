package pers.zylai.pac01_sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/09/24/16:31
 * @Description:
 * 冒泡排序
 */
public class HeapSort {

    public static void heapSort(int[] arr){
        if(arr == null || arr.length < 2){
            return ;
        }
        int heapSize = arr.length;
        //从底往上创建小根堆，这个时候时间复杂度会比从上往下创建小根堆小
        for(int i = arr.length - 1; i >= 0;i--){
            heapify(arr,heapSize,i);
        }
        //进行堆排序，就是将顶部的元素换到最后，然后调整堆
        while(heapSize > 0){
            swap(arr,0,--heapSize);
            heapify(arr,heapSize,0);
        }
    }


    //将index位置的元素向下比较，如果孩子结点比他小，就进行交换
    public static void heapify(int[] arr,int heapSize, int index){
        //得到左孩子的角标
        int left = 2 * index + 1;
        //如果左孩子存在
        while(left < heapSize){
            //这里获取左孩子和右孩子中较大者的下标
            //逻辑是，如果右孩子存在且其值大于于左孩子就使用右孩子的下标，否则用左孩子的
            int largest = left + 1 < heapSize && arr[left] < arr[left+1] ? left+1 :left;
            //如果孩子中较大者没有父结点大，那么就不会交换，结束循环
            if(arr[index] > arr[largest]){
                break;
            }
            //否则，就交换父结点和孩子中的较大者，并以孩子中较大者的下标为index，继续循环
            swap(arr,index,largest);
            index = largest;
            left = 2 * index + 1;
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
        int size  = 650000;
        //根据种子获取一个长度为10000的数组
        int[] arr = random.ints(size).toArray();

        //开始堆排序
        //记录开始时间戳
        long start = System.currentTimeMillis();
        heapSort(arr);
        //排序完成，记录结束时间戳
        long end = System.currentTimeMillis();

        System.out.println("堆排序，排序长度为"+size+"的数组，所用时间为："+(end-start)+"毫秒");
    }
}
