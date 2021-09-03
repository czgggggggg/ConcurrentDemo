package com.bupt.test;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @Author czgggggggg
 * @Date 2021/9/3
 * @Description
 */
@Slf4j(topic = "c.Test15_join3")
public class Test15_join3 {
    static int r = 0;
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            r = 10;
        },"t1");

        t1.start();

        long start = System.currentTimeMillis();
        log.debug("join start");
        t1.join(1500);
        log.debug("join end");
        long end = System.currentTimeMillis();
        log.debug("r = {}, cost = {}",r,end - start);
    }
}

//t1.join();
//15:44:12.040 c.Test15_join3 [main] - join start
//15:44:13.053 c.Test15_join3 [main] - join end
//15:44:13.053 c.Test15_join3 [main] - r = 10, cost = 1015

//t1.join(500);
//15:45:11.359 c.Test15_join3 [main] - join start
//15:45:11.868 c.Test15_join3 [main] - join end
//15:45:11.868 c.Test15_join3 [main] - r = 0, cost = 510

//t1.join(1500);
//15:45:37.052 c.Test15_join3 [main] - join start
//15:45:38.055 c.Test15_join3 [main] - join end
//15:45:38.055 c.Test15_join3 [main] - r = 10, cost = 1005