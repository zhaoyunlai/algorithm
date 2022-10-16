package pers.zylai.algorithm.pac01_list.study;

import pers.zylai.algorithm.pac01_list.node.Node;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/10/15/15:56
 * @Description:
 * 给定两个可能有环也可能无环的单链表，头节点head1和head2。
 * 请实现一个函数，如果两个链表相交，请返回相交的 第一个节点。
 * 如果不相交，返回null
 */
public class Code05_FindFirstIntersectNode {

    //主函数
    public static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);
        //如果两个链表都没有环,调用对应的无环方法
        if (loop1 == null && loop2 == null) {
            return noLoop(head1, head2);
        }
        //如果两个链表都有环,调用对应的有环方法
        if (loop1 != null && loop2 != null) {
            return bothLoop(head1, loop1, head2, loop2);
        }
        //如果一个有环,一个无环,不可能相交
        return null;
    }

    // 找到链表第一个入环节点，如果无环，返回null
    //这里使用快慢指针解决，没有使用容器，使用容器的话很简单
    public static Node getLoopNode(Node head) {
        //排除三个情况，就是排除前面2个结点的情况
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }

        // n1 慢  n2 快
        Node slow = head.next;
        Node fast = head.next.next;
        while (slow != fast) {
            //快指针如果提前结束，必然无环
            if (fast.next == null || fast.next.next == null) {
                return null;
            }
            //快指针一次走两步，慢指针一次走一步
            fast = fast.next.next;
            slow = slow.next;
        }
        // slow fast  相遇
        fast = head; // 让快指针回到头结点
        //那么两个指针相遇的地方就是入环阶段
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    // 如果两个链表都无环，返回第一个相交节点，如果不相交，返回null
    public static Node noLoop(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node cur1 = head1;
        Node cur2 = head2;
        int n = 0;
        while (cur1.next != null) {
            n++;
            cur1 = cur1.next;
        }
        while (cur2.next != null) {
            n--;
            cur2 = cur2.next;
        }
        if (cur1 != cur2) {
            return null;
        }
        // n  :  链表1长度减去链表2长度的值
        cur1 = n > 0 ? head1 : head2; // 谁长，谁的头变成cur1
        cur2 = cur1 == head1 ? head2 : head1; // 谁短，谁的头变成cur2
        n = Math.abs(n);
        while (n != 0) {
            n--;
            cur1 = cur1.next;
        }
        while (cur1 != cur2) {
            cur1 = cur1.next;
            cur2 = cur2.next;
        }
        return cur1;
    }

    // 两个有环链表，返回第一个相交节点，如果不想交返回null
    public static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        Node cur1 = null;
        Node cur2 = null;
        if (loop1 == loop2) {
            //如果入环结点一样,那么相遇的地方结点肯定是在入环结点之前或者是入环结点
            cur1 = head1;
            cur2 = head2;
            int n = 0;
            while (cur1 != loop1) {
                n++;
                cur1 = cur1.next;
            }
            while (cur2 != loop2) {
                n--;
                cur2 = cur2.next;
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n != 0) {
                n--;
                cur1 = cur1.next;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        } else {
            //如果入环结点不一样,转一圈如果转圈的过程中遇到了loop2,那么返回loop1或者loop2都可以
            cur1 = loop1.next;
            while (cur1 != loop1) {
                if (cur1 == loop2) {
                    return loop1;
                }
                cur1 = cur1.next;
            }
            //如果转了一圈没有遇到loop2,那么就返回空
            return null;
        }
    }

    public static void main(String[] args) {
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        System.out.println(getIntersectNode(head1, head2).value);

        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        System.out.println(getIntersectNode(head1, head2).value);

    }
}
