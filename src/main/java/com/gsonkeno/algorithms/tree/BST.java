package com.gsonkeno.algorithms.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉查找树
 * <a href="https://www.cnblogs.com/yaobolove/p/6213936.html">二叉树遍历博客</a>
 * <a href="https://algs4.cs.princeton.edu/home/">算法第四版</a>
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

    /**
     * 中序遍历
     */
    public  void midOrderTravel(){
        midOrderTravel(root);
    }

    private void midOrderTravel(Node x){
        if (x != null){
            midOrderTravel(x.left);
            System.out.println(x.key + ":" + x.value);
            midOrderTravel(x.right);
        }
    }
    /**
     * 中序遍历(非递归)
     */
    public  void midOrderTravelByStack(){
        midOrderTravelByStack(root);
    }

    private  void midOrderTravelByStack(Node x){
        Stack<Node> stack = new Stack<>();
        Node node = x;

        while (node != null || stack.size()>0){
            if (node != null ){
                stack.push(node);
                node = node.left;
            }else {
                node = stack.pop();
                System.out.println(node.key + ":" + node.value);
                node = node.right;
            }
        }
    }

    /**
     * 先序遍历(递归)
     */
    public  void preOrderTravel(){
        preOrderTravel(root);
    }

    private   void preOrderTravel(Node x){
        if (x != null){
            System.out.println(x.key + ":" + x.value);
            preOrderTravel(x.left);
            preOrderTravel(x.right);
        }
    }



    public  void preOrderTravelByStack(){
        preOrderTravelByStack(root);
    }
    /**
     * 先序遍历(非递归，使用栈)
     * @param x
     */
    private   void preOrderTravelByStack(Node x){
        Stack<Node> stack = new Stack<>();
        Node node = x;

        while (node != null || stack.size() >0){
            if (node != null){
                System.out.println(node.key + ":" + node.value);
                stack.push(node); //可以想象是第一次执行时，根节点先打印，再入站
                node = node.left; //node指向根的左节点
            }else{
                //如果根的左节点为空，则获取根的右节点，但是根现在在哪里呢？其实在栈顶
                node = stack.pop(); //node指向根
                node = node.right; //node指向根的右节点
            }
        }
    }

    /**
     * 后序遍历
     */
    public void  posOrderTravel(){
        posOrderTravel(root);
    }
    private  void posOrderTravel(Node x){
        if (x != null){
            posOrderTravel(x.left);
            posOrderTravel(x.right);
            System.out.println(x.key + ":" + x.value );
        }
    }

    /**
     * 后序遍历(非递归)
     */
    public  void posOrderTravelByStack(){
        posOrderTravelByStack(root);
    }
    /**
     * 后序遍历(非递归)
     * @param x
     */
    private   void posOrderTravelByStack(Node x){
        Stack<Node> stack = new Stack<>();
        Stack<Node> temp = new Stack<>();
        Node node = x;
        while (node != null || stack.size()>0){
            if (node != null){
                stack.push(node);
                temp.push(node);
                node = node.right;
            }else {
                node = stack.pop();
                node = node.left;
            }
        }

        while (temp.size()>0){
            Node popNode = temp.pop();
            System.out.println(popNode.key + ":" + popNode.value);
        }
    }

    public int getDepth(){
        return getDepth(root);
    }

    /**
     * 获取以x为根结点的子树的深度
     * @param x
     * @return
     */
    private  int getDepth(Node x){
        if (x == null) return 0;

        int left = getDepth(x.left);
        int right = getDepth(x.right);
        return Math.max(left,right) + 1;
    }


    /**
     * 获取树的宽度
     * @return
     */
    public int getWidth(){
        return getWidth(root);
    }

    /**
     * 获取以x为根结点的树的宽度,层序遍历，遍历一层后，下一层入队列，同时该层元素全部出队列，比较每层元素的数量
     * @param x
     * @return
     */
    private   int getWidth(Node x){
        if (x == null) return 0;
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(x);
        int width = 1;
        while (true){
            int lineSize = queue.size();

            if (lineSize == 0 ) break;
            while (lineSize>0){
                Node node = queue.poll();
                if (node.left != null) queue.add(node.left);
                if (node.right != null)queue.add(node.right);
                lineSize --;
            }

            width = Math.max(width,queue.size());
        }
        return width;
    }
}
