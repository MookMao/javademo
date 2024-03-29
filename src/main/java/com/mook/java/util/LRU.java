package com.mook.java.util;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

//实现LRU
//使用LinkedHashMap实现
//LinkedHashMap底层就是用的【HashMap 加 双链表】实现的，而且本身已经实现了按照访问顺序的存储。
//此外，LinkedHashMap中本身就实现了一个方法removeEldestEntry用于判断是否需要移除最不常读取的数，
//方法默认是直接返回false，不会移除元素，所以需要重写该方法,即当缓存满后就移除最不常用的数。
public class LRU<K,V> {
    private LinkedHashMap<K, V> map;
    private int cacheSize;

    public LRU(int cacheSize)
    {
        this.cacheSize = cacheSize;
        // accessOrder说明依据访问顺序存储->双向链表，即迭代器访问元素的时候按从近期访问最少到近期访问最多的顺序
        map = new LinkedHashMap<K,V>(16,0.75F,true){

            @Override
            // 重写该方法，表示是否需要移除最近访问最少的元素
            protected boolean removeEldestEntry(Entry eldest) {
                if(cacheSize + 1 == map.size()){
                    return true;
                }else{
                    return false;
                }
            }
        };  //end map
    }

    public synchronized V get(K key) {
        return map.get(key);
    }
    public synchronized void put(K key,V value) {
        map.put(key, value);
    }
    public synchronized void clear() {
        map.clear();
    }
    public synchronized int usedSize() {
        return map.size();
    }
    public void print() {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            System.out.print(entry.getValue() + "--");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // 缓存大小：3
        LRU<String, Integer> LRUMap = new LRU<>(3);
        LRUMap.put("key1", 1);
        LRUMap.put("key2", 2);
        LRUMap.put("key3", 3);
        // 1--2--3--
        // 按访问顺序，由少到多
        LRUMap.print();
        LRUMap.put("key4", 4);
        // 2--3--4--
        // 移除了不常访问的元素
        LRUMap.print();
        LRUMap.get("key3");
        // 2--4--3--
        // 按访问顺序，由少到多
        LRUMap.print();

        // LinkedHashMap默认按照插入顺序组织元素，FIFO
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        map.put("key1", 1);
        map.put("key2", 2);
        map.put("key3", 3);
        // key1 key2 key3
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.printf(entry.getKey() + " ");
        }
    }
}
