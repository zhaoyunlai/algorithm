package pers.zylai.algorithm.list.mycode;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/08/08/11:00
 * @Description: 链表的结点
 */
public class Node {
    public int value;
    public Node next;

    public Node(int value){
        this.value = value;
    }

    public void printLinkedList(){
        System.out.print("Linked List: ");
        Node cur = this;
        while(cur!=null){
            System.out.print(cur.value+" ");
            cur = cur.next;
        }
        System.out.println();
    }
}
