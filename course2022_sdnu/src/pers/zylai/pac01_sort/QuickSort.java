package pers.zylai.pac01_sort;

import java.util.Random;


/**
 * @Author: Zhao YunLai
 * @Date: 2022/10/05/20:58
 * @Description:
 */
public class QuickSort {

    /***
     * 荷兰国旗问题，快排的分治思想
     * 这里随机选取一个数作为参考值，返回相等的区间范围
     * @param arr 数组
     * @param left 左侧
     * @param right 右侧
     * @return 相等的区间
     */
    public static int[] netherlandsFlag(int[] arr,int left,int right){
        if(left > right ){
            return new int[] {-1,-1};
        }
        if(left == right){
            return new int[] {left,right};
        }
        //随机选取划分值
        int num = arr[(int)(left+Math.random()*(right-left))];
        //小于区域和大于区域的边界
        int small = left-1, big = right+1;
        for (int i = left; i < big; i++) {
            if(arr[i] < num){
                //如果[i]小于num，交换[i]和小于区域的右侧一个数，小于区域右扩一位
                swap(arr,i,++small);
            }else if(arr[i] > num){
                //如果[i]大于num，交换[i]和大于区域的左侧一个数，大于区域左扩一位，i保持不变
                swap(arr,i,--big);
                i--;
            }
        //    如果相等，i++即可
        }
        return new int[] {small+1,big-1};
    }

    /***
     * 快排的递归函数
     * @param arr 数组
     * @param left 左边界
     * @param right 右边界
     */
    public static void process(int[] arr,int left,int right){
        if(left >= right){
            return ;
        }
        //进行荷兰国旗问题的划分
        int[] equalArea = netherlandsFlag(arr, left, right);
        //左侧区域继续排序
        process(arr,left,equalArea[0]-1);
        //右侧区域排序
        process(arr,equalArea[1]+1,right);
    }

    public static void quickSort(int[] arr){
        if(arr == null || arr.length < 2){
            return ;
        }
        process(arr,0,arr.length-1);
    }


    //交换
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
        //int size  = 705000000;
        int size  = 650000;
        //根据种子获取一个长度为size的数组
        int[] arr = random.ints(size).toArray();

        //开始堆排序
        //记录开始时间戳
        long start = System.currentTimeMillis();
        quickSort(arr);
        //排序完成，记录结束时间戳
        long end = System.currentTimeMillis();

        System.out.println("快速排序，排序长度为"+size+"的数组，所用时间为："+(end-start)+"毫秒");
    }
}
