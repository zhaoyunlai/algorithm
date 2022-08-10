package pers.zylai.algorithm.sort.quick;

import pers.zylai.algorithm.sort.basesort.Code03_InsertionSort;
import pers.zylai.algorithm.sort.utils.SortTestUtil;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/08/09/19:25
 * @Description:
 * 快排2.0和3.0版本
 */
public class QuickSort {

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int[] netherlandsFlag(int[] arr,int L,int R){
        if(L > R){
            return new int[]{-1,-1};
        }
        if(L == R){
            return new int[]{L,R};
        }
        //荷兰国旗问题，以最后的一个元素作为划分值，一步到位分好
        int less = L-1;
        int more = R+1;
        int mid = arr[R];
        for(int i = L ; i < more; i++){
            if(arr[i] < mid){
                //交换小于区域的右侧一个元素和[i],i++
                swap(arr,i,++less);
            }else if(arr[i] > mid){
                //交换大于区域的左侧一个元素和[i],i保持不变
                swap(arr,i,--more);
                i--;
            }
            //等于，i++即可
        }
        return new int[] {less+1,more-1};
    }

    /**
     * 快排2.0，每次划分选取数组最右侧的值
     * @param arr
     */
    public static void quickSort2(int[] arr){
        if(arr==null||arr.length<2){
            return ;
        }
        process2(arr,0,arr.length-1);
    }

    /**
     *
     * @param arr 待排序数组
     * @param L 待排序的数组部分的左侧索引
     * @param R 待排序的数组部分的右侧索引
     */
    public static void process2(int[] arr,int L,int R){
        if(L>=R){
            return;
        }
        int[] equalArea = netherlandsFlag(arr, L, R);
        //左侧排序，排序范围就是左侧小于划分值的
        process2(arr,L,equalArea[0]-1);
        //右侧排序，范围是右侧大于划分值的
        process2(arr,equalArea[1]+1,R);
    }

    /**
     * 快排 3.0，随机选取划分值
     *
     */
    public static void quickSort3(int[] arr){
        if(arr == null || arr.length < 2){
            return ;
        }
        int index = (int)(Math.random()*arr.length);
        swap(arr,index,arr.length-1);
        process3(arr,0,arr.length-1);
    }
    private static void process3(int[] arr,int L,int R){
        if(L >= R){
            return;
        }
        int index = (int)(L + Math.random()*(R-L));
        swap(arr,index,R);
        int[] equalArea = netherlandsFlag(arr, L, R);
        process3(arr,L,equalArea[0]-1);
        process3(arr,equalArea[1]+1,R);
    }

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        //快排在数据量大的时候是比较有优势的
        SortTestUtil.run(50000,800,100,new QuickSort(),"quickSort2");
        System.out.println(System.currentTimeMillis() - start);

        long start1 = System.currentTimeMillis();
        //快排在数据量大的时候是比较有优势的
        SortTestUtil.run(50000,800,100,new QuickSort(),"quickSort3");
        System.out.println(System.currentTimeMillis() - start1);

    }

}
