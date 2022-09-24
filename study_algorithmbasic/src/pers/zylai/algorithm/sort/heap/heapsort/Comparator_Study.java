package pers.zylai.algorithm.sort.heap.heapsort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/08/14/20:47
 * @Description:
 */
public class Comparator_Study {

    /**
     * 	compare方法里，遵循一个统一的规范：
     * 	返回负数的时候，认为第一个参数应该排在前面，即认为第一个数小
     * 	返回正数的时候，认为第二个参数应该排在前面，即认为第二个数小
     * 	返回0的时候，认为无所谓谁放前面
     */
    //1、对于基础数据类型，自定义比较器
    public static class Comp1 implements Comparator<Integer>{

        @Override
        public int compare(Integer o1, Integer o2) {
            //正常的逻辑，升序，o1大o1就排前面
//            if(o1 > o2){
//                return 1;
//            }else if(o1 < o2){
//                return -1;
//            }else{
//                return 0;
//            }
            //可以简化为
//            return o1-o2;

            //所以如果想要降序，就是如果o1大，o1排到后面，那么就返回负数
            return o2 - o1;
        }
    }

    /**
     * 不过比较器用的比较多的还是自定义类型，就是自己创建的类
     */
    public static class Student{
        public String name;
        public int id;
        public int age;

        public Student(String name, int id, int age) {
            this.name = name;
            this.id = id;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", id=" + id +
                    ", age=" + age +
                    '}';
        }
    }

    //根据id升序排列
    public static class ComIdAsc implements Comparator<Student>{

        @Override
        public int compare(Student o1, Student o2) {
//            if(o1.id < o2.id){
//                return -1;
//            }else if (o1.id > o2.id){
//                return 1;
//            }else{
//                return 0;
//            }
            //简化为
            return o1.id - o2.id;
        }
    }

    //id从小到大，如果id一样就按照年龄从大到小
    public static class ComIdAscAgeDesc implements Comparator<Student>{
        @Override
        public int compare(Student o1, Student o2) {
//            if(o1.id < o2.id){
//                return -1;
//            }else if(o1.id > o2.id){
//                return 1;
//            }else{//id相等
//                if (o1.age < o2.age){
//                    return 1;
//                }else if(o1.age > o2.age){
//                    return -1;
//                }else{
//                    return 0;
//                }
//            }

            //上面一大坨可以进行简化
            return (o1.id != o2.id) ? (o1.id - o2.id) : (o2.age - o1.age);
        }
    }


    public static void main(String[] args) {
        Integer[] arr = {2,1,3,2,5,3,8,1};
        Arrays.sort(arr,new Comp1());
        System.out.println(Arrays.toString(arr));

        Student student1 = new Student("A", 4, 40);
        Student student2 = new Student("B", 4, 21);
        Student student3 = new Student("C", 3, 12);
        Student student4 = new Student("D", 3, 62);
        Student student5 = new Student("E", 3, 42);
        Student[] students = new Student[] { student1, student2, student3, student4, student5 };

//        Arrays.sort(students,new ComIdAsc());
        Arrays.sort(students,new ComIdAscAgeDesc());
        for (Student student : students) {
            System.out.println(student);
        }

        System.out.println("========TreeMap=========");

        TreeMap<Student,String> treeMap = new TreeMap<>(new ComIdAscAgeDesc());
        treeMap.put(student1,"A");
        treeMap.put(student2,"B");
        treeMap.put(student3,"C");
        treeMap.put(student4,"D");
        treeMap.put(student5,"E");

        for(Student s : treeMap.keySet()){
            System.out.println(s);
        }
    }
}
