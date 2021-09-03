package com.bupt.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author czgggggggg
 * @Date 2021/9/3
 * @Description
 */
@Slf4j(topic = "c.Test21_daemon")
public class Test21_daemon {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while(true){
                if(Thread.currentThread().isInterrupted()){
                    break;
                }
            }
            log.debug("结束");
        },"t1");

        t1.setDaemon(true);
        t1.start();
        Thread.sleep(3000);
        log.debug("结束");
    }
}
