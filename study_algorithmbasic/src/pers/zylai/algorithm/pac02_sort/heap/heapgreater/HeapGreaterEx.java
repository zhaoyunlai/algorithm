package pers.zylai.algorithm.pac02_sort.heap.heapgreater;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/10/02/16:10
 * @Description:
 * 手写加强堆，练习
 */
public class HeapGreaterEx<T> {
    //存放堆元素的动态数组
    private ArrayList<T> heap;
    //反向索引表
    private HashMap<T,Integer> indexMap;
    //堆的大小
    private int heapSize;
    //比较器
    private Comparator<? super T> comparator;

    public HeapGreaterEx(Comparator<? super T> comparator){
        this.comparator = comparator;
        this.heap = new ArrayList<>();
        this.indexMap = new HashMap<>();
        this.heapSize = 0;
    }

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

    //添加一个元素
    public void push(T obj){
        heap.add(obj);
        indexMap.put(obj,heapSize);
        heapInsert(heapSize++);
    }

    //弹出堆顶的元素
    public T pop(){
        //记录堆顶的元素
        T t = heap.get(0);
        //交换堆顶的元素和末尾的元素
        swap(0,--heapSize);
        //两个表中都移除堆顶元素
        heap.remove(t);
        indexMap.remove(t);
        //向下调整堆
        heapify(0);
        return t;
    }

    //调整堆结构，就是堆中某个元素的属性变化了，需要进行调整
    public void resign(T obj){
        Integer index = indexMap.get(obj);
        heapInsert(index);
        heapify(index);
    }


    //删除某一个元素
    public void remove(T obj){
        //获取堆最后一个元素待替换
        T replace = heap.get(heapSize - 1);
        //获取obj的索引
        Integer index = indexMap.get(obj);
        indexMap.remove(obj);
        heap.remove(--heapSize);
        //如果删除的元素不是末尾的元素
        if(replace != obj){
            //新增反向索引表的内容
            indexMap.put(replace,index);
            //更新堆的数组
            heap.set(index,replace);
            //删除之后在调整堆
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


    //向上调整堆结构
    private void heapInsert(int index){
        //如果父结点小于子节点，就交换
        while(comparator.compare(heap.get((index-1)/2),heap.get(index))<0){
            swap((index-1)/2,index);
            index = (index-1)/2;
        }
    }

    //向下调整堆结构
    private void heapify(int index){
        int left = index * 2 + 1;
        while(left < heapSize){
            //使用比较器比较，比较器中小的元素放在上面
            int largest = left+1 < heapSize && comparator.compare(heap.get(left+1),heap.get(left))<0 ? left+1 : left;
            if(comparator.compare(heap.get(index),heap.get(largest))<=0){
                break;
            }
            swap(largest,index);
            index = largest;
            left = index*2+1;
        }
    }

    //交换两个元素，交换的同时更新反向索引表
    private void swap(int i,int j){
        T t1 = heap.get(i);
        T t2 = heap.get(j);
        //交换
        heap.set(i,t2);
        heap.set(j,t1);
        //更新反向索引
        indexMap.put(t1,j);
        indexMap.put(t2,i);
    }

}
