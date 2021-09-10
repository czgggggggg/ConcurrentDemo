package com.bupt.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author czgggggggg
 * @Date 2021/9/10
 * @Description
 */
@Slf4j(topic = "c.Test25")
public class Test25 {
    static int number = 0;
    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for(int i = 0; i < 5000; i++){
                synchronized (lock){
                    number++;
                }
            }
        },"t1");

        Thread t2 = new Thread(() -> {
            for(int i = 0; i < 5000; i++){
                synchronized (lock){
                    number--;
                }
            }
        },"t2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        log.debug("最终number的值为：{}",number);
    }
}
