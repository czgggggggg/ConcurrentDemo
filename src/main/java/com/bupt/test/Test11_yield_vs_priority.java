package com.bupt.test;

import lombok.extern.slf4j.Slf4j;


/**
 * @Author czgggggggg
 * @Date 2021/9/3
 * @Description
 */
@Slf4j(topic = "c.Test11_yield_vs_priority")
public class Test11_yield_vs_priority {
    public static void main(String[] args){
        Runnable task1 = new Runnable() {
            @Override
            public void run() {
                int count = 0;
                for(;;){
//                    Thread.yield();
                    System.out.println("----->1 " + count++);
                }
            }
        };
        Runnable task2 = new Runnable() {
            @Override
            public void run() {
                int count = 0;
                for(;;){
                    System.out.println("               ----->2 " + count++);
                }
            }
        };

        Thread t1 = new Thread(task1,"t1");
        Thread t2 = new Thread(task2,"t2");
//        t1.setPriority(Thread.MIN_PRIORITY);
//        t2.setPriority(Thread.MAX_PRIORITY);
        t1.start();
        t2.start();
    }
}
