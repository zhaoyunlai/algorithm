package pers.zylai.leetcode.link.node;

import java.util.Scanner;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/08/09/16:45
 * @Description:
 */
public class ListNodeUtils {

    public static Scanner scanner = new Scanner(System.in);

    /**
     * 打印链表
     * @param head 头结点
     */
    public static void printLinkedList(ListNode head){
        System.out.print("LinkedList: ");
        while(head!=null){
            System.out.print(head.val +" ");
            head = head.next;
        }
        System.out.println();
    }

    /**
     * 根据用户输入创建链表
     * @return
     */
    public static ListNode createLinkedList(){
        System.out.print("Please input the number of Nodes: ");
        int num = scanner.nextInt();
        ListNode order = new ListNode();
        ListNode pis = order;
        System.out.println("please input the nodes: ");
        for (int i = 0; i < num; i++) {
            int value = scanner.nextInt();
            ListNode listNode = new ListNode(value);
            pis.next = listNode;
            pis = listNode;
        }
        return order.next;
    }

    /**
     * 创建size大小的有序链表
     * @param size 链表的规格
     * @return 头结点
     */
    public static ListNode createOrderLinkedList(int size){
        if(size < 1){
            return null;
        }
        ListNode head = new ListNode(1);
        ListNode finalHead = head;
        for (int i = 2; i <= size; i++) {
            ListNode listNode = new ListNode(i);
            head.next = listNode;
            head = listNode;
        }
        return finalHead;
    }
}
