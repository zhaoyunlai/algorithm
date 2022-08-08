package pers.zylai.algorithm.list.mycode;

import java.util.Stack;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/08/08/10:53
 * @Description:
 */
public class IsPalindromeList {
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
     * 重点，第三个，空间复杂度为O(1)，就是慢指针过了中点之后，中点后面的链表反转
     *
     *
     */
    public static boolean isPalindrome3(Node head){
        if(head == null || head.next == null){
            return true;
        }
        Node slow = head.next;
        Node fast = head;

        while(fast.next !=null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        //此时，slow位于中点的后一个结点或者第二个中点
        //需要使当前slow指向的结点指向为空，然后将后面的链表反转
        slow.next = null;
        while(slow!=null){

        }

        return false;
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(1);

        head.printLinkedList();

        System.out.println("1:"+isPalindrome1(head));
        System.out.println("2:"+isPalindrome2(head));
    }

}
