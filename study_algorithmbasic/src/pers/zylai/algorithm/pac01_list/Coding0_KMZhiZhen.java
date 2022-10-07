package pers.zylai.algorithm.pac01_list;

import pers.zylai.algorithm.pac01_list.node.Node;
import pers.zylai.algorithm.pac01_list.node.NodeUtils;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/10/06/19:34
 * @Description:
 * 1. 奇数个结点，返回中点。偶数个结点，返回两个中点中的前一个
 * 2. 偶数个结点返回下中点
 * 3. 返回1中的中点的前一个结点
 * 4. 返回2中的中点的前一个结点
 */
public class Coding0_KMZhiZhen {


    /**
     *
     * @param head 头结点
     * @return 奇数个结点，返回中点。偶数个结点，返回上中点
     */
    public static Node demo1(Node head){
        //如果链表的结点不到三个，直接返回头结点即可
        if(head == null || head.next == null || head.next.next == null){
            return head;
        }

        //结点的个数大于等于三个
        //快慢指针，因为结点大于等于三个，所以初始slow和fast肯定不为空
        Node slow = head.next;
        Node fast = head.next.next;

        //快指针一次两步，慢指针一次一步
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    /**
     *
     * @param head 头结点
     * @return 偶数个结点返回下中点
     */
    public static Node demo2(Node head){
        if(head == null || head.next == null){
            return head;
        }
        //快慢指针
        Node slow = head.next;
        Node fast = head.next;

        while(fast.next != null && fast.next.next != null){
            slow=slow.next;
            fast=fast.next.next;
        }

        return slow;
    }

    /**
     *
     * @param head 头结点
     * @return 返回1中的中点的前一个结点
     */
    public static Node demo3(Node head){
        if(head == null || head.next == null || head.next.next == null){
            return head;
        }

        Node slow = head;
        Node fast = head.next.next;

        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    /**
     *
     * @param head 头结点
     * @return 返回2中的中点的前一个结点
     */
    public static Node demo4(Node head){
        if(head == null || head.next == null){
            return head;
        }

        Node slow = head;
        Node fast = head.next;

        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public static void main(String[] args) {

        Node head = NodeUtils.createLinkedList();
        System.out.println(demo1(head).value);
        System.out.println(demo2(head).value);
        System.out.println(demo3(head).value);
        System.out.println(demo4(head).value);


    }
}
