package pers.zylai.algorithm.sort.p01bit;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/08/07/10:11
 * @Description:
 *
 * 第一问：已知一个数组中只有一种数出现了奇数次，其他所有数都出现了偶数次，
 * 怎么找到这个出现了奇数次的数？
 *
 * 第二问：已知一个数组中有两种数出现了奇数次，其他所有数都出现了偶数次，
 * 怎么找到这两个出现了奇数次的数？
 *
 * 以上两问都要求要求时间O(N)，空间O(1)
 */
public class EvenTimesOddTimes {

    /**
     * 第一问：很简单，声明一个变量eor，初始为0，
     * 遍历数组都异或一边，最终的结果就是这个值
     * @param arr
     */
    public static void getEvenTimesNum(int[] arr){
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        System.out.println(eor);
    }







    public static void main(String[] args) {
        int[] arr = {2,3,4,5,3,4,5};
        getEvenTimesNum(arr);
    }
}
