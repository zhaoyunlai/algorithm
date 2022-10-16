package pers.zylai.algorithm.pac01_list.coding;

import pers.zylai.algorithm.pac01_list.node.Node;
import pers.zylai.algorithm.pac01_list.node.NodeUtils;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/10/08/11:21
 * @Description:
 */
public class Coding01_LinkNodeTest {

    /**
     * 反转链表
     * @param head 头结点
     */
    public static void reverse(Node head){
        if(head == null || head.next == null){
            return ;
        }
        Node fast = head.next;
        Node temp = null;
        Node slow = head;
        //首先让头结点的next为null
        slow.next = null;
        while(fast != null){
            temp = fast.next;
            fast.next = slow;
            slow = fast;
            fast = temp;
        }
        NodeUtils.printLinkedList(slow);
    }

    public static void main(String[] args) {

        Node head = NodeUtils.createOrderLinkedList(1);
        NodeUtils.printLinkedList(head);
        reverse(head);
    }
}
