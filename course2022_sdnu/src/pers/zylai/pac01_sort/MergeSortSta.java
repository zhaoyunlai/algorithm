package pers.zylai.pac01_sort;

import pers.zylai.pac01_sort.utils.SortTestUtil;

import java.util.Random;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/10/05/20:25
 * @Description: 归并排序
 */
public class MergeSortSta {

    //将数组设置为公共变量，节省空间
    public static int[] arr;

    public static int[] help;


    public static void mergesort(){
        if(arr == null || arr.length < 2){
            return ;
        }
        process(0,arr.length-1);
    }

    /***
     * 递归的主体函数，将数组左侧和右侧都排有序之后进行合并
     //* @param arr 待排序的数组
     * @param left 左侧起点（包括）
     * @param right 右侧终点（包括）
     */
    public static void process(int left,int right){
        //终止条件
        if(left>=right){
            return ;
        }
        //int mid = (left+right)/2;
        //这里使用位运算计算终点，是为了防止数组过大，left+right直接溢出
        int mid = left +((right-left)>>1);

        //进行递归，先让左侧有序
        process(left,mid);

        //再让右侧有序
        process(mid+1,right);

        //合并
        merge(left,right,mid);

    }


    /**
     *
     //* @param arr 数组
     * @param left 左侧边界
     * @param right 右侧边界
     * @param mid 中点
     */
    public static void merge(int left,int right,int mid){
        //辅助数组
        help = new int[right-left+1];
        //左指针，指向数组左侧
        int p1 = left;
        //右指针指向右侧数组的起点
        int p2 = mid+1;
        //辅助数组的起点
        int i = 0;
        while(p1<=mid && p2<=right){
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }
        //上面这个循环结束了之后，只有肯定有一个指针没有走向终点，将其走向终点即可
        while(p1<=mid){
            help[i++] = arr[p1++];
        }

        while(p2<=right){
            help[i++]=arr[p2++];
        }

        //将排序后的结果写回2原数组
        for(i = 0; i < help.length;i++){
            arr[left+i] = help[i];
        }

    }

    public static void main(String[] args) {
        //实例化Random类
        Random random = new Random();
        //设置随机种子
        random.setSeed(71212L);
        int size  = 390000000;
        //根据种子获取一个长度为size的数组
        arr = random.ints(size).toArray();

        //开始堆排序
        //记录开始时间戳
        long start = System.currentTimeMillis();
        mergesort();
        //排序完成，记录结束时间戳
        long end = System.currentTimeMillis();

        System.out.println("归并排序，排序长度为"+size+"的数组，所用时间为："+(end-start)+"毫秒");
        //SortTestUtil.run(10000,new MergeSort(),"mergesort");
    }
}
