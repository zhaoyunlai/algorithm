package pers.zylai.leetcode.link;

import pers.zylai.leetcode.link.node.ListNode;
import pers.zylai.leetcode.link.node.ListNodeUtils;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/10/08/20:23
 * @Description: 删除倒数第n个元素
 *
 * https://leetcode.cn/leetbook/read/top-interview-questions-easy/xn2925/
 */
public class DeleteNthFromEnd {


    //比较傻的一种方法，反转链表
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null ){
            return null;
        }
        if(head.next == null && n == 1){
            return null;
        }
        //反转链表
        ListNode fast = head.next;
        ListNode slow = head;
        ListNode temp = null;
        slow.next = null;
        while(fast != null){
            temp = fast.next;
            fast.next = slow;
            slow = fast;
            fast = temp;
        }
        //记录尾结点
        temp = slow;
        // 反转完成，去删除第n个结点
        if(n == 1){
           slow.next = null;
        }else{
            int num = 2;
            while(slow.next != null){
                if(num == n){
                    slow.next = slow.next.next;
                    break;
                }
                slow = slow.next;
                num++;
            }
        }

        //再将链表反转过来
        fast = temp.next;
        slow = temp;
        slow.next = null;
        while(fast != null){
            temp = fast.next;
            fast.next = slow;
            slow = fast;
            fast = temp;
        }

        return slow;
    }

    /**
     * 快慢指针解决
     */
    public static ListNode removeNthFromEnd1(ListNode head,int n){

        if(head == null || head.next == null){
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        //fast向前移动n步
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        //如果fast为null，即走到尾结点后面那个了，表示删除的是头结点
        if(fast == null){
            return head.next;
        }
        //向下走
        while(fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return head;
    }
    public static void main(String[] args) {
        //ListNode head = ListNodeUtils.createOrderLinkedList(4);
        //ListNodeUtils.printLinkedList(head);
        //
        //ListNodeUtils.printLinkedList(removeNthFromEnd(head,4));

        ListNode head = ListNodeUtils.createOrderLinkedList(3);
        ListNodeUtils.printLinkedList(head);

        ListNodeUtils.printLinkedList(removeNthFromEnd1(head,3));
    }
}
