package pers.zylai.algorithm.pac01_list;

import pers.zylai.algorithm.pac01_list.node.Node;
import pers.zylai.algorithm.pac01_list.node.NodeUtils;

import java.util.Stack;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/08/08/10:53
 * @Description:
 * 回文链表的判断
 */
public class Code01_IsPalindromeList {
    //需要n个额外空间
    public static boolean isPalindrome1(Node head){
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        while(cur!=null){
            stack.push(cur);
            cur = cur.next;
        }
        cur = head;
        boolean flag = true;
        while(cur!=null){
            if(cur.value != stack.pop().value){
                flag = false;
                break;
            }
            cur = cur.next;
        }
        return flag;
    }

    /**
     * need n/2 extra space
     *需要额外n/2个空间
     */
    public static boolean isPalindrome2(Node head){
        if(head == null || head.next == null){
            return true;
        }

        /**
         * 慢指针，从头结点开始指向，之后快指针一次走两步，他走一步
         * 让慢指针从next开始是为了使得快指针结束时，慢指针指向中点的下一个结点。或者第二个中点
         */
        Node slow = head.next; //

        Node fast = head;//快指针，一次走两步
        //这里的判断保证快指针走两步都不为空
        while(fast.next!=null && fast.next.next != null){
             slow = slow.next;
             fast = fast.next.next;
        }
        Stack<Node> stack = new Stack<>();
        while(slow!=null){
            stack.push(slow);
            slow = slow.next;
        }
        slow = head;
        boolean flag = true;
        while(!stack.isEmpty()){
            if(slow.value!=stack.pop().value){
                flag = false;
            }
            slow = slow.next;
        }

        return flag;
    }

    /**
     * 第三种方法，就是慢指针走到后半段之后，把后半段链表反转
     * 之后前半段从左往右，后半段从右向左，开始比较
     * 比较结束之后，一定要把链表复原
     *
     * 空间复杂度为O(1)
     */
    public static boolean isPalindrome3(Node head){
        if(head == null || head.next == null){
            return true;
        }

        //开始进行走到中点的操作
        Node fast = head;
        Node slow = head.next;//让慢指针指向初始结点的下一个，
        // 可以保证最终慢指针指向中点的下一个结点或者第二个中点，即指向后半段的第一个结点
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        //此时slow指向后半段的第一个结点
        //开始反转后半段链表
        fast = slow.next;//记录下一个结点
        slow.next = null;//后半段第一个结点的next置空
        Node temp = null;
        while(fast!=null){
            temp = fast.next;//记录fast.next
            fast.next = slow;//反转指向，指向前面的一个结点
            slow = fast;//前面的结点向后移动
            fast = temp;//后面的结点继续向下移动
        }
        //反转之后的结果，
        // 比如反转前：1->2->3->4->3->2->1
        //    反转后：1->2->3->4->3<-2<-1，第二个3指向null

        //从左向右和从右向左开始比较
        boolean flag = true;
        temp = head;
        fast = slow;//slow的值先不改变
        while(fast != null && temp != null){
            if(fast.value != temp.value){
                flag = false;
                break;
            }
            fast = fast.next;//从右向左
            temp = temp.next;//从左向右
        }

        //不论是否为回文链表，都需要把链表复原
        fast = slow.next;
        slow.next = null;
        while(fast!=null){
            temp = fast.next;
            fast.next = slow;//反转指向
            slow = fast;
            fast = temp;
        }
        //这里，就算是把后半段完成了复原，对于中点是否能够连着后半段，
        // 开始反转的时候，中点已经指向了后半段第一个结点

        return flag;
    }

    public static void main(String[] args) {
//        Node head = new Node(1);
//        head.next = new Node(2);
//        head.next.next = new Node(3);
//        head.next.next.next = new Node(2);
//        head.next.next.next.next = new Node(1);


        Node head = NodeUtils.createLinkedList();
        NodeUtils.printLinkedList(head);

        System.out.println("1:"+isPalindrome1(head));
        System.out.println("2:"+isPalindrome2(head));
        System.out.println("3:"+isPalindrome3(head));


        NodeUtils.printLinkedList(head);

    }

}
