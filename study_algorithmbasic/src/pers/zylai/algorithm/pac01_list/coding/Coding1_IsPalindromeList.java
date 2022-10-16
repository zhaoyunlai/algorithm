package pers.zylai.algorithm.pac01_list.coding;

import pers.zylai.algorithm.pac01_list.node.Node;
import pers.zylai.algorithm.pac01_list.node.NodeUtils;

import java.util.Stack;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/10/06/20:29
 * @Description:
 * 判断是否为回文链表
 */
public class Coding1_IsPalindromeList {


    /**
     * 判断是否为回文链表，使用栈结构，空间复杂度为O(n/2)，复杂度不是很好
     * @param head 头结点
     * @return 是否为回文结构
     */
    public static boolean isPalindromeListStack(Node head){
        if(head == null || head.next == null){
            return true;
        }
        Node fast = head;
        Node slow = head;

        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        //此时slow为下中点，从slow开始往下读，每次都把一个结点压栈
        Stack<Integer> stack = new Stack<>();
        while(slow != null){
            stack.push(slow.value);
            slow = slow.next;
        }

        //开始从头比较前半段和后半段逆序是否相等
        fast = head;
        boolean flag = true;
        while(fast != null && !stack.isEmpty()){
            if(fast.value != stack.pop()){
                flag = false;
                break;
            }
            fast = fast.next;
        }

        return flag;
    }

    /**
     * 判断是否为回文结构，这里使用空间复杂度为O（1）的方法，就是将后半段链表进行反转
     * @param head 表头
     * @return 是否为回文链表
     */
    public static boolean isPalindromeList(Node head){
        if(head == null || head.next == null){
            return true;
        }
        Node fast = head.next;
        Node slow = head;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        //slow为上中点
        Node preMidNode = slow;
        //开始反转下半段链表
        slow = slow.next;
        preMidNode.next = null;
        fast = slow.next;
        Node temp = null;
        slow.next = null;
        while(fast != null){
            temp = fast.next;
            fast.next = slow;
            slow = fast;
            fast = temp;
        }
        //下半段链表反转完毕，此时下半段头结点为slow
        //记录最后的结点
        Node tail = slow;

        //下面开始判断是否为回文链表
        fast = head;
        boolean flag = true;
        while(fast != null && slow != null){
            if(fast.value != slow.value){
                flag = false;
                break;
            }
            fast = fast.next;
            slow = slow.next;
        }

        //无论这个链表是否为回文链表，还需要将其恢复
        slow = tail;
        fast = slow.next;
        slow.next = null;
        while(fast != null){
            temp = fast.next;
            fast.next = slow;
            slow = fast;
            fast = temp;
        }
        //将前后两段拼上
        preMidNode.next = slow;

        return flag;
    }

    public static void main(String[] args) {
        Node head = NodeUtils.createLinkedList();
        System.out.println(isPalindromeList(head));

        System.out.println(isPalindromeListStack(head));
    }
}
