package com.gsonkeno.algorithms.tree;

/**
 * 二叉查找树
 * @author gaosong
 * @since 2018-09-08
 */
public class BST<Key extends Comparable<Key>, Value> {

    private Node root; //二叉查找树的根节点

    private class Node{
        private Key key;  //键
        private Value value; //值
        private Node left, right; //左右子节点
        private int N; //以该节点为根的子树节点的个数

        public Node(Key key, Value value, int n) {
            this.key = key;
            this.value = value;
            N = n;
        }
    }

    /**
     * 获取二叉查找树的节点个数
     * @return
     */
    public int size(){
        return size(root);
    }

    private int size(Node x){
        return x.N;
    }

    /**
     * 查找二叉树中key所对应的值
     * @param key
     * @return
     */
    public Value get(Key key){
        return get(root,key);
    }

    /**
     * 以x为根节点的子树中查找并返回key所对应的值
     * @param x
     * @param key
     * @return
     */
    private Value get(Node x, Key key){
        if (x == null) return null;
        int compare = key.compareTo(x.key);

        if (compare < 0) return get(x.left,key);
        else if (compare > 0) return get(x.right,key);
        else return x.value;
    }

    /**
     * 若key存在，更新该节点；否则，创建节点
     * @param key
     * @param value
     */
    public void put(Key key, Value value){

    }

    /**
     * 如果key存在以x为根节点的子树中，则更新它的值
     * 否则将以key，value为键值对的新节点插入到该子树中
     * @param x
     * @param key
     * @param value 操作后的以x为根节点的子树
     */
    private Node put(Node x,Key key, Value value){
        if (x == null) return new Node(key,value,1);
        int compare = key.compareTo(x.key);

        if (compare < 0) x.left = put(x.left, key, value);
        else if (compare > 0) x.right = put(x.right, key, value);
        else x.value = value;

        x.N = size(x.left) + size(x.right) + 1;

        return x; //x节点的左右子树可能发生变化
    }
}
