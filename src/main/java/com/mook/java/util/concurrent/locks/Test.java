package com.mook.java.util.concurrent.locks;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 使用非阻塞队列、Object的wait/notify实现生产者-消费者模型
 * 1.同一时刻，生产者和消费者只有一个能操作
 * 2.队列满时，生产者不能操作
 * 3.队列空时，消费者不能操作
 */
public class Test {
    private static final int QUEUE_SIZE = 10;

    private static final Queue<Integer> QUEUE = new PriorityQueue<>(QUEUE_SIZE);

    public static void main(String[] args) {
        Producer producer = new Producer();
        Consumer consumer = new Consumer();
        producer.start();
        consumer.start();
    }

    static class Producer extends Thread {
        public void run() {
            // 如果将获取锁的操作放在while循环外面，则在生产者插满队列之前，是不会释放锁的，消费者只有等队列满了才能够去消费队列
            while (true) {
                synchronized (QUEUE) {
                    if (QUEUE.size() == QUEUE_SIZE) {
                        try {
                            System.out.println("队列满，等待有空余空间");
                            QUEUE.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    QUEUE.offer(QUEUE.size());
                    QUEUE.notify();
                    System.out.println("向队列取中插入一个元素，队列剩余空间：" + (QUEUE_SIZE - QUEUE.size()));
                }
            }
        }
    }

    static class Consumer extends Thread {
        public void run() {
            while (true) {
                synchronized (QUEUE) {
                    if (QUEUE.size() == 0) {
                        try {
                            System.out.println("队列空，等待数据");
                            QUEUE.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Consumer: " + QUEUE.poll());
                    QUEUE.notify();
                    System.out.println("从队列取走一个元素，队列剩余" + QUEUE.size() + "个元素");
                }
            }
        }
    }
}
