package pers.zylai.algorithm.sort.heap.heapsort;

import pers.zylai.algorithm.sort.utils.SortTestUtil;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/08/15/16:37
 * @Description:
 */
public class HeapSort {

    public static void heapSort(int[] arr){
        if(arr == null || arr.length < 2){
            return ;
        }
        int heapSize = arr.length;
        //从底往上创建大根堆，这时复杂度为O(N)
        for(int i = arr.length -1;i >= 0 ;i--){
            heapify(arr,heapSize,i);
        }

        //复杂度为O(N)*O(logN)
        while(heapSize > 0){
            swap(arr,0,--heapSize);
            heapify(arr,heapSize,0);
            //swap(arr,0,--heapSize);
        }

    }

    //就是将index位置上的数，向上比较，如果他大于父结点，就交换
    public static void heapInsert(int[] arr,int index){
        //如果index=0，(index-1)/2 = 0
        //所以这个判断条件不需要担心越界
        //代表着，如果干不掉自己的父结点或者到0了就停止了
        while(arr[index] > arr[(index-1)/2]){
            swap(arr,index,(index-1)/2);
            index = (index-1)/2;
        }
    }

    //将index位置的元素向下比较，如果孩子结点比他大，就交换
    public static void heapify(int[] arr,int heapSize,int index){
        int left = 2 * index + 1;
        //如果左孩子存在
        while(left < heapSize){
            //如果右孩子存在且右孩子大于左孩子，就让最大值索引等于右孩子，否则还是左孩子
            int largest = left+1 < heapSize && arr[left] < arr[left+1] ? left+1 : left;
            //如果孩子中最大者没有父结点大，就结束循环
            if(arr[largest] <= arr[index]){
                break;
            }
            //否则，就交换父结点和孩子中最大者，并继续循环
            swap(arr,index,largest);
            index = largest;
            left = 2*index + 1;
        }
    }



    private static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }



    public static void main(String[] args) {

        SortTestUtil.run(500000,new HeapSort(),"heapSort");
    }
}
