package com.bupt.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @Author czgggggggg
 * @Date 2021/9/3
 * @Description
 */
@Slf4j(topic = "c.Test14_join2")
public class Test14_join2 {
    static int r1 = 0;
    static int r2 = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            r1 = 10;
        },"r1");
        Thread t2 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            r2 = 20;
        },"r2");

        t1.start();
        t2.start();

        long start = System.currentTimeMillis();
        log.debug("join start");
//        t1.join();
//        log.debug("t1 join end");
//        t2.join();
//        log.debug("t2 join end");
        t2.join();
        log.debug("t2 join end");
        t1.join();
        log.debug("t1 join end");
        long end = System.currentTimeMillis();

        log.debug("r1 = {}, r2 = {}, cost = {}", r1,r2,end - start);
    }
}

//注意一共等待的时间是2秒，而不是3秒。

//15:25:16.308 c.Test14_join2 [main] - join start
//15:25:17.319 c.Test14_join2 [main] - t1 join end
//15:25:18.318 c.Test14_join2 [main] - t2 join end
//15:25:18.318 c.Test14_join2 [main] - r1 = 10, r2 = 20, cost = 2013


//颠倒t1和t2的次序，此时等待时间还是2秒。因为在等待t2的过程中，t1已经执行完了任务。
//15:32:37.167 c.Test14_join2 [main] - join start
//15:32:39.170 c.Test14_join2 [main] - t2 join end
//15:32:39.170 c.Test14_join2 [main] - t1 join end
//15:32:39.170 c.Test14_join2 [main] - r1 = 10, r2 = 20, cost = 2004
