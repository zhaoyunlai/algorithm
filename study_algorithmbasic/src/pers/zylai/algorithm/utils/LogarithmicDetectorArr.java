package pers.zylai.algorithm.utils;

import java.util.Arrays;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/08/03/11:34
 * @Description: 对数器工具类
 */
public class LogarithmicDetectorArr {

    public static final int epoch = 100000;
    public static final int maxSize = 100;
    public static final int maxValue = 100;


    /**
     * 重载方法
     * @return 随机生成数组，采用默认值
     */
    public static int[] generateRandomArray() {
        // Math.random() -> [0,1) 所有的小数，等概率返回一个
        // Math.random() * N -> [0,N) 所有小数，等概率返回一个
        // (int)(Math.random() * N) -> [0,N-1] 所有的整数，等概率返回一个
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())]; // 长度随机
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    /**
     * 产生随机数组
     * @param maxSize 数组最大长度
     * @param maxValue 元素最大值
     * @return 随机数组
     */
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        // Math.random() -> [0,1) 所有的小数，等概率返回一个
        // Math.random() * N -> [0,N) 所有小数，等概率返回一个
        // (int)(Math.random() * N) -> [0,N-1] 所有的整数，等概率返回一个
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())]; // 长度随机
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    /**
     * 系统提供的排序方法
     * @param arr arr
     */
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    /**
     * 深拷贝拷贝数组
     * @param arr 待拷贝的数组
     * @return 拷贝之后的数组
     */
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    /**
     * 比较两个数组是否相同
     * @param arr1 数组1
     * @param arr2 数组2
     * @return 是否相同
     */
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }


    public static void printAllArrays(int[] arr,int[] arr1,int[] arr2){
        if(arr == null || arr1 == null || arr2 == null){
            return ;
        }
        System.out.println("原始数组arr："+Arrays.toString(arr));
        System.out.println("排序数组arr1："+Arrays.toString(arr1));
        System.out.println("排序数组arr2："+Arrays.toString(arr2));
    }

}
