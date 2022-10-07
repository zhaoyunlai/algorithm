package pers.zylai.algorithm.pac01_list.node;

import java.util.Scanner;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/08/09/16:45
 * @Description:
 */
public class NodeUtils {

    public static Scanner scanner = new Scanner(System.in);

    public static void printLinkedList(Node head){
        System.out.print("LinkedList: ");
        while(head!=null){
            System.out.print(head.value+" ");
            head = head.next;
        }
        System.out.println();
    }

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
}
