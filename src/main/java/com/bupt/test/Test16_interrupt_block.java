package com.bupt.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author czgggggggg
 * @Date 2021/9/3
 * @Description
 */
@Slf4j(topic = "c.Test16_interrupt_block")
public class Test16_interrupt_block {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.debug("sleeping...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1");

//        Thread t1 = new Thread(() -> {
//            while (true){
//
//            }
//        },"t1");

        t1.start();
        Thread.sleep(500);
        log.debug("interrupt");
        t1.interrupt();
        log.debug("打断标记：{}", t1.isInterrupted());//打断标记：false  //打断标记：true
    }
}
