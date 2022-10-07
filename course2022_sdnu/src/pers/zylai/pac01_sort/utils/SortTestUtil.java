package pers.zylai.pac01_sort.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/08/07/11:08
 * @Description:
 *
 * 用于直接测试的方法，省去了很多重复的代码
 */
public class SortTestUtil {

    public static final int maxSize = 100;
    public static final int maxValue = 100;

    private static Method getTargetSortMethod(Object obj, String methodName) {
        Class<?> clazz = obj.getClass();

        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (methodName.equals(method.getName())) {
                return method;
            }
        }
        throw new RuntimeException("没有指定的方法，检查一下方法名");
    }

    public static void run(int times,Object obj,String methodName){
        run(times,maxSize,maxValue,obj,methodName);
    }


    public static void run(int times,int maxSize, int maxValue, Object obj, String methodName) {

        //得到目标方法
        Method targetSortMethod = getTargetSortMethod(obj, methodName);

        int[] arr = {};
        int[] arr1 = {};
        int[] arr2 = {};
        boolean flag = true;
        for (int i = 0; i < times; i++) {
            arr = LogarithmicDetectorArr.generateRandomArray(maxSize,maxValue);
            arr1 = LogarithmicDetectorArr.copyArray(arr);
            arr2 = LogarithmicDetectorArr.copyArray(arr);

            //执行方法
            try {
                targetSortMethod.invoke(obj, arr1);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                System.out.println("方法执行失败");
                return;
            }

            LogarithmicDetectorArr.comparator(arr2);
            if (!LogarithmicDetectorArr.isEqual(arr1, arr2)) {
                flag = false;
                System.out.println("error in time:" + i);
                break;
            }
        }
        System.out.println(flag ? "Lucky!!! Test"+times+" times, there is no bug . Successful!" : "苦逼得去debug吧");
        System.out.println("最后一次执行结果如下：");
        LogarithmicDetectorArr.printAllArrays(arr, arr1, arr2);

    }
}
