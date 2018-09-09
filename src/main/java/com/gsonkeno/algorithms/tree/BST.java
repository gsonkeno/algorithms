package com.gsonkeno.algorithms.tree;

/**
 * 二叉查找树
 * @author gaosong
 * @since 2018-09-08
 */
public class BST<Key extends Comparable<Key>, Value> {

    /**二叉查找树的根节点**/
    private Node root;

    private class Node{
        private Key key;  //键
        private Value value; //值
        private Node left, right; //左右子节点
        private int n; //以该节点为根的子树节点的个数

        public Node(Key key, Value value, int n) {
            this.key = key;
            this.value = value;
            this.n = n;
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
        if (x == null){
            return 0;
        }else {
            return x.n;
        }
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
        if (x == null) {
            return null;
        }
        int compare = key.compareTo(x.key);

        if (compare < 0) {
            return get(x.left,key);
        } else if (compare > 0) {
            return get(x.right,key);
        } else {
            return x.value;
        }
    }

    /**
     * 若key存在，更新该节点；否则，创建节点
     * @param key
     * @param value
     */
    public void put(Key key, Value value){
        root = put(root, key, value);
    }

    /**
     * 如果key存在以x为根节点的子树中，则更新它的值
     * 否则将以key，value为键值对的新节点插入到该子树中
     * @param x
     * @param key
     * @param value 操作后的以x为根节点的子树
     */
    private Node put(Node x,Key key, Value value){
        if (x == null) {
            System.out.println("键为" + key +"的节点的子树节点个数为1");
            return new Node(key,value,1);
        }
        int compare = key.compareTo(x.key);

        if (compare < 0) {
            x.left = put(x.left, key, value);
        } else if (compare > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }

        x.n = size(x.left) + size(x.right) + 1;
        System.out.println("键为" + x.key +"的节点的子树节点个数为" + x.n);

        //x节点的左右子树可能发生变化
        return x;
    }

    /**
     * 获取二叉树中最小的键
     * @return
     */
    public Key min(){
        return min(root).key;
    }

    /**
     * 获取比x节点的键还小的最小节点
     * @param x
     * @return
     */
    private Node min(Node x){
        if (x == null){
            return null;
        }
        return min(x.left);
    }

    /**
     * 获取二叉查找树<=key的最大key
     * @param key
     * @return
     */
    public Key floor(Key key){
        Node node = floor(root, key);
        if (node == null){
            return null;
        }
        return node.key;
    }

    private Node floor(Node x, Key key){
        if (x == null){
            return null;
        }
        int compare = key.compareTo(x.key);
        if (compare == 0){
            return x;
        }else if (compare < 0){
            return floor(x.left, key);
        }else {
            Node t = floor(x.right, key);
            if (t == null){
                return x ;
            }else {
                return t;
            }
        }
    }

    /**
     * 获取排名为k的键(树中有k个小于它的键)
     * @param k
     * @return
     */
    public Key select(int k){
        return select(root,k).key;
    }

    private Node select(Node node, int k){
        if (node == null){
            return null;
        }

        int t = size(node.left);
        if (t > k){
            return select(node.left, k);
        }else if ( t < k){
            return select(node.right, k - t - 1);
        }else {
            //node的左子树(不包含自身节点)恰好拥有k个节点
            return node;
        }
    }

    /**
     * 返回key的排名
     * @param key
     * @return
     */
    public int rank(Key key){
        return rank(key, root);
    }

    private int rank(Key key, Node x){
        //返回以x为根节点的子树中小于x.key的键的数量
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);

        if (cmp < 0){
            return rank(key, x.left);
        }else if ( cmp > 0){
            return 1 + size(x.left) + rank(key, x.right);
        }else {
            //恰好x节点的左子树(不包含x节点自身)的键全部小于参数key
            return size(x.left);
        }
    }
}
