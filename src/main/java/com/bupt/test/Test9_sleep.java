package com.bupt.test;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author czgggggggg
 * @Date 2021/9/3
 * @Description
 */
@Slf4j(topic = "c.Test9_sleep")
public class Test9_sleep {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread("t1") {
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        t1.start();
        log.debug("t1 state : {}", t1.getState());//11:37:39.170 c.Test9_sleep [main] - t1 state : RUNNABLE
        Thread.sleep(500);
        log.debug("t1 state : {}", t1.getState());//11:37:39.689 c.Test9_sleep [main] - t1 state : TIMED_WAITING
    }
}
