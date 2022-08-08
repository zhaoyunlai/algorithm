package pers.zylai.algorithm.sort.bit;

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
     */
    public static void getEvenTimesNum(int[] arr){
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        System.out.println(eor);
    }

    public static void getTwoEvenTimeNums(int[] arr){
        int eor = 0;
        for (int num : arr) {
            eor ^= num;
        }
        /** 循环结束，假设那两个数为a和b，那么eor = a^b;
         *
         * a和b不相等，那么他们肯定有一个位上的数不同，
         * 就代表着eor上必然有一位的数字为1
         * 下面来找到这个1
         *
         * eor最右侧的1，提取出来
         * eor :     00110010110111000
         * ~eor:     11001101001000111
         * ~eor+1:   11001101001001000
         *
         * 就是补码，所以直接取-eor = ~eor+1
         * eor和 - eor进行与运算，得到最后侧的1
         * eor :     00110010110111000
         * -eor:     11001101001001000
         * &运算:    000000000000001000
         */
        // rightOne :00000000000001000
//		int rightOne = eor &( ~eor +1);//取反加一，相当于-eor
        int rightOne = eor & (-eor); // 提取出最右的1

        /**
         * 找到这个位置上的1之后，那么数组中的数就可以分为两类，一类是这个位置上为0的，一个是为1的
         * 这样a和b肯定不会分到同一类中，所以就可以拆分成了第一问那种情况
         */

        int aOrb = 0;
        for (int i = 0; i < arr.length; i++) {
            if((arr[i]&rightOne) == 0){//即，这个位置上不是1
                //得出的结果不是a就是b
                aOrb ^= arr[i];
            }
        }
        System.out.println(aOrb+","+(aOrb^eor));
    }



    public static void main(String[] args) {
        int[] arr = {2,3,4,5,3,4,5};
        getEvenTimesNum(arr);//2
        int[] arr1 = {2,3,4,5,3,4,5,5};
        getTwoEvenTimeNums(arr1);//2,5
    }
}
