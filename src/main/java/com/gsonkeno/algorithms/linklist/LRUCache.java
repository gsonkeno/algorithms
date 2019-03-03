package com.gsonkeno.algorithms.linklist;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache extends LinkedHashMap {

    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private final int maxCapacity;

    public LRUCache(int maxCapacity){
        //因为DEFAULT_LOAD_FACTOR是在HashMap中定义的，没有使用范围修饰符，所以HashMap包外是不可访问的
        super(maxCapacity, DEFAULT_LOAD_FACTOR);
        this.maxCapacity = maxCapacity;
    }
    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        //当容器中元素个数大于 maxCapacity时，移除最旧的
        if (size() > maxCapacity){
            return true;
        }
        return false;
    }



    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(4);
        lruCache.put(1,1);
        lruCache.put(2,2);
        lruCache.put(3,3);
        lruCache.put(4,4);

        System.out.println(lruCache);
        //因为最多允许4个元素，所以put5,6,7,后之前的1,2,3移除了
        lruCache.put(5,5);
        lruCache.put(6,6);
        lruCache.put(7,7);
        System.out.println(lruCache);
}
}
