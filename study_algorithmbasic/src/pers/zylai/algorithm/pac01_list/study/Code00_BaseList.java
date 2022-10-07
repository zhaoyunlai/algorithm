package pers.zylai.algorithm.pac01_list.study;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/08/08/10:44
 * @Description:
 */
public class Code00_BaseList {
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", next=" + next +
                    '}';
        }
    }

    public static void printLinkedList(Node node){
        System.out.print("Linked List: ");
        while(node!=null){
            System.out.print(node.value+" ");
            node = node.next;
        }
    }

    public static void main(String[] args) {
        Node head = new Node(29);
        head.next = new Node(9292);
        head.next.next = new Node(-2394);
//        System.out.println(head);
        printLinkedList(head);
    }

}
