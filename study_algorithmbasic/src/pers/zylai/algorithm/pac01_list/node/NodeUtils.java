package pers.zylai.algorithm.pac01_list.node;

import java.util.Scanner;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/08/09/16:45
 * @Description:
 */
public class NodeUtils {

    public static Scanner scanner = new Scanner(System.in);

    /**
     * 打印链表
     * @param head 头结点
     */
    public static void printLinkedList(Node head){
        System.out.print("LinkedList: ");
        while(head!=null){
            System.out.print(head.value+" ");
            head = head.next;
        }
        System.out.println();
    }

    /**
     * 根据用户输入创建链表
     * @return
     */
    public static Node createLinkedList(){
        System.out.print("Please input the number of Nodes: ");
        int num = scanner.nextInt();
        Node order = new Node();
        Node pis = order;
        System.out.println("please input the nodes: ");
        for (int i = 0; i < num; i++) {
            int value = scanner.nextInt();
            Node node = new Node(value);
            pis.next = node;
            pis = node;
        }
        return order.next;
    }

    /**
     * 创建size大小的有序链表
     * @param size 链表的规格
     * @return 头结点
     */
    public static Node createOrderLinkedList(int size){
        if(size < 1){
            return null;
        }
        Node head = new Node(1);
        Node finalHead = head;
        for (int i = 2; i <= size; i++) {
            Node node = new Node(i);
            head.next = node;
            head = node;
        }
        return finalHead;
    }
}
