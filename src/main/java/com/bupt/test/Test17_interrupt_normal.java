package com.bupt.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author czgggggggg
 * @Date 2021/9/3
 * @Description
 */
@Slf4j(topic = "c.Test17_interrupt_normal")
public class Test17_interrupt_normal {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true) {
                //不在死循环中添加判断退出逻辑，线程并不会结束。
                boolean interrupted = Thread.currentThread().isInterrupted();
                if(interrupted){
                    log.debug("被打断了，退出循环");
                    break;
                }
            }
        }, "t1");

        t1.start();
        Thread.sleep(1000);
        log.debug("interrupt");
        t1.interrupt();
    }
}
