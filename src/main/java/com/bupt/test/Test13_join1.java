package com.bupt.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @Author czgggggggg
 * @Date 2021/9/3
 * @Description
 */
@Slf4j(topic = "c.Test13_join1")
public class Test13_join1 {
    static int r = 0;

    public static void main(String[] args) throws InterruptedException {
        log.debug("开始");
        Thread t1 = new Thread(() -> {
            log.debug("开始");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.debug("结束");
            r = 10;
        },"t1");

        t1.start();
//        t1.join();
        log.debug("r的值为：{}",r);
        log.debug("结束");
    }
}

//未开启join()注释
//15:13:46.510 c.Test13_join1 [main] - 开始
//15:13:46.567 c.Test13_join1 [t1] - 开始
//15:13:46.567 c.Test13_join1 [main] - r的值为：0
//15:13:46.569 c.Test13_join1 [main] - 结束
//15:13:47.582 c.Test13_join1 [t1] - 结束

//开启join()注释
//15:09:15.704 c.Test13_join1 [main] - 开始
//15:09:15.772 c.Test13_join1 [t1] - 开始
//15:09:16.778 c.Test13_join1 [t1] - 结束
//15:09:16.778 c.Test13_join1 [main] - r的值为：10
//15:09:16.780 c.Test13_join1 [main] - 结束