package pers.zylai.algorithm.sort.heap.heapgreater;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/08/16/9:53
 * @Description: 自定义加强堆
 */
public class HeapGreater<T> {
    //存放堆元素的动态数组
    private ArrayList<T> heap;
    //反向索引表
    private HashMap<T,Integer> indexMap;
    //堆大小
    private int heapSize;
    //比较器
    private Comparator<? super T> comparator;

    //构造器，完成初始化操作
    public HeapGreater (Comparator<? super T> comparator){
        this.comparator = comparator;
        this.heapSize = 0;
        this.heap = new ArrayList<>();
        this.indexMap = new HashMap<>();
    }

    //========最基础的操作（不需要用到heapInsert和heapify的）=========
    public boolean isEmpty(){
        return heapSize == 0;
    }

    public int size(){
        return heapSize;
    }

    public boolean contains(T obj){
        return heap.contains(obj);
    }

    //返回堆顶元素
    public T peek(){
        return heap.get(0);
    }

    //=======最基础操作结束========

    //=======核心操作=============
    public void push(T obj){
        heap.add(obj);
        //反向索引表记录
        indexMap.put(obj,heapSize);
        heapInsert(heapSize++);
    }

    public T pop(){
        //记录堆顶的元素
        T top = heap.get(0);
        //交换堆顶的元素和最后一个元素
        swap(0,heapSize-1);
        //从反向索引表和堆中移除原来堆顶的那个元素，并且heapSize--
        indexMap.remove(top);
        heap.remove(--heapSize);
        //对新换上来的元素heapify即可
        heapify(0);
        return top;
    }

    public void resign(T obj){
        Integer index = indexMap.get(obj);
        heapInsert(index);
        heapify(index);
    }

    //这样写是有问题的，如果obj正好是堆最后一个元素，那么在最后heap.get(index)就会越界
    //public void remove(T obj){
    //    //获取obj的索引
    //    Integer index = indexMap.get(obj);
    //    //与堆最后一个元素交换
    //    swap(index,heapSize-1);
    //    //删除堆中和反向索引表中的这个obj元素，并且heapSize--
    //    heap.remove(--heapSize);
    //    indexMap.remove(obj);
    //    //调整新换上来的元素
    //    resign(heap.get(index));
    //}

    public void remove(T obj){
        //获取堆最后一个元素待替换
        T replace = heap.get(heapSize - 1);
        //获取obj的索引
        Integer index = indexMap.get(obj);
        indexMap.remove(obj);
        heap.remove(--heapSize);
        if(replace != obj){
            indexMap.put(replace,index);
            heap.set(index,replace);
            resign(replace);
        }
    }

    //一定不要直接返回this.heap，会在外边破坏
    public List<T> getAllElements(){
        List<T> ans = new ArrayList<>();
        for (T c : heap) {
            ans.add(c);
        }
        return ans;
    }



    //=======核心操作结束=========

    //======heapInsert和heapify以及swap核心方法=========

    private void heapInsert(int index){
        //如果父元素小于当前元素，主要使用比较器比较
        while(comparator.compare(heap.get((index-1)/2),heap.get(index))<0){
            //进行交换，反向索引表也会更新
            swap(index,(index-1)/2);
            index = (index-1)/2;
        }
    }

    private void heapify(int index){
        int left = 2*index +1;
        while(left < heapSize){
            //这里要用比较器比较，比较器中认为小的元素应该方法上面
            int largest = left+1 < heapSize && comparator.compare(heap.get(left+1),heap.get(left))<0 ? left+1 : left;
            if(comparator.compare(heap.get(index),heap.get(largest))<=0){
                break;
            }
            //进行交换，反向索引表也会更新
            swap(index,largest);
            index = largest;
            left = 2*index+1;
        }
    }

    private void swap(int i,int j){
        //首先拿到这两个元素
        T o1 = heap.get(i);
        T o2 = heap.get(j);
        //元素进行交换
        heap.set(i,o2);
        heap.set(j,o1);
        //更新反向索引表
        indexMap.put(o1,j);
        indexMap.put(o2,i);
    }


}
