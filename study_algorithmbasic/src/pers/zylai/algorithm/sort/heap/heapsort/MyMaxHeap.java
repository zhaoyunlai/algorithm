package pers.zylai.algorithm.sort.heap.heapsort;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/08/15/16:38
 * @Description: 自己写的大根堆，主要是heapInsert和heapify操作
 */
public class MyMaxHeap {

    //元素最大个数
    private final int limit;
    //堆的大小
    private int heapSize;
    //存放元素的数组
    private final int[] heap;

    public MyMaxHeap(int limit) {
        this.limit = limit;
        this.heapSize = 0;
        this.heap = new int[limit];
    }

    public void add(int num){
        if(heapSize >= limit){
            throw new RuntimeException("超出堆限制的最大结点数量:"+limit);
        }
        heap[heapSize] = num;
        heapInsert(heapSize++);
    }

    /***
     * 返回最大值，并把最大值删掉，保证剩下的部分仍然是大根堆
     * @return 最大值
     */
    public int pop(){
        if(isEmpty()){
            throw new RuntimeException("当前堆内没有元素");
        }
        int res = heap[0];
        swap(0,--heapSize);
        heapify(0);
        return res;
    }

    public boolean isEmpty(){
        return heapSize == 0;
    }


    //新加进来的数，现在停在了数组的index位置，创建大根堆向上比较并以移动
    private void heapInsert(int index){
        //如果index=0，(index-1)/2 = 0
        //所以这个判断条件不需要担心越界
        //代表着，如果干不掉自己的父结点或者到0了就停止了
        while(heap[index] > heap[(index-1)/2]){
            swap(index,(index-1)/2);
            index = (index-1)/2;
        }
    }

    //将index位置的数，向下比较
    private void heapify(int index){
        //先拿到左孩子结点
        int left = 2*index +1;
        //判断左孩子是否存在
        while(left < heapSize){
            //这里如果右孩子存在且大于左孩子，那么largest就取右孩子，否则为左孩子
            int largest = left+1 < heapSize && heap[left+1] > heap[left] ? left+1 : left;
            //如果父结点比较大，那就不用下沉了，结束循环
            if(heap[largest] < heap[index]){
                break;
            }
            //孩子结点大，交换位置，继续下沉
            swap(largest,index);
            index = largest;
            left = 2*index+1;
        }
    }

    private void swap(int i,int j){
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
}
