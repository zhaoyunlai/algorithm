package pers.zylai.leetcode.link;

import pers.zylai.leetcode.link.node.ListNode;
import pers.zylai.leetcode.link.node.ListNodeUtils;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/10/09/23:32
 * @Description: 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * https://leetcode.cn/leetbook/read/top-interview-questions-easy/xnnbp2/
 */
public class MergeTwoLists {

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        //如果二者中有一个为空
        if(list1 == null || list2 == null){
            return list1 == null ? list2 : list1;
        }

        ListNode merge = new ListNode();
        ListNode mergeHead = merge;
        //合并
        while(list1 != null && list2 != null){
            if(list1.val < list2.val){
                merge.next = list1;
                list1 = list1.next;
            }else{
                merge.next = list2;
                list2 = list2.next;
            }
            merge = merge.next;
        }
        //把剩下的两个拼接到后面
        merge.next = list1 == null ? list2 : list1;

        return mergeHead.next;
    }

    public static void main(String[] args) {
        ListNode list1 = ListNodeUtils.createOrderLinkedList(10);
        ListNode list2 = ListNodeUtils.createOrderLinkedList(5);
        ListNode listNode = mergeTwoLists(list1, list2);
        ListNodeUtils.printLinkedList(listNode);
    }
}
