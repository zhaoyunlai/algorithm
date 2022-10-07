package pers.zylai.algorithm.pac03_tree.trietree;

/**
 * @Author: Zhao YunLai
 * @Date: 2022/10/05/15:48
 * @Description:
 * 前缀树
 */
public class TrieTree {
    //树的结点
    public static class Node{
        //经过此结点的路径数目
        public int pass;
        //以此结点为终点的路径数目
        public int end;
        //下一个结点
        public Node[] nexts;

        public Node(){
            this.pass = 0;
            this.end = 0;
            //一共26个字母
            this.nexts = new Node[26];
        }
    }
}
