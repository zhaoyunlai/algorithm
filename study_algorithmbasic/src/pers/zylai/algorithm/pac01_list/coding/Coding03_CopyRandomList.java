package pers.zylai.algorithm.pac01_list.coding;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/10/14/16:14
 * @Description: 拷贝带有随机指针的链表
 *  测试链接 : https://leetcode.cn/problems/copy-list-with-random-pointer/
 */
public class Coding03_CopyRandomList {

    public static class Node{
        public int value;
        public Node next;
        public Node random;

        public Node(int value){
            this.value = value;
        }

        public Node(){

        }
    }

    //借助容器实现，这样的话，空间复杂度会有点高，但是很容易想到，时间复杂度可以接收，笔试题使用这种方法
    public static Node copyRandomList1(Node head){
        Node cur = head;
        HashMap<Node,Node> map = new HashMap<>();
        //先将拷贝节点放到哈希表中
        while(cur != null){
            map.put(cur,new Node(cur.value));
            cur = cur.next;
        }

        //走一遍，将随机指针加上，串起来
        cur = head;
        while(cur != null){
            Node node = map.get(cur);
            node.random = map.get(cur.random);
            node.next = map.get(cur.next);
            cur = cur.next;
        }

        return map.get(head);
    }

    //不使用容器实现，节省空间复杂度，借助原来的链表实现
    public static Node copyRandomList2(Node head){
        if(head == null){
            return null;
        }
        Node cur = head;
        Node temp = null;
        //拷贝节点插入到原来的结点后面
        while(cur != null){
           // 记录下一个结点
           temp = cur.next;
           //创建克隆结点
           cur.next = new Node(cur.value);
           //让克隆结点的next指向temp
           cur.next.next = temp;
           cur = temp;
        }

        //一次取出一对结点，拷贝随机指针
        cur = head;
        while(cur != null){
            //注意，cur的next肯定有值，next为拷贝结点
            temp = cur.next.next;
            //需要判断随机指针是否为空
            cur.next.random = cur.random == null ? null : cur.random.next;
            cur = temp;
        }
        //从链表中抽离出拷贝结点，将链表复原
        cur = head;
        Node copy = new Node();
        //拷贝结点的头指针
        Node copyHead = head.next;
        while(cur != null){
            //cur的next结点必不为空
            temp = cur.next.next;
            //连接copy结点
            copy.next = cur.next;
            copy = copy.next;

            //连接原来的链表
            cur.next = temp;
            cur = temp;
        }

        return copyHead;
    }

    public static void printRandomList(Node head){
        Node cur = head;
        while(cur != null){
            System.out.print("value:"+cur.value);
            if(cur.random != null){
                System.out.print("\trandom:"+cur.random.value);
            }
            System.out.println();
            cur = cur.next;
        }
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);

        head.next = n2;
        head.random = n4;

        n2.next = n3;
        n2.random = head;

        n3.next = n4;
        n3.random = n5;

        n4.next = n5;
        n4.random = n2;

        n5.next = null;
        n5.random = n3;

        printRandomList(head);
        Node res1 = copyRandomList2(head);
        System.out.println("---");
        printRandomList(res1);
        printRandomList(head);


    }


}
