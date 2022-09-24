package pers.zylai.algorithm.pac02_sort.heap.heapsort;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/08/15/19:57
 * @Description:
 *
 * 已知一个几乎有序的数组。几乎有序是指，如果把数组排好顺序的话，每个元素移动的距离一定不超过k，并且k相对于数组长度来说是比较小的。
 *
 * 请选择一个合适的排序策略，对这个数组进行排序。
 *
 * 他的意思就是，排序之后，元素移动的距离在k之内
 *
 * 解决思路：移动距离不超过k，就从0开始，构建一个0-K的小根堆，然后将小根堆最小值放到0上，再将k+1位置上的数入堆，然后最小值出堆放到1位置上
 * 如此就可
 */
public class SortArrayDistanceLessK {

    public static void sortArrayDistanceLessK(Integer[] arr,int k){
        if(arr == null || arr.length < k){
            return;
        }
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < k+1; i++) {
            priorityQueue.add(arr[i]);
        }
        for (int i = k+1; i < arr.length; i++) {
            arr[i-k-1] = priorityQueue.poll();
            priorityQueue.add(arr[i]);
        }
        for (int i = arr.length-k-1; i <arr.length; i++) {
            arr[i] = priorityQueue.poll();
        }
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{3,4,1,2,5};
        sortArrayDistanceLessK(arr,2);
        System.out.println(Arrays.toString(arr));
    }
}
