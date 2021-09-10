package com.bupt.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author czgggggggg
 * @Date 2021/9/10
 * @Description
 */
@Slf4j(topic = "c.Test23")
public class Test23 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                log.debug("洗水壶");
                Thread.sleep(1000);
                log.debug("烧开水");
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"老王");

        Thread t2 = new Thread(() -> {
            try {
                log.debug("洗茶壶");
                Thread.sleep(1000);
                log.debug("洗茶杯");
                Thread.sleep(2000);
                log.debug("拿茶叶");
                Thread.sleep(1000);
                t1.join();
                log.debug("泡茶");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"小王");

        t1.start();
        t2.start();
    }
}
//16:18:53.656 c.Test23 [小王] - 洗茶壶
//16:18:53.656 c.Test23 [老王] - 洗水壶
//16:18:54.661 c.Test23 [小王] - 洗茶杯
//16:18:54.661 c.Test23 [老王] - 烧开水
//16:18:56.669 c.Test23 [小王] - 拿茶叶
//16:19:09.673 c.Test23 [小王] - 泡茶