package pers.zylai.leetcode.link;

import pers.zylai.leetcode.link.node.ListNode;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/10/09/23:53
 * @Description: 给你一个链表的头节点 head ，判断链表中是否有环。
 * https://leetcode.cn/leetbook/read/top-interview-questions-easy/xnwzei/
 */
public class HasCycle {
    public static boolean hasCycle(ListNode head){
        if(head == null || head.next == null){
            return false;
        }
        ListNode fast = head;
        ListNode slow = head;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast){
                return true;
            }
        }
        return false;
    }
}
