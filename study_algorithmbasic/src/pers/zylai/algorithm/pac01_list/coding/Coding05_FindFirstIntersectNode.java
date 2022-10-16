package pers.zylai.algorithm.pac01_list.coding;

import pers.zylai.algorithm.pac01_list.node.Node;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/10/15/16:40
 * @Description:
 */
public class Coding05_FindFirstIntersectNode {

    //返回一个链表的入环结点，如果没有环，就返回空
    public static Node getLoopNode(Node head){
        if(head == null || head.next == null){
            return null;
        }
        Node slow = head;
        Node fast = head;
        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
            //如果相遇，就说明有环
            if(fast == slow){
                //让快指针到头结点
                fast = head;
                while(fast != slow){
                    //一次走一步，那么就会在入环结点处相遇
                    fast = fast.next;
                    slow = slow.next;
                }
                return fast;
            }
        }
        return null;
    }

    //如果两个链表都没有环
    public static Node bothNoLoop(Node head1,Node head2){
        if(head1 == null || head2 == null){
            return null;
        }
        Node cur1 = head1;
        Node cur2 = head2;
        //初始n为0
        int n = 0;
        while(cur1.next != null){
            n++;
            cur1 = cur1.next;
        }
        while(cur2.next != null){
            n--;
            cur2 = cur2.next;
        }
        //如果尾结点不同，那么肯定不相交，如果尾结点相同，那么肯定相交
        if (cur1 != cur2) {
            return null;
        }

        //cur1为长的那个链表,cur2为短的那个链表
        if(n < 0){
            cur1 = head2;
            cur2 = head1;
        }else{
            cur1 = head1;
            cur2 = head2;
        }
        n = Math.abs(n);

        while(n > 0){
            cur1 = cur1.next;
            n--;
        }

        while(cur1 != cur2){
            cur1 = cur1.next;
            cur2 = cur2.next;
        }

        return cur1;
    }

    public static Node bothLoop(Node head1,Node loop1,Node head2,Node loop2){
        if(head1 == null || head2 == null){
            return null;
        }
        Node cur1 = head1;
        Node cur2 = head2;
        //如果两个链表的入环结点一样
        if(loop1 == loop2){
            int n = 0;
            while(cur1 != loop1){
                cur1 = cur1.next;
                n++;
            }
            while(cur2 != loop2){
                cur2 = cur2.next;
                n--;
            }

            //让cur1为长的那个链表，cur2为短的
            if(n < 0){
                cur1 = head2;
                cur2 = head1;
            }else{
                cur1 = head1;
                cur2 = head2;
            }
            n = Math.abs(n);

            while(n > 0){
                cur1 = cur1.next;
                n--;
            }
            while(cur1 != cur2){
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        }else{
            //如果两个入环结点不一样，要么没有相交，要么在环内相交
            //如果在环内相交，那么绕环一圈肯定能够遇到，一旦遇到，就返回任意一个入环结点即可|
            //如果遇不到，就表明没有相交，就返回空
            cur1 = loop1.next;
            cur2 = loop2;
            while(cur1 != loop1){
                if(cur1 == cur2){
                    return loop1;
                }
                cur1 = cur1.next;
            }
        }
        return null;
    }

    public static Node getIntersectNode(Node head1,Node head2){
        if(head1 == null || head2 == null){
            return null;
        }
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);

        if(loop1 == null && loop2 == null){
            return bothNoLoop(head1, head2);
        }
        if(loop1 != null && loop2 != null){
            return bothLoop(head1,loop1,head2,loop2);
        }
        return null;
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
