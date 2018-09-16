package com.gsonkeno.algorithms.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 二叉查找树
 * @author gaosong
 * @since 2018-09-08
 */
public class BST<Key extends Comparable<Key>, Value> {

    /**二叉查找树的根结点**/
    private Node root;

    private class Node{
        private Key key;  //键
        private Value value; //值
        private Node left, right; //左右子结点
        private int n; //以该结点为根的子树结点的个数

        public Node(Key key, Value value, int n) {
            this.key = key;
            this.value = value;
            this.n = n;
        }


    }


    /**
     * 获取二叉查找树的结点个数
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
     * 查找二叉树中key所对应的值(非递归方式)
     * @param key
     * @return
     */
    public Value get1(Key key){
        if ( root == null) return null;
        Node x = root;

        while ( x != null){
            int cmp = key.compareTo(x.key);

            if (cmp == 0){
                return x.value;
            }else if (cmp <0 ){
                x = x.left;
            }else {
                x = x.right;
            }
        }
        return null;
    }

    /**
     * 以x为根结点的子树中查找并返回key所对应的值
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
     * 若key存在，更新该结点；否则，创建结点
     * @param key
     * @param value
     */
    public void put(Key key, Value value){
        root = put(root, key, value);
    }

    /**
     * 如果key存在以x为根结点的子树中，则更新它的值
     * 否则将以key，value为键值对的新结点插入到该子树中
     * @param x
     * @param key
     * @param value 操作后的以x为根结点的子树
     */
    private Node put(Node x,Key key, Value value){
        if (x == null) {
            System.out.println("键为" + key +"的结点的子树结点个数为1");
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
        System.out.println("键为" + x.key +"的结点的子树结点个数为" + x.n);

        //x结点的左右子树可能发生变化
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
     * 获取比x结点的键还小的最小结点
     * @param x
     * @return
     */
    private Node min(Node x){
        if (x.left== null){
            return x;
        }
        return min(x.left);
    }


    /**
     * 获取二叉树中最大的键
     * @return
     */
    public Key max(){
        return max(root).key;
    }

    /**
     * 获取比x结点的键还大的最大结点
     * @param x
     * @return
     */
    private Node max(Node x){
        if (x.right == null){
            return x;
        }
        return max(x.right);
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
            //node的左子树(不包含自身结点)恰好拥有k个结点
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
        //返回以x为根结点的子树中小于x.key的键的数量
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);

        if (cmp < 0){
            return rank(key, x.left);
        }else if ( cmp > 0){
            return 1 + size(x.left) + rank(key, x.right);
        }else {
            //恰好x结点的左子树(不包含x结点自身)的键全部小于参数key
            return size(x.left);
        }
    }

    /**
     * 删除最小键的结点
     */
    public void deleteMin(){
        root = deleteMin(root);
    }

    /**
     * 删除比x结点小的最小键结点
     * @param x
     * @return 调整后的以x为根结点的子树
     */
    private Node deleteMin(Node x){
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.n = size(x.left) + size(x.right) + 1;
        return x;
    }

    /**
     * 删除键key
     * @param key
     */
    public void deleteKey(Key key){
        root = delete(root, key);
    }

    /**
     * 删除以x为根结点的子树中的键key
     * @param x
     * @param key
     * @return 调整后的以x结点为根的子树
     */
    private Node delete(Node x, Key key){
        if ( x == null ) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = delete(x.left, key);
        } else if (cmp > 0) {
            x.right = delete(x.right, key);
        } else {
            if (x.right == null) {
                return x.left;
            }else if (x.left == null){
                return x.right;
            }

            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.n = size(x.left) + size(x.right) + 1;
        return x;

    }

    /**
     * 二叉查找树的键范围查找
     * @return
     */
    public Iterable<Key> keys(){
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi){
        Queue<Key> queue = new LinkedList<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue<Key> queue, Key lo, Key hi){
        if ( x == null) return;
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);

        if (cmplo < 0){
            keys(x.left, queue, lo, hi);
        }
        if (cmplo <= 0 && cmphi >= 0){
            queue.add(x.key);
        }
        if (cmphi > 0 ){
            keys(x.right, queue, lo, hi);
        }
    }
}
