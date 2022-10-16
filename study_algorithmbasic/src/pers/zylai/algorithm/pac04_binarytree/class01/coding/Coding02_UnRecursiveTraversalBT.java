package pers.zylai.algorithm.pac04_binarytree.class01.coding;

import java.util.Stack;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/10/16/11:23
 * @Description: 二叉树的非递归遍历
 */
public class Coding02_UnRecursiveTraversalBT {
    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node(int v) {
            value = v;
        }
    }

    //先序遍历
    public static void pre(Node head){
        if(head == null){
            return ;
        }
        Stack<Node> s = new Stack<>();
        s.push(head);
        Node cur = null;
        while(!s.isEmpty()){
            //打印栈顶
            cur = s.pop();
            System.out.println(cur.value);
            //先压右孩子
            if(cur.right != null){
                s.push(cur.right);
            }
            if(cur.left != null){
                s.push(cur.left);
            }
        }
    }

    //中序遍历
    public static void in(Node head){
        if(head == null){
            return;
        }
        Stack<Node> s = new Stack<>();
        s.push(head);
        Node cur = head;
        while(!s.isEmpty()){
            if(cur.left != null){
                s.push(cur.left);
                cur = cur.left;
            }else{
                cur = s.pop();
                System.out.println(cur.value);
                if(cur.right != null){
                    s.push(cur.right);
                    cur = cur.right;
                }
            }
        }

    }

    //后续遍历
    public static void pos(Node head){
        if(head == null){
            return ;
        }
        Stack<Node> s1 = new Stack<>();
        Stack<Node> s2 = new Stack<>();

        Node cur = null;
        s1.push(head);
        while(!s1.isEmpty()){
            cur = s1.pop();
            //不打印，而是加入另外一个栈
            s2.push(cur);
            if(cur.left != null){
                s1.push(cur.left);
            }
            if(cur.right != null){
                s1.push(cur.right);
            }
        }
        //此时压栈完成，往s2中压的顺序是头右左，那么出栈遍历的顺序就是左右头
        while(!s2.isEmpty()){

            System.out.println(s2.pop().value);
        }
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);


        System.out.println("先序遍历");
        pre(head);

        System.out.println("中序遍历");
        in(head);

        System.out.println("后续遍历");
        pos(head);
    }
}
